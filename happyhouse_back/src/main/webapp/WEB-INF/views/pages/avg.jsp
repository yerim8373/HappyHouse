<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script> 
	$(document).ready(function(){
		$.ajax({
			url: "/avg/sido",
			type: 'GET',
			success: function(response){
				console.log(response);
				let code = "<option value=\"\">도/광역시</option>";
                $.each(response,function (i, sido) {
                    // console.log(sido);
                     //console.log(${sido.sidoName})
                    // code+="${sido.sidoName}"
                   code += `<option value="\${sido.sidoCode}" id="sido-item" >\${sido.sidoName}</option>`;
                });
                $("#sido").empty().append(code);
			}
		})
        $(document).on('change','#sido',function(){
            let sidocode = $(this).val();
            $.ajax({
                //url: "${root}/api",
                url: "/avg/gugun?sido=" + sidocode,
                //data: {'act': 'gugun', "sidocode":sidocode},
                  type: 'GET',
                  success: function (response) {
                      console.log(response);
                    let code = "<option value=\"\">시/도</option>";
                    $.each(response,function (i, gugun) {
                      code += `<option value="\${gugun.gugunCode}" id="gugun-item" >\${gugun.gugunName}</option>`;
                    });
                    $("#gugun").empty().append(code);
                  }
				})
        })
        $(document).on('change','#gugun',function(){
            let guguncode = $(this).val();
            $.ajax({
                //url: "${root}/api",
                url: "/avg/apt?gugun=" + guguncode,
                //data: {'act': 'dong', "guguncode":guguncode},
                  type: 'GET',
                  success: function (response) {
                    console.log(response);
                    console.log()
                    let code = ``;
                    $.each(response,function (i, deal) {
                      code += ` 
                      <tr>
                      <td>\${i+1}</td>
                      			<td>\${deal.dongName}</td>
                    	  		<td>\${deal.dealAvg}</td>
                    	  		</tr>`;
                    });
                    $("#tb").empty().append(code);
                  }
            })
        })        
	})
</script>
<style type="text/css">
.body {
	margin-top: 100px;
}

select {
	background-color: orange;
	color: white;
	border: 0;
	border-radius: 70px;
	padding: 7px;
}

select option {
	background-color: white;
	color: black;
	font-weight: 400;
}
</style>
</head>
<body>
	<%@ include file="../template/header.jsp"%>
	<div class="container body">
		<div class="row col-lg-12 w-100">
			<!-- 왼쪽 메뉴 영역 -->
			<!--  			<div class="col-lg-3">
				<h4>거래 정보</h4>
				<hr>
				<div class="infobox" id="infobox"
					style="height: 680px; overflow: auto;"></div>
			</div> -->

			<!-- 중앙 컨텐츠 영역-->
			<div>
				<div class="row">
					<div class="mb-2 justify-content-center">
						<div class="d-flex w-100">
							<form action="initpage.do" method="post" id="rform"
								class="w-50 d-flex">
								<div class="row w-100 justify-content-between">
									<select id="sido" class="col-md-4 mr-1 mt-1">
										<option value="">도/광역시</option>
									</select> <select id="gugun" class="col-md-4 mr-1 mt-1">
										<option value="">구군</option>
									</select>
								</div>
							</form>
							<form id="sform" class="w-50 form-inline justify-content-end">
								<input class="form-control mr-2 w-70" id="apt" name="apt"
									type="text" /> <input
									class="form-contraol btn btn-warning w-30" type="button"
									value="검색" id="search" />
							</form>
						</div>
					</div>
				</div>
				<div id="avg" style="width: 100%; height: 720px; margin: auto; overflow:auto;"
					class="mb-3">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>순위</th>
								<th>동</th>
								<th>거래 평균 금액 (단위 : 백만)</th>
							</tr>
						</thead>
						<tbody id="tb">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../template/footer.jsp"%>
</body>
</html>