package com.unibridge.app.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.admin.dao.AdNoticeBoardDAO;
import com.unibridge.app.admin.dto.AdNoticeBoardDTO;
import com.unibridge.app.file.dao.FileDAO;
import com.unibridge.app.file.dto.FileDTO;

public class AdminNoticeWriteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AdNoticeBoardDTO boardDTO = new AdNoticeBoardDTO();
		AdNoticeBoardDAO boardDAO = new AdNoticeBoardDAO();
		FileDAO fileDAO = new FileDAO();
		Result result = new Result();
		
		//로그인 한 회원 정보 가져오기
		Integer adminNumber = (Integer)request.getSession().getAttribute("adminNumber");
		
		System.out.println("현재 로그인한 회원 번호 : " + adminNumber);
		
		//파일 업로드 환경 설정
		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		final int FILE_SIZE = 1024 * 1024 * 5; //5MB
		System.out.println("파일 업로드 경로 : " + UPLOAD_PATH);
		
		File uploadDir = new File(UPLOAD_PATH);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		//MultipartRequest를 이용한 데이터 파싱
		MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "utf-8", 
				new DefaultFileRenamePolicy()); 
		
		
		
		
		//파일 업로드 처리
		//Enumeration : java.util 패키지에 포함된 인터페이스, Itrator와 비슷한 역할을 함
		Enumeration<String> fileNames = multipartRequest.getFileNames();
		Integer fileNumber = null;
		while(fileNames.hasMoreElements()) {
			FileDTO fileDTO = new FileDTO();
			String name = fileNames.nextElement();
			String fileSystemName = multipartRequest.getFilesystemName(name);
			String fileOriginalName = multipartRequest.getOriginalFileName(name);
				
			if(fileSystemName == null) {
				continue;
			}
			
			fileDTO.setFileName(fileSystemName);
			fileDTO.setFileOriginalName(fileOriginalName);
			
			fileNumber = fileDAO.insertFileIfExists(fileDTO);
			System.out.println(fileNumber);
			System.out.println("업로드 파일 정보 : " + fileDTO);
		}
		
		//게시글 정보 설정
		boardDTO.setBoardTitle(multipartRequest.getParameter("boardTitle"));
		boardDTO.setBoardContent(multipartRequest.getParameter("boardContent"));
		boardDTO.setBoardType(multipartRequest.getParameter("boardType"));
		boardDTO.setAdminNumber(adminNumber);
		boardDTO.setFileNumber(fileNumber);
		System.out.println("게시글 추가 - BoardDTO : " + boardDTO);
		
		//게시글 추가
		int boardNumber = boardDAO.insertBoard(boardDTO);
		System.out.println("생성된 게시글 번호 : " + boardNumber);

		result.setPath(request.getContextPath()+"/noticeList.admin");
		result.setRedirect(true);
		
		return result;

	}

}
