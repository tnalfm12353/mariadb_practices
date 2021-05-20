package bookshop.test;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		// insertTest();

	}

	private static void insertTest() {
		BookVo vo = null;
		
		vo = new BookVo();
		vo.setAuthorNo(1l);
		vo.setStatus("대여가능");
		vo.setTitle("트와일라잇");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(1l);
		vo.setStatus("대여가능");
		vo.setTitle("뉴문");
		new BookDao().insert(vo);

		vo = new BookVo();
		vo.setAuthorNo(1l);
		vo.setStatus("대여가능");
		vo.setTitle("이클립스");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(1l);
		vo.setStatus("대여가능");
		vo.setTitle("브레이킹던");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(2l);
		vo.setStatus("대여가능");
		vo.setTitle("아리랑");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(3l);
		vo.setStatus("대여가능");
		vo.setTitle("젊은그들");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(4l);
		vo.setStatus("대여가능");
		vo.setTitle("아프니깐 청춘이다");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(5l);
		vo.setStatus("대여가능");
		vo.setTitle("귀천");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(2l);
		vo.setStatus("대여가능");
		vo.setTitle("태백산맥");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(6l);
		vo.setStatus("대여가능");
		vo.setTitle("풀하우스");
		new BookDao().insert(vo);
	}

}
