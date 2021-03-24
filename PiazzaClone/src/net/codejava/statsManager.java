package net.codejava;

import java.util.List;

import net.DBResults.UserStats;

public class statsManager {
	// Singleton instance
	private static statsManager instance;
	
	private statsManager() {}
	
	public static statsManager getInstance() {
		if(instance == null)
		{
			instance = new statsManager();
		}
		return instance;
	}
	
	public List<UserStats> getStats() {
		return DatabaseAPI.getInstance().getStats();
	}
}
