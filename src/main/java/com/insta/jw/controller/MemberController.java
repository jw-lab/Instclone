package com.insta.jw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insta.jw.dto.MemberDto;
import com.insta.jw.dto.ProfileDto;
import com.insta.jw.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
//	로그인 페이지로 이동
	@RequestMapping(value = "/insta/login", method = RequestMethod.GET)
	public String instaLogin() throws Exception{
		return "/accounts/login";
	}
	
	
//	로그인 form email, pwd 체크
	@RequestMapping(value = "/insta/login", method = RequestMethod.POST)
	public String instaLoginProcess(MemberDto member, HttpServletRequest request,Model model) throws Exception{
		int count1 = memberService.emailCheck(member.getEmail()); // 이메일 확인
		int count2 = memberService.loginCheck(member.getEmail(),member.getPwd());// 비밀번호도 확인
		if(count1 == 1 && count2 == 1) { // 로그인 성공
			HttpSession session = request.getSession();
			MemberDto member_ = memberService.getMember(member.getEmail());
			session.setAttribute("idx", member_.getIdx());
			session.setAttribute("email", member_.getEmail());
			session.setAttribute("name", member_.getName());
//			본인이 구독한 사용자만 가져오기
			List<MemberDto> followList = memberService.getFollowList(member_.getIdx());
			if(followList.size()>1)
				return "redirect:/insta";
			else {
				return "redirect:/insta/explore";
			}
		}else if(count1 == 1 && count2 !=1){//이메일은 있지만 비밀번호는 틀린 경우
			model.addAttribute("message", "잘못된 비밀번호입니다. 다시 확인하세요.");
			return "/accounts/login";
		} else { // 이메일이 없는 경우
			model.addAttribute("message", "입력한 사용자 이름을 사용하는 계정을 찾을 수 없습니다. 사용자 이름을 확인하고 다시 시도하세요.");
			return "/accounts/login";
		}
	}
	
//	로그아웃
	@RequestMapping(value = "/insta/logout", method = RequestMethod.GET)
	public String instaLogout(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.removeAttribute("email");
		session.invalidate();
		return "redirect:/insta/login";
	}
	
//	회원가입 페이지 이동
	@RequestMapping(value = "/insta/signup", method = RequestMethod.GET)
	public String instaSignup() throws Exception{
		return "/accounts/signup";
	}
	
//	회원가입 form 처리 - email, name 중복 체크
	@RequestMapping(value = "/insta/signup", method = RequestMethod.POST)
	public String instaSignupProcess(MemberDto member,Model model) throws Exception{
		int count1 = memberService.emailCheck(member.getEmail()); // 이메일 확인
		int count2 = memberService.nameCheck(member.getName()); // 사용자 이름 확인
		if(count1 !=1 && count2 != 1) {
			memberService.signup(member);
			return "redirect:/insta";
		} else if(count1 == 1){// 이메일이 중복되는 경우
			model.addAttribute("message", "이메일이 중복됩니다. 다시 시도하세요.");
			return "/accounts/signup";
		} else { // 사용자 이름이 중복되는 경우
			model.addAttribute("message", "사용자 이름이 중복됩니다. 다시 시도하세요.");
			return "/accounts/signup";
		}
	}
	
//	회원 프로필 편집 페이지 이동
	@RequestMapping(value = "/insta/edit/{idx}", method = RequestMethod.GET)
	public ModelAndView instaEditMember(@PathVariable("idx") int idx) throws Exception{
		ModelAndView mv = new ModelAndView("/board/edit");
		
//		사용자 idx로 member 가져오기	
		MemberDto member = memberService.getMemberByIdx(idx);
		
		String name = member.getName();
		
//		프로필 사진 유무 체크
		int count = memberService.profileCheck(name);
		if(count!=0) { //프로필 사진 가져오기
			ProfileDto profileDto = memberService.getMemberProfile(name);
			mv.addObject("profile", profileDto);
		}
		
		mv.addObject("member", member);
		return mv;
	}
	
//	회원 프로필 편집
	@RequestMapping(value = "/insta/edit/{idx}",method = RequestMethod.PUT)
	public String instaEditProcess(MemberDto member) throws Exception{
//		현재 자신의 데이터 가져오기
		MemberDto myMember = memberService.getMemberByIdx(member.getIdx());
//		사용자 이름, 이메일 중복 체크
		int count1 = memberService.emailCheck(member.getEmail()); // 이메일 확인
		int count2 = memberService.nameCheck(member.getName()); // 사용자 이름 확인
		
		String myEmail = myMember.getEmail();// 현재 자신의 이메일
		String myName = myMember.getName(); //현재 자신의 이름
		
		String email = member.getEmail(); //가져온 이메일
		String name = member.getName(); //가져온 이름
		if(myEmail.equals(email) && myName.equals(name)) {
			//둘 다 기존과 동일하면 중복체크 할 필요 없음
			memberService.editMember(member);
			return "redirect:/insta/edit/{idx}";
		} else if(myName.equals(name) == false && count2 == 1) { // 이름이 기존값과 다르지만 중복
			return "redirect:/insta/edit/{idx}";
		} else if(myEmail.equals(email) == false && count1 == 1) {//이메일이 기존값과 다르지만 중복
			return "redirect:/insta/edit/{idx}";
		} else {
			memberService.editMember(member);
			return "redirect:/insta/edit/{idx}";
		}
	}
	
//	ajax로 회원 프로필 편집시 중복 체크
	@ResponseBody
	@RequestMapping(value = "/insta/edit/{idx}" , method = RequestMethod.POST)
	public Object instaEditCheck(MemberDto member) throws Exception{
//		현재 자신의 데이터 가져오기
		MemberDto myMember = memberService.getMemberByIdx(member.getIdx());
//		사용자 이름, 이메일 중복 체크
		int count1 = memberService.emailCheck(member.getEmail()); // 이메일 확인
		int count2 = memberService.nameCheck(member.getName()); // 사용자 이름 확인
		
		String myEmail = myMember.getEmail();// 현재 자신의 이메일
		String myName = myMember.getName(); //현재 자신의 이름
		
		String email = member.getEmail(); //가져온 이메일
		String name = member.getName(); //가져온 이름
		if(myEmail.equals(email) && myName.equals(name)) {
			//둘 다 기존과 동일하면 중복체크 할 필요 없음
			return "checked";
		} else if(myName.equals(name) == false && count2 == 1) { // 이름이 기존값과 다르지만 중복
			return "existName";
		} else if(myEmail.equals(email) == false && count1 == 1) {//이메일이 기존값과 다르지만 중복
			return "existEmail";
		} else {
			return "checked";
		}
	}
	
//	회원 탈퇴
	@RequestMapping(value = "/insta/edit/{idx}", method = RequestMethod.DELETE)
	public String instaDeleteMember(@PathVariable("idx") int idx) throws Exception{
		memberService.deleteMember(idx);
		return "redirect:/insta/login";
	}
	
//	계정 구독
	@ResponseBody
	@RequestMapping(value = "/insta/follow", method = RequestMethod.POST)
	public void instaFollow(int followIdx, int followedIdx) throws Exception{
		memberService.follow(followIdx,followedIdx);
	}
	
//	계정 구독 해제
	@ResponseBody
	@RequestMapping(value = "/insta/follow", method = RequestMethod.DELETE)
	public void instaUnfollow(int followIdx, int followedIdx) throws Exception{
		memberService.unfollow(followIdx,followedIdx);
	}
}
