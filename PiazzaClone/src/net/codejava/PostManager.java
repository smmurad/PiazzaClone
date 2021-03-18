package net.codejava;

import java.util.concurrent.ThreadLocalRandom;
import java.sql.Timestamp;

import net.DBResults.Post;
import net.DBResults.PostType;



// This class handles the creation, viewing, changing and deletion of posts
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
	
	public boolean AddQuestionPost(String postTitle, String postText, String folderName) {
		
		try
		{
			
			Timestamp sqlDate = new Timestamp(System.currentTimeMillis());
			
			// Create a new post
			Post newPost = new Post(postTitle, postText, PostType.question, sqlDate);
			
			// Fill it up
			newPost.createdBy = UserHandler.getInstance().GetUniqueID();		
			newPost.postID = getUniqueID();
			newPost.colorID = PostManager.GetColor(newPost.postType);
			
			// If folder does not exist, create new. 
			if(!DatabaseAPI.getInstance().FolderExist(folderName))
			{
				System.out.println("Folder does not exist, creating new folder");
				DatabaseAPI.getInstance().CreateFolder(folderName);
			}
			
			// Try to add it to the Database
			DatabaseAPI.getInstance().CreateNewPost(newPost);
			
			return true;
		}
		catch (Exception e)
		{
			System.err.println("[PostManager] Exeption: " + e.getMessage());
			return false;
		}
		
	}
	
	// TODO Should be changed to something that actually checks the db
	private static int getUniqueID() {
		return ThreadLocalRandom.current().nextInt(0, 1000000000);
		
	}

	// Converts type into color
	private static int GetColor(PostType postType) {
		switch(postType) {
		case answer:
			return 0; // 
		case followup:
			return 1;
		case head:
			return 2;
		case question:
			return 3;
		default:
			break;
		
		}
		return -1;
	}
	
}
