package com.insta.jw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.insta.jw.dto.BoardDto;
import com.insta.jw.dto.CommentsDto;
import com.insta.jw.dto.FileDto;
import com.insta.jw.dto.LikesDto;

@Mapper
public interface BoardMapper {

	void insertBoard(BoardDto board) throws Exception;

	void insertBoardFileList(List<FileDto> list) throws Exception;

	List<BoardDto> selectBoard(int idx) throws Exception;

	List<FileDto> getFileList(int idx) throws Exception;

	List<BoardDto> selectMyBoard(int idx) throws Exception;

	void deleteBoard(int idx) throws Exception;

	List<BoardDto> selectAllBoard() throws Exception;

	void insertComments(CommentsDto comments) throws Exception;

	List<CommentsDto> getCommentsList(int idx) throws Exception;

	int getCommentsCount(int idx) throws Exception;

	void insertLikes(@Param("boardIdx") int boardIdx,@Param("memberIdx") int memberIdx) throws Exception;

	List<LikesDto> selectLikes(int boardIdx) throws Exception;

	void deleteLikes(@Param("boardIdx") int boardIdx,@Param("memberIdx") int memberIdx) throws Exception;

	int getLikesCount(int boardIdx) throws Exception;

}
