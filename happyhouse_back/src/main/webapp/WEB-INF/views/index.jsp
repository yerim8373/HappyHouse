<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="template/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- bootstrap -->
<style type="text/css">
.body {
	margin-top: 100px;
}

.map {
	height: 500px;
	background-color: gray;
}

.info-contents {
	width: 100%;
	height: 300px;
	margin-top: 20px;
	background-color: orange;
	display: flex;
	align-items: center;
	justify-content: center;
}

.info-contents div {
	width: 30%;
	height: 90%;
	background-color: whitesmoke;
	margin: auto;
	text-align: center;
	font-size: 24px;
	border-radius: 10px;
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
</head>
<body>
	<div class="container body">
		<!-- BANNER 시작 -->
		<div id="carouselExampleCaptions" class="carousel slide"
			data-bs-ride="carousel">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleCaptions"
					data-bs-slide-to="0" class="active" aria-current="true"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleCaptions"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleCaptions"
					data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="./img/banner3.jpg" class="d-block w-100" alt="..."
						style="height: 450px" />
					<div class="carousel-caption d-none d-md-block">
						<!-- <h5>Happy House</h5> -->
						<p style="font-size: 20px">Welcome, Happy House</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="./img/banner2.jpg" class="d-block w-100" alt="..."
						style="height: 450px" />
					<div class="carousel-caption d-none d-md-block"></div>
				</div>
				<div class="carousel-item">
					<img src="./img/banner1.png" class="d-block w-100" alt="..."
						style="height: 450px" />
					<div class="carousel-caption d-none d-md-block">
						<h5>송예림 이우원</h5>
						<p>Web Spring 관통 프로젝트</p>
					</div>
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
		<!-- BANNER 끝 -->
<%-- 		<div class="mt-5 row text-center">
			<c:if test="${empty user }">
				<h1>로그인이 필요합니다</h1>
			</c:if>
			<c:if test="${!empty user }">
				<c:if test="${empty user.no }">
					<h1>관심지역 설정이 필요합니다.</h1>
				</c:if>
				<c:if test="${!empty user.no }">
					<h1>관심지역 중점으로 지도 보여주기.</h1>
				</c:if>
			</c:if>
		</div> --%>
		<div class="info-contents mt-5">
			<div>광고</div>
			<div>게시판</div>
			<div>뉴스</div>
		</div>
	</div>
</body>
</html>
<%@ include file="template/footer.jsp"%>