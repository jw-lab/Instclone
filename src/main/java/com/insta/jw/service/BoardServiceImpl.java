package com.insta.jw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.insta.jw.common.FileUtils;
import com.insta.jw.dto.BoardDto;
import com.insta.jw.dto.CommentsDto;
import com.insta.jw.dto.FileDto;
import com.insta.jw.dto.LikesDto;
import com.insta.jw.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
//	게시물 등록
	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multiFiles) throws Exception {
		boardMapper.insertBoard(board); // memberIdx, contents만 가져와서 등록
		
		List<FileDto> list = fileUtils.parseFileInfo(board, multiFiles);
		
		if(CollectionUtils.isEmpty(list)==false) {
			boardMapper.insertBoardFileList(list);
		}
	}

//	게시물 목록 가져오기
	@Override
	public List<BoardDto> selectBoard(int idx) throws Exception {
		List<BoardDto> boardList = boardMapper.selectBoard(idx);
		return boardList;
	}

//	각 게시물에 해당하는 파일 리스트 불러오기
	@Override
	public List<FileDto> getFileList(int idx) throws Exception {
		
		return boardMapper.getFileList(idx);
	}

//	개인페이지 게시물 목록
	@Override
	public List<BoardDto> selectMyBoard(int idx) throws Exception {
		return boardMapper.selectMyBoard(idx);
	}

//	게시글 삭제
	@Override
	public void deleteBoard(int idx) throws Exception {
		boardMapper.deleteBoard(idx);
		
	}

//	모든 게시물 가져오기
	@Override
	public List<BoardDto> selectAllBoard() throws Exception {
		return boardMapper.selectAllBoard();
	}

//	댓글 저장
	@Override
	public void insertComments(CommentsDto comments) throws Exception {
		boardMapper.insertComments(comments);
		
	}

//	각 게시물의 댓글 조회
	@Override
	public List<CommentsDto> getCommentsList(int idx) throws Exception {
		return boardMapper.getCommentsList(idx);
	}

//	각 게시물 댓글 갯수
	@Override
	public int getCommentsCount(int idx) throws Exception {
		return boardMapper.getCommentsCount(idx);
	}

//	좋아요 추가
	@Override
	public void insertLikes(int boardIdx, int memberIdx) throws Exception {
		boardMapper.insertLikes(boardIdx,memberIdx);
		
	}

//	각 게시물 좋아요 조회
	@Override
	public List<LikesDto> selectLikes(int memberIdx) throws Exception {

		return boardMapper.selectLikes(memberIdx);
	}

//	좋아요 취소
	@Override
	public void deleteLikes(int boardIdx, int memberIdx) throws Exception {
		boardMapper.deleteLikes(boardIdx,memberIdx);
		
	}

//	각 게시물 좋아요 갯수
	@Override
	public int getLikesCount(int boardIdx) throws Exception {
		return boardMapper.getLikesCount(boardIdx);
	}

}
