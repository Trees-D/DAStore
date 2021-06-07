<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="da.store.models.Category" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
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
            <h2>商品分类信息</h2>
        </caption>
        <tr>
            <td>分类名称</td>
            <td>分类描述</td>
            <td>操作</td>
        </tr>

        <%
        if (categories.size() > 0)
        for (Category category : categories) {%>
            <tr>
                <td><%=category.getName()%></td>
                <td><%=category.getDescription()%></td>
                <td><a href="${pageContext.request.contextPath}/admin/CategoryServlet?method=remove&cid=<%=category.getID()%>">删除</a></td>
            </tr>
        <%}%>
    </table>
</body>

</html>