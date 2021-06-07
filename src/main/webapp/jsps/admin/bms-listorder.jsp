<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="da.store.models.Order" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    List<Order> orders = (List<Order>) request.getAttribute("list");
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
            <h2>订单信息</h2>
        </caption>
        <tr>
            <td>订单人</td>
			<td>下单时间</td>
			<td>订单状态</td>
			<td>订单总价</td>
			<td>操作</td>
        </tr>

        <%
        if (orders.size() > 0)
        for (Order order : orders) {%>
            <tr>
                <td><%=order.getUser().getName()%></td>
                <td><%=order.getTime()%></td>
                <td><%=(order.getStatus() == false ? "未发货" : "已发货")%></td>
                <td><%=order.getTotalPrice()%>元</td>
                <td><a href="${ pageContext.request.contextPath }/admin/OrderServlet?method=find&id=<%=order.getID()%>">查看明细</a></td>
            </tr>
        <%}%>
    </table>
    </br>
    
</body>

</html>