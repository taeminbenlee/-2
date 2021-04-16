package main;

import java.util.Scanner;

import dao.empDao;
import dto.empDto;



public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			empDao dao = empDao.getInstance();
			
			System.out.println("==================menu==================");
			System.out.println("1.사원추가");
			System.out.println("2.사원삭제");
			System.out.println("3.사원검색");
			System.out.println("4.사원수정");
			System.out.println("5.모든사원출력");
			System.out.println("6.종료");
			System.out.print("============================================");
			System.out.println("원하는 작업을 선택하세요");
			int work = sc.nextInt();
			
			switch(work) {
				case 1:
					dao.addMember();		
					break;
				case 2:
					dao.deleteMember();
					break;
				case 3:
					dao.detailInfo();
					
					break;
				case 4:
					dao.updateMember();
					break;
				case 5:
					dao.getAllMemberList();
					break;
				case 6:
					System.exit(0);					
					break;			
			}		
			
			
		}	
		
		
	}

}
