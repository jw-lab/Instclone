package com.insta.jw.dto;

import lombok.Data;

@Data
public class FileDto {
	private int fileIdx;
	private String originalFileName;
	private String storedFilePath;
	private String fileSize;
	private String createdDt;
	private int boardIdx;
	private int memberIdx;
	
}
