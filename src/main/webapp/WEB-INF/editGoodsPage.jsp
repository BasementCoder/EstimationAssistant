<%--<jsp:useBean id="goods" scope="request" type="com.garagonic.estimationAssistant.repository.Goods"/>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title> Goods In </title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>

<ul>
    <li style="color: #4CAF50"><strong>Goods In, Connecting people...</strong></li>
    <li style="float:right"><a href="<c:url value="/logout" />" class="logout-button">Log Out</a></li>
</ul>

<br>
<center><strong>Editing Mode:</strong></center>
<br>
<form action="/${goods.id}/edit/submit" align="center" method="post" enctype="multipart/form-data" >

    <table class="main" align="center">
        <tr>
            <td><strong>Image:</strong></td>
            <td><input type="file" name="image" value=${goods.image}></td>
        </tr>
        <tr>
            <td><strong>Name:</strong></td>
            <td><input type="text" name="name" value=${goods.name}></td>
        <tr/>
        <tr>
            <td><strong>Quote date:</strong></td>
            <td><input type="text" name="manualDateInput" value=${goods.manualDateInput}></td>
        <tr/>
        <tr>
            <td><strong>Quote Nr:</strong></td>
            <td><input type="text" name="quote" value=${goods.quote}></td>
        <tr/>
        <tr>
            <td><strong>Estimate:</strong></td>
            <td><input type="text" name="estimate" value=${goods.estimate}></td>
        <tr/>
        <tr>
            <td><strong>Type:</strong></td>
            <td><input type="text" name="type" value=${goods.type}></td>
        <tr/>

        <tr>
            <td><input type="submit" name = "submit" value = "Complete"></td>
            <td><input type="submit" name="cancel" value = "Cancel"></td>
        </tr>
    </table>

</form>
<br>
</table>

</body>
</html>