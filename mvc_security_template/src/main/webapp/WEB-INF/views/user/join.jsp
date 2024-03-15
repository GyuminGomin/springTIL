<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
	.maxWidth{
		max-width:600px;
	}
	
	span.text-danger{
		display:block;
		color:red;
		font-size: 10px;
		padding:5px;
	}
	
	.uploadImage {
		width: 100px;
		height: 100px;
		border-radius:50px;
		border:1px solid #ccc;
	}
	
	textarea{
		resize:none;
	}
	
</style>
<div class="container">
	<!-- ?${_csrf.parameterName}=${_csrf.token} -->
	<form id="joinForm" action="${path}/user/joinPost" method="post" enctype="multipart/form-data">
		<table class="container table table-bordered maxWidth">
			<tr>
				<th colspan="2" class="text-center"><h1>JOIN PAGE </h1></th>
			</tr>
			<tr>
				<td>프로필 이미지</td>
				<td class="text-center">
					<img src="${path}/resources/img/profile.jpg" id="viewImage" class="uploadImage" /> 
					<div class="row">
						<div class="col-md-6">
							<input type="file" id="profileImage" name="profileImage" accept="image/*" class="full-left"/>
						</div>
						<div class="col-md-6">
							<input type="button" id="removeProfile" value="삭제"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>아이디(email)</td>
				<td>
					<input type="text" class="form-control" name="u_id" id="u_id" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" class="form-control" name="u_pw" id="u_pw" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td>
					<input type="password" class="form-control" name="u_repw" id="u_repw"/>
				</td>
			</tr>
			<tr>
				<td>이름(2~6자이내)</td>
				<td>
					<input type="text" name="u_name" class="form-control" id="u_name"/>
				</td>
			</tr>
			<tr>
				<td>전화번호(-제외 숫자만)</td>
				<td>
					<input type="text" name="u_phone" class="form-control" id="u_phone"/>
				</td>
			</tr>
			<tr>
				<td>생년월일(ex-19820607)</td>
				<td>
					<input type="text" name="u_birth" class="form-control" id="u_birth" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<div class="row">
						<div class="col-md-8">
							<input type="text" class="form-control" name="u_post" id="u_post"/>
						</div>
						<div class="col-md-4">
							<input type="button" class="form-control btn btn-default" onclick="sample6_execDaumPostcode();" value="주소찾기"/>
						</div>
					</div>
					<br/>
					<input type="text" class="form-control" name="u_addr" id="u_addr"/>
					<br/>
					<input type="text" class="form-control" name="u_addr_detail" id="u_addr_detail"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<h4>이용약관</h4>
					<textarea class="form-control" readonly>당신의 개인정보는 언제든지 회사에서 사용할수 있으며... 10원에 팔아먹을수도 있어요... 그래도 이 사이트를 이용하시겠습니까??</textarea>
					<label>
						개인정보 이용동의
						<input type="checkbox" name="u_info" id="u_info" value="y"/>
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" id="joinBtn" class="form-control btn btn-primary" value="회원가입" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</div>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>

	var imgTemp = $("#viewImage").attr("src");
	
	$("#profileImage").on("change", function(){
		var files = $(this)[0].files[0];
		console.log(files.type);
		if (!files.type.startsWith('image/')) {
			alert('이미지를 선택해주세요.');
			removeImage();
		}else{
			var path = window.URL.createObjectURL(files);
			$("#viewImage").attr("src",path);	
		}
	});
	
	$("#removeProfile").click(function(){
		removeImage();
	});
	
	function removeImage(){
		$("#profileImage").val("");
		$("#viewImage").attr("src",imgTemp);
	}


	function sample6_execDaumPostcode(){
		new daum.Postcode({
			oncomplete : function(data){
				var fullAddr='';
				var extraAddr = '';
				if(data.userSelectedType === 'R'){ 
					fullAddr = data.roadAddress;
					if(data.bname !== ''){
						extraAddr += data.bname;
					}
					if(data.buildingName !== ''){
						extraAddr += (extraAddr !== '' ?','+data.buildingName : data.buildingName);
					}
					fullAddr += (extraAddr !== '' ? ' ('+extraAddr+')' : '');
				}else{
					fullAddr = data.jibunAddress;
				}
				$("#u_post").val(data.zonecode);
				$("#u_addr").val(fullAddr);
				$("#u_addr_detail").focus();
			}
		}).open();
	}
	
	$("#u_birth").datepicker({
		  howOn: "both", // 버튼과 텍스트 필드 모두 캘린더를 보여준다.
		  //buttonImage: "/application/db/jquery/images/calendar.gif", // 버튼 이미지
		  //buttonImageOnly: true, // 버튼에 있는 이미지만 표시한다.
		  changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
		  changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
		  minDate: '-100y', // 현재날짜로부터 100년이전까지 년을 표시한다.
		  nextText: '다음 달', // next 아이콘의 툴팁.
		  prevText: '이전 달', // prev 아이콘의 툴팁.
		  numberOfMonths: [1,1], // 한번에 얼마나 많은 월을 표시할것인가. [2,3] 일 경우, 2(행) x 3(열) = 6개의 월을 표시한다.
		  stepMonths: 3, // next, prev 버튼을 클릭했을때 얼마나 많은 월을 이동하여 표시하는가.
		  // 3개월씩 이동
		  yearRange: 'c-100:c+0', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가.
		 // yearRange: "2010:2013",  //연도 범위
		  showButtonPanel: true, // 캘린더 하단에 버튼 패널을 표시한다. 
		  currentText: '오늘 날짜' , // 오늘 날짜로 이동하는 버튼 패널
		  closeText: '닫기',  // 닫기 버튼 패널
		  dateFormat: "yymmdd", // 텍스트 필드에 입력되는 날짜 형식.
		  // yy : 2022, y : 22
		  // mm : short 1~12 
		  // MM : 1월~8월
		  // DD : 월요일 ~일요일
		  // dd : 1~31
		  showAnim: "show", //애니메이션을 적용한다. // show(디폴트), slide, fade
		  duration: "slow",	// 애니메이션 시간 slow , normal , fast
		  showMonthAfterYear: true , // 월, 년순의 셀렉트 박스를 년,월 순으로 바꿔준다. 
		  dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], // 요일의 한글 형식.
		  dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
	      monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'], // 월의 한글 형식.
          monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
          yearSuffix: "년" 	
	});
	
	// 정규표현식을 통한 검증 추가
	$.validator.addMethod("regex",function(value,element,regexpr){
		return regexpr.test(value);
	});
	
	var regexPass = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;	
	var regexMobile = /^[0-9]{2,3}?[0-9]{3,4}?[0-9]{4}$/;
	
	$("#joinForm").validate({
		// 포커스 out시 유효성 검사
		onfocusout: function(element) {
            this.element(element);
        },
		rules : {
			u_id : {
				required : true,
				email : true,
				remote : {
					type : "POST",
					url : "${path}/user/uidCheck"
					/*,
					data : {
						'${_csrf.parameterName}':'${_csrf.token}'
					}
					*/
				}
			},
			u_pw : {
				required : true, 
				minlength : 8,
				maxlength : 16,
				regex : regexPass
			},
			u_repw : {
				required : true, 
				minlength : 8,
				maxlength : 16,
				equalTo : "#u_pw"
			},
			u_name : {
				required : true,
				rangelength : [2,6]
			},
			u_phone : {
				required : true,
				regex : regexMobile
			},
			u_birth : {
				required : true
			},
			u_post : {
				required : true
			},
			u_addr : {
				required : true
			},
			u_addr_detail : {
				required : true
			},
			u_info : {
				required : true
			}
		},
		messages : {
			u_id : {
				required : "이메일(아이디)를 작성해주세요.",
				email : "올바른 이메일 형식이 아닙니다.",
				remote : "이미 존재하는 ID입니다."
			},
			u_pw : {
				required : "비밀번호를 작성해주세요.",
				minlength : "비밀번호는 최소 8글자 이상입니다.",
				maxlength : "비밀번호는 최대 16글자만 가능합니다.",
				regex : "비밀번호는 특수문자와 숫자를 포함해야합니다."
			},
			u_repw : {
				required : "비밀번호를 작성해주세요.",
				minlength : "비밀번호는 최소 8글자 이상입니다.",
				maxlength : "비밀번호는 최대 16글자만 가능합니다.",
				equalTo : "비밀번호가 일치하지 않습니다."
			},
			u_name : {
				required : "이름을 작성해 주세요.",
				rangelength : $.validator.format(
					"문자 길이가 {0}에서 {1}사이의 값을 입력하세요."
				)
			},
			u_phone : {
				required : "전화번호를 입력해주세요.",
				regex : "올바른 전화번호 형식이 아닙니다."
			},
			u_birth : {
				required : "생년월일을 작성해주세요."
			},
			u_post : {
				required : "우편번호를 확인해 주세요."
			},
			u_addr : {
				required : "주소를 확인해 주세요."
			},
			u_addr_detail : {
				required : "상세주소를 입력해주세요."
			},
			u_info : {
				required : "개인정보이용을 동의해 주세요."
			}
		},
		errorElement : "span",
		errorClass : "text-danger"
	});
	
	$.validator.setDefaults({
		submitHandler : function(){
			$("#joinForm").submit();
		}
	});
	
	// 모든 ajax 통신에 csrf 토큰 전달.
	$(document).ajaxSend(function(e,xhr,options){
		xhr.setRequestHeader(
				'${_csrf.headerName}',
				'${_csrf.token}');
	});
	
</script>
</body>
</html>











