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
    int n1 = Integer.parseInt(request.getParameter("nota1"));
    int n2 = Integer.parseInt(request.getParameter("nota2"));
    int n3 = Integer.parseInt(request.getParameter("nota3"));
    int media = (n1 + n2 + n3)/3;
    if (media >= 7){
    	out.print("Parabéns, você foi aprovado!");
    }else if (media >= 5){
    	out.print("Você tera que fazer recuperação");
    }else{
    	out.print("Infelizmente você foi reprovado!");
    }
  
  %>
</body>
</html>