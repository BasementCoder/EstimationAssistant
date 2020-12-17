<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="body" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title> Estimation Assistant </title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
</head>
<body>
<ul>
    <li style="color: #4CAF50"><strong> Welcome to Estimation App </strong></li>
    <li style="float:right"><a href="<c:url value="/logout" />" class="logout-button">Log Out</a></li>
</ul>

<c:if test="${showErrorMessage}">
    <div class="errorMessage"><strong>Error: At least one filter field has to be populated.</strong></div>
</c:if>

<br>
<form action="/" method="post" enctype="multipart/form-data">

    <table class="main" align="center">
        <tr>
            <td><strong>Name:</strong></td>
            <td>
                <input type="text" onfocus="this.value=''" name="name" value="${lastGoodsSearchFilter.name}">
            </td>
        <tr/>
        
        <tr>
            <td><strong>Quote Nr.:</strong></td>
            <td><input type="text" onfocus="this.value=''" name="quote" value="${lastGoodsSearchFilter.quote}"></td>
        <tr/>
        <tr>
            <td><strong>Estimate:</strong></td>
            <td><input type="text" onfocus="this.value=''" name="estimate" value="${lastGoodsSearchFilter.estimate}"></td>
        <tr/>
        <tr>
            <td><strong>Quote Date:</strong></td>
            <td><input type="text" onfocus="this.value=''" name="manualDateInput" value="${lastGoodsSearchFilter.manualDateInput}"></td>
        <tr/>
        <tr>
            <td><strong>Type:</strong></td>
            <td><input type="text" onfocus="this.value=''" name="type" value="${lastGoodsSearchFilter.type}"></td>
        <tr/>
        <tr>
            <td align="center"><input type="submit" name="search" value="Search"></td>
            <td align="center"><security:authorize access="hasRole('ROLE_ADMIN')"><input type="submit" name="addGoods" value="+Add Record"></security:authorize> </td>
        </tr>
    </table>
</form>
<br>

</table>

<c:if test="${!empty goodsList}">
    <table border="10" align="center">
        <tr>
            <th>ID</th>
            <th>Image</th>
            <th>Name</th>
            <th>Quote Date</th>
            <th>Quote</th>
            <th>Estimate</th>
            <th>Type</th>
            <th>Upload Date</th>
            <th>Settings</th>
        </tr>

        <c:forEach items="${goodsList}" var="goods">
            <tr>
                <td><c:out value="${goods.id}"/></td>
<%--                <td><c:out value="${goods.image}"/></td>--%>
<%--                <td><div class="dropdown"> <img width="100" height="100" src="./getImage/${goods.id}"> </div></td>--%>
                <td>
                    <img class="thumbnail" src="./getImage/${goods.id}" width="100" height="100" alt="${goods.id}" >
                </td>
                <td><c:out value="${goods.name}"/></td>
                <td><c:out value="${goods.manualDateInput}"/></td>
                <td><c:out value="${goods.quote}"/></td>
                <td><c:out value="${goods.estimate}"/></td>
                <td><c:out value="${goods.type}"/></td>
                <td><c:out value="${goods.inDate}"/></td>
                <td>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <a href="<c:url value='/${goods.id}/delete'/>" class="delete-button">Delete</a>
                        <a href="<c:url value='/${goods.id}/edit'/>" class="edit-button">Edit</a>
                    </security:authorize>

<%--                    <c:choose>--%>
<%--                        <c:when test="${goods.id}">--%>
<%--                            <security:authorize access="hasRole('ROLE_ADMIN')">--%>
<%--                                <a href="<c:url value='/${goods.id}/delete'/>" class="delete-button">Delete</a>--%>
<%--                                <a href="<c:url value='/${goods.id}/edit'/>" class="edit-button">Edit</a>--%>
<%--                            </security:authorize>--%>
<%--                        </c:when>--%>
<%--                    </c:choose>--%>
                </td>
            </tr>
        </c:forEach>
        <br>
    </table>
    <br>
<%--    <div align="center" class="pagination">--%>
<%--        <a href="#">&laquo;</a>--%>
<%--        <a class="active" href="#">1</a>--%>
<%--        <a href="#">2</a>--%>
<%--        <a href="#">3</a>--%>
<%--        <a href="#">4</a>--%>
<%--        <a href="#">5</a>--%>
<%--        <a href="#">6</a>--%>
<%--        <a href="#">&raquo;</a>--%>
<%--        <br>--%>
<%--    </div>--%>

</c:if>

</body>
</html>