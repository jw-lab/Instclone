package com.insta.jw.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.insta.jw.dto.MemberDto;
import com.insta.jw.dto.ProfileDto;

public interface MemberService {

	int loginCheck(String email, String pwd) throws Exception;

	int emailCheck(String email) throws Exception;
	
	void signup(MemberDto member) throws Exception;

	int nameCheck(String name) throws Exception;

	MemberDto getMember(String email) throws Exception;

	MemberDto getMemberByName(String name) throws Exception;

	List<MemberDto> getUnfollowList(int idx) throws Exception;

	void profileUpload(int boardIdx, MultipartHttpServletRequest multiFiles) throws Exception;

	int profileCheck(String name) throws Exception;

	ProfileDto getMemberProfile(String name) throws Exception;

	void editMember(MemberDto member) throws Exception;

	MemberDto getMemberByIdx(int idx) throws Exception;

	void deleteMember(int idx) throws Exception;

	void follow(int followIdx, int followedIdx) throws Exception;

	void unfollow(int followIdx, int followedIdx) throws Exception;

	List<MemberDto> getFollowList(int idx) throws Exception;

	int countFollower(int idx) throws Exception;

	List<MemberDto> getMemberList() throws Exception;


}
