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
<form action="/" method="post" >

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
                <input type="text" name="rack" style="width:40px;">-<input type="text" name="shelf" style="width:40px;">-<input type="text" name="shelfPosition" style="width:40px;">
            </td>
        <tr/>

    </table>
    <center><input type="submit" name = "search" value = "Search">
            <input type="submit" name="addGoods" value = "Add Goods"></center>
</form>
<br>
</table>

<c:if test="${!empty goodsList}">
    <table width="800" border="2" align="center">
        <tr>
            <th width="98">ID</th>
            <th width="98">Stock Status</th>
            <th width="98">Title</th>
            <th width="98">PO</th>
            <th width="98">WO</th>
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
                <td>
                    <c:choose>
                        <c:when test="${goods.inStock}">
                            <a href="<c:url value='/${goods.id}/delete'/>">Delete</a>
                            <a href="<c:url value='/${goods.id}/edit'/>">Edit</a>
                        </c:when>
                    </c:choose>

                </td>

            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>