<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DAStore Background Management System</title>
    <style>
        ::-webkit-scrollbar {
            display: none;
        }
    </style>
</head>
<frameset cols="20%, *">
    <frame src="${ pageContext.request.contextPath }/jsps/admin/bms-menu.jsp" name="menu" noresize="noresize">
    <frameset rows="15%, *">
        <frame src="${ pageContext.request.contextPath }/jsps/admin/bms-head.jsp" name="head" noresize="noresize">
        <frame name="main-page" noresize="noresize">
    </frameset>
</frameset>
</html>