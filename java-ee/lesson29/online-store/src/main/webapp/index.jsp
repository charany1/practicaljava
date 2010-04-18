<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <title>On-line store</title></head>
<body>

<jsp:useBean id="storeItems" scope="application"
             class="com.practicaljava.lesson29.onlinestore.StoreItems"/>

<c:import url="template/userInfo.jsp"/>


<table width="80%">
    <col/>
    <col id="middle" width="50%" />
    <col/>
    <col/>
    <thead>
    <th>Name</th>
    <th>Description</th>
    <th>Price</th>
    </thead>
    <tbody>
    <c:forEach var="cartItem" items="${storeItems.items}" varStatus="loop">
        <c:choose>
            <c:when test="${loop.index % 2 == 0}">
                <tr id="odd">
            </c:when>
            <c:otherwise>
                <tr id="">
            </c:otherwise>
        </c:choose>

        <td align="center">
            <a class="column-link" href=<c:url value="item.jsp">
                <c:param name="id" value="${cartItem.productCode}"/> </c:url>>
                    ${cartItem.name}
            </a>
        </td>
        <td align="center">${cartItem.description}</td>
        <td align="center">${cartItem.priceInPoints}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>