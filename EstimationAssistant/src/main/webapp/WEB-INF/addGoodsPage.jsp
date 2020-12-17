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
<center><strong>Add Goods Mode+</strong></center>
<br>
<form action="addGoodsPage" align="center" method="post">

    <table style="with: 50%" align="center">
        <tr>
            <td>PO:</td>
            <td><input type="text" name="po"></td>
        </tr>
        <tr>
            <td>WO:</td>
            <td><input type="text" name="wo"></td>
        <tr/>
        <tr>
            <td>SO:</td>
            <td><input type="text" name="so"></td>
        <tr/>
        <tr>
            <td>Customer:</td>
            <td><input type="text" name="customer"></td>
        <tr/>
        <tr>
            <td>Title:</td>
            <td><input type="text" name="title"></td>
        <tr/>
        <tr>
            <td>Location by barcode:</td>
        <tr/>
        <tr>
            <td>Ile  -  Shelf  -  SPosition</td>
        <tr/>
        <tr>
            <td>
                <input type="text" style="width:40px;" name="rack" >
                -<input type="text" style="width:40px;" name="shelf" >-
                <input type="text" style="width:40px;" name="shelfPosition">
            </td>
        <tr/>

    </table>
    <input type="submit" name="addGoods" value = "Add Goods">
    <input type="submit" name = "cancel" value = "Cancel">

<br>
</table>

<c:if  test="${!empty goodsList}" >
    <table width="800" border="2" >
        <tr>
            <th width="98">ID</th>
            <th width="98">Stock Status</th>
            <th width="98">Title</th>
            <th width="98">PO</th>
            <th width="98">WO</th>l
            <th width="98">SO</th>
            <th width="98">Customer</th>
            <th width="98">Comment</th>
            <th width="98">Rack</th>
            <th width="98">Shelf</th>
            <th width="98">Shelf Position</th>
            <th width="98">Barcode Nr</th>
            <th width="180">In Date</th>
            <th width="180">Out Date</th>
            <th width="180">Settings</th>
        </tr>

        <c:forEach items="${goodsList}" var="goods">
            <tr>
                <td><c:out value="${goods.id}"/></td>
                <td><c:out value="${goods.inStock}"/></td>
                <td><c:out value="${goods.title}"/></td>
                <td><c:out value="${goods.po}"/></td>
                <td><c:out value="${goods.wo}"/></td>
                <td><c:out value="${goods.so}"/></td>
                <td><c:out value="${goods.customer}"/></td>
                <td><c:out value="${null}"/></td> <%-- comment--%>
                <td><c:out value="${goods.rack}"/></td>
                <td><c:out value="${goods.shelf}"/></td>
                <td><c:out value="${goods.shelfPosition}"/></td>
                <td><c:out value="${null}"/></td>  <%-- Barcode Nr--%>
                <td><c:out value="${goods.inDate}"/></td>
                <td><c:out value="${goods.outDate}"/></td> <%-- out date--%>
                       <td> <input type="submit" name="delete" value = "Delete"></td>--%>
                <td>
                    <c:choose>
                        <c:when test="${goods.inStock}">
<%--                            <a href="<c:url value='/goodsInPage/deleteGoods/${goods.id}'/>"> Delete </a>--%>
<%--                           <a href="<c:url value='/goodsInPage/openEditGoodsPage/${goods.id}'/>"> Edit </a> <br>--%>
                            <a href="<c:url value='/${goods.id}/delete'/>">Delete</a>
                            <a href="<c:url value='/${goods.id}/edit'/>">Edit</a>

                        </c:when>
                    </c:choose>

                </td>

            </tr>
        </c:forEach>
    </table>
</c:if>
</form>
</body>
</html>