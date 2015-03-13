<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="${pageContext.request.contextPath}/docs/favicon.ico">
<title>Cadastro - SCAP</title>
</head>
<body>

<jsp:directive.include file="../header.jsp" />

<div class="container">

<form class="form-horizontal" action="${linkTo[ParentescoController].salva}" method="post">
<fieldset>

<!-- Form Name -->
<legend>Entre com as informa��es do Parentesco:</legend>

<!-- Text input
<div class="control-group" >
  <label class="control-label" for="textinput">Matr�cula um</label>
  <div class="controls">
    <input id="textinput" name="matricula1" type="text" placeholder="0000000" class="input-large" required="">
    
  </div>
</div>
-->
<input id="textinput" type="hidden" name="matricula1" type="text" value="${matricula1}">
<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Matr�cula</label>
  <div class="controls">
    <input id="textinput" name="matricula2" type="text" placeholder="0000000" class="input-large" required="">
    
  </div>
</div>

<!-- Select Basic -->
<div class="control-group">
  <label class="control-label" for="selectbasic">Parent�sco</label>
  <div class="controls">
    <select id="selectbasic" name="tipo.tipoParentesco" class="input-medium">
      <option value="MATRIOMONIAL">Matrim�nial</option>
  	  <option value="SANGUINEO">Sangu�neo</option>
    </select>
  </div>
</div>

<!-- Button -->
<div class="control-group">
  <label class="control-label" for="singlebutton"></label>
  <div class="controls">
    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Salvar</button>
  </div>
</div>

</fieldset>
</form>
</div>

</body>
</html>