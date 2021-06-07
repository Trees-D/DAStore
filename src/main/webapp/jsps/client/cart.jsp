<%@ page import="da.bookstore.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="da.store.models.User" %>
<%@ page import="da.store.models.Goods" %>
<%@ page import="da.store.models.Category" %>
<%@ page import="da.store.models.ShoppingCart" %>
<%@ page import="da.store.models.CartItem" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<%
    User user = (User) request.getSession().getAttribute("user");
    ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DAStore</title>
    <style>
        body {
            text-align: center;
            background: #eceffc;
        }

        .homepage-title {
            margin: 1em 0em 1em 0em;
            font-weight: 600;
            position: relative;
            padding: 15px 15px 15px 15px;
            color: white;
            border-radius: 0 10px 0 10px;
            background-color: #1559c0;
            border-color: #27425c;
            border: 10px;
        }

        #container {
            width: 100%;
            height: 210px;
        }

        .btn {
            position: relative;
            padding: 0.375rem 0.75rem;
            line-height: 1.5;
            color: white;
            text-decoration: none;
            background-color: #1559c0;
            border: transparent;
            border-radius: 3px;
            -webkit-transition: 0.25s;
            transition: 0.25s;
            outline: none;
        }

        .btn:hover {
            transform: scale(1.02);
            box-shadow: 0 0.4px 0.4px rgba(128, 128, 128, 0.109),
                0 1px 1px rgba(128, 128, 128, 0.155),
                0 2.1px 2.1px rgba(128, 128, 128, 0.195),
                0 4.4px 4.4px rgba(128, 128, 128, 0.241),
                0 12px 12px rgba(128, 128, 128, 0.35);
        }

        #main {
            width: 100%;
            margin-top: 20px;
            display : flex;
        }

        #categories {
            border: solid 1px blue;
            width: 100px;
            height: 50%;

            padding-left: 20px;
            line-height: 40px;
            float: left;
            margin: auto 50px auto 50px;
        }

        #goods {
            /* float: left; */
            display : flex;
            margin : 20px auto;
        }
    </style>
</head>

<body>
    <div id="container">
        <div class="homepage-title">
            <h2>DAStore</h2>
            <p>Hello, <%=user.getName()%></p>
        </div>
        <div style="float: right">
            <form action="${  pageContext.request.contextPath }/client/LogoutServlet" method="post" style="float: right">
                <input type="submit" value="注销" class="btn">
            </form>
        </div>
        <div style="float: right">
            <form action="${  pageContext.request.contextPath }/client/LoginBMSServlet" method="post" style="float: right">
                <input type="submit" value="后台" class="btn">
            </form>
        </div>
        <div style="float: right">
            <form action='${  pageContext.request.contextPath }/client/BuyServlet?id=' method="post" style="float: right">
                <input type="submit" value="购物车" class="btn">
            </form>
        </div>
        <div style="float: right">
            <form action="${  pageContext.request.contextPath }/client/OrderServlet?method=getAll&id=<%=user.getID()%>&status=false" method="post" style="float: right">
                <input type="submit" value="未发货" class="btn">
            </form>
        </div>
        <div style="float: right">
            <form action="${  pageContext.request.contextPath }/client/OrderServlet?method=getAll&id=<%=user.getID()%>&status=true" method="post" style="float: right">
                <input type="submit" value="历史订单" class="btn">
            </form>
        </div>
        <div style="float: right">
            <form action="${  pageContext.request.contextPath }/client/IndexServlet" method="post" style="float: right">
                <input type="submit" value="首页" class="btn">
            </form>
        </div>
    </div>

    <div id="main">
    <table border="1px" cellpadding="0" cellspacing="0" width="90%" align="center">
		<caption>
			<h2>购物车</h2>
		</caption>
		<tr>
			<td>商品</td>
			<td>售价</td>
			<td>数量</td>
			<td>小计</td>
		</tr>
		<%-- <c:forEach var="entry" items="${cart.map}">
			<tr>
				<td>${entry.value.book.name }</td>
				<td>${entry.value.book.price }</td>
				<td>${entry.value.quantity }</td>
				<td>${entry.value.price }元</td>
			</tr>
		</c:forEach> --%>
        <%
            if (cart.getItems().size() > 0)
            for (Map.Entry<String, CartItem> entry : cart.getItems().entrySet()) {
        %>
            <tr>
				<td><%=entry.getValue().getGoods().getName()%></td>
				<td><%=entry.getValue().getGoods().getPrice()%></td>
				<td><%=entry.getValue().getNumber()%></td>
				<td><%=entry.getValue().getTotalPrice()%>元</td>
			</tr>
        <%}%>
		<tr>
			<td colspan="2">合计</td>
			<td colspan="2"><%=cart.getTotalPrice()%>元</td>
		</tr>
	</table>
	<form action="${  pageContext.request.contextPath }/client/OrderServlet" method="post" style="float: right">
        <input type="hidden" name="method" value="create">
        <input type="submit" value="结算" class="btn">
    </form>
    </div>

</body>

</html>