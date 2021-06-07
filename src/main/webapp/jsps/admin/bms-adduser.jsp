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
    <form action="${ pageContext.request.contextPath }/admin/UserServlet" method="post">
    	<table width="500px;">
			<input type="hidden" name="method" value="add">
	    	<tr>
	    		<td>用户名：</td>
	    		<td><input type="text" name="name" style="width: 200px"></td>
	    	<tr>
	    	<tr>
	    		<td>密码：</td>
	    		<td><input type="text" name="password" style="width: 200px"></td>
	    	</tr>
            <tr>
	    		<td>邮箱：</td>
	    		<td><input type="text" name="email" style="width: 200px"></td>
	    	</tr>
            <tr>
	    		<td>地址：</td>
	    		<td><input type="text" name="address" style="width: 200px"></td>
	    	</tr>
            <tr>
	    		<td>权限：</td>
	    		<td>
                    <select name="permission">
                        <option value="S">管理员</option>
                        <option value="N">用户</option>
                    </select>
                </td>
	    	</tr>
	    	<tr>
	    		<td></td><td><input type="submit" value="添加用户"></td>
	    	</tr>
    	</table>
    </form>
</body>
</html>