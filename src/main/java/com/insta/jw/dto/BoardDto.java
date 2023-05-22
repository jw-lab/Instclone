package com.insta.jw.dto;

import java.util.List;

import lombok.Data;

@Data
public class BoardDto {

	private int idx;
	private String contents;
	private String creatorId;
	private String createdDt;
	private int memberIdx;

	private List<FileDto> fileList;
	private int commentsCount;
	private List<LikesDto> likeIdxs;
	private int likesCount;
}
