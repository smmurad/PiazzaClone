package net.codejava;

import net.DBResults.TableEnum;

// This class is only for the current user using the system. 
public class UserHandler {
	
	private boolean isLoggedIn = false;
	
	public int uniqueId = 0;

	// Singleton instance
	private static UserHandler instance;

	public boolean IsInstructor = true;
	
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
			uniqueId = DatabaseAPI.getInstance().getUniqueID(userName);
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

	public int GetUniqueID(String email) 
	{
		return DatabaseAPI.getInstance().getUniqueID(email);
	}
}
