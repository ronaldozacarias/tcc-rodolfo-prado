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

<!--<form action="${linkTo[DocumentoController].salva}" method="post" enctype = "multipart/form-data">

	Título do Documento:
    <input type="text" name="documento.tituloDocumento"/><br/>
    Arquivo:
    <input type="file" name="file">

    <input type="submit" value="Salvar" />
</form>-->

<form class="form-horizontal" action="${linkTo[DocumentoController].salva}" method="post" enctype = "multipart/form-data">
<fieldset>
<input type="hidden" name="id_documento" value="${id_afastamento}"/>
<!-- Form Name -->
<legend>Entre com os dados do Documento</legend>

<!-- Text input-->
<div class="control-group" >
  <label class="control-label" for="textinput">Título do Arquivo</label>
  <div class="controls">
    <input id="textinput" name="documento.tituloDocumento" type="text" placeholder="Título do Arquivo" class="input-large" required="">
    
  </div>
</div>

<!-- File Button --> 
<div class="control-group">
  <label class="control-label" for="filebutton">Arquivo</label>
  <div class="controls">
    <input id="filebutton" name="file" class="input-file" type="file">
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