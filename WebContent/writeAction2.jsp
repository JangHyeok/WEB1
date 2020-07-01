<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bbs2.BbsDAO2"%>
<%@ page import="java.io.PrintWriter"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="bbs2" class="bbs2.Bbs2" scope="page" />
<jsp:setProperty name="bbs2" property="bbsTitle" />
<jsp:setProperty name="bbs2" property="bbsContent" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>요리 어때</title>
</head>
<body> <!-- 로그인 액션은 userDAO와 로그인 값을 액션처리해서 값을 비교하고 승인하는 JSP -->
	<% 
		String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	if (userID == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 하세요.')");
		script.println("location.href='login.jsp'");
		script.println("</script>");
	}
	else
	{
		if (bbs2.getBbsTitle () == null || bbs2.getBbsContent()==null)  {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안된 사항이 있습니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else {
			BbsDAO2 bbsDAO = new BbsDAO2();
			int result = bbsDAO.write(bbs2.getBbsTitle(),userID,bbs2.getBbsContent());
			if (result == -1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글쓰기에 실패했습니다.')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("location.href = 'bbs2.jsp'");
				script.println("</script>");
	}
		}
	}
	%>

</body>
</html>