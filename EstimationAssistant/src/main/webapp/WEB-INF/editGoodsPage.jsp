<%--<jsp:useBean id="goods" scope="request" type="com.garagonic.estimationAssistant.repository.Goods"/>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title> Goods In </title>
</head>
<body>
<center><strong>Goods In, Connecting people...</strong></center>

<div style="text-align: center;"> ${result} </div>
<br>
<br>
<center><strong>Editing Mode:</strong></center>
<br>
<form action="/${goods.id}/edit/submit" align="center" method="post" >

    <table style="with: 50%" align="center">
        <tr>
            <td>PO:</td>
            <td><input type="text" name="po" value=${goods.po}></td>
        </tr>
        <tr>
            <td>WO:</td>
            <td><input type="text" name="wo" value=${goods.wo}></td>
        <tr/>
        <tr>
            <td>SO:</td>
            <td><input type="text" name="so" value=${goods.so}></td>
        <tr/>
        <tr>
            <td>Customer:</td>
            <td><input type="text" name="customer" value=${goods.customer}></td>
        <tr/>
        <tr>
            <td>Title:</td>
            <td><input type="text" name="title" value=${goods.title}></td>
        <tr/>
        <tr>
            <td>Location by barcode:</td>
        <tr/>
        <tr>
            <td>Ile  -  Shelf  -  SPosition</td>
        <tr/>
        <tr>
            <td >
                <input type="text" style="width:40px;" name="rack" value ="${goods.rack}" >-
                <input type="text" style="width:40px;" name="shelf" value="${goods.shelf}" >-
                <input type="text" style="width:40px;" name="shelfPosition" value="${goods.shelfPosition}">
            </td>
        <tr/>

    </table>
    <input type="submit" name = "submit" value = "Complete">
    <input type="submit" name="cancel" value = "Cancel">
</form>
<br>
</table>

</body>
</html>