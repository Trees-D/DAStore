<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DAStore Background Management System</title>
</head>
<body>
    <br/><br/>
    <form action="${ pageContext.request.contextPath }/admin/CategoryServlet" method="post">
    	<table width="500px;">
			<input type="hidden" name="method" value="add">
	    	<tr>
	    		<td>分类名称：</td>
	    		<td><input type="text" name="name" style="width: 200px"></td>
	    	<tr>
	    	<tr>
	    		<td>分类描述：</td>
	    		<td><textarea rows="4" cols="40" name="description"></textarea></td>
	    	</tr>
	    	<tr>
	    		<td></td><td><input type="submit" value="添加分类"></td>
	    	</tr>
    	</table>
    </form>
</body>
</html>