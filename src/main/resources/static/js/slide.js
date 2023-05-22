// 이미지 슬라이드
const articles = document.querySelectorAll("article");
let slideIndex = 1;
showDivs();

	$(".leftBtn").on("click",(e)=>{
		slideIndex = slideIndex-1;
		let artIdx = e.target.value;
		let artId ="art"+String(artIdx);
		let art = document.getElementById(artId);
		let x = art.querySelectorAll(".myImgs");
		if (slideIndex > x.length) {slideIndex = 1}    
		if (slideIndex < 1) {slideIndex = x.length} ;
		for (let j = 0; j < x.length; j++) {
		    x[j].style.display = "none";  
		}
		x[slideIndex-1].style.display = "block";
	});
	$(".rightBtn").on("click",(e)=>{
	    slideIndex = slideIndex+1;
		let artIdx = e.target.value;
		let artId ="art"+String(artIdx);
		let art = document.getElementById(artId);
		let x = art.querySelectorAll(".myImgs");
		if (slideIndex > x.length) {slideIndex = 1}    
		if (slideIndex < 1) {slideIndex = x.length} ;
		for (let j = 0; j < x.length; j++) {
		    x[j].style.display = "none";  
		}
		x[slideIndex-1].style.display = "block";
	});


function showDivs() {
  for(let i=0;i<articles.length;i++){
  	  let article_=articles[i];
	  let x = article_.querySelectorAll(".myImgs");  
	  for (let j = 0; j < x.length; j++) {
	     x[j].style.display = "none";  
	  }
	  x[slideIndex-1].style.display = "block";  
  }
}

// 이미지 슬라이드 - detail 모달
const details = document.querySelectorAll(".detail");
let detailSlideIndex = 1;
showDetailDivs();

	$(".leftDetailBtn").on("click",(e)=>{
		detailSlideIndex = detailSlideIndex-1;
		let detailIdx = e.target.value;
		let detailId ="detail"+String(detailIdx);
		let detail = document.getElementById(detailId);
		let x = detail.querySelectorAll(".myImgs");
		if (detailSlideIndex > x.length) {detailSlideIndex = 1}    
		if (detailSlideIndex < 1) {detailSlideIndex = x.length} ;
		for (let j = 0; j < x.length; j++) {
		    x[j].style.display = "none";  
		}
		x[detailSlideIndex-1].style.display = "block";
	});
	$(".rightDetailBtn").on("click",(e)=>{
	    detailSlideIndex = detailSlideIndex+1;
		let detailIdx = e.target.value;
		let detailId ="detail"+String(detailIdx);
		let detail = document.getElementById(detailId);
		let x = detail.querySelectorAll(".myImgs");
		if (detailSlideIndex > x.length) {detailSlideIndex = 1}    
		if (detailSlideIndex < 1) {detailSlideIndex = x.length} ;
		for (let j = 0; j < x.length; j++) {
		    x[j].style.display = "none";  
		}
		x[detailSlideIndex-1].style.display = "block";
	});


function showDetailDivs() {
  for(let i=0;i<details.length;i++){
  	  let detail_=details[i];
	  let x = detail_.querySelectorAll(".myImgs");
	  for (let j = 0; j < x.length; j++) {
	     x[j].style.display = "none";  
	  }
	  x[detailSlideIndex-1].style.display = "block";  
  }
}