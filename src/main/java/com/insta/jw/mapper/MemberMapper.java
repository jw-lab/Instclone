package com.insta.jw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.insta.jw.dto.MemberDto;
import com.insta.jw.dto.ProfileDto;

@Mapper
public interface MemberMapper {

	int emailCheck(String email) throws Exception;
	
	int loginCheck(@Param("email") String email,@Param("pwd") String pwd) throws Exception;

	void signup(MemberDto member) throws Exception;

	int nameCheck(String name) throws Exception;
 
	MemberDto getMember(String email) throws Exception;

	MemberDto getMemberByName(String name) throws Exception;

	List<MemberDto> getUnfollowList(int idx) throws Exception;

	void profileUpload(ProfileDto profileDto) throws Exception;

	int profileCheck(String name) throws Exception;

	ProfileDto getMemberProfile(String name) throws Exception;

	void editMember(MemberDto member) throws Exception;

	MemberDto getMemberByIdx(int idx) throws Exception;

	void deleteMember(int idx) throws Exception;

	void follow(@Param("followIdx") int followIdx,@Param("followedIdx") int followedIdx) throws Exception;

	void unfollow(@Param("followIdx") int followIdx,@Param("followedIdx") int followedIdx) throws Exception;

	List<MemberDto> getFollowList(int idx) throws Exception;

	int countFollower(int idx) throws Exception;

	List<MemberDto> getMemberList() throws Exception;


}
