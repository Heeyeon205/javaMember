package 회원관리;

// DAO(Data Access Object): 여러 VO들을 관리하는 클래스(CRUD)
// Member(VO: Value Object): 실제 데이터를 저장한 클래스
// Controller: 메뉴 선택 화면(사용자와 실제 기능을 구현하는 클래스를 연결하는 클래스)
// Utils: 여러 클래스에서 공유해서 사용하는 자원(Scanner, Random, File)

public class Main {

	public static void main(String[] args) {
		MemberController controller = new MemberController();
		controller.run();
	}

}

