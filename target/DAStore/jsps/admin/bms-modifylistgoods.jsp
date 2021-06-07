<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="da.store.models.Goods" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    List<Goods> goodslist = (List<Goods>) request.getAttribute("goodslist");
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>DAStore Background Management System Menu</title>
</head>

<body style="text-align: center">
    <br />
    <br />
    <table border="1px" cellpadding="0" cellspacing="0" width="90%">
        <caption>
            <h2>商品信息</h2>
        </caption>
        <tr>
            <td>商品名称</td>
            <td>商品描述</td>
            <td>商品价格</td>
            <td>商品类别</td>
            <td>操作</td>
        </tr>

        <%
        if (goodslist.size() > 0)
        for (Goods goods : goodslist) {%>
        <tr>
            <td><%=goods.getName()%></td>
            <td><%=goods.getDescription()%></td>
            <td><%=goods.getPrice()%></td>
            <td><%=goods.getCategory().getName()%></td>
            <td><a href="${pageContext.request.contextPath}/admin/GoodsServlet?method=modifyPage&gid=<%=goods.getID()%>">修改</a></td>
        </tr>
        <%}%>
    </table>
</body>

</html>