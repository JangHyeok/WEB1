<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>요리 어때</title>

    <style>
    * {
        margin:0;
        padding:0;
        border:0;
        text-decoration:none;
        letter-spacing:0px;
    }
    .menubar
    {
        width:1145px;
        margin: 0 auto;
        border:none;
        background: #F7313E;
        overflow: hidden;
    }

    .menuLogo
    {
        width:180px;
        float:left;
    }

    .menuItem
    {
        float:right;
    }

    .menubar ul
    {
        background: #F7313E;
        height:50px;
        list-style:none;
    }

    .menubar li
    {
        float:left;
    }

    .menubar a
    {
        color:#FFFFFF;
        display:block;
        font-weight:normal;
        line-height:49px;
        padding:1px 25px;
        text-align:center;
    }

    .menubar li a:hover,
    .menubar ul li:hover a {
        background: #636363;
        color:#FFFFFF;
    }

    .menubar li:hover ul
    {
        display:block;
    }

    .menubar li:hover li a
    {
        background:none;
    }

    .menubar li ul
    {
        background: #F7313E;
        display:none;
        height:auto;
        position:absolute;
        z-index:999;
    }

    .menubar li li a ,
    .menubar li li
    {
        background: #F7313E;
        display:block;
        float:none;
        min-width:135px;
    }

    .menubar li ul a
    {
        display:block;
        height:50px;
        font-size:12px;
        font-style:normal;
        padding:0px 10px 0px 15px;
        text-align:left;
    }

    .menubar li ul a:hover,
    .menubar li ul li:hover a{
        background: #636363;
        border:0px;
        color:#ffffff;
    }

    .footer
    {
        width:1140px;
        margin: 0 auto;
        border:none;
        background: #E6E6E6;
        overflow: hidden;
        text-align:center;
        font-weight:normal;
    }

    .section
    {
        width:1140px;
        margin:auto;
        border:auto;
        overflow: hidden;
        text-align:center;
    }

    .absolute{
      position: absolute;
    }
    
    img.logo {
      position: relative;
      left: -440px;
    }
    
    .top
    {
        width:1140px;
        margin: 0 auto;
        border:none;
        overflow: hidden;
        text-align:center;
    }
    </style>

<body>

	<%
		String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	%>
		
	<header>
    <div class="top">
    
    <a href="main.jsp"><img src="images/logo.png" width=256px class="logo">
    </div>
        <div class="menubar">
            <ul class="menuItem">

                <li><a href="bbs.jsp">레시피 게시판</a></li>
                
                <li><a href="bbs1.jsp">공지 게시판</a></li>
                
                <li><a href="bbs2.jsp">자유 게시판</a></li>
                
                <li><a href="bbs3.jsp">질문 게시판</a></li>
                
                <li><a href="bbs4.jsp">중고거래 게시판</a></li>

                <li><a href="http://janghyeok.iptime.org:8989/">배추 가격 예측</a></li>
                                
                <li>
                  <a href="#">계정 관리</a>
                  			<%
				if (userID == null) {
			%>
				  <ul>
                      <li><a href="login.jsp">로그인</a></li>
                      <li><a href="join.jsp">회원가입</a></li>
                  </ul>
                  
                  			<%
				} else {
			%>	
                  				  <ul>
                      <li><a href="logoutAction.jsp">로그아웃</a></li>
                  </ul>
                  
                </li>
               <%
               }%> 

            </ul>
        </div>
    </header>
<br>
	<div class ="container">
	<div class="jumbotron">
	<center>
	<img src="images/pig.png">
	<h1>사이트 소개</h1>
	<p>요리 어때는 회원들과의 다양한 요리 정보를 공유하고 질문/답변 및 중고거래 등 여러가지 커뮤니티의 기능과 배추가격 예측등의 분석을 위해 만든 사이트 입니다.</p>
	<p><a class="btn btn-primary btn-pull" href="https://github.com/JangHyeok/WEB_Programming" role="button">3팀 GitHub 방문하기</a></p>
	</center>
	</div>
	</div>
	<div class="container">
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
	<ol class="carousel-indicators">
	<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<li data-target="#myCarousel" data-slide-to="3"></li>
			<li data-target="#myCarousel" data-slide-to="4"></li>
			<li data-target="#myCarousel" data-slide-to="5"></li>
	</ol>
	<div class="carousel-inner">
	<div class="item active">
	<img src="images/1.jpg">
	</div>
		<div class="item">
	<img src="images/2.jpg">
	</div>
		<div class="item">
	<img src="images/3.jpg">
	</div>
	<div class="item">
	<img src="images/4.jpg">
	</div>
	<div class="item">
	<img src="images/5.jpg">
	</div>
	</div>
	<a class="left carousel-control" href="#myCarousel" data-slide="prev">
	<span class="glyphicon glyphicon-chevron-left"></span>
	</a>
	<a class="right carousel-control" href="#myCarousel" data-slide="next">
	<span class="glyphicon glyphicon-chevron-right"></span>
	</a>
	</div>
	</div>

	<SCRIPT LANGUAGE="JavaScript">

function change(form) {
    if (form.url.selectedIndex !=0)
        parent.location = form.url.options[form.url.selectedIndex].value
    }

function setCookie( name, value, expiredays ){
    var todayDate = new Date();
        todayDate.setDate( todayDate.getDate() + expiredays );
        document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}

function getCookie( name ){
    var nameOfCookie = name + "=";
    var x = 0;
        while ( x <= document.cookie.length ) {
            var y = (x+nameOfCookie.length);
                if ( document.cookie.substring( x, y ) == nameOfCookie ) {
                if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
                    endOfCookie = document.cookie.length;
                return unescape( document.cookie.substring( y, endOfCookie ) );
            }
        x = document.cookie.indexOf( " ", x ) + 1;
        if ( x == 0 )
        break;
    }
    return "";
}


    if ( getCookie( "Notice" ) != "done" ) {
    noticeWindow  =  window.open('popup.html','notice','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=400,height=580');
    noticeWindow.opener = self;
}

</SCRIPT>

<br>

	 <footer class="footer">
      <b> 
       <div class="item">2020-1 : 고급웹프로그래밍[ITEC416004] 3팀</div>
      <div class="item">&copy;2013105086 장혁, 2017115112 김보성</div>
      </b>
    </footer>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>