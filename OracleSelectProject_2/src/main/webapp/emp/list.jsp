<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*,com.sist.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
        EmpDAO dao=new EmpDAO();
		ArrayList<EmpVO> list=dao.empAllData();
		for(EmpVO vo:list)
		{
			out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getHiredate()+" "
					+vo.getSal()+" "
					+vo.getDvo().getDname()+" "
					+vo.getDvo().getLoc()+" "
					+vo.getSvo().getGrade()+"<br>");
		}
%>
</body>
</html>