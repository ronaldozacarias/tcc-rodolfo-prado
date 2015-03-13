<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="${pageContext.request.contextPath}/docs/favicon.ico">
<title>Busca - SCAP</title>
</head>
<body>
<jsp:directive.include file="../header.jsp" />

<div class="container">

<form class="form-horizontal" action="${linkTo[PessoaController].listar}" method="post">
<fieldset>

<!-- Form Name -->
<legend>Entre com o Nome e o Sobrenome da Pessoa:</legend>

<!-- Text input-->
<div class="control-group" >
  <label class="control-label" for="textinput">Nome</label>
  <div class="controls">
    <input id="textinput" name="nome" type="text" placeholder="nome" class="input-large" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="control-group" >
  <label class="control-label" for="textinput">Sobrenome</label>
  <div class="controls">
    <input id="textinput" name="sobreNome" type="text" placeholder="sobrenome" class="input-large" required="">
    
  </div>
</div>

<!-- Button -->
<div class="control-group">
  <label class="control-label" for="singlebutton"></label>
  <div class="controls">
    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Buscar</button>
  </div>
</div>

</div>

</body>
</html>