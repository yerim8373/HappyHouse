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
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=eba9b037cc90d73c5795649cf9bf7e16&libraries=services"></script>

<script>
    $(document).ready(function () {
    	
    	var container = document.getElementById('map'),
        options = {
             center: new kakao.maps.LatLng(33.450701, 126.570667),
             level: 5
        };
     
    	var map = new kakao.maps.Map(container, options);
    	var moveLatLon;
    	var marker;
    	var markers = [];
    	var infowindows = [];
        //test
        var sidocode = '1100000000';
        var guguncode = '1114000000';
        $.ajax({
            //url: "${root}/map/sido",
            url: "/map/sido",
            //data: {'act': 'sido'},
              type: 'GET',
              success: function (response) {
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
                url: "/map/gugun?sido=" + sidocode,
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
                url: "/map/dong?gugun=" + guguncode,
                //data: {'act': 'dong', "guguncode":guguncode},
                  type: 'GET',
                  success: function (response) {
                      console.log(response);
                    let code = "<option value=\"\">동</option>";
                    $.each(response,function (i, dong) {
                      code += `<option value="\${dong.dongCode}" id="dong-item" >\${dong.dongName}</option>`;
                    });
                    $("#dong").empty().append(code);
                  }
            })
        })
        $(document).on('change','#dong',function(){
            let dongcode = $(this).val();
            $.ajax({
                //url: "${root}/api",
                url: "/map/apt?dong=" + dongcode,
                //data: {'act': 'house', "dongcode":dongcode},
                  type: 'GET',
                  success: function (response) {
                    console.log(response);
                    let code =``;
                    setMarker(null);
                    markers = [];
                    console.log(markers);
                    $.each(response,function (i, house) {
                    	code += `
                            <h5>\${house.aptName}</h5><br>
                            <span>거래금액 : \${house.dealAmount}</span><br>
                            <span> 전용면적 : \${house.area} </span><br>
                            <span> 층 : \${house.floor} </span><br>
                            <hr>`;
                            
                        infowindow = new kakao.maps.InfoWindow({
                            content: `<div class="info-title"><span>\${house.aptName}</span>
                            <br>
                            <span>거래금액 : \${house.dealAmount}</span><br>
                            <span> 전용면적 : \${house.area} </span><br>
                            <span> 층 : \${house.floor} </span></div>` // 인포윈도우 내부에 들어갈 컨텐츠 입니다.
                        });
                            
                    	moveLatLon = new kakao.maps.LatLng(Number(`\${house.lat}`), Number(`\${house.lng}`));
                    	marker = new kakao.maps.Marker({map: map, position: moveLatLon, clickable: true});
                    	markers.push(marker);
                    	map.setCenter(moveLatLon);
                    	
                    	kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
                        kakao.maps.event.addListener(marker, 'click', function(){
                      		$.ajax({
                                url: "/map/aptCode?aptCode=" + house.aptCode,
                                //data: {'act': 'search2', "aptCode":`\${house.aptCode}`},
                                  type: 'GET',
                                  success: function (response) {
                                	  code =``;
                                    console.log(response);
                                    $.each(response,function (i, s) {
                                    	
                                    	code += `
                                            <h5>\${s.aptName}</h5><br>
                                            <span> 거래금액 : \${s.dealAmount} </span><br>
                                            <span> 전용면적 : \${s.area} </span><br>
                                            <span> 층수 : \${s.floor} </span><br>
                                            <hr>`;
                                    });
                                  }
                            });
                      		$("#infobox").empty().append(code);
                        });
                        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
                    });
                    setMarker(map);
                    $("#infobox").empty().append(code);
                  }
            })
        })
        $(document).on('click','#search',function(){
            let apt = $("#apt").val();
            $.ajax({
                //url: "${root}/api",
                //data: {'act': 'search', "apt":apt},
                url: "/map/aptSearch?aptName=" + apt,
                  type: 'GET',
                  success: function (response) {
                    console.log(response);
                    let code =``;
                    setMarker(null);
                    markers = [];
                    $.each(response,function (i, house) {
                    	code += `
                            <h5>\${house.aptName}</h5><br>
                            <span> 거래금액 : \${house.dealAmount} </span><br>
                            <span> 전용면적 : \${house.area} </span><br>
                            <span> 층수 : \${house.floor} </span><br>
                            <hr>`;
                    	

                            
                            infowindow = new kakao.maps.InfoWindow({
                                content: `<div class="info-title"><span>\${house.aptName}</span>
                                <br>
                                <span>거래금액 : \${house.dealAmount}</span><br>
                                <span> 전용면적 : \${house.area} </span><br>
                                <span> 층수 : \${house.floor} </span><br></div>` // 인포윈도우 내부에 들어갈 컨텐츠 입니다.
                            });
                            
                        moveLatLon = new kakao.maps.LatLng(Number(`\${house.lat}`), Number(`\${house.lng}`));
                    	marker = new kakao.maps.Marker({map: map, position: moveLatLon, clickable: true});
                    	
                    	kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
                        kakao.maps.event.addListener(marker, 'click', function(){
                      		$.ajax({
                                //url: "${root}/api",
                                //data: {'act': 'search2', "aptCode":`\${house.aptCode}`},
                                url: "/map/aptCode?aptCode=" + house.aptCode,
                                  type: 'GET',
                                  success: function (response) {
                                	  code =``;
                                    console.log(response);
                                    $.each(response,function (i, s) {
                                    	
                                    	code += `
                                            <h5>\${s.aptName}</h5><br>
                                            <span> 거래금액 : \${s.dealAmount} </span><br>
                                            <span> 전용면적 : \${s.area} </span><br>
                                            <span> 층수 : \${s.floor} </span><br>
                                            <hr>`;
                                    });
                                  }
                            });
                      		$("#infobox").empty().append(code);
                        });
                        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
                    	
                        
                    	markers.push(marker);
                    	map.setCenter(moveLatLon);
                        
                    });
                    setMarker(map);
                    $("#infobox").empty().append(code);
                  }
            })
        })
		
        function setMarker(map){
        	for (var i = 0; i < markers.length; i++) {
                markers[i].setMap(map);
                
                
            }
        }
        
        
        
        function makeOverListener(map, marker, infowindow) {
            return function () {
              infowindow.open(map, marker);
              var infoTitle = document.querySelectorAll('.info-title');
              infoTitle.forEach(function (e) {
                var w = e.offsetWidth + 10;
                var ml = w / 2;

                e.parentElement.previousSibling.style.display = "center";
                e.parentElement.parentElement.style.border = "0px";
                e.parentElement.parentElement.style.background = "unset";
              });
            };
          }
        
     // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
        function makeOutListener(infowindow) {
          return function () {
            infowindow.close();
          };
        }
        
    })
    
  </script>
<style type="text/css">
.body {
	margin-top: 100px;
	
}

select{
	background-color:orange;
	color:white;
	border:0;
	border-radius:70px;
	padding: 7px;
}

select option{
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
			<div class="col-lg-3">
				<h4>거래 정보</h4>
				<hr>
				<div class="infobox" id="infobox"
					style="height: 680px; overflow: auto;"></div>
			</div>

			<!-- 중앙 컨텐츠 영역-->
			<div class="col-lg-9">
				<!-- Kakao Map start -->
				<div class="row">
					<div class="mb-2 justify-content-center">
						<div class="d-flex w-100">
							<form action="initpage.do" method="post" id="rform" class="w-50 d-flex">
								<div class="row w-100 justify-content-between">
									<select id="sido" class="col-md-4 mr-1 mt-1">
										<option value="">도/광역시</option>
									</select> <select id="gugun" class="col-md-4 mr-1 mt-1">
										<option value="">구군</option>
									</select> <select id="dong" name="dong" class="col-md-3 mr-1 mt-1">
										<option value="">동</option>
									</select>
								</div>
							</form>
							<form id="sform"
								class="w-50 form-inline justify-content-end">
								<input class="form-control mr-2 w-70" id="apt" name="apt" type="text" /> 
								<input class="form-contraol btn btn-warning w-30" type="button"
									value="검색" id="search" />
							</form>
						</div>
					</div>
				</div>
				<div id="map" style="width: 100%; height: 720px; margin: auto"
					class="mb-3"></div>
				<!-- Kakao Map end -->
			</div>
		</div>

	</div>
	<%@ include file="../template/footer.jsp"%>

</body>

</html>
