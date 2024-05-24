package com.parkosa.test;

import java.time.LocalDate;

import com.parkosa.dao.AccountDAO;
import com.parkosa.vo.AccountVO;

public class AccountTest {
	
	public static void main(String[] args) {
		//accountTest();
		dupTest();
	}
	
	public static void accountTest() {
		AccountVO man1 = new AccountVO("01012345678", "12345", "aaa", "abcd@naver.com", LocalDate.now());
		AccountVO man2 = new AccountVO("01012345679", "0000", "bbb", "ad@naver.com", LocalDate.now());
		
		AccountDAO accountDAO = new AccountDAO();
		accountDAO.insertAccount(man1);
		accountDAO.insertAccount(man2);
	}
	
	public static void dupTest() {
		AccountDAO accountDAO = new AccountDAO();
		boolean dup1 = accountDAO.checkPhoneNumberDuplicated("01000000000");
		System.out.println(dup1);
		boolean dup2 = accountDAO.checkPhoneNumberDuplicated("01000");
		System.out.println(dup2);
	}
	
}
