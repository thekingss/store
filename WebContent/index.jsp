<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
			<!--  
				好处: 能去servlet绕一圈取数据 然后到index.jsp展示数据
			-->
			<%request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);%>
</body>
</html>