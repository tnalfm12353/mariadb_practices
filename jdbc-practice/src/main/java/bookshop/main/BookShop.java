package bookshop.main;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookShop {

	public static void main(String[] args) {
		displayBookInfo();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		
		Long no = scanner.nextLong();
		scanner.close();
		
		Boolean result = new BookDao().update(no, "대여중");
		BookVo vo = new BookDao().findById(no);
		System.out.print(vo.getTitle());
		System.out.println(result? "이(가) 대여 됐습니다." :"는 대여중입니다.");
		displayBookInfo();
	}

	public static void displayBookInfo() {
		System.out.println("*****도서 정보 출력하기******");
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			vo.print();
		}
	}
}
