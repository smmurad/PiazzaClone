package net.codejava;

import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import net.DBResults.Post;
import net.DBResults.TableEnum;
import net.DBResults.UserStats;

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
		String values = "";
		values += Integer.toString(ThreadLocalRandom.current().nextInt(0, 1000000000)); //Creates random FolderID with very low chance of duplicate
		values += ",";
		values += Post.paddWithFnut(folderName);
		values += ",";
		values += "null"; //parentFolder, assumed to be null
		values += ",";
		values += "1"; //Assumes course to be 1
		Driver.getInstance().InsertIntoTable(TableEnum.folder, values);
	}

	public void CreateNewPost(Post newPost, String threadID, String folderID) {
		
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
		if (threadID == "newThread") 
		{
			String newThreadID = Integer.toString(ThreadLocalRandom.current().nextInt(0, 1000000000));
			values += newThreadID;
			Driver.getInstance().createNewThread(newThreadID, folderID); // threadID int 
		} 
		else 
		{
			values += threadID;
		}
		values += ",";
		values += newPost.tagID; // tagID int 
		values += ",";
		values += Post.paddWithFnut(newPost.dateTime.toString()); // postDate Date (String)  
		values += ",";
		values += Post.paddWithFnut(newPost.postText); // text string 
		
		Driver.getInstance().InsertIntoTable(TableEnum.post, values);
		
	}
	
	public void CreateRelationBetweeenPosts(String postID, String replyID) {
		String values = "";
		values += postID;
		values += ",";
		values += replyID;
		Driver.getInstance().InsertIntoTable(TableEnum.relationbetweenposts, values);
	}

	public List<Integer> getAllElementsContainingSearchword(TableEnum post, String ColumnName, String searchText,  String specificSearchword) {
		// TODO Auto-generated method stub
		return Driver.getInstance().getAllElementsContainingSearchword(post, ColumnName, searchText, specificSearchword);
	}
	public int getUniqueID(String email) {
		return Driver.getInstance().getUniqueID(email);
	}
	
	public int updateStatistics() {
		return Driver.getInstance().updateStatistics();
	}
	public List<UserStats> getStats() {
		try {
			// Get list of result
			ResultSet resultSets = Driver.getInstance().getStats();
			
			// Convert result into stats objects
			List<UserStats> statsDatum = new ArrayList<UserStats>();

			
			String username;
			int postsViewed;
			int postsCreated;
			if(resultSets == null) {
				System.out.println("result set is null");
				return null;
			}

			while(resultSets.next())
			{
				username = resultSets.getString(1);
				postsViewed = resultSets.getInt(2);
				postsCreated = resultSets.getInt(3);

				
				System.out.println("Username: " + username + "pview: " + postsViewed + "postsCreated: " + postsCreated);
				
				UserStats newStat = new UserStats(username, postsViewed, postsCreated);
				statsDatum.add(newStat);
			}
			return statsDatum;
		}catch (Exception e) {
			System.err.println("[DatabaseAPI]: " + e.getCause());
			return null;
		}
	}
	
	public List<String> getAllThreads(){
		return Driver.getInstance().getAllElements(TableEnum.thread, "threadID");
	}
	
	public String getPostFromThread(String ThreadID) {
		String text ="";
		List<String> textList = Driver.getInstance().getAllElementsSorted(TableEnum.post, "text", "postDate", true, "threadID", ThreadID);
		for (int i=0; i<textList.size(); i++) {
			text += textList.get(i);
			text += "\n\n --------------------------- \n\n";
		}
		return text;
	}
	
}
