<%@page import="DAO.ComandosDAO"%>
<%@page import="DTO.ClientesDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
  ClientesDTO cliente = new ClientesDTO();
  ComandosDAO DAO = new ComandosDAO();
  String id = request.getParameter("id_cliente");
  String nome = request.getParameter("nome_cliente");
  String idade = request.getParameter("idade");
  String Situacao = request.getParameter("situacao");
  

  cliente.setIdade( Integer.parseInt(idade) );
  cliente.setSituacao( Situacao.charAt(0) );
  cliente.setNome_cliente(nome);
  
  if (id.equals("-1")){
	 DAO.insereRegistro(cliente);
  }else{
	  cliente.setID_CLIENTE( Integer.parseInt(id) );
	  DAO.atualizaRegistro(cliente);
  }
  response.sendRedirect("cadastroCliente.jsp");

%>
</body>
</html>