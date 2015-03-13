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


<form class="form-horizontal" action="${linkTo[ParecerController].salvar}" method="post">
<fieldset>

<!-- Form Name -->
<legend>Entre com os Dados do seu Parecer:</legend>

<!-- Textarea -->
<div class="control-group">
  <label class="control-label" for="textarea">Motivo</label>
  <div class="controls">                     
    <textarea id="textarea" name="parecer.motivoIndeferimento">Digite aqui o Motivo do Ideferimento</textarea>
  </div>
</div>

<!-- Select Basic -->
<div class="control-group">
  <label class="control-label" for="selectbasic">Julgamento</label>
  <div class="controls">
    <select id="selectbasic" name="tipoParecer.julgamento" class="input-medium">
      <option value="FAVORAVEL">Favorável</option>
      <option value="DESFAVORAVEL">Desfavorável</option>
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