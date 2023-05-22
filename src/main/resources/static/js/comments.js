// 댓글 게시 - 메인페이지
$(".comments-submit").on("click",(e)=>{
	let boardIdx = e.target.value;
	let frmName = "frm"+String(boardIdx);
	let frm = document.forms[frmName];
	
	//접근한 form 태그안에 있는 input값들 가져오기 - name 속성으로 가져온다
	let memberIdx= frm.elements["memberIdx"].value;
	let commentsText = frm.elements["commentsText"].value;
	$.ajax({
		url:"/insta/comments",
		type:"POST",
		data:{boardIdx:boardIdx,memberIdx:memberIdx,commentsText:commentsText},
		success:(data)=>{
			frm.elements["commentsText"].value = null;
		},
		error:()=>{
			alert("ajax 통신 실패");
		}
	})
})

// 댓글 게시 - detail 모달창
$(".comments-detail-submit").on("click",(e)=>{
	let boardIdx = e.target.value;
	let frmName = "detailFrm"+String(boardIdx);
	let frm = document.forms[frmName];
	
	//접근한 form 태그안에 있는 input값들 가져오기 - name 속성으로 가져온다
	let memberIdx= frm.elements["memberIdx"].value;
	let commentsText = frm.elements["commentsText"].value;
	$.ajax({
		url:"/insta/comments",
		type:"POST",
		data:{boardIdx:boardIdx,memberIdx:memberIdx,commentsText:commentsText},
		success:(data)=>{
			frm.elements["commentsText"].value = null;
			$.ajax({
    				url:"/insta/comments/"+boardIdx,
    				type:"get",
    				success:(data)=>{
    					let detailIdx = "detail"+String(boardIdx);
    					let detailModal = document.getElementById(detailIdx);
    					let commentsList = detailModal.querySelector(".commentsList");
    					// 댓글 div의 모든 자식노드 삭제
    					while( commentsList.hasChildNodes() ){
    						commentsList.removeChild(commentsList.firstChild);
    					}
    					//댓글 내용을 노드에 추가
    					for(let i=0;i<data.length;i++){
    						let result;
    						// 프로필 사진 유무 체크
    						if(data[i].profile != null){
		    					result = "<div class='p-3 d-flex'><div><img class='me-3' src="+'/'+data[i].profile.storedFilePath+" style='width: 28px;height: 28px; border-radius: 50%'></div>";
		    					result += "<div><a href='/insta/"+data[i].name+"' class='fw-bold me-2'>"+data[i].name+"</a>";
		    					result += "<span>"+data[i].commentsText+"</span>";
		    					result += "<div class='my-2 text-muted'>"+data[i].createdDt+"</div></div>";
    						} else{
    							result = "<div class='p-3 d-flex'><div><img class='me-3' src='/img/profile.jpg' style='width: 28px;height: 28px; border-radius: 50%'></div>";
    							result += "<div><a href='/insta/"+data[i].name+"' class='fw-bold me-2'>"+data[i].name+"</a>";
		    					result += "<span>"+data[i].commentsText+"</span>";
		    					result += "<div class='my-2 text-muted'>"+data[i].createdDt+"</div></div>";
    						}
	    					commentsList.innerHTML += result;
    					}
    				},
    				error:()=>{
    					alert("ajax 통신 실패");
    				}
    			});
		},
		error:()=>{
			alert("ajax 통신 실패");
		}
	})
})

//게시물 상세보기 클릭시 ajax로 댓글 보기 - main 페이지
$(".comment-btn").on("click",(e)=>{
	let boardIdx = e.target.value;
	$.ajax({
		url:"/insta/comments/"+boardIdx,
		type:"get",
		success:(data)=>{
			let detailIdx = "detail"+String(boardIdx);
			let detailModal = document.getElementById(detailIdx);
			let commentsList = detailModal.querySelector(".commentsList");
			// 댓글 div의 모든 자식노드 삭제
			while( commentsList.hasChildNodes() ){
				commentsList.removeChild(commentsList.firstChild);
			}
			//댓글 내용을 노드에 추가
			for(let i=0;i<data.length;i++){
				let result;
				if(data[i].profile != null){
					result = "<div class='p-3 d-flex'><div><img class='me-3' src="+'/'+data[i].profile.storedFilePath+" style='width: 28px;height: 28px; border-radius: 50%'></div>";
					result += "<div><a href='/insta/"+data[i].name+"' class='fw-bold me-2'>"+data[i].name+"</a>";
					result += "<span>"+data[i].commentsText+"</span>";
					result += "<div class='my-2 text-muted'>"+data[i].createdDt+"</div></div>";
				} else{
					result = "<div class='p-3 d-flex'><div><img class='me-3' src='/img/profile.jpg' style='width: 28px;height: 28px; border-radius: 50%'></div>";
					result += "<div><a href='/insta/"+data[i].name+"' class='fw-bold me-2'>"+data[i].name+"</a>";
					result += "<span>"+data[i].commentsText+"</span>";
					result += "<div class='my-2 text-muted'>"+data[i].createdDt+"</div></div>";
				}
				commentsList.innerHTML += result;
			}
		},
		error:()=>{
			alert("ajax 통신 실패");
		}
	});
})

//게시물 상세보기 클릭시 ajax로 댓글 보기 - profile, explore 페이지
$(".darkness").on("click",(e)=>{
	let boardIdx = e.target.title;
	$.ajax({
		url:"/insta/comments/"+boardIdx,
		type:"get",
		success:(data)=>{
			let detailIdx = "detail"+String(boardIdx);
			let detailModal = document.getElementById(detailIdx);
			let commentsList = detailModal.querySelector(".commentsList");
			// 댓글 div의 모든 자식노드 삭제
			while( commentsList.hasChildNodes() ){
				commentsList.removeChild(commentsList.firstChild);
			}
			//댓글 내용을 노드에 추가
			for(let i=0;i<data.length;i++){
				let result;
				if(data[i].profile != null){
					result = "<div class='p-3 d-flex'><div><img class='me-3' src="+'/'+data[i].profile.storedFilePath+" style='width: 28px;height: 28px; border-radius: 50%'></div>";
					result += "<div><a href='/insta/"+data[i].name+"' class='fw-bold me-2'>"+data[i].name+"</a>";
					result += "<span>"+data[i].commentsText+"</span>";
					result += "<div class='my-2 text-muted'>"+data[i].createdDt+"</div></div>";
				} else{
					result = "<div class='p-3 d-flex'><div><img class='me-3' src='/img/profile.jpg' style='width: 28px;height: 28px; border-radius: 50%'></div>";
					result += "<div><a href='/insta/"+data[i].name+"' class='fw-bold me-2'>"+data[i].name+"</a>";
					result += "<span>"+data[i].commentsText+"</span>";
					result += "<div class='my-2 text-muted'>"+data[i].createdDt+"</div></div>";
				}
				commentsList.innerHTML += result;
			}
		},
		error:()=>{
			alert("ajax 통신 실패");
		}
	});
})