<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title> Estimation Assistant </title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>

<ul>
    <li style="color: #4CAF50"><strong>Goods In, Connecting people...</strong></li>
    <li style="float:right"><a href="<c:url value="/logout" />" class="logout-button">Log Out</a></li>
</ul>

<%--<c:if test="${showErrorMessage}">--%>
<%--    <div class="errorMessage"><strong>Error: Please make sure that at least fields of "Customer" and "Location" is populated.</strong></div>--%>
<%--</c:if>--%>

<br>
<center><strong>Add Records Menu</strong></center>
<br>
<form action="addGoodsPage" align="center" method="post" enctype="multipart/form-data">

    <table class="main" align="center" >
        <tr>
            <td><strong>Image:</strong></td>
            <td><input type="file" name="image" value="${addedGoods.image}"/></td>
        </tr>
        <tr>
            <td><strong>Name:</strong></td>
            <td><input type="text" name="name" value="${addedGoods.name}"></td>
        <tr/>
        <tr>
            <td><strong>Quote date:</strong></td>
            <td><input type="text" name="manualDateInput" value="${addedGoods.manualDateInput}"></td>
        <tr/>
        <tr>
            <td><strong>Quote:</strong></td>
            <td><input type="text" name="quote" value="${addedGoods.quote}"></td>
        <tr/>
        <tr>
            <td><strong>Estimate:</strong></td>
            <td><input type="text" name="estimate" value="${addedGoods.estimate}"></td>
        <tr/>
        <tr>
            <td><strong>Type:</strong></td>
            <td><input type="text" name="type" value="${addedGoods.type}"></td>
       
<%--        <tr>--%>
<%--            <td><strong>Ile  -  Shelf  -  SPosition</strong></td>--%>
<%--        <tr/>--%>
<%--        <tr>--%>
<%--            <td>--%>
<%--                <input type="text" style="width:40px;" name="rack" value="${addedGoods.rack}">--%>
<%--                -<input type="text" style="width:40px;" name="shelf" value="${addedGoods.shelf}">---%>
<%--                <input type="text" style="width:40px;" name="shelfPosition" value="${addedGoods.shelfPosition}">--%>
<%--            </td>--%>
<%--        <tr/>--%>
        <tr>
            <td><input type="submit" name="addGoods" value = "Complete"></td>
            <td><input type="submit" name = "cancel" value = "Cancel"></td>
        </tr>
    </table>



<br>
</table>
</form>

</body>
</html>