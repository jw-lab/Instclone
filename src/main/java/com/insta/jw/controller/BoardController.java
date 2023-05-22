package com.insta.jw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.insta.jw.dto.BoardDto;
import com.insta.jw.dto.CommentsDto;
import com.insta.jw.dto.FileDto;
import com.insta.jw.dto.LikesDto;
import com.insta.jw.dto.MemberDto;
import com.insta.jw.dto.ProfileDto;
import com.insta.jw.service.BoardService;
import com.insta.jw.service.MemberService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/")
	public String index() throws Exception{
		return "redirect:/insta/login";
	}
	
//	메인페이지 
	@RequestMapping(value = "/insta", method = RequestMethod.GET)
	public ModelAndView instaMain(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("/board/main");
		
//		이메일 세션 정보로 본인 계정의 정보 가져오기
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		MemberDto member = memberService.getMember(email);
		
//		프로필 사진 유무 체크
		int count = memberService.profileCheck(member.getName());
		if(count!=0) { //프로필 사진 가져오기
			ProfileDto profileDto = memberService.getMemberProfile(member.getName());
			mv.addObject("profile", profileDto);
		}
		
		mv.addObject("member", member);
		
//		모든 회원정보 가져오기
		List<MemberDto> memberList = memberService.getMemberList();
		
//		회원들 프로필 사진 체크 및 가져오기
		for(MemberDto members : memberList) {
			count = memberService.profileCheck(members.getName());
			if(count!=0) { //프로필 사진 가져오기
				ProfileDto profileDto = memberService.getMemberProfile(members.getName());
				members.setProfile(profileDto);
			}
		}
		
		mv.addObject("memberList", memberList);
		
//		회원정보 목록 가져오기(구독 안한 사용자만 가져오기)
		List<MemberDto> unfollowList = memberService.getUnfollowList(member.getIdx());
		
//		회원들 프로필 사진 체크 및 가져오기
		for(MemberDto members : unfollowList) {
			count = memberService.profileCheck(members.getName());
			if(count!=0) { //프로필 사진 가져오기
				ProfileDto profileDto = memberService.getMemberProfile(members.getName());
				members.setProfile(profileDto);
			}
		}
		
		mv.addObject("unfollowList", unfollowList);
		
//		회원정보 목록 가져오기(구독한 사용자만 가져오기)
		List<MemberDto> followList = memberService.getFollowList(member.getIdx());
		
//		회원들 프로필 사진 체크 및 가져오기
		for(MemberDto members : followList) {
			count = memberService.profileCheck(members.getName());
			if(count!=0) { //프로필 사진 가져오기
				ProfileDto profileDto = memberService.getMemberProfile(members.getName());
				members.setProfile(profileDto);
			}
		}
		
		mv.addObject("followList", followList);
		
//		게시물 목록 가져오기(구독한 것만)
		List<BoardDto> boardList = boardService.selectBoard(member.getIdx());
		
//		각 게시물에 해당하는 파일리스트를 가져오기
		for(BoardDto boardDto : boardList) {
			List<FileDto> fileList = boardService.getFileList(boardDto.getIdx());
			boardDto.setFileList(fileList);
//			댓글 수 카운트
			int commentsCount = boardService.getCommentsCount(boardDto.getIdx());
			boardDto.setCommentsCount(commentsCount);
//			좋아요 수 카운트
			int likesCount = boardService.getLikesCount(boardDto.getIdx());
			boardDto.setLikesCount(likesCount);
		}

		mv.addObject("boardList", boardList);

		return mv;
	}
	
//	각 계정 개인페이지로 이동
	@RequestMapping(value = "/insta/{name}", method = RequestMethod.GET)
	public ModelAndView instaProfile(@PathVariable("name") String name,HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("/board/profile");
		
//		사용자 이름으로 접근 
		MemberDto member = memberService.getMemberByName(name);
		
//		프로필 사진 유무 체크
		int count = memberService.profileCheck(name);
		if(count!=0) { //프로필 사진 가져오기
			ProfileDto profileDto = memberService.getMemberProfile(name);
			mv.addObject("profile", profileDto);
		}
		
		mv.addObject("member", member);
		
//		모든 회원정보 가져오기
		List<MemberDto> memberList = memberService.getMemberList();
		
//		회원들 프로필 사진 체크 및 가져오기
		for(MemberDto members : memberList) {
			count = memberService.profileCheck(members.getName());
			if(count!=0) { //프로필 사진 가져오기
				ProfileDto profileDto = memberService.getMemberProfile(members.getName());
				members.setProfile(profileDto);
			}
		}
		
		mv.addObject("memberList", memberList);
		
//		다른 사용자의 구독한 사용자 정보 가져오기
		List<MemberDto> userFollowList = memberService.getFollowList(member.getIdx());
		
		mv.addObject("userFollowList", userFollowList);
		
//		다른 계정을 팔로우 하는 사람 숫자(팔로워 숫자)
		int userFollower = memberService.countFollower(member.getIdx());
		mv.addObject("userFollower", userFollower);
//		이메일 세션 정보로 본인 계정의 정보 가져오기
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		MemberDto myMember = memberService.getMember(email);
		
//		회원정보 목록 가져오기(본인이 구독 안한 사용자만 가져오기)
		List<MemberDto> unfollowList = memberService.getUnfollowList(myMember.getIdx());
		
		mv.addObject("unfollowList", unfollowList);
		
//		본인이 구독한 사용자만 가져오기
		List<MemberDto> followList = memberService.getFollowList(myMember.getIdx());
		
		mv.addObject("followList", followList);
		
//		나를 팔로우 하는 사람 숫자(팔로워 숫자)
		int myFollower = memberService.countFollower(myMember.getIdx());
		mv.addObject("myFollower", myFollower);
		
//		개인페이지 게시물목록
		List<BoardDto> boardList = boardService.selectMyBoard(member.getIdx());
		
//		각 게시물에 해당하는 파일리스트를 가져오기
		for(BoardDto boardDto : boardList) {
			List<FileDto> fileList = boardService.getFileList(boardDto.getIdx());
			boardDto.setFileList(fileList);
//			좋아요 수 카운트
			int likesCount = boardService.getLikesCount(boardDto.getIdx());
			boardDto.setLikesCount(likesCount);
		}
		mv.addObject("boardList", boardList);
		return mv;
	}
	
//	개인 페이지 프로필 사진 등록
	@RequestMapping(value = "/insta/{name}", method = RequestMethod.POST)
	public String instaProfileUpload(@PathVariable("name") String name,MultipartHttpServletRequest multiFiles) throws Exception{
		MemberDto member = memberService.getMemberByName(name);
		memberService.profileUpload(member.getIdx(),multiFiles);
		return "redirect:/insta/{name}";

	}
	
//	게시물 등록 - 모달 프로세스
	@RequestMapping(value = "/insta", method = RequestMethod.POST)
	public String instaPost(BoardDto board, MultipartHttpServletRequest multiFiles) throws Exception{
		boardService.insertBoard(board,multiFiles);
		return "redirect:/insta";
	}
	
//	게시물 삭제
	@ResponseBody
	@RequestMapping(value = "/insta/{idx}", method = RequestMethod.DELETE)
	public void instaBoardDelete(@PathVariable("idx") int idx) throws Exception{
		boardService.deleteBoard(idx);
	}
	
//	게시물 탐색
	@RequestMapping(value = "/insta/explore", method = RequestMethod.GET)
	public ModelAndView instaExplore() throws Exception{
		ModelAndView mv = new ModelAndView("/board/explore");
		
//		모든 게시물 가져오기
		List<BoardDto> boardAllList = boardService.selectAllBoard();
				
//		각 게시물에 해당하는 파일리스트를 가져오기
		for(BoardDto boardDto : boardAllList) {
			List<FileDto> fileList = boardService.getFileList(boardDto.getIdx());
			boardDto.setFileList(fileList);
//			좋아요 수 카운트
			int likesCount = boardService.getLikesCount(boardDto.getIdx());
			boardDto.setLikesCount(likesCount);
		}
		
		mv.addObject("boardList", boardAllList);
		
//		모든 회원정보 가져오기
		List<MemberDto> memberList = memberService.getMemberList();
		
//		회원들 프로필 사진 체크 및 가져오기
		for(MemberDto members : memberList) {
			int count = memberService.profileCheck(members.getName());
			if(count!=0) { //프로필 사진 가져오기
				ProfileDto profileDto = memberService.getMemberProfile(members.getName());
				members.setProfile(profileDto);
			}
		}
		
		mv.addObject("memberList", memberList);
		
		return mv;
	}
	
//	댓글 등록
	@ResponseBody
	@RequestMapping(value = "/insta/comments", method = RequestMethod.POST)
	public void instaComments(CommentsDto comments) throws Exception{
		//댓글 저장
		boardService.insertComments(comments);
	}
	
//	댓글 조회
	@ResponseBody
	@RequestMapping(value = "/insta/comments/{idx}", method = RequestMethod.GET)
	public List<CommentsDto> instaGetComments(@PathVariable("idx") int boardIdx) throws Exception{
//		각 게시물에 있는 댓글 조회
		List<CommentsDto> commentsList = boardService.getCommentsList(boardIdx);
		
		for(CommentsDto commentsDto : commentsList) {
			int count = memberService.profileCheck(commentsDto.getName());
			if(count!=0) { //프로필 사진 가져오기
				ProfileDto profileDto = memberService.getMemberProfile(commentsDto.getName());
				commentsDto.setProfile(profileDto);
			}
		}
		return commentsList;
	}
	
//	좋아요 추가
	@ResponseBody
	@RequestMapping(value = "/insta/likes", method = RequestMethod.POST)
	public void instaLikes(@RequestParam("boardIdx") int boardIdx,@RequestParam("memberIdx") int memberIdx) throws Exception{
		boardService.insertLikes(boardIdx,memberIdx);
	}

//	본인 계정으로 좋아요한 경우 가져오기
	@ResponseBody
	@RequestMapping(value = "/insta/likes/{memberIdx}", method = RequestMethod.GET)
	public Object instaGetLikes(@PathVariable("memberIdx") int memberIdx) throws Exception{
//		내가 좋아요한 memberIdx 추가
		List<LikesDto> likeIdxs= boardService.selectLikes(memberIdx);
		return likeIdxs;
	}
	
//	좋아요 취소
	@ResponseBody
	@RequestMapping(value = "/insta/likes", method = RequestMethod.DELETE)
	public void instaDeleteLikes(@RequestParam("boardIdx") int boardIdx,@RequestParam("memberIdx") int memberIdx) throws Exception{
		boardService.deleteLikes(boardIdx,memberIdx);
	}

}
