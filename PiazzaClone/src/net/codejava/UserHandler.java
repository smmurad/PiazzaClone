package net.codejava;

public class UserHandler {
	
	private boolean isLoggedIn = false;
	
	//Is this at the right place?
	private boolean folderExists = false;

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
	
	//Is this supposed to be here or somewhere else? Change this function anyway
	public boolean Post(String postText)
	{
		String bob = "Bob"; //Dummy variable for testing
		//change "1 == 1" to something like "check if the folder exists", something a la (DatabaseAPI.getInstance().VerifyPassword(userName, Password))
		if(bob == "Bob")
		{
			folderExists = true;
			return true;
		}
		else
		{
			return false;
		}
	}
}
