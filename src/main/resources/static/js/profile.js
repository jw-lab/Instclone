const name= $("#memberName").val();
	//프로필 사진 변경
	$("#profile-img").on("change",()=>{
		const frm = $("#frm")[0];
		frm.method = "post";
		frm.action = "/insta/"+name;
		frm.submit();
	})
	
	//프로필 편집 페이지로 이동
	$(".btn-edit").on("click",()=>{
		location.href="/insta/edit/"+$(".btn-edit").val();
	});