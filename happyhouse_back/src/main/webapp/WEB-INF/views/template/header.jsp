<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

<script type="text/javascript">
	function updateUser() {
		document.getElementById("rform").action = "/user/updateUser";
		document.getElementById("rform").submit();
	}
	function deleteUser() {
		document.getElementById("rform").action = "/user/deleteUser";
		document.getElementById("rform").submit();
	}
</script>
</head>
<body>
	<header class="navbar navbar-light bg-light fixed-top container">
		<div class="container-fluid">
			<a class="navbar-brand" href="/"
				style="font-size: 30px; font-weight: bold; color: orange"> <i
				class="fa-solid fa-house-chimney-window"></i> HAPPY HOUSE
			</a>
			<div class="navbar-expand-sm">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/user/map">MAP</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/avg">Deal</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/store">Around Market</a></li>
				</ul>
			</div>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
				aria-controls="offcanvasNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="offcanvas offcanvas-end" tabindex="-1"
				id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
				<div class="offcanvas-header">
					<h5 class="offcanvas-title" id="offcanvasNavbarLabel">회원 관리</h5>
					<button type="button" class="btn-close text-reset"
						data-bs-dismiss="offcanvas" aria-label="Close"></button>
				</div>
				<div class="offcanvas-body">
					<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
						<c:if test="${!empty user }">
							<div class="mb-3">${user.name }님안녕하세요</div>
							<li class="nav-item mb-3"><a
								class="btn btn-warning menu-btn" id="logoutbtn" href="/user/logout">로그아웃</a></li>
							<li class="nav-item mb-3">
								<button type="button" class="btn btn-warning menu-btn"
									data-bs-toggle="modal" data-bs-target="#exampleModalconfirm"
									data-bs-whatever="@mdo" id="confirminfo">회원정보확인</button>
							</li>

						</c:if>
						<c:if test="${empty user }">
							<li class="nav-item mb-3">
								<button type="button" class="btn btn-warning menu-btn"
									data-bs-toggle="modal" data-bs-target="#exampleModallogin"
									data-bs-whatever="@mdo">로그인</button>
							</li>
							<li class="nav-item mb-3">
								<button type="button" class="btn btn-warning menu-btn"
									data-bs-toggle="modal" data-bs-target="#exampleModalregist"
									data-bs-whatever="@mdo">회원가입</button>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</header>
	<!-- 로그인 -->
	<div class="modal fade" id="exampleModallogin" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabellogin">로그인</h5>
				</div>
				<div class="modal-body">

					<form class="row g-3" action="/user/login" method="post">
						<div class="col-12">
							<label for="loginId" class="form-label">Id</label> <input
								type="text" class="form-control" id="loginId" placeholder="id"
								name="id" />
						</div>
						<div class="col-12">
							<label for="loginPassword" class="form-label">Password</label> <input
								type="password" class="form-control" id="loginPassword"
								placeholder="password" name="password" />
							<div id="find-password">[비밀번호 찾기]</div>
						</div>
						<div class="col-12"
							style="width: fit-content; margin: auto; display: block; margin-top: 20px;">
							<button type="submit" class="btn btn-primary" id="loginbtn"
								style="background-color: burlywood; text-decoration: black; border: none;"
								data-bs-dismiss="modal">로그인</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">닫기</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>

	<!-- 회원가입 -->
	<div class="modal fade" id="exampleModalregist" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabelregist">회원가입</h5>
				</div>
				<div class="modal-body">

					<form class="row g-3" action="/user/register" method="post">
						<div class="col-md-6">
							<label for="inputId" class="form-label">ID</label> <input
								type="text" class="form-control" id="inputId" placeholder="id"
								name="id" />
						</div>
						<div class="col-md-6">
							<label for="inputPassword" class="form-label">비밀번호</label> <input
								type="password" class="form-control" id="inputPassword"
								placeholder="영문, 숫자포함 6자리 이상" name="password" />
						</div>
						<div class="col-12">
							<label for="inputEmail" class="form-label">Email</label> <input
								type="text" class="form-control" id="inputEmail"
								placeholder="email" name="email" />
						</div>
						<div class="col-12">
							<label for="inputNumber" class="form-label">휴대폰 번호</label> <input
								type="text" class="form-control" id="inputNumber"
								placeholder="010-xxxx-xxxx" name="phone" />
						</div>
						<div class="col-12">
							<label for="inputName" class="form-label">이름</label> <input
								type="text" class="form-control" id="inputName"
								placeholder="name" name="name" />
						</div>
						<div class="col-12">
							<label for="inputName" class="form-label">관심 지역</label> <input
								type="text" class="form-control" id="inputName"
								placeholder="관심 지역" name="no" />
						</div>
						<div class="col-12"
							style="width: fit-content; margin: auto; display: block; margin-top: 20px;">
							<button type="submit" class="btn btn-primary" id="registbtn"
								style="background-color: burlywood; text-decoration: black; border: none;"
								data-bs-dismiss="modal">등록</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">닫기</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>

	<!-- 회원정보확인 -->
	<div class="modal fade" id="exampleModalconfirm" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabelregist">회원정보확인</h5>
				</div>
				<div class="modal-body">

					<form class="row g-3" action="" method="post" id="rform">
						<div class="col-md-6">
							<label for="inputId" class="form-label">ID</label> <input
								type="text" class="form-control" id="inputId" placeholder="id"
								name="id" value="${user.id }" />
						</div>
						<div class="col-md-6">
							<label for="inputPassword" class="form-label">비밀번호</label> <input
								type="password" class="form-control" id="inputPassword"
								placeholder="영문, 숫자포함 6자리 이상" name="password"
								value="${user.password }" />
						</div>
						<div class="col-12">
							<label for="inputEmail" class="form-label">Email</label> <input
								type="text" class="form-control" id="inputEmail"
								placeholder="email" name="email" value="${user.email }" />
						</div>
						<div class="col-12">
							<label for="inputNumber" class="form-label">휴대폰 번호</label> <input
								type="text" class="form-control" id="inputNumber"
								placeholder="010-xxxx-xxxx" name="phone" value="${user.phone }" />
						</div>
						<div class="col-12">
							<label for="inputName" class="form-label">이름</label> <input
								type="text" class="form-control" id="inputName"
								placeholder="name" name="name" value="${user.name }" />
						</div>
						<div class="col-12">
							<label for="inputName" class="form-label">관심 지역</label> <input
								type="text" class="form-control" id="inputName"
								placeholder="관심 지역" name="no" value="${user.no }" />
						</div>
						<div class="col-12"
							style="width: fit-content; margin: auto; display: block; margin-top: 20px;">
							<button type="submit" class="btn btn-primary" id="registbtn"
								style="background-color: burlywood; text-decoration: black; border: none;"
								data-bs-dismiss="modal" onclick="updateUser()">수정</button>
							<button type="button" class="btn btn-danger"
								data-bs-dismiss="modal" onclick="deleteUser()">탈퇴</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">닫기</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>

	<!-- 회원수정창 -->
	<div class="modal fade" id="exampleModalchangeinfo" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabelregist">회원수정</h5>
				</div>
				<div class="modal-body" id="modal-body-changeinfo"></div>
			</div>
		</div>
	</div>
</body>
</html>