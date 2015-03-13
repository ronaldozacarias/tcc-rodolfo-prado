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
<!-- 
<form action="${linkTo[MandatoController].salva}" method="post">

	Matrícula:
    <input type="text" name="matricula"/><br/>
   	Data do Início:
    <input type="date" name="data_iniMandato"/><br/>
    Data do Fim:
    <input type="date" name="data_fimMandato"/><br/>


    <input type="submit" value="Salvar" />
</form>
-->

<form class="form-horizontal" action="${linkTo[MandatoController].salva}" method="post">
<fieldset>

<!-- Form Name -->
<legend>Entre com as informações do Mandato:</legend>

<!-- Text input-->
<div class="control-group" >
  <label class="control-label" for="textinput">Matrícula</label>
  <div class="controls">
    <input id="textinput" name="matricula" type="text" placeholder="Nome Evento" class="input-large" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Início do Mandato</label>
  <div class="controls">
    <input id="textinput" name="data_iniMandato" type="date" placeholder="" class="input-medium" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Fim do Mandato</label>
  <div class="controls">
    <input id="textinput" name="data_fimMandato" type="date" placeholder="" class="input-medium" required="">
    
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