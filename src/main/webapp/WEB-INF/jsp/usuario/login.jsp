<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/docs/favicon.ico">
    
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/docs/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/docs/dist/css/signin.css" rel="stylesheet" type="text/css">

<title>login</title>
</head>
<body>
<%session = request.getSession(true); %>
<div class="container">
		<form class="form-signin" role="form" action="${linkTo[UsuarioController].logar}" method="post">
        <h2 class="form-signin-heading">Entre com seu Login</h2>
        <label for="inputEmail" class="sr-only">Matrícula</label>
        <input type="text" id="inputEmail" class="form-control" placeholder="Matrícula" name="usuario.matricula"  required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="usuario.password" required>
        <p>${variable}</p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
      </form>
      
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src=""${pageContext.request.contextPath}/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
