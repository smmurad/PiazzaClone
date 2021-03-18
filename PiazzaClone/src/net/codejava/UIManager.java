package net.codejava;

// This class handles all imput
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
			getInstance().startAddPost();
		}
		else
		{
			
		}
	}

	private boolean startTextbasedLogin() {
		try
		{
			String username = InputHandler.getStringInput("Type your username (mail):");
			
			String password = InputHandler.getStringInput("Type your password:");
			
			if(UserHandler.getInstance().Login(username, password))
			{
				System.out.println("Login success");
				return true;
			}
			else
			{
				System.out.println("Login failed, username or password wrong...");
				return false;
			}
		}
		catch(Exception e)
		{
            System.err.println("[UIManager] Input Exception");
            return false;
		}
		
	}
	
	private void startAddPost() {
		try
		{
			String postTitle = InputHandler.getStringInput("Type your post title:");
			String postText = InputHandler.getStringInput("Type your post text:");
			String folderName = InputHandler.getStringInput("Enter which folder this post should be added to:");
			
			if(PostManager.getInstance().AddQuestionPost(postTitle, postText, folderName))
				System.out.println("Post can be created");
			else
				System.out.println("Failed to create post...");
		}
		catch(Exception e)
		{
            System.err.println("[UIManager] Exeption: " + e.getMessage());
		}
	}
	
}
