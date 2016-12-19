<%--
  Created by IntelliJ IDEA.
  User: iWM
  Date: 2016/10/7
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>alzhang first jsp</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <script type="javascript">
        function login() {
            var userName = document.getElementsByName("name").value;
//            if (userName == "") {
                alert("请输入用户名");
//                return;
//            }

            $("loginForm").submit();
        }
    </script>

</head>
<body>
    <h1>${name} love ling </h1>
    <h1>ling love ${ming}</h1>

    <div class="container">

        <form class="form-signin" role="form" name="loginForm" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>
            <input type="email" class="form-control" name="email" placeholder="Email address" required autofocus>
            <input type="password" class="form-control" name="password" placeholder="Password" required>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="return login()">Sign in</button>
        </form>

    </div> <!-- /container -->
</body>
</html>
