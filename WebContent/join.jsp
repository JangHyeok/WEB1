<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>�丮 �</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapsed-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp"><img src="images/icon3.png"></a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">����</a>
				<li><a href="main.jsp">������ �Խ���</a>
				<li><a href="bbs1.jsp">���� �Խ���</a></li>
				<li><a href="bbs2.jsp">���� �Խ���</a></li>
				<li><a href="bbs3.jsp">���� �Խ���</a></li>
				<li><a href="bbs4.jsp">�߰�ŷ� �Խ���</a></li>
				<li><a href="bbs5.jsp">��ᰡ�� ����</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropddown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">�����ϱ�<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">�α���</a></li>
						<li class="active"><a href="join.jsp">ȸ������</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbtron" style="padding-top: 20px;">
				<form method="post" action="joinAction.jsp">
					<h3 style="text-align: center;">ȸ������ ȭ��</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="���̵�"
							name="userID" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="��й�ȣ"
							name="userPassword" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="�̸�"
							name="userName" maxlength="20">
					</div>
					<div class="form-group" style="text-align: center;">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary active"> <input
								type="radio" name="userGender" autocomplete="off" value="����"
								checked>����
							</label> <label class="btn btn-primary "> <input type="radio"
								name="userGender" autocomplete="off" value="����" checked>����
							</label>
						</div>
					</div>
					<div class="form-group">
						<input type="email" class="form-control" placeholder="�̸���"
							name="userEmail" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control"
						value="ȸ������">
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>