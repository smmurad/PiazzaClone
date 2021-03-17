package net.codejava;

public class UserHandler {
	
	private boolean isLoggedIn = false;

	// Singleton instance
	private static UserHandler instance;
	
	private UserHandler() {}
	
	public boolean isLoggedIn() { return isLoggedIn;} 
	
	public static UserHandler getInstance() {
		if(instance == null)
		{
			instance = new UserHandler();
		}
		return instance;
	}

	public boolean Login(String userName, String Password)
	{
		if(DatabaseAPI.getInstance().VerifyPassword(userName, Password))
		{
			// Might want a session token
			isLoggedIn = true;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void Logout()
	{
		isLoggedIn = false;
	}
}
