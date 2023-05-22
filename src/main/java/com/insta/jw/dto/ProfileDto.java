package com.insta.jw.dto;

import lombok.Data;

@Data
public class ProfileDto {
	
	private int idx;
	private int memberIdx;
	private String originalFileName;
	private String storedFilePath;
	private String fileSize;
	private String createdDt;
}
