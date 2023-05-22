package com.insta.jw.dto;

import lombok.Data;

@Data
public class MemberDto {
	private int idx;
	private String name;
	private String originalName;
	private String pwd;
	private String email;
	
	private String website;
	private String websiteContents;
	private String phone;
	private String gender;
	
	private ProfileDto profile;
}
