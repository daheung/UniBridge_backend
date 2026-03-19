package com.unibridge.app.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unibridge.app.Execute;
import com.unibridge.app.Result;
import com.unibridge.app.member.controller.MenteeDeleteController;
import com.unibridge.app.member.controller.MenteeMangeController;
import com.unibridge.app.member.controller.MatchingController;
import com.unibridge.app.member.controller.MenteeUpdateOkController;
import com.unibridge.app.member.controller.MenteeVerifyController;
import com.unibridge.app.mypage.matching.controller.PayLogController;
import com.unibridge.app.mypage.survey.controller.MenteeSurveyController;

public class MenteeFrontController implements Execute {
	Result outResult = new Result();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
<<<<<<< HEAD
			throws ServletException, IOException {
			// 멘티 컨트롤러
			String requestURI = request.getRequestURI();
			String target = extractTargetPath(requestURI);
			
			System.out.println("===MenteeFrontController===");
			
			switch (target) {
			case  "myPage.my": // 마이페이지
				System.out.println("[Log] 결과: MenteeMangeController 실행 시도...");
				this.outResult = new MenteeMangeController().execute(request, response);
				System.out.println("[Log] 결과: MenteeMangeController 실행 완료!");
				break;
			case "verify.my": // 인증 로직 처리
				System.out.println("[Log] 결과: MenteeVerifyController 실행 시도...");
			    this.outResult = new MenteeVerifyController().execute(request, response);
			    System.out.println("[Log] 결과: MenteeVerifyController 실행 완료!");
			    break;
			case "updateOk.my":
	            // [추가] 실제 DB 데이터 수정 처리
	            System.out.println("[Log] 결과: MenteeUpdateOkController 실행...");
	            outResult = new MenteeUpdateOkController().execute(request, response);
	            System.out.println("[Log] 결과: MenteeUpdateOkController 실행완료!");
	            break;
			case "finishUpdate.my": // 수정 완료 단순 이동 처리
	            System.out.println("[Log] 결과: 수정 완료 후 마이페이지 메인으로 리다이렉트");
	            this.outResult = new Result();
	            // JSP가 아닌 '컨트롤러'를 호출해서 데이터를 새로고침함
	            this.outResult.setPath(request.getContextPath() + "/auth/mentee/myPage.my");
	            this.outResult.setRedirect(true); 
	            break;
			case "survey.my": // 설문조사
				System.out.println("[Log] 결과: MenteeSurveyController 실행 시도...");
			    this.outResult = new MenteeSurveyController().execute(request, response);
			    System.out.println("[Log] 결과: MenteeSurveyController 실행 완료!");
			    break;
			case "delete.my": //회원탈퇴
				System.out.println("[Log] 결과: MenteeDeleteController 실행 시도...");
				this.outResult = new MenteeDeleteController().execute(request, response);
				System.out.println("[Log] 결과: MenteeDeleteController 실행 완료!");
				break;
			case "matching.my": // 매칭정보
				System.out.println("[Log] 결과: MatchingController 실행 시도...");
				this.outResult = new MatchingController().execute(request, response);
				System.out.println("[Log] 결과: MatchingController 실행 완료!");
				break;
			case "log.my": //결제정보
				System.out.println("[Log] 결과: PayLogController 실행 시도...");
				this.outResult = new PayLogController().execute(request, response);
				System.out.println("[Log] 결과: PayLogController 실행 완료!");
				break;
			default:
				System.out.println("[Warn] 매칭되는 target이 없음: " + target);
				break;
			}
			return outResult;
=======
	        throws ServletException, IOException {
	    
	    String requestURI = request.getRequestURI();
	    // 로그를 찍어보면 /unibridge/mvc/auth/undecided/myPage.my 처럼 전체가 나옵니다.
	    System.out.println("[Log] 하위 컨트롤러 수신 URI: " + requestURI);

	    Result result = new Result();

	    // switch 대신 if-else contains 방식을 사용하면 경로가 복잡해도 정확히 잡아냅니다.
	    if (requestURI.contains("myPage.my")) {
	        System.out.println("계정관리 요청 수신");
	        result = new UpdateController().execute(request, response);
	    } 
	    else if (requestURI.contains("survey.my")) {
	        System.out.println("설문 요청 수신");
	        result = new SurveyController().execute(request, response);
	    } 
	    else if (requestURI.contains("delete.my")) {
	        System.out.println("회원탈퇴 신청 요청 수신");
	        result = new DeleteController().execute(request, response);
	    } 
	    else if (requestURI.contains("matching.my")) {
	        System.out.println("매칭 정보 조회 수신");
	        result = new MatchingController().execute(request, response);
	    } 
	    else if (requestURI.contains("log.my")) {
	        System.out.println("결제 정보 조회 수신");
	        result = new PayLogController().execute(request, response);
	    } 
	    else {
	        System.out.println("[Warn] 매칭되는 기능을 찾을 수 없음: " + requestURI);
	    }
	    
	    return result;
>>>>>>> main
	}
	
	private String extractTargetPath(String requestUri) {
		String[] splitedPaths = requestUri.split("/");
		String   target = splitedPaths[splitedPaths.length - 1];
		return target;
	}
}
