<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="${pageContext.request.contextPath}/docs/favicon.ico">
<title>Lista - SCAP</title>
</head>
<body>
<jsp:directive.include file="../header.jsp" />

<div class="container">
<table id="table-transform1" data-toggle="table" data-sort-name="name" data-sort-order="desc">
   <thead>
        <tr>
        	<th data-field="name1" data-align="center" data-sortable="false">Nome</th>
            <th data-field="name2" data-align="center" data-sortable="false">Matr�cula</th>
            <th data-field="name3" data-align="center" data-sortable="false">Email</th>
            <th data-field="name4" data-align="center" data-sortable="false">Telefone</th>
            <th data-field="name5" data-align="center" data-sortable="false">Cadastrar Parentesco</th>
            <th data-field="name6" data-align="center" data-sortable="false">Cadastrar como Relator</th>
        </tr>
    </thead>
  <tbody>
    <c:forEach items="${pessoaList}" var="pessoa">
      <tr>
      	<td>${pessoa.nome} ${pessoa.sobreNome }</td>
        <td>${pessoa.matricula}</td>
        <td>${pessoa.email}</td>
        <td>${pessoa.telefone}</td>
	    <td>
	    	<form action="${linkTo[ParentescoController].formulario}" method="post">
	    		<input type="hidden" name="matricula1" value="${pessoa.matricula}"/>
				<button id="singlebutton" name="singlebutton" class="btn btn-xs btn-default" >Cadastrar</button>
			</form>
        </td>
        <td>
	    	<form action="${linkTo[RelatorController].porAfastamento}" method="post">
	    		<input type="hidden" name="matricula" value="${pessoa.matricula}"/>
				<button id="singlebutton" name="singlebutton" class="btn btn-xs btn-default" >Cadastrar</button>
			</form>
        </td>
      </tr>          
    </c:forEach>
  </tbody>
</table>

</div>
</body>
</html>