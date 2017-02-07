package coen359.vendingmachine.admin.authentication;

import coen359.vendingmachine.admin.Admin;
import coen359.vendingmachine.admin.AdminService;
import coen359.vendingmachine.admin.LoginView;

public class PasswordStrategy implements AuthenticateStrategy {

	String password;

	public PasswordStrategy(String password){
		this.password = password;
	}

	@Override
	public Boolean authenticateLogin(String userName) {

		Boolean allowAccess = false;

		if ( userName.equals("admin") || userName.equals("supervisor") ){

			Admin result = new AdminService().readAdmin(userName);

			if (result.getPassword().equals(password)) {

				allowAccess = true;
			}
		}

		return allowAccess;
	}



}
