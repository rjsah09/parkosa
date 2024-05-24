package com.parkosa.sign;

public class SignedAccount {
	private static SignedAccount account;
	private static String phoneNumber;
	
	private SignedAccount() {}
	
	public static SignedAccount getInstance() {
		if (account == null) {
            account = new SignedAccount();
        }
        
        return account;
	}
	
	public static String getPhoneNumber() {
		return phoneNumber;
	}
	
	public static void signIn(String phoneNumber) {
		SignedAccount.phoneNumber = phoneNumber;
	}
	
	public static void signOut() {
		SignedAccount.phoneNumber = null;
	}

}
