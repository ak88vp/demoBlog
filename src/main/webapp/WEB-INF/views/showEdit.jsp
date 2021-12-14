<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tien Dung
  Date: 12/9/2021
  Time: 9:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<center>
    <form method="post">

        <input type="text" name="name" value="${product.name}">
        <br>

        <select name="idCategory">
            <c:forEach var="i" begin="0" end="${categorys.size()-1}">

                <option value="${categorys.get(i).id}"> ${categorys.get(i).name} </option>

            </c:forEach>

        </select>
        <br>
        <input type="text" name="price" value="${product.price}">
        <input type="text" name="quantity" value="${product.quantity}">
        <input type="text" name="color" value="${product.color}">
        <input type="text" name="description" value="${product.description}">
        <br>
        <button>Sửa</button>
    </form>
</center>
</body>
</html>
