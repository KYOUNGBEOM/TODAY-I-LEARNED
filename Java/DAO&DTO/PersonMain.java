import java.util.ArrayList;
import java.util.Scanner;

public class PersonMain {
	Scanner sc = new Scanner(System.in);

	PersonDao dao = new PersonDao();
	
	PersonMain() {
		System.out.println("PersonMain 생성자");
		init();
	}
	
	private void init() {
		int num = 0;
		String name = "";
		int age = 0;
		String gender = "";
		String birth = "";
		while(true) {
			System.out.println("------------메뉴 선택하기-----------");
			System.out.println("1. 전체 정보 조회");
			System.out.println("2. 성별로 조회");
			System.out.println("3. 정보 수정");
			System.out.println("4. 정보 삭제");
			System.out.println("5. 정보 추가");
			System.out.println("6. 프로그램 종료");
			System.out.print(">> 메뉴 번호 입력 : ");
			int menu = sc.nextInt();
	
			switch(menu) {
			case 1 : 
				ArrayList<PersonBean> al = dao.select();
				for(int i = 0; i < al.size(); i++) 
					System.out.println(al.get(i).getNum() + " " + al.get(i).getName() + " " + al.get(i).getAge() + " " + al.get(i).getGender() + " " + al.get(i).getBirth());
				break;
			case 2 :
				System.out.print("찾는 성별 입력 : ");
				gender = sc.next();
				al = dao.getGender(gender);
				for(int i = 0; i < al.size(); i++) 
					System.out.println(al.get(i).getNum() + " " + al.get(i).getName() + " " + al.get(i).getAge() + " " + al.get(i).getGender() + " " + al.get(i).getBirth());
				break;
			case 3 :
				System.out.print("수정할 숫자를 입력하세요 : ");
				num = sc.nextInt();
				System.out.print("이름을 입력하세요 : ");
				name = sc.next();
				System.out.print("나이를 입력하세요 : ");
				age = sc.nextInt();
				System.out.print("성별을 입력하세요 : ");
				gender = sc.next();
				System.out.print("생일을 입력하세요 : ");
				birth = sc.next();
				
				dao.update(num, name, age, gender, birth);
				break;
			case 4 :
				System.out.print("삭제할 숫자를 입력하세요 : ");
				num = sc.nextInt();
				dao.delete(num);
				break;
			case 5 :
				System.out.println("숫자는 시퀀스입니다.");
				System.out.print("이름을 입력하세요 : ");
				name = sc.next();
				System.out.print("나이를 입력하세요 : ");
				age = sc.nextInt();
				System.out.print("성별을 입력하세요 : ");
				gender = sc.next();
				System.out.print("생일을 입력하세요 : ");
				birth = sc.next();
				
				dao.insert(name, age, gender, birth);
				break;
			case 6 : 
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default :
				System.out.println("1~6의 번호만 입력 가능합니다.");
				break;
			} // switch
		
		} // while
	} // init
	
	public static void main(String[] args) {
		PersonMain per = new PersonMain();
	}
}
