<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DAStore Background Management System Menu</title>
    <style>
        body {
            display: flex;
            height: 80%;
            justify-content: center;
            align-items: center;
            background: #eceffc;
        }

        ::-webkit-scrollbar {
            display: none;
        }

        .menu {
            width: 100%;
            padding: 0;
            overflow: hidden;
            list-style-type: none;
            border-radius: 3px;
        }

        .menu .menu-item .menu-link {
            position: relative;
            display: block;
            padding: 16px 20px;
            text-decoration: none;
            color: white;
            background-color: #1559c0;
            transition: 0.5s;
        }

        .menu .menu-item .menu-link svg.chevron {
            position: absolute;
            top: 16px;
            right: 10px;
            color: white;
            transition: 0.5s;
        }

        .menu .menu-item .menu-submenu {
            max-height: 0;
            padding: 0;
            background-color: #27425c;
            overflow: hidden;
            list-style-type: none;
            transition: 0.5s;
        }

        .menu .menu-item .menu-submenu a {
            display: block;
            padding: 1em 2em;
            color: white;
            text-decoration: none;
            transition: 0.5s;
        }

        .menu .menu-item .menu-submenu a:hover {
            background-color: #1559c0d3;
        }

        .menu .menu-item:target .menu-submenu {
            max-height: 10em;
        }

        .menu .menu-item:target .menu-link svg.chevron {
            transform: rotate(0.5turn);
        }
    </style>
</head>

<body>
    <ul class="menu">
        <li class="menu-item" id="item-1">
            <a href="#item-1" class="menu-link">商品<svg t="1580188559675" class="icon chevron" viewBox="0 0 1024 1024"
                    version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="616" width="30" height="30">
                    <path
                        d="M316.16 366.08 512 561.92 707.84 366.08 768 426.666667 512 682.666667 256 426.666667 316.16 366.08Z"
                        p-id="617" fill="#ffffff"></path>
                </svg></a>
            <ul class="menu-submenu">
                <li><a href="${ pageContext.request.contextPath }/admin/GoodsServlet?method=selectCategory" target="main-page">添加</a></li>
                <li><a href="${ pageContext.request.contextPath}/admin/GoodsServlet?method=getAll" target="main-page">查看</a></li>
                <li><a href="${ pageContext.request.contextPath}/admin/GoodsServlet?method=modifyAll" target="main-page">修改</a></li>
            </ul>
        </li>
        <li class="menu-item" id="item-2">
            <a href="#item-2" class="menu-link">分类<svg t="1580188559675" class="icon chevron" viewBox="0 0 1024 1024"
                    version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="616" width="30" height="30">
                    <path
                        d="M316.16 366.08 512 561.92 707.84 366.08 768 426.666667 512 682.666667 256 426.666667 316.16 366.08Z"
                        p-id="617" fill="#ffffff"></path>
                </svg></a>
            <ul class="menu-submenu">
                <li><a href="${ pageContext.request.contextPath }/jsps/admin/bms-addcategory.jsp" target="main-page">添加</a></li>
                <li><a href="${ pageContext.request.contextPath}/admin/CategoryServlet?method=getAll" target="main-page">查看</a></li>
            </ul>
        </li>
        <li class="menu-item" id="item-3">
            <a href="#item-3" class="menu-link">用户<svg t="1580188559675" class="icon chevron" viewBox="0 0 1024 1024"
                    version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="616" width="30" height="30">
                    <path
                        d="M316.16 366.08 512 561.92 707.84 366.08 768 426.666667 512 682.666667 256 426.666667 316.16 366.08Z"
                        p-id="617" fill="#ffffff"></path>
                </svg></a>
            <ul class="menu-submenu">
                <li><a href="${ pageContext.request.contextPath }/jsps/admin/bms-adduser.jsp" target="main-page">添加</a></li>
            </ul>
        </li>
        <li class="menu-item" id="item-4">
            <a href="#item-4" class="menu-link">订单<svg t="1580188559675" class="icon chevron" viewBox="0 0 1024 1024"
                    version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="616" width="30" height="30">
                    <path
                        d="M316.16 366.08 512 561.92 707.84 366.08 768 426.666667 512 682.666667 256 426.666667 316.16 366.08Z"
                        p-id="617" fill="#ffffff"></path>
                </svg></a>
            <ul class="menu-submenu">
                <li><a href="${ pageContext.request.contextPath }/admin/OrderServlet?method=getAll&status=false" target="main-page">未处理</a></li>
                <li><a href="${ pageContext.request.contextPath }/admin/OrderServlet?method=getAll&status=true" target="main-page">已处理</a></li>
            </ul>
        </li>
        <li class="menu-item" id="item-5">
            <a href="${ pageContext.request.contextPath }/pre-load.jsp" class="menu-link" target="_top">退出后台</a>
        </li>
        
    </ul>
</body>

</html>