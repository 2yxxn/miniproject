import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

interface menu {
	void print_menu(); // �޴� ����
	void start_menu(); // ���� �޴� ����
}
class Diet {
	private String diet; // �Ĵ�
	private int money;   // ����
	
	public void diet_add(String diet, int money) {this.diet = diet; this.money = money;} // �Ĵ��̶� ���� �Է¹ޱ�
	public String diet_get() {return diet;} // �Ĵ� ���
	public int diet_money() {return money;} // ���� ���
	public void diet_show() {
		if (diet == null)
			System.out.println("���� �Էµ��� �ʾҽ��ϴ�");
		else
			System.out.println(diet + "�Դϴ�");
	}
}
class Diet_list {
	 public static ArrayList<Diet> diets = new ArrayList<Diet>();
}
class Manager implements menu {
	static String password = "0000"; // �ۿ��� �׳� Manager.password�� �� �� �ְ�
	private Scanner sc;
	
	public Manager() {
		sc = new Scanner(System.in);
		for(int i = 0; i < 31; i++)			 // 31�ϱ��� �����Ѵٰ� ����
			Diet_list.diets.add(new Diet());
	}
	@Override
	public void print_menu() {
		System.out.println("1. �Ĵ� �Է� (1��  ~ 31��)");
		System.out.println("2. ��¥�� �Ĵ� ��� (1��  ~ 31��)");
		System.out.println("3. ��ü �Ĵ� ��� (1��  ~ 31��)");
		System.out.println("4. ������ â ������");
		System.out.print("�޴��� �����ϼ��� : ");
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
				System.out.println("������ â�� �����մϴ�");
				System.out.println("-----------------------");
				return;
			default:
				System.out.println("�޴��� �ٽ� �Է��ϼ���");
				System.out.println("-----------------------");
			}
		}
	}
	public void diets_add() { // �Ĵ� �Է�
		System.out.print("�Ĵ��� �Է��� ��¥�� �����ϼ��� : ");
		int day = sc.nextInt();
		--day;
		if (day < 0 || day >= 31) { // �ùٸ� ��¥ üũ
			System.out.println("��¥�� �߸� �Է��߽��ϴ�");
			return;
		}
		if (Diet_list.diets.get(day).diet_get() != null) { // diets.get(day)�� ���ؼ� Diet�� ������ arraylist�� ���ҿ� ����
			System.out.println("�̹� �Ĵ��� �Էµ� ��¥�Դϴ�");
			return;
		}
		System.out.print("�Ĵ��� ��ĭ���� �Է����ּ��� : ");
		String diet = sc.next();
		System.out.print("������ �Է����ּ��� : ");
		int money = sc.nextInt();
		Diet_list.diets.get(day).diet_add(diet, money);
	}
	public void diets_day_show() {
		System.out.print("�Ĵ��� ����� ��¥�� �����ϼ��� : ");
		int day = sc.nextInt();
		--day;
		if (day < 0 || day >= 31) {
			System.out.println("��¥�� �߸� �Է��߽��ϴ�");
			return;
		}
		System.out.print((day + 1) + "���� �Ĵ��� "); // �տ��� --day�� ��¥ ��ȿ üũ�߱� ������ +1�� ���־ �������� ��¥ ���
		Diet_list.diets.get(day).diet_show();
	}
	public static void diets_all_show() {
		int count = 0;
		for(int i = 0; i < Diet_list.diets.size(); i++) { // ��ü �ݺ������� Ȯ��
			if (Diet_list.diets.get(i).diet_get() != null) {
				System.out.print((i + 1) + "���� �Ĵ��� ");
				Diet_list.diets.get(i).diet_show();
			}
			else
				count++; // ���� �Ĵ��� �Էµ��� ���� �� üũ
		}
		if (count == Diet_list.diets.size())
			System.out.println("���� �Էµ� �Ĵ��� �����ϴ�");
	}
}
interface val_print {
	void valprint();
}
class Student<T> implements menu {
	private T stunumber; // �й�
	private int money;     // ���� �ܾ�
	private Scanner sc;
	private Set<Integer> buy_list = new HashSet<>();
	val_print val = () -> {System.out.println("���� �ܾ� : " + money);}; // ����
	
	public Student(T stunumber) {
		this.stunumber = stunumber;
		this.money = 0;
		sc = new Scanner(System.in);
	}
	public T stunumber_get() {return stunumber;}
	@Override
	public void print_menu() {
		System.out.println("1. ���� �ܾ� Ȯ��");
		System.out.println("2. �Ա��ϱ�");
		System.out.println("3. �н� �����ϱ�");
		System.out.println("4. ���� ��� Ȯ���ϱ�");
		System.out.println("5. �л� â ������");
		System.out.print("�޴��� �����ϼ��� : ");
	}
	@Override
	public void start_menu() {
		System.out.println("�ȳ��ϼ���, " + stunumber + "��!");
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
				System.out.println("�л� â�� �����մϴ�");
				System.out.println("-----------------------");
				return;
			default:
				System.out.println("�޴��� �ٽ� �Է��ϼ���");
				System.out.println("-----------------------");
			}
		}
	}
	public void stu_show_money() {val.valprint();}
	public void stu_add_money() {
		System.out.print("�Ա��ϰ����ϴ� �ܾ��� �Է����ּ��� : ");
		int money = sc.nextInt();
		if (money <= 0) { // ������ ���� �Ա��� �� ������ ����
			System.out.println("�߸��� �ݾ��� �Է��߽��ϴ�");
			return;
		}
		this.money += money;
	}
	public void stu_buy_diet() {
		System.out.println("----- �н� ��� -----");
		Manager.diets_all_show();
		System.out.print("�н� ���Ÿ� ���ϴ� ��¥�� �Է����ּ��� : ");
		int day = sc.nextInt();
		day--;
		if (Diet_list.diets.get(day).diet_get() == null) {        // �Ĵ��� �Էµ� ���� ���� �����ϵ���
			System.out.println("���� �Ĵ� �Է��� �� �� ��¥�Դϴ�");
			return;
		}
		if (buy_list.contains(day)) {
			System.out.println("�̹� �ı��� �����Ͽ����ϴ�");
			return;
		}
		if (this.money < Diet_list.diets.get(day).diet_money()) { // ���� ���� �ܾ׺��� �Ĵ��� ������ �� ��θ�
			System.out.println("�ܾ��� �����մϴ�");
			return;
		}
		this.money -= Diet_list.diets.get(day).diet_money();
		buy_list.add(day);
		System.out.println("���Ű� �Ϸ�Ǿ����ϴ�");
	}
	public void stu_check() {
		if (buy_list.size() == 0) {
			System.out.println("������ �ı��� �����ϴ�");
			return;
		}
		System.out.println("----- ������ �н� ��� -----");
		Iterator<Integer> itr = buy_list.iterator();
		while(itr.hasNext()) {
			int day = itr.next() + 1;
			System.out.print(day + "��, ");
		}
		System.out.println("�� �����Ͽ����ϴ�");
	}
}
public class proj_2019204034 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Manager manager = new Manager();
		String pass; // ������ ��� ��ȣ
		List<Student<Integer>> stulist = new ArrayList<>(); // �й��� integer �ڷ������θ� �Է¹ް�
		int stunumber;
		
		System.out.println("----- ����� �н� ���� -----");
		while(true) {
			System.out.println("1. ������");
			System.out.println("2. �л�");
			System.out.println("3. ���α׷� ����");
			System.out.print("�޴��� �����ϼ��� : ");
			int menu = sc.nextInt();
			System.out.println("-----------------------");
			
			switch(menu) {
			case 1:
				System.out.print("������ ��й�ȣ�� �Է��ϼ��� : ");
				pass = sc.next();
				if (pass.equals(Manager.password)) {
					System.out.println("�ȳ��ϼ���, �����ڴ�!");
					System.out.println("-----------------------");
					manager.start_menu();
				}
				else {
					System.out.println("��й�ȣ�� �ٽ� �Է����ּ���");
					System.out.println("-----------------------");
				}
				break;
			case 2:
				System.out.print("�й��� �Է��ϼ��� : ");
				stunumber = sc.nextInt();
				int j = 0;
				if (stulist.size() == 0) {
					stulist.add(new Student<Integer>(stunumber));
					j = 0;
				}
				else {
					int i = 0;
					for(; i < stulist.size(); i++) {
						if (stunumber == stulist.get(i).stunumber_get()) { // �л� ��ü�� �ߺ� �������� �ʵ��� 
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
				System.out.println("���α׷��� �̿����ּż� �����մϴ�");
				return;
			default:
				System.out.println("�޴��� �ٽ� �Է��ϼ���");
				System.out.println("-----------------------");
			}
		}
	}
}