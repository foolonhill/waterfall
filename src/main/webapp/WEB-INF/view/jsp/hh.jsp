<%--
  Created by IntelliJ IDEA.
  User: iWM
  Date: 2016/12/18
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>waterfall</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <script type="javascript">
        function sqlImport() {
            var ip = document.get.getElementsByName("id").value;
            var port = document.getElementsByName("name").value;
            var userName = document.getElementsByName("name").value;
            var password = document.get.getElementsByName("password").value;
            var database = document.get.getElementsByName("database").value;

            if (ip == "") {
                alert("请输入数据库IP");
                return;
            }
            if (port == "") {
                alert("请输入数据库端口");
                return;
            }
            if (userName == "") {
                alert("请输入数据库用户名");
                return;
            }
            if (password == "") {
                alert("请输入数据库密码");
                return;
            }
            if (database == "") {
                alert("请输入数据库库名");
                return;
            }


            var form = $("mysqlForm");
            form.action = "${pageContext.request.contextPath}/stream";
            form.method = "post";
            form.submit();

//            $("mysqlForm").submit();
        }
    </script>

</head>
<body>

<h1>导入数据</h1>

<div class="container">

    <form class="form-mysql" role="form" id="mysqlForm" method="post" action="${pageContext.request.contextPath}/stream">
        <h1>***源数据库***</h1>
        <p class="form-mysql-heading">源数据库的表</p>
        <%--<input type="srcDb" class="form-control" name="srcDb" placeholder="srcDb" required autofocus>--%>
        <select name="srcTable">
            <option value="driver">driver</option>
            <option value="order">order</option>
            <option value="strategy">strategy</option>
        </select>

        <h2>***目的数据库***</h2>
        <p class="form-mysql-heading">数据库IP</p>
        <input type="ip" class="form-control" name="ip" placeholder="ip" required>
        <p class="form-mysql-heading">数据库Port</p>
        <input type="port" class="form-control" name="port" placeholder="port" required>
        <p class="form-mysql-heading">数据库username</p>
        <input type="username" class="form-control" name="username" placeholder="username" required>
        <p class="form-mysql-heading">数据库password</p>
        <input type="password" class="form-control" name="password" placeholder="password" required>
        <p class="form-mysql-heading">数据库database</p>
        <input type="database" class="form-control" name="database" placeholder="database" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="return sqlImport()">导入</button>
    </form>

</div> <!-- /container -->



<!--jQuery (necessary for Bootstrap's JavaScript plugins)-->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!--Include all compiled plugins (below), or include individual files as needed-->
<script src="../js/bootstrap.min.js"></script>
</body>
</html>