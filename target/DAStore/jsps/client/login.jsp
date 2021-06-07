<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>DAStore</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: #eceffc;
        }

        .btn {
            position: relative;
            padding: 0.375rem 0.75rem;
            line-height: 1.5;
            color: #1559c0;
            text-decoration: none;
            background-color: white;
            border: transparent;
            border-radius: 3px;
            background-color: white;
            -webkit-transition: 0.25s;
            transition: 0.25s;
            outline: none;
        }

        .btn:hover {
            /* background-color: ; */
            transform: scale(1.05);
            box-shadow: 0 0.4px 0.4px rgba(128, 128, 128, 0.109),
                0 1px 1px rgba(128, 128, 128, 0.155),
                0 2.1px 2.1px rgba(128, 128, 128, 0.195),
                0 4.4px 4.4px rgba(128, 128, 128, 0.241),
                0 12px 12px rgba(128, 128, 128, 0.35);
        }


        .form-input-material {
            --input-default-border-color: white;
            --input-border-bottom-color: white;
        }

        .form-input-material input {
            color: #093377;
        }

        .login-form {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            padding: 50px 40px;
            color: white;
            background-color: #1559c0;
            border-radius: 10px;
            box-shadow: 0 0.4px 0.4px rgba(128, 128, 128, 0.109),
                0 1px 1px rgba(128, 128, 128, 0.155),
                0 2.1px 2.1px rgba(128, 128, 128, 0.195),
                0 4.4px 4.4px rgba(128, 128, 128, 0.241),
                0 12px 12px rgba(128, 128, 128, 0.35);
        }

        .login-form h1 {
            margin: 0 0 24px 0;
        }

        .login-form .form-input-material {
            margin: 12px 0;
        }

        .login-form .btn {
            width: 100%;
            margin: 18px 0 9px 0;
        }

        input {
            outline: 0;
        }
    </style>
</head>

<body>
    <form class="login-form" action="${ pageContext.request.contextPath }/client/LoginServlet" method="post">
        <h1>登录</h1>
        <div class="form-input-material">
            <input type="text" name="username" id="username"/>
            <label for="username">用户名</label>
        </div>
        <div class="form-input-material">
            <input type="password" name="password" id="password"/>
            <label for="password">密码</label>
        </div>
        <input type="submit" value="登录" class="btn">
        <input type="button" value="注册" class="btn" onclick='location.href=("${ pageContext.request.contextPath }/jsps/client/register.jsp")'>
    </form>
</body>

</html>