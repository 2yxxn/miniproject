import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

interface menu {
	void print_menu(); // 메뉴 츌력
	void start_menu(); // 메인 메뉴 시작
}
class Diet {
	private String diet; // 식단
	private int money;   // 가격
	
	public void diet_add(String diet, int money) {this.diet = diet; this.money = money;} // 식단이랑 가격 입력받기
	public String diet_get() {return diet;} // 식단 출력
	public int diet_money() {return money;} // 가격 출력
	public void diet_show() {
		if (diet == null)
			System.out.println("아직 입력되지 않았습니다");
		else
			System.out.println(diet + "입니다");
	}
}
class Diet_list {
	 public static ArrayList<Diet> diets = new ArrayList<Diet>();
}
class Manager implements menu {
	static String password = "0000"; // 밖에서 그냥 Manager.password로 쓸 수 있게
	private Scanner sc;
	
	public Manager() {
		sc = new Scanner(System.in);
		for(int i = 0; i < 31; i++)			 // 31일까지 존재한다고 가정
			Diet_list.diets.add(new Diet());
	}
	@Override
	public void print_menu() {
		System.out.println("1. 식단 입력 (1일  ~ 31일)");
		System.out.println("2. 날짜별 식단 출력 (1일  ~ 31일)");
		System.out.println("3. 전체 식단 출력 (1일  ~ 31일)");
		System.out.println("4. 관리자 창 나가기");
		System.out.print("메뉴를 선택하세요 : ");
	}
	@Override
	public void start_menu() {
		while(true) {
			print_menu();
			int menu = sc.nextInt();
			System.out.println("-----------------------");
			switch(menu) {
			case 1:
				diets_add();
				System.out.println("-----------------------");
				break;
			case 2:
				diets_day_show();
				System.out.println("-----------------------");
				break;
			case 3:
				diets_all_show();
				System.out.println("-----------------------");
				break;
			case 4:
				System.out.println("관리자 창을 종료합니다");
				System.out.println("-----------------------");
				return;
			default:
				System.out.println("메뉴를 다시 입력하세요");
				System.out.println("-----------------------");
			}
		}
	}
	public void diets_add() { // 식단 입력
		System.out.print("식단을 입력할 날짜를 선택하세요 : ");
		int day = sc.nextInt();
		--day;
		if (day < 0 || day >= 31) { // 올바른 날짜 체크
			System.out.println("날짜를 잘못 입력했습니다");
			return;
		}
		if (Diet_list.diets.get(day).diet_get() != null) { // diets.get(day)를 통해서 Diet를 저장한 arraylist의 원소에 접근
			System.out.println("이미 식단이 입력된 날짜입니다");
			return;
		}
		System.out.print("식단을 빈칸없이 입력해주세요 : ");
		String diet = sc.next();
		System.out.print("가격을 입력해주세요 : ");
		int money = sc.nextInt();
		Diet_list.diets.get(day).diet_add(diet, money);
	}
	public void diets_day_show() {
		System.out.print("식단을 출력할 날짜를 선택하세요 : ");
		int day = sc.nextInt();
		--day;
		if (day < 0 || day >= 31) {
			System.out.println("날짜를 잘못 입력했습니다");
			return;
		}
		System.out.print((day + 1) + "일의 식단은 "); // 앞에서 --day로 날짜 유효 체크했기 때문에 +1을 해주어서 정상적인 날짜 출력
		Diet_list.diets.get(day).diet_show();
	}
	public static void diets_all_show() {
		int count = 0;
		for(int i = 0; i < Diet_list.diets.size(); i++) { // 전체 반복문으로 확인
			if (Diet_list.diets.get(i).diet_get() != null) {
				System.out.print((i + 1) + "일의 식단은 ");
				Diet_list.diets.get(i).diet_show();
			}
			else
				count++; // 아직 식단이 입력되지 않은 날 체크
		}
		if (count == Diet_list.diets.size())
			System.out.println("아직 입력된 식단이 없습니다");
	}
}
interface val_print {
	void valprint();
}
class Student<T> implements menu {
	private T stunumber; // 학번
	private int money;     // 현재 잔액
	private Scanner sc;
	private Set<Integer> buy_list = new HashSet<>();
	val_print val = () -> {System.out.println("현재 잔액 : " + money);}; // 람다
	
