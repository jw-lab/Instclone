package com.insta.jw.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.insta.jw.dto.BoardDto;
import com.insta.jw.dto.FileDto;
import com.insta.jw.dto.ProfileDto;

@Component
public class FileUtils {

	public List<FileDto> parseFileInfo(BoardDto board,MultipartHttpServletRequest multiFiles) throws Exception{
		
		if(ObjectUtils.isEmpty(multiFiles)) {
			return null;
		}
		
		List<FileDto> fileList = new ArrayList<>();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
	
		//경로 설정
		String path = "images/"+current.format(format);
		File file = new File(path);
		
		if(file.exists()==false) {
			file.mkdir();// 폴더가 없으면 생성
		}
		
		Iterator<String> iterator = multiFiles.getFileNames();
		String newFileName; // 파일이름이 저장되는 변수
		String originalFileExtension; // 확장자가 저장되는 변수
		String contentType; // 컨텐트 타입이 저장
		
		while (iterator.hasNext()) {
			String name = iterator.next();
			List<MultipartFile> list = multiFiles.getFiles(name);
			
			for(MultipartFile mFile : list) {
				//파일 확장자명 설정
				if(mFile.isEmpty()==false) {
					contentType = mFile.getContentType();
					if(ObjectUtils.isEmpty(contentType))
						break;
					else {
						if(contentType.contains("image/jpeg"))
							originalFileExtension = ".jpg";
						else if(contentType.contains("image/png"))
							originalFileExtension = ".png";
						else if(contentType.contains("image/gif"))
							originalFileExtension = ".gif";
						else
							break;
					}
				
				
				//현재 시간을 기준으로 파일 이름 설정
				newFileName = Long.toString(System.nanoTime())+ originalFileExtension;
				
				FileDto fileDto = new FileDto();
				fileDto.setBoardIdx(board.getIdx());
				fileDto.setMemberIdx(board.getMemberIdx());
				fileDto.setFileSize(Long.toString(mFile.getSize()));
				fileDto.setOriginalFileName(mFile.getOriginalFilename());
				fileDto.setStoredFilePath(path + "/" + newFileName);

				
				fileList.add(fileDto);
				
				file = new File(path + "/" + newFileName);
				
				mFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
	
	public ProfileDto parseProfileInfo(int memberIdx,MultipartHttpServletRequest multiFiles) throws Exception{
		if(ObjectUtils.isEmpty(multiFiles)) {
			return null;
		}
		
		ProfileDto profileDto = new ProfileDto();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
	
		//경로 설정
		String path = "images/"+current.format(format);
		File file = new File(path);
		
		if(file.exists()==false) {
			file.mkdir();// 폴더가 없으면 생성
		}
		
		Iterator<String> iterator = multiFiles.getFileNames();
		String newFileName; // 파일이름이 저장되는 변수
		String originalFileExtension; // 확장자가 저장되는 변수
		String contentType; // 컨텐트 타입이 저장
		
		while (iterator.hasNext()) {
			String name = iterator.next();
			List<MultipartFile> list = multiFiles.getFiles(name);
			
			for(MultipartFile mFile : list) {
				//파일 확장자명 설정
				if(mFile.isEmpty()==false) {
					contentType = mFile.getContentType();
					if(ObjectUtils.isEmpty(contentType))
						break;
					else {
						if(contentType.contains("image/jpeg"))
							originalFileExtension = ".jpg";
						else if(contentType.contains("image/png"))
							originalFileExtension = ".png";
						else if(contentType.contains("image/gif"))
							originalFileExtension = ".gif";
						else
							break;
					}
				
				
				//현재 시간을 기준으로 파일 이름 설정
				newFileName = Long.toString(System.nanoTime())+ originalFileExtension;
				
				profileDto.setMemberIdx(memberIdx);
				profileDto.setFileSize(Long.toString(mFile.getSize()));
				profileDto.setOriginalFileName(mFile.getOriginalFilename());
				profileDto.setStoredFilePath(path + "/" + newFileName);

				file = new File(path + "/" + newFileName);
				
				mFile.transferTo(file);
				}
			}
		}
		return profileDto;
	}
}
