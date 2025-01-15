package 회원관리;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class MemberDAO {
	private ArrayList<Member> memberList;

	public MemberDAO() {
		init();
	}

	public void init() {
		memberList = new ArrayList<>();
	}

	public void join() {
		String id = Utils.getId(memberList, "[가입]\nid 입력: ");
		if (id == null) {
			System.out.println("중복 id입니다.");
			return;
		}
		String pw = Utils.getInfo("pw 입력: ");
		String name = Utils.getInfo("name 입력: ");
		memberList.add(new Member(id, pw, name));
		System.out.printf("%s님 회원가입 완료!\n", id);
	}

	public void delete() {
		if (!Utils.hasData(memberList))
			return;
		String id = Utils.findId(memberList, "[삭제]\nid 입력: ");
		if (id == null) {
			System.out.println("데이터에 없는 id입니다.");
			return;
		}
		int idx = Utils.getIdIdx(memberList, id);
		memberList.remove(idx);
		System.out.printf("%s님 데이터 삭제 완료!\n", id);
	}

	public void update() {
		if (!Utils.hasData(memberList))
			return;
		String id = Utils.findId(memberList, "[비밀번호 수정]\nid 입력: ");
		if (id == null) {
			System.out.println("데이터에 없는 id입니다.");
			return;
		}
		int idx = Utils.getIdIdx(memberList, id);
		String newPw = Utils.checkPwDup(memberList, "수정할 비밀번호 입력: ");
		if (newPw == null) {
			System.out.println("현재 pw와 동일한 pw입니다.");
			return;
		}
		memberList.get(idx).setPw(newPw);
		System.out.printf("%s님 데이터 수정 완료!\n", id);
	}

	public void printAll() {
		if (!Utils.hasData(memberList)) return;
		System.out.printf("%4s %4s %6s\n", "ID", "PW", "NAME");
		for(Member m : memberList) {
			System.out.println(m);
		}
	}

	public ArrayList<Member> getMemberList() {
		return memberList;
	}

	public void addLoadedMemberData(ArrayList<Member> loadDataFromFile) {
		if(loadDataFromFile == null || loadDataFromFile.isEmpty()) {
			System.out.println("로드된 데이터가 없습니다.");
		}
		for(Member member : loadDataFromFile) {
			memberList.add(member);
		}
//		memberList = loadDataFromFile;
	}
}
