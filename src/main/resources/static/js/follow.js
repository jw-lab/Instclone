// 팔로우,언팔로우 버튼클릭 후 버튼 디스플레이 로직
const followed = $("#followed").val();
if(followed !=undefined){
	if(followed == "unfollow"){
		document.querySelector(".unfollowBtn").classList.add("d-none");
	} else{
		document.querySelector(".followBtn").classList.add("d-none");
	}
}

//팔로우 추가
$(".followBtn").on("click",(e)=>{
	let followIdx = $("#memberIdx").val(); // post.html의 session.idx value이다
	let followedIdx = e.target.value;
	$.ajax({
		url:"/insta/follow",
		type:"post",
		data:{followIdx:followIdx,followedIdx:followedIdx},
		success:()=>{
			e.target.classList.add("d-none");
			let unfollowBtn = e.target.nextSibling.nextSibling;//형제 요소중 #text가 먼저 나옴
			unfollowBtn.classList.remove("d-none");
			unfollowBtn.classList.add("text-black");
		},
		error:()=>{
			alert("ajax 통신 실패");
		}
	});
})

//팔로우 해제
$(".unfollowBtn").on("click",(e)=>{
	let followIdx = $("#memberIdx").val(); // post.html의 session.idx value이다
	let followedIdx = e.target.value;
	$.ajax({
		url:"/insta/follow",
		type:"DELETE",
		data:{followIdx:followIdx,followedIdx:followedIdx},
		success:()=>{
			e.target.classList.add("d-none");
			let followBtn = e.target.previousSibling.previousSibling;//형제 요소중 #text가 먼저 나옴
			followBtn.classList.remove("d-none");
		},
		error:()=>{
			alert("ajax 통신 실패");
		}
	});
})

