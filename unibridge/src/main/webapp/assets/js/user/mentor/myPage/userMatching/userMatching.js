/**
 * 매칭 정보 관리 JavaScript
 * JSP의 동적 ID(matchingModal_번호)와 연동됩니다.
 */

// 1. 매칭 취소 모달 열기
function openCancelModal(matchNum) {
    console.log("모달 열기 시도 - 매칭번호: " + matchNum);
    const modal = document.getElementById('matchingModal_' + matchNum);
    
    if (modal) {
        // 배경을 포함한 모달 전체를 화면에 표시
        modal.style.display = "flex"; 
    } else {
        console.error("해당 모달을 찾을 수 없습니다: matchingModal_" + matchNum);
    }
}

// 2. 매칭 취소 모달 닫기
function closeCancelModal(matchNum) {
    const modal = document.getElementById('matchingModal_' + matchNum);
    if (modal) {
        modal.style.display = "none";
    }
}

// 3. 매칭 취소 신청 제출 처리
function submitCancel(matchNum) {
    // 1. 취소 사유 입력 확인 (필요 시)
    const modal = document.getElementById('matchingModal_' + matchNum);
    const reasonArea = modal.querySelector('textarea');
    const reason = reasonArea ? reasonArea.value.trim() : "";

    // 2. 사용자 최종 확인
    const isConfirmed = confirm("정말로 " + matchNum + "번 매칭을 취소 신청하시겠습니까?");

    if (isConfirmed) {
        // [경로 설정] 현재 프로젝트의 ContextPath를 고려한 경로 생성
        const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
        
        // 실제 컨트롤러 주소 (매핑된 주소로 수정 필요)
        const targetPath = contextPath + "/mvc/auth/mentor/matchingCancel.my";
        
        // 실제 운영 시에는 아래처럼 POST 방식으로 데이터를 보내거나 location.href를 사용합니다.
        console.log("취소 신청 경로: " + targetPath + "?matchinNumber=" + matchNum);
        
        alert(matchNum + "번 매칭 취소 신청이 완료되었습니다.");
        
        // 임시: 요청 후 리스트 페이지로 이동 혹은 새로고침
        // window.location.href = contextPath + "/mvc/auth/mentor/matching.my";
        location.reload(); 
    }
}

// 4. 모달 바깥쪽(어두운 배경) 클릭 시 닫기 처리
window.onclick = function(event) {
    // 클릭한 대상의 클래스가 'matingCancel'(배경 레이어)이면 닫기
    if (event.target.classList.contains('matingCancel')) {
        event.target.style.display = "none";
    }
};