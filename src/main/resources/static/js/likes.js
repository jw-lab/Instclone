// 내가 좋아요한 정보 가져오기 -ajax로 가져오기

$.ajax({
	url:"/insta/likes/"+memberIdx,
	type:"get",
	success:(data)=>{
		for(let i=0;i<data.length;i++){
			let boardIdx = data[i].boardIdx;
			let artId = "art"+ String(boardIdx);
			let art = document.getElementById(artId);
			if(art!=null){
				let likesBtn = art.querySelector('.likesBtn');
				likesBtn.classList.add("d-none");
				let likedBtn = art.querySelector('.likedBtn');
				likedBtn.classList.remove("d-none");
			}
		}	
	},
	error:()=>{
		alert("ajax 통신 실패");
	}
});

// 좋아요 추가 - ajax
$('.likesBtn').on("click",(e)=>{
	let memberIdx = $("#memberIdx").val(); //post.html의 session.idx이다
	let boardIdx_ = e.target.title;
	
	$.ajax({
		url:"/insta/likes",
		type:"post",
		data:{boardIdx:boardIdx_,memberIdx:memberIdx},
		success:(data)=>{
			// 내가 좋아요한 정보 가져오기
    		$.ajax({
    			url:"/insta/likes/"+memberIdx,
    			type:"get",
    			success:(data)=>{
    				for(let i=0;i<data.length;i++){
	    				let boardIdx = data[i].boardIdx;
	    				let artId = "art"+ String(boardIdx);
	    				let art = document.getElementById(artId);
	    				let likesBtn = art.querySelector('.likesBtn');
	    				let likedBtn = art.querySelector('.likedBtn');
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
$('.likedBtn').on("click",(e)=>{
	let memberIdx = $("#memberIdx").val(); //post.html의 session.idx이다
	let boardIdx_ = e.target.title;
	
	$.ajax({
		url:"/insta/likes",
		type:"DELETE",
		data:{boardIdx:boardIdx_,memberIdx:memberIdx},
		success:(data)=>{
			// 삭제된 경우 하트 아이콘 변경
			let artId = "art"+ String(boardIdx_);
			let art = document.getElementById(artId);
			let likesBtn = art.querySelector('.likesBtn');
			let likedBtn = art.querySelector('.likedBtn');
			likesBtn.classList.remove("d-none");
			likedBtn.classList.add("d-none");
			// 내가 좋아요한 정보 가져오기
    		$.ajax({
    			url:"/insta/likes/"+memberIdx,
    			type:"get",
    			success:(data)=>{
    				for(let i=0;i<data.length;i++){
	    				let boardIdx = data[i].boardIdx;
	    				let artId = "art"+ String(boardIdx);
	    				let art = document.getElementById(artId);
	    				let likesBtn = art.querySelector('.likesBtn');
	    				let likedBtn = art.querySelector('.likedBtn');
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