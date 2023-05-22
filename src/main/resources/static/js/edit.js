const name= $("#memberName").val();
	//프로필 사진 변경
	$("#profile-img").on("change",()=>{
		const frm = $("#frm")[0];
		frm.method = "post";
		frm.action = "/insta/"+name;
		frm.submit();
	})
	
	// 회원 정보 수정 - 사용자 이름, 이메일 중복 체크, 본인 데이터는 제외
	const idx = $("#idx").val();
	$("#btn-edit").on("click",()=>{
		const name = $("#name").val();
		const email = $("#email").val();
		const datas ={idx:idx,name:name,email:email};
		$.ajax({
			url:"/insta/edit/"+idx,
			type:"post",
			data:datas,
			success:(data)=>{
				const frm2 = document.forms["frm2"];
		    	if(data == "checked"){
		    		$("#method").val("PUT");
   		    		frm2.action="/insta/edit/"+idx;
   		    		frm2.submit();
		    	} else if(data == "existName"){
		    		alert("사용자 이름이 중복됩니다");
		    	} else{
		    		alert("이메일이 중복됩니다");
		    	}
			},
			error:()=>{
				alert("ajax 통신 실패");
			}
		});
	});
	
	//회원 탈퇴 - 간소화
	$("#btn-del").on("click",()=>{
		const frm2 = document.forms["frm2"];
		$("#method").val("DELETE");
		frm2.action="/insta/edit/"+idx;
		frm2.submit();
	});