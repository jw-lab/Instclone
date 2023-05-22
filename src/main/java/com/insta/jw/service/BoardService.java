package com.insta.jw.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.insta.jw.dto.BoardDto;
import com.insta.jw.dto.CommentsDto;
import com.insta.jw.dto.FileDto;
import com.insta.jw.dto.LikesDto;

public interface BoardService {

	void insertBoard(BoardDto board, MultipartHttpServletRequest multiFiles) throws Exception;

	List<BoardDto> selectBoard(int idx) throws Exception;

	List<FileDto> getFileList(int idx) throws Exception;

	List<BoardDto> selectMyBoard(int idx) throws Exception;

	void deleteBoard(int idx) throws Exception;

	List<BoardDto> selectAllBoard() throws Exception;

	void insertComments(CommentsDto comments) throws Exception;

	List<CommentsDto> getCommentsList(int idx) throws Exception;

	int getCommentsCount(int idx) throws Exception;

	void insertLikes(int boardIdx, int memberIdx) throws Exception;

	List<LikesDto> selectLikes(int boardIdx) throws Exception;

	void deleteLikes(int boardIdx, int memberIdx) throws Exception;

	int getLikesCount(int boardIdx) throws Exception;
}
