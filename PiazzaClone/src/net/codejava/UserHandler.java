package net.codejava;

// This class is only for the current user using the system. 
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

	public int GetUniqueID() 
	{
		System.out.println("Get Unique UserID not yet implemented");
		return 0;
	}
}
