package 회원관리;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
	private static Scanner sc = new Scanner(System.in);
	private static final String CUR_PATH = System.getProperty("user.dir") + "/src/" + Utils.class.getPackageName()+ "/";
	private static final String File_Path = CUR_PATH + "member.txt";
	private static Utils instance;
	
	private Utils() {}
	
	public static Utils getInstance() {
		if(instance == null) instance = new Utils();
		return instance;
	}
	
	
	public static boolean hasData(ArrayList<Member> memberList) {
		if (memberList.size() == 0) {
			System.out.println("데이터가 없습니다.");
			return false;
		}
		return true;
	}
	
	public static int getValue(String msg, int max, int min) {
		while (true) {
			System.out.print(msg);
			try {
				int num = sc.nextInt();
				sc.nextLine();
				if (num < min || num >= max) {
					System.out.println("입력 범위 오류입니다.");
					return -1;
				}
				return num;
			}catch(InputMismatchException e) {
				System.out.println("숫자만 입력 가능합니다.");
				sc.nextLine();
				continue;
			}
		}
	}

	public static String getId(ArrayList<Member> memberList, String msg) {
		System.out.print(msg);
		String id = sc.nextLine();
		if (memberList.size() == 0) {
			return id;
		}
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getId().equals(id)) {
				return null;
			}
		}
		return id;
	}

	public static int getIdIdx(ArrayList<Member> memberList, String id) {
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	public static String getInfo(String msg) {
		System.out.print(msg);
		String info = sc.nextLine();
		return info;
	}

	public static String findId(ArrayList<Member> memberList, String msg) {
		System.out.print(msg);
		String id = sc.nextLine();
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getId().equals(id)) {
				return id;
			}
		}
		return null;
	}

	public static String checkPwDup(ArrayList<Member> memberList, String msg) {
		System.out.print(msg);
		String pw = sc.nextLine();
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getPw().equals(pw)) {
				return null;
			}
		}
		return pw;
	}
	
	public static void saveDataToFile(ArrayList<Member> memberList) {
		if (!Utils.hasData(memberList)) return;
		try (FileWriter fw = new FileWriter(File_Path);){
			for(Member m : memberList) {
				fw.write(m + "\n");
			}
			System.out.println("파일 저장 성공!\n경로: " + File_Path);
		}catch(FileNotFoundException e) {
			System.out.println("파일 경로를 찾을 수 없습니다!");
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("파일 저장 실패!");
			e.printStackTrace();
		}
	}

	public static ArrayList<Member> LoadDataFromFile() {
		ArrayList<Member> memberList = new ArrayList<>();
		try (FileReader fr = new FileReader(File_Path);
				BufferedReader br = new BufferedReader(fr);){
			String line;
			while(((line = br.readLine()) != null)){
				String[] temp = line.split("/");
				memberList.add(new Member(temp[0], temp[1], temp[2]));
			}
			System.out.println("파일 불러오기 완료!");
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException e) {
			System.out.println("파일 불러오기 중 오류 발생!");
		}
		return memberList;
	}
}
