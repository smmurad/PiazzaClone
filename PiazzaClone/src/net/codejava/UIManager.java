package net.codejava;

public class UIManager {
	
	static boolean isTextbased = true;
	
	// Singleton instance
	private static UIManager instance;
	
	private UIManager() {}
	
	public static UIManager getInstance() {
		if(instance == null)
		{
			instance = new UIManager();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		if(isTextbased)
		{
			getInstance().startTextbasedLogin();
		}
		else
		{
			
		}
	}

	private void startTextbasedLogin() {
		try
		{
			String username = InputHandler.getStringInput("Type your username (mail):");
			
			String password = InputHandler.getStringInput("Type your password:");
			
			if(UserHandler.getInstance().Login(username, password))
				System.out.println("Login success");
			else
				System.out.println("Login failed, username or password wrong...");
		}
		catch(Exception e)
		{
            System.err.println("[UIManager] Input Exception");
		}
		
	}

}
