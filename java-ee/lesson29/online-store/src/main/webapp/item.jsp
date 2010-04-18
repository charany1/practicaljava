<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <title>On-line store</title></head>
<body>
<jsp:useBean id="storeItems" scope="application"
             class="com.practicaljava.lesson29.onlinestore.StoreItems"/>


<c:set var="id" value="${param['id']}"/>
<c:set var="cartItem" value="${storeItems.getItem(id)}"/>

<c:import url="template/userInfo.jsp"/>

<p>You selected <b>${cartItem.name}</b></p>
<p><b>Description:</b> ${cartItem.description}</p>

<form action="add">
    <input type="hidden" name="id" value="${cartItem.productCode}"/>
    <button>Add to cart</button>
</form>

</body>
</html>