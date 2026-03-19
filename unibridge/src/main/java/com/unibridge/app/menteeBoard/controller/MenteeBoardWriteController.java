package com.unibridge.app.menteeBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;

public class MenteeBoardWriteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("게시글 작성 페이지 컨트롤러 이동 완료");
        Result result = new Result();
        HttpSession session = request.getSession();
        Integer memberNumber = (Integer) session.getAttribute("memberNumber");
        String path = null;

        if (memberNumber == null) {
            path = "/app/user/signin/signin.jsp";
        } else {
            path = "/app/user/mentee/menteeBoard/MenteeBoardCreate.jsp";
        }

        result.setPath(path);
        result.setRedirect(false);

        return result;
	    }

	}









