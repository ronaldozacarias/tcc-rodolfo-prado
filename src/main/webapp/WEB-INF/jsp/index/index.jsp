<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="${pageContext.request.contextPath}/docs/favicon.ico">
<title>Home - SCAP</title>
</head>
<body>
<jsp:directive.include file="../header.jsp" />


<div class="container">
<fieldset>


<c:if test="${secretario}">
<form action="${linkTo[PessoaController].formulario}" method="post">

	<!-- Button -->
<div class="control-group">
  <label class="control-label" for="singlebutton"></label>
  <div class="controls">
    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Cadastrar um novo usuario</button>
  </div>
</div>
</form>

<form action="${linkTo[PessoaController].busca}" method="post">
	<div class="control-group">
  <label class="control-label" for="singlebutton"></label>
  <div class="controls">
    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Procurar um usuario</button>
  </div>
</div>
</form>

<form action="${linkTo[ParentescoController].formulario}" method="post">
	<div class="control-group">
  <label class="control-label" for="singlebutton"></label>
  <div class="controls">
    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Cadastrar um Parentesco</button>
  </div>
</div>
</form>

</c:if>

<form action="${linkTo[AfastamentoController].busca}" method="post">
	<div class="control-group">
  <label class="control-label" for="singlebutton"></label>
  <div class="controls">
    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Procurar um afastamento</button>
  </div>
</div>
</form>

<form action="${linkTo[AfastamentoController].formulario}" method="post">
	<div class="control-group">
  <label class="control-label" for="singlebutton"></label>
  <div class="controls">
    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Cadastrar um afastamento</button>
  </div>
</div>

</form>

</fieldset>
</div>
</body>
</html>