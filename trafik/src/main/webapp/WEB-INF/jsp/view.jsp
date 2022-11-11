<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>View bus</title>
        <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>BusLine</th>
                    <th>BusStop Names</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${busStops}" var="bus">
                    <tr>
                       <td>${bus.busLine}</td>
                       <td>${bus.names}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </body>
</html>