<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="${pageContext.request.contextPath}/docs/favicon.ico">
<title>Afastamento - SCAP</title>
</head>

<body>

<jsp:directive.include file="../header.jsp" />

<%session = request.getSession(false); %>
<div class="container">

<div class="row">
	<div class="col-lg-4">
				<div class="panel panel-info">
					  <div class="panel-heading" style="color:#fff;background-color:#337ab7;border-color:#2e6da4">
					    <h3 class="panel-title">Solicitante</h3>
					  </div>
					  <div class="panel-body">
					    ${SolicitanteNome}<br>
					    Matrícula: ${SolicitanteMatricula}<br>
					    Email: ${SolicitanteEmail}<br>
					    Tel: ${SolicitanteTel}
					  </div>
				 </div>
	</div>
	<div class="col-lg-4">
		<div class="panel panel-info">
					  <div class="panel-heading" style="color:#fff;background-color:#337ab7;border-color:#2e6da4">
					    <h3 class="panel-title">Evento</h3>
					  </div>
					  <div class="panel-body">
					    ${afastamentonomeEvento}<br>
					    Cidade: ${afastamentonomeCidade}<br>
					    Início: ${afastamentoData_iniEvento}<br>
					    Fim: ${afastamentoData_fimEvento}
					  </div>
		</div>
	</div>
	<div class="col-lg-4">
		<div class="panel panel-info">
					  <div class="panel-heading" style="color:#fff;background-color:#337ab7;border-color:#2e6da4">
					    <h3 class="panel-title">Afastamento</h3>
					  </div>
					  <div class="panel-body">
					    Status: ${afastamentostatus}<br>
					    Tipo: ${afastamentotipo} Ônus: ${afastamentoonus}<br>
					    Início: ${afastamentoData_iniAfasta}<br>
					    Fim: ${afastamentoData_fimAfasta}<br>
					  </di>
		</div>
		</div>
	</div>
</div>

<p>
<a href="${linkTo[AfastamentoController].mudarStatusForm()}" class="btn btn-primary btn-default">Mudar Status</a>
<c:if test="${professor}">
<a href="${linkTo[ParecerController].formulario()}" class="btn btn-primary btn-default">Deferir um Parecer</a>
</c:if>
<c:if test="${chefe}">
<a href="${linkTo[RelatorController].formulario()}" class="btn btn-primary btn-default">Cadastrar um Relator</a>
</c:if>
<a href="${linkTo[DocumentoController].formulario()}" class="btn btn-primary btn-default">Cadastrar um Documento</a>

<a href="${linkTo[ParecerController].listar()}" class="btn btn-primary btn-default">Ver pareceres</a>

</p>

<table id="table-transform" data-toggle="table" data-sort-name="name" data-sort-order="desc">
   <thead>
        <tr>
        	<th data-field="name1" data-align="center" data-sortable="false">Título</th>
            <th data-field="id" data-align="center" data-sortable="false">Data Juntada</th>
             <th data-field="download" data-align="center" data-sortable="false">Download</th>
        </tr>
    </thead>
  <tbody>
    <c:forEach items="${documentoListaList}" var="documentoLista">
      <tr>
      	<td>${documentoLista.titulo}</td>
        <td>${documentoLista.juntada}</td>
	    <td>
	    	<form action="${linkTo[DocumentoController].downloadArquivo}" method="post">
	    		<input type="hidden" name="id_documento" value="${documentoLista.id}"/>
				<button id="singlebutton" name="singlebutton" class="btn btn-xs btn-default" >Download</button>
			</form>
        </td>
      </tr>          
    </c:forEach>
  </tbody>
</table>

</div>
</body>
</html>