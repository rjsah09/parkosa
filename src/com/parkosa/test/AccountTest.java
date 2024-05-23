package com.parkosa.test;

import java.time.LocalDate;

import com.parkosa.dao.AccountDAO;
import com.parkosa.vo.AccountVO;

public class AccountTest {
	
	public static void main(String[] args) {
		accountTest();
	}
	
	public static void accountTest() {
		AccountVO man1 = new AccountVO("01012345678", "12345", "홍길동", "abcd@naver.com", LocalDate.now());
		AccountVO man2 = new AccountVO("01012345679", "0000", "임꺽정", "ad@naver.com", LocalDate.now());
		
		AccountDAO accountDAO = new AccountDAO();
		accountDAO.insertAccount(man1);
		accountDAO.insertAccount(man2);
	}
	
}