	public Student(T stunumber) {
		this.stunumber = stunumber;
		this.money = 0;
		sc = new Scanner(System.in);
	}
	public T stunumber_get() {return stunumber;}
	@Override
	public void print_menu() {
		System.out.println("1. 현재 잔액 확인");
		System.out.println("2. 입금하기");
		System.out.println("3. 학식 구매하기");
		System.out.println("4. 구매 목록 확인하기");
		System.out.println("5. 학생 창 나가기");
		System.out.print("메뉴를 선택하세요 : ");
	}
	@Override
	public void start_menu() {
		System.out.println("안녕하세요, " + stunumber + "님!");
		System.out.println("-----------------------");
		while(true) {
			print_menu();
			int menu = sc.nextInt();
			System.out.println("-----------------------");
			switch(menu) {
			case 1:
				stu_show_money();
				System.out.println("-----------------------");
				break;
			case 2:
				stu_add_money();
				System.out.println("-----------------------");
				break;
			case 3:
				stu_buy_diet();
				System.out.println("-----------------------");
				break;
			case 4:
				stu_check();
				System.out.println("-----------------------");
				break;
			case 5:
				System.out.println("학생 창을 종료합니다");
				System.out.println("-----------------------");
				return;
			default:
				System.out.println("메뉴를 다시 입력하세요");
				System.out.println("-----------------------");
			}
		}
	}
	public void stu_show_money() {val.valprint();}
	public void stu_add_money() {
		System.out.print("입금하고자하는 잔액을 입력해주세요 : ");
		int money = sc.nextInt();
		if (money <= 0) { // 음수의 돈을 입금할 수 없도록 설정
			System.out.println("잘못된 금액을 입력했습니다");
			return;
		}
		this.money += money;
	}
	public void stu_buy_diet() {
		System.out.println("----- 학식 목록 -----");
		Manager.diets_all_show();
		System.out.print("학식 구매를 원하는 날짜를 입력해주세요 : ");
		int day = sc.nextInt();
		day--;
		if (Diet_list.diets.get(day).diet_get() == null) {        // 식단이 입력된 날만 결재 가능하도록
			System.out.println("아직 식단 입력이 안 된 날짜입니다");
			return;
		}
		if (buy_list.contains(day)) {
			System.out.println("이미 식권을 구매하였습니다");
			return;
		}
		if (this.money < Diet_list.diets.get(day).diet_money()) { // 현재 남은 잔액보다 식단의 가격이 더 비싸면
			System.out.println("잔액이 부족합니다");
			return;
		}
		this.money -= Diet_list.diets.get(day).diet_money();
		buy_list.add(day);
		System.out.println("구매가 완료되었습니다");
	}
	public void stu_check() {
		if (buy_list.size() == 0) {
			System.out.println("구매한 식권이 없습니다");
			return;
		}
		System.out.println("----- 구매한 학식 목록 -----");
		Iterator<Integer> itr = buy_list.iterator();
		while(itr.hasNext()) {
			int day = itr.next() + 1;
			System.out.print(day + "일, ");
		}
		System.out.println("을 구매하였습니다");
	}
}
public class proj_2019204034 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Manager manager = new Manager();
		String pass; // 관리자 비밀 번호
		List<Student<Integer>> stulist = new ArrayList<>(); // 학번을 integer 자료형으로만 입력받게
		int stunumber;
		
		System.out.println("----- 광운대 학식 서비스 -----");
		while(true) {
			System.out.println("1. 관리자");
			System.out.println("2. 학생");
			System.out.println("3. 프로그램 종료");
			System.out.print("메뉴를 선택하세요 : ");
			int menu = sc.nextInt();
			System.out.println("-----------------------");
			
			switch(menu) {
			case 1:
				System.out.print("관리자 비밀번호를 입력하세요 : ");
				pass = sc.next();
				if (pass.equals(Manager.password)) {
					System.out.println("안녕하세요, 관리자님!");
					System.out.println("-----------------------");
					manager.start_menu();
				}
				else {
					System.out.println("비밀번호를 다시 입력해주세요");
					System.out.println("-----------------------");
				}
				break;
			case 2:
				System.out.print("학번을 입력하세요 : ");
				stunumber = sc.nextInt();
				int j = 0;
				if (stulist.size() == 0) {
					stulist.add(new Student<Integer>(stunumber));
					j = 0;
				}
				else {
					int i = 0;
					for(; i < stulist.size(); i++) {
						if (stunumber == stulist.get(i).stunumber_get()) { // 학생 객체가 중복 생성되지 않도록 
							j = i;
							break;
						}
					}
					if (i == stulist.size()) {
						j = stulist.size();
						stulist.add(new Student<Integer>(stunumber));
					}
				}
				stulist.get(j).start_menu();
				break;
			case 3:
				System.out.println("프로그램을 이용해주셔서 감사합니다");
				return;
			default:
				System.out.println("메뉴를 다시 입력하세요");
				System.out.println("-----------------------");
			}
		}
	}
}