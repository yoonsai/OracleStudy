<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
   //delete.jsp?hakbun=1
   String hakbun=request.getParameter("hakbun"); // 이름이 다르면 값을 받을 수 없음
   StudentDAO dao=new StudentDAO();
   dao.stdDelete(Integer.parseInt(hakbun));
   response.sendRedirect("list.jsp");
   
%>