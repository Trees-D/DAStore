<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" %>
<%@ page import="da.store.models.Category" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DAStore Background Management System</title>
</head>
<body>
    <br/><br/>
    <form action="${ pageContext.request.contextPath }/admin/GoodsServlet" method="post">
    	<table width="500px;">
			<input type="hidden" name="method" value="add">
	    	<tr>
	    		<td>商品名称：</td>
	    		<td><input type="text" name="name" style="width: 200px"></td>
	    	<tr>
	    	<tr>
	    		<td>商品描述：</td>
	    		<td><textarea rows="4" cols="40" name="description"></textarea></td>
            </tr>
            <tr>
	    		<td>商品价格：</td>
	    		<td><input type="text" name="price" style="width: 200px"></td>
	    	<tr>
            <tr>
                <td>所属类别：</td>
                <td>
                    <select name="category_id">
                        <%if (categories.size() > 0) for (Category category : categories) {%>
                            <option value="<%=category.getID()%>"><%=category.getName()%></option>
                        <%}%>
                    </select>
                </td>
            </tr>
	    	<tr>
	    		<td></td><td><input type="submit" value="添加商品"></td>
	    	</tr>
    	</table>
    </form>
</body>
</html>