package com.insta.jw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.insta.jw.common.FileUtils;
import com.insta.jw.dto.MemberDto;
import com.insta.jw.dto.ProfileDto;
import com.insta.jw.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
//	email 체크
	@Override
	public int emailCheck(String email) throws Exception {
		return memberMapper.emailCheck(email);
	}
	
//	email,pwd 체크
	@Override
	public int loginCheck(String email, String pwd) throws Exception {

		return memberMapper.loginCheck(email,pwd);
	}

//	회원가입
	@Override
	public void signup(MemberDto member) throws Exception {
		memberMapper.signup(member);
		
		//회원가입시 자기 자신 follow
		memberMapper.follow(member.getIdx(),member.getIdx());
	}

//	회원가입시 사용자 이름 중복 확인
	@Override
	public int nameCheck(String name) throws Exception {
		return memberMapper.nameCheck(name);
	}

//	이메일로 회원정보 가져오기
	@Override
	public MemberDto getMember(String email) throws Exception {
		
		return memberMapper.getMember(email);
	}

//	사용자 이름으로 회원정보 가져오기
	@Override
	public MemberDto getMemberByName(String name) throws Exception {
		return memberMapper.getMemberByName(name);
	}

//	회원 목록 불러오기(구독안한 사용자만 불러오기)
	@Override
	public List<MemberDto> getUnfollowList(int idx) throws Exception {
		return memberMapper.getUnfollowList(idx);
	}

//	프로필 사진 업로드
	@Override
	public void profileUpload(int memberIdx, MultipartHttpServletRequest multiFiles) throws Exception {
		ProfileDto profileDto = fileUtils.parseProfileInfo(memberIdx, multiFiles);
		
		if(profileDto!=null) {
			memberMapper.profileUpload(profileDto);
		}
		
	}
//	프로필 사진 유무 체크
	@Override
	public int profileCheck(String name) throws Exception {
		return memberMapper.profileCheck(name);
	}

//	프로필 사진 가져오기
	@Override
	public ProfileDto getMemberProfile(String name) throws Exception {
		return memberMapper.getMemberProfile(name);
	}

//	회원정보 수정
	@Override
	public void editMember(MemberDto member) throws Exception {
		memberMapper.editMember(member);
	}

//	사용자 idx로 member 가져오기
	@Override
	public MemberDto getMemberByIdx(int idx) throws Exception {
		return memberMapper.getMemberByIdx(idx);
	}

//	회원 탈퇴
	@Override
	public void deleteMember(int idx) throws Exception {
		memberMapper.deleteMember(idx);
		
	}

//	계정 팔로우
	@Override
	public void follow(int followIdx, int followedIdx) throws Exception {
		memberMapper.follow(followIdx,followedIdx);
		
	}
//	계정 언팔로우
	@Override
	public void unfollow(int followIdx, int followedIdx) throws Exception {
		memberMapper.unfollow(followIdx,followedIdx);
		
	}

//	구독한 사용자만 불러오기
	@Override
	public List<MemberDto> getFollowList(int idx) throws Exception {
		return memberMapper.getFollowList(idx);
	}

//	팔로워 숫자
	@Override
	public int countFollower(int idx) throws Exception {
		return memberMapper.countFollower(idx);
	}

//	모든 회원 목록 불러오기
	@Override
	public List<MemberDto> getMemberList() throws Exception {
		return memberMapper.getMemberList();
	}

	

}
