package 회원관리;

// 수정 id로 수정 -> 비밀번호
// 삭제 id로 삭제
public class MemberController {
	private MemberDAO memberDAO;

	private void init() {
		memberDAO = new MemberDAO();
	}

	public void run() {
		init();
		while (true) {
			System.out.println("[1]추가 [2]삭제 [3]수정 [4]출력 [5]파일저장 [6]불러오기 [0]종료");
			int num = Utils.getValue("메뉴 선택: ", 7, 0);
			if (num == 1) {
				memberDAO.join();
			} else if (num == 2) {
				memberDAO.delete();
			} else if (num == 3) {
				memberDAO.update();
			} else if (num == 4) {
				memberDAO.printAll();
			} else if (num == 5) {
				Utils.saveDataToFile(memberDAO.getMemberList());
			} else if (num == 6) {
				memberDAO.addLoadedMemberData(Utils.LoadDataFromFile());
			} else if (num == 0) {
				System.out.println("[프로그램 종료]");
				break;
			}
		}
	}
}
