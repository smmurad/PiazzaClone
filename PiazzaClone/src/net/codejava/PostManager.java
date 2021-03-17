package net.codejava;

public class PostManager {

	static boolean isTextbased = true;
	
	// Singleton instance
	private static PostManager instance;
	
	private PostManager() {}
	
	public static PostManager getInstance() {
		if(instance == null)
		{
			instance = new PostManager();
		}
		return instance;
	}
	
	private void startAddPost() {
		try
		{
			String postText = InputHandler.getStringInput("Type your post text:");
			
			if(UserHandler.getInstance().Post(postText))
				System.out.println("Post can be created");
			else
				System.out.println("Failed to create post...");
		}
		catch(Exception e)
		{
            System.err.println("[UIManager] Input Exception");
		}
	}
	
}
