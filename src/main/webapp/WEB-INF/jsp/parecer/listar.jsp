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

<%session = request.getSession(false); %>
<div class="container">

<legend>Lista de Pareceres do Afastamento:</legend>

<table id="table-transform" data-toggle="table" data-sort-name="name" data-sort-order="desc">
   <thead>
        <tr>
        	<th data-field="name1" data-align="center" data-sortable="false">Criador</th>
            <th data-field="id1" data-align="center" data-sortable="false">Data</th>
             <th data-field="naome2" data-align="center" data-sortable="false">Julgamento</th>
             <th data-field="naome3" data-align="center" data-sortable="false">Motivo</th>
        </tr>
    </thead>
  <tbody>
    <c:forEach items="${parecerListaList}" var="parecerLista">
      <tr>
      	<td>${parecerLista.nomeCriador}</td>
      	<td>${parecerLista.data}</td>
      	<td>${parecerLista.julgamento}</td>
      	<td>${parecerLista.motivo}</td>
      </tr>          
    </c:forEach>
  </tbody>
</table>

</div>
</body>
</html>