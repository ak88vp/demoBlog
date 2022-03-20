
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style> table, th, td{
        border:1px solid #d21d1d;
    }
    table{
        border-collapse:collapse;
    }
    </style>
</head>
<body>

<center>
    <p><a href="/create"> Thêm mới</a></p>
    <br>
    <form action="/products">
        <input type="text" name="key" placeholder="Enter name you want find?">
        <button style="background: darkorchid"> find</button>
    </form>

    <table>
        <tr>
            <td> Tên</td>
            <td> Gía</td>
            <td> số lượng</td>
            <td> màu</td>
            <td> Mô tả</td>
            <td> Thể loại</td>
            <td>Sửa</td>
            <td>Xóa</td>
        </tr>
        <c:forEach var="i" begin="0" end="${products.size()-1}">
            <tr>
                <td>${products.get(i).name}</td>
                <td>${products.get(i).price}</td>
                <td>${products.get(i).quantity}</td>
                <td>${products.get(i).color}</td>
                <td>${products.get(i).description}</td>
                <td>${categorys.get(i).name}</td>
                <td><a href="/edit?id=${products.get(i).id}"> sửa</a></td>
                <td> <a href="/delete?id=${products.get(i).id}" onclick="if (confirm('Delete selected item?')){return true;}else{event.stopPropagation(); event.preventDefault();};" title="Link Title">
                    Xóa
                </a> </td>
            </tr>
        </c:forEach>

    </table>
</center>
</body>
</html>
