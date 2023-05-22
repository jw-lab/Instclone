package com.insta.jw.dto;

import lombok.Data;

@Data
public class CommentsDto {

	private int idx;
	private String commentsText;
	private String createdDt;
	private int memberIdx;
	private int boardIdx;
	
	private String name;
	private ProfileDto profile;
}
