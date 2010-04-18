<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<jsp:useBean id="user" scope="session"
             class="com.practicaljava.lesson29.onlinestore.User"/>


<div class="panel">
    <span class="user-name">${user.id}</span>, You have ${user.rewardPoints} points to spend in our store.
    <c:choose>
        <c:when test="${user.cartItems.size() > 0}">
            <p>Your items</p>
            <c:forEach var="cartItem" items="${user.cartItems}" varStatus="loop">
                <p>${cartItem.name}: ${cartItem.priceInPoints}</p>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>Your cart is empty</p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>