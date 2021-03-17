package net.codejava;

public class DatabaseAPI {
	
	// Singleton instance
	private static DatabaseAPI instance;
	
	private DatabaseAPI() {}
	
	public static DatabaseAPI getInstance() {
		if(instance == null)
		{
			instance = new DatabaseAPI();
		}
		return instance;
	}
 
	public boolean VerifyPassword(String username, String passwordToVerify)
	{
		String password = Driver.getInstance().getSingleColElement(Driver.UserTableName, "password", "email", username);
		
		return password.equals(passwordToVerify);
	}
}
