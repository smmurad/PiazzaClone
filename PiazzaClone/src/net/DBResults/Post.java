package net.DBResults;

import java.sql.Timestamp;

// This is the dataclass for a Post
public class Post {
	
	public Post(String title, String postText, PostType postType, Timestamp dateTime) 
	{
		this.title = title;
		this.postText = postText;
		this.postType = postType;
		this.dateTime = dateTime;
	}

	public String title;
	public String postText;
	public PostType postType;
	public int postID;
	
	
	public String folderName;
	public int tagID;
	// Unique ID of User
	public int threadID;
	public int createdBy;
	public int colorID = 0;	
	public int timesViewed = 0;
	public Timestamp dateTime;
	
	public void Save()
	{
		
	}
	
	public static String paddWithFnut(String textToPadd)
	{
		return paddWithChar(textToPadd, "\"");
	}
	
	public static String paddWithChar(String textToPadd, String charToPadd) {
		String paddedString = charToPadd + textToPadd + charToPadd;
		return paddedString;
	}

	public String postTypeString() {
		switch(postType)
		{
		case answer:
			return "answer";
		case followup:
			return "followup";
		case head:
			return "head";
		case question:
			return "question";
		default:
			break;
		
		}
		return "";
	}

	public int getThreadID() {
		System.out.println("GetThreadID not yet implemented");
		return 0;
	}
	
	
}
