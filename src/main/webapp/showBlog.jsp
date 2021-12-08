<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tien Dung
  Date: 12/8/2021
  Time: 10:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <p><a href="/blogs?action=create"> Thêm mới</a></p>
    <br>
    <table>
        <tr>
            <td> Tiêu đề</td>
            <td> Nội dung</td>
            <td> Danh mục</td>
            <td>Sửa</td>
            <td>Xóa</td>
        </tr>
        <c:forEach var="i" begin="0" end="${blogs.size()-1}">
            <tr>
            <td>${blogs.get(i).name}</td>
            <td>${blogs.get(i).content}</td>
            <td>${categorys.get(i).name}</td>
            <td><a href="/blogs?action=edit&id=${blogs.get(i).id}"> sửa</a></td>
            <td> <a href="/blogs?action=delete&id=${blogs.get(i).id}" onclick="if (confirm('Delete selected item?')){return true;}else{event.stopPropagation(); event.preventDefault();};" title="Link Title">
                Xóa
            </a> </td>
            </tr>
        </c:forEach>

    </table>
</center>
</body>
</html>
