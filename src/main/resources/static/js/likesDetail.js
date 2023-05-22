// ********detail 모달창*********//
// 내가 좋아요한 정보 가져오기 -ajax로 가져오기
let memberIdx = $("#memberIdx").val(); //post.html의 session.idx이다
$.ajax({
	url:"/insta/likes/"+memberIdx,
	type:"get",
	success:(data)=>{
		for(let i=0;i<data.length;i++){
			let boardIdx = data[i].boardIdx;
			let detailIdx = "detail"+ String(boardIdx);
			let detailModal = document.getElementById(detailIdx);
			if(detailModal!=null){
				let likesBtn = detailModal.querySelector('.detailLikesBtn');
				let likedBtn = detailModal.querySelector('.detailLikedBtn');
				likesBtn.classList.add("d-none");
				likedBtn.classList.remove("d-none");
			}
		}	
	},
	error:()=>{
		alert("ajax 통신 실패");
	}
});

// 좋아요 추가 - ajax
$('.detailLikesBtn').on("click",(e)=>{
	let memberIdx = $("#memberIdx").val(); //post.html의 session.idx이다
	let boardIdx_ = e.target.title;
	
	$.ajax({
		url:"/insta/likes",
		type:"post",
		data:{boardIdx:boardIdx_,memberIdx:memberIdx},
		success:(data)=>{
			// 내가 좋아요한 정보 가져오기 - ajax , detail 모달
			$.ajax({
				url:"/insta/likes/"+memberIdx,
				type:"get",
				success:(data)=>{
					for(let i=0;i<data.length;i++){
						let boardIdx = data[i].boardIdx;
						let detailIdx = "detail"+ String(boardIdx);
						let detailModal = document.getElementById(detailIdx);
						let likesBtn = detailModal.querySelector('.detailLikesBtn');
						let likedBtn = detailModal.querySelector('.detailLikedBtn');
						likesBtn.classList.add("d-none");
						likedBtn.classList.remove("d-none");
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
	});
})

// 좋아요 취소 -ajax
$('.detailLikedBtn').on("click",(e)=>{
	let memberIdx = $("#memberIdx").val(); //post.html의 session.idx이다
	let boardIdx_ = e.target.title;
	
	$.ajax({
		url:"/insta/likes",
		type:"DELETE",
		data:{boardIdx:boardIdx_,memberIdx:memberIdx},
		success:(data)=>{
			// 삭제된 경우 하트 아이콘 변경
			let detailIdx = "detail"+ String(boardIdx_);
			let detailModal = document.getElementById(detailIdx);
			let likesBtn = detailModal.querySelector('.detailLikesBtn');
			let likedBtn = detailModal.querySelector('.detailLikedBtn');
			likesBtn.classList.remove("d-none");
			likedBtn.classList.add("d-none");
			// 내가 좋아요한 정보 가져오기 - ajax , detail 모달
			$.ajax({
				url:"/insta/likes/"+memberIdx,
				type:"get",
				success:(data)=>{
					for(let i=0;i<data.length;i++){
						let boardIdx = data[i].boardIdx;
						let detailIdx = "detail"+ String(boardIdx);
						let detailModal = document.getElementById(detailIdx);
						let likesBtn = detailModal.querySelector('.detailLikesBtn');
						let likedBtn = detailModal.querySelector('.detailLikedBtn');
						likesBtn.classList.add("d-none");
						likedBtn.classList.remove("d-none");
					}
    			},
    			error:()=>{
    				alert("ajax 통신 실패-get");
    			}
    		});
		},
		error:()=>{
			alert("ajax 통신 실패-delete");
		}
	});
})