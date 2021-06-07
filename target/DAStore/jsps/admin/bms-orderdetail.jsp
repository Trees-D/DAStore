<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="da.store.models.Order" %>
<%@ page import="da.store.models.OrderItem" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    Order order = (Order) request.getAttribute("order");
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
            <td>商品</td>
			<td>价格</td>
			<td>数量</td>
			<td>小计</td>
        </tr>

        <%
        if (order.getItems().size() > 0)
        for (OrderItem item : order.getItems()) {%>
            <tr>
                <td><%=item.getGoods().getName()%></td>
                <td><%=item.getGoods().getPrice()%></td>
                <td><%=item.getNumber()%></td>
                <td><%=item.getTotalPrice()%>元</td>
            </tr>
        <%}%>
        
    </table>
    </br>
    <table border="1px" cellpadding="0" cellspacing="0" width="90%">
        <caption>
            <h2>顾客信息</h2>
        </caption>
        <tr>
            <td>用户</td>
			<td>邮箱</td>
			<td>地址</td>
        </tr>
        <tr>
            <td><%=order.getUser().getName()%></td>
            <td><%=order.getUser().getEmail()%></td>
            <td><%=order.getUser().getAddress()%></td>
        </tr>
        
    </table>
    </br>
    </br>
    <%if (order.getStatus() == false) {%>
        <%-- <a href="${ pageContext.request.contextPath }/admin/OrderServlet?method=set&id=<%=order.getID()%>&status=true">设为完成</a> --%>
    <input type="button" value="设为完成" onclick='location.href=("${ pageContext.request.contextPath }/admin/OrderServlet?method=set&id=<%=order.getID()%>&status=true")'>
    <%}%>
    
	<%-- <input type="button" value="返回" οnclick="self.location=document.referrer;"> --%>
    
</body>

</html>