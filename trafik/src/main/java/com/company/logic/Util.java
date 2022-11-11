package com.company.logic;

import com.company.client.ClientException;
import com.company.entity.Response;
import com.company.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Util {

    private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

    private static final Comparator<Map.Entry<String, List<String>>> ENTRY_COMPARATOR = new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2) {
                return o1.getValue().size() - o2.getValue().size();
            }};

    public static String[][] getFirstMaxBusStops(Response response1,
                                                                        Response response2,
                                                                        int limit) throws ClientException {
        if (limit < 1) {
            return new String[0][];
        }

        if (response1 == null
                || response2 == null
                || response1.responseData == null
                || response2.responseData == null
                || response1.responseData.resultList == null
                || response2.responseData.resultList == null) {
            throw new ClientException("json mapping error.");
        }

        final Map<String, String> names = getNameMap(response1.responseData.resultList);
        final Map<String, List<String>> stops = getStopsMap(response2.responseData.resultList);

        final int max = limit + 1;
        PriorityQueue<Map.Entry<String, List<String>>> queue = new PriorityQueue<>(max, ENTRY_COMPARATOR);

        for (Map.Entry<String, List<String>> entry : stops.entrySet()) {
            queue.add(entry);

            if (queue.size() == max) {
                queue.poll();
            }
        }

        LOGGER.info(" : " + queue.size());
        return convertToName(queue, max, names);    }

    private static String[][] convertToName(Queue<Map.Entry<String, List<String>>> queue,
                                                                   int max,
                                                                   Map<String, String> names) {
        String[][] result = new String[queue.size()][2];
        int i = queue.size() - 1 ;
        while (!queue.isEmpty()) {
            Map.Entry<String, List<String>> entry = queue.poll();

            List<String> temp = entry.getValue().stream().map(x -> names.get(x)).collect(Collectors.toList());
            result[i--] = new String[] {entry.getKey(), temp.stream().collect(Collectors.joining(", "))};

            LOGGER.debug("lineNr: " + entry.getKey() + " , size: " + entry.getValue().size());
        }

        return result;
    }

    private static Map<String, String> getNameMap(List<Result> list) throws ClientException {
        final Map<String, String> map = new HashMap<>();
        for (Result result : list) {
            if (result.stopPointNumber == null || result.stopPointName == null) {
                throw new ClientException("json mapping error.");
            }
            map.put(result.stopPointNumber, result.stopPointName);
        }

        return map;
    }

    private static Map<String, List<String>> getStopsMap(List<Result> list) throws ClientException {
        Map<String, List<String>> map = new HashMap<>();
        for (Result result : list) {
            if (result.lineNumber == null || result.pointNumber == null) {
                throw new ClientException("json mapping error.");
            }

            List<String> value = map.getOrDefault(result.lineNumber, new ArrayList<>());
            value.add(result.pointNumber);

            map.put(result.lineNumber, value);
        }
        return map;
    }
}
