package net.codejava;

import java.util.*;

import net.DBResults.Post;
import net.DBResults.TableEnum;

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
		String password = Driver.getInstance().getSingleColElement(TableEnum.users, "password", "email", username);
		
		return password.equals(passwordToVerify);
	}
	
	// Checks if db returns any result with the foldername search
	public boolean FolderExist(String folderName) {
		String result = Driver.getInstance().getSingleColElement(TableEnum.folder, "name", "name", folderName);
		return result != "";
	}

	public void CreateFolder(String folderName) {
		System.out.println("Creating folder Not yet implemented");
	}

	public void CreateNewPost(Post newPost) {
		
		String values = "";
		values += newPost.postID; // postID int
		values += ",";
		values += newPost.colorID; //colorID int
		values += ",";
		values += newPost.createdBy; // userID int
		values += ",";
		values += 0; // goodComments int
		values += ",";
		values += 0; // timesViewed int
		values += ",";
		values += Post.paddWithFnut(newPost.postTypeString()); // posttype String
		values += ",";
		values += newPost.getThreadID(); // threadID int 
		values += ",";
		values += newPost.tagID; // tagID int 
		values += ",";
		values += Post.paddWithFnut(newPost.dateTime.toString()); // postDate Date (String)  
		values += ",";
		values += Post.paddWithFnut(newPost.postText); // text string 
		
		Driver.getInstance().InsertIntoTable(TableEnum.post, values);
		
	}

	public List<Integer> getAllElementsContainingSearchword(TableEnum post, String ColumnName, String searchText,  String specificSearchword) {
		// TODO Auto-generated method stub
		return Driver.getInstance().getAllElementsContainingSearchword(post, ColumnName, searchText, specificSearchword);
	}
	
}
