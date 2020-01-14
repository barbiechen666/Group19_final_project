

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CAFETCH</title>
</head>
<body bgcolor=#FDF5E6>
<%
String[][] orderList = (String[][])  request.getAttribute("query");

for(int i =0 ; i < orderList.length;i++){%>
	<a href='<%= orderList[i][1] %>' style="font-family:Microsoft JhengHei;"><%= orderList[i][0] %></a><br>
	<h style="font-size:10px ;" face="serif"><%= orderList[i][1] %></h><br><br>
<%
}
%>
<div>
<h><br/><br/><br/>相關搜尋</h><br></br>
<%
String[] r = (String[])  request.getAttribute("relativeKeywords");
String[] url = (String[])  request.getAttribute("relativeUrl");

for(int i =3 ; i < r.length;i++){%>
	<a href='<%= url[i] %>' style="font-family:Microsoft JhengHei;"><%= r[i] %></a><br>
	<h style="font-size:10px ;" face="serif"><%= url[i] %></h><br><br>
<%
}
%>

</div>
</body>
</html>