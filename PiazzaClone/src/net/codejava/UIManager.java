package net.codejava;

import java.util.List;

import net.DBResults.UserStats;
import net.DBResults.TableEnum;


// This class handles all input
public class UIManager {
	
	static boolean isTextbased = false;
	
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
			getInstance().startSearchForPostContent();
			getInstance().startViewStatistics();
		}
		else
		{
			//getInstance().startGuiLogin();
			//getInstance().startGuiAddPost();
			//getInstance().startRunSearchForWord();
			getInstance().startGuiNavigate();
		}
		System.out.println("-----------Program ended-------------");
	}

	private void startSearchForPostContent() {
		try {
			String searchword = InputHandler.getStringInput("Type in searchword:");
			List<Integer> ids = PostManager.getInstance().SearchForPost(searchword);
			//for loop view results
			if(ids.isEmpty())
			{
				System.out.println("No posts containing searchword: " + searchword + " ...\n\n");
				return;
			}
			System.out.println("Found "  + ids.size() + " matching rows with searchword: "  + searchword + "\n");
			for (int id:ids) {
				System.out.println("postID: " + id);
			}

		}
		
		catch(Exception e)
		{
            System.err.println("[UIManager] Input Exception");
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
	
	private boolean startGuiLogin() {
		net.Gui.LogIn.runLogin(null);
		return true;
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
	
	private boolean startGuiAddPost() {
		net.Gui.AddPost.runAddPost(null);
		return true;
	}
	
	private boolean startRunSearchForWord() {
		net.Gui.SearchForWord.runSearchForWord(null);
		return true;
	}
	
	private boolean startReplyToPost() {
		net.Gui.reply.runReply(null);
		return true;
	}
	
	private boolean startGuiNavigate() {
		net.Gui.Navigate.runNavigate(null);
		return true;
	}
	private boolean startTreadView() {
		net.Gui.ThreadOverview.runThreadOverview(null);
		return true;
	}
	private boolean startShowThread(String ThreadID) {
		net.Gui.ShowThread.runShowThread(ThreadID);
		return true;
	}
	
	
	
	
	private void startViewStatistics() {
		if(!UserHandler.getInstance().IsInstructor)
		{
			System.out.println("Cannot view stats unless you are instructor");
			return;
		}
		try
		{
			List<UserStats> datas = statsManager.getInstance().getStats();
			if(datas.size() > 0)
			{
				System.out.println("USER STATS: \n\n ------------------------- \n");
				for(UserStats stats : datas)
				{
					System.out.println("Username: " + stats.username + "\nPosts viewed: " + stats.postsViewed + "\nPosts created: " + stats.postsCreated + "\n");
				}
				
			}
			// Print data for loop
			else
				System.out.println("No statistics to show...");
		}
		catch(Exception e)
		{
            System.err.println("[UIManager] Exeption: " + e.getMessage());
		}
	}
	
}
