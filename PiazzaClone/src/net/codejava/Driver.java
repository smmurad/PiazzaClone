package net.codejava;

import java.sql.*;
import java.util.*;

import net.DBResults.Post;
import net.DBResults.TableEnum;

public class Driver {
	
	private String _dbUrl = "jdbc:mysql://localhost:3306/piazza";
	private String _dbUserName = "root";
	private String _dbPassword = "12345678";
	

	private Connection _conn;
	private ResultSet _result;
	private Statement _statement;
		
	// Singleton instance
	private static Driver instance;
	
	private Driver() {}
	
	public static Driver getInstance() {
		if(instance == null)
		{
			instance = new Driver();
		}
		return instance;
	}
 
	// Expects to only receive one row from the table as a result, will return element from the last row if multiple is received. 
	public String getSingleColElement(TableEnum tableNameEnum, String colName, String attributeToSearch, String specificSearchword)
	{
		try 
		{

			String tableName = "";
			
			tableName = getTableName(tableNameEnum);
			
			String sqlQuery = "SELECT " + colName + " FROM " + tableName + " WHERE " + attributeToSearch + " = \""+ specificSearchword + "\";";
			System.out.println("SQL query: " + sqlQuery);

			_result = queryDatabase(sqlQuery);
			
			String resultStr = "";			
			while(_result.next()) {
				resultStr = _result.getString(1);
			}
			
			return resultStr;
			
	    } catch (Exception e) {
            System.err.println("[DRIVER]: " + e.getStackTrace());
	    } finally {
	        close();
	    }
		return null;
	}

	private ResultSet queryDatabase(String sqlQuery) {
		
		try {
			_conn = DriverManager.getConnection(_dbUrl, _dbUserName, _dbPassword);
			
			_statement = _conn.createStatement();
			
			_result = _statement.executeQuery(sqlQuery);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _result;
	}
	
	private ResultSet updateDatabase(String sqlQuery) {
		
		try {
			_conn = DriverManager.getConnection(_dbUrl, _dbUserName, _dbPassword);
			
			_statement = _conn.createStatement();
			
			_statement.executeUpdate(sqlQuery);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return _result;
	}
	
	public void InsertIntoTable(TableEnum tableNameEnum, String values)
	{
		try 
		{
			
			String tableName = getTableName(tableNameEnum);
			
			String sqlQuery = "INSERT INTO " + tableName + " VALUES(" + values + ");";
			System.out.println("SQL query: " + sqlQuery);

			updateDatabase(sqlQuery);
			
	    } catch (Exception e) {
            System.err.println("[DRIVER]: " + e.getStackTrace());
	    } finally {
	        close();
	    }
	}
	
	private String getTableName(TableEnum tableNameEnum) {
		switch(tableNameEnum) {
		case folder:
			return "folder";
		case users:
			return "users";
		case colorcode:
			return "colorcode";
		case course:
			return "course";
		case post:
			return "post";
		case relationbetweenposts:
			return "relationbetweenposts";
		case tag:
			return "tag";
		case takingcourse:
			return "takingcourse";
		case thread:
			return "thread";
		default:
			return "";
		}
	}
	
	public List<Integer> getAllElementsContainingSearchword(TableEnum tableNameEnum, String colName, String postText, String specificSearchword)
	{
		try 
		{

			String tableName = "";
			List<Integer> list = new ArrayList<Integer>();
			
			tableName = getTableName(tableNameEnum);
			
			String sqlQuery = "SELECT " + colName + " FROM " + tableName + " WHERE " + postText + " LIKE \"" + "%" + specificSearchword + "%\";";
			System.out.println("SQL query: " + sqlQuery);

			_result = queryDatabase(sqlQuery);
			
			//List of all IDs			
			while(_result.next()) {
				int number = Integer.parseInt(_result.getString(1));
				list.add(number);
			}
			
			return list;
			
	    } catch (Exception e) {
            System.err.println("[DRIVER]: " + e.getStackTrace());
	    } finally {
	        close();
	    }
		return null;
	}
	
	
	public List<String> getAllElements(TableEnum tableNameEnum, String colName)
	{
		try 
		{

			String tableName = "";
			List<String> list = new ArrayList<String>();
			
			tableName = getTableName(tableNameEnum);

			String sqlQuery = "SELECT " + colName + " FROM " + tableName + ";";
			System.out.println("SQL query: " + sqlQuery);

			_result = queryDatabase(sqlQuery);
			
			//List of all IDs			
			while(_result.next()) {
				String value = _result.getString(1);
				list.add(value);
			}
			
			return list;
			
	    } catch (Exception e) {
            System.err.println("[DRIVER]: " + e.getStackTrace());
	    } finally {
	        close();
	    }
		return null;
	}
	
	public List<String> getAllElementsSorted(TableEnum tableNameEnum, String colName, String sortCol, boolean filterBool, String filterCol, String filterValue)
	{
		try 
		{

			String tableName = "";
			List<String> list = new ArrayList<String>();
			
			tableName = getTableName(tableNameEnum);
			String sqlQuery = "";
			if(!filterBool) {
				sqlQuery = "SELECT " + colName + " FROM " + tableName + " ORDER BY " + sortCol + " ASC;";
			}
			else {
				sqlQuery = "SELECT " + colName + " FROM " + tableName + " WHERE " + filterCol + " = " + Post.paddWithFnut(filterValue) + " ORDER BY " + sortCol + " ASC;";
			}

			
			System.out.println("SQL query: " + sqlQuery);

			_result = queryDatabase(sqlQuery);
			
			//List of all IDs			
			while(_result.next()) {
				String value = _result.getString(1);
				list.add(value);
			}
			
			return list;
			
	    } catch (Exception e) {
            System.err.println("[DRIVER]: " + e.getStackTrace());
	    } finally {
	        close();
	    }
		return null;
	}
	
	public int getUniqueID(String email) {
		try {
			String tableName = "";
			tableName = getTableName(TableEnum.users);
			int uniqueUserId = -1;
			
			String sqlQuery = "SELECT userID FROM " + tableName + " WHERE email = " + Post.paddWithFnut(email) + ";";
			System.out.println("SQL query: " + sqlQuery);

			_result = queryDatabase(sqlQuery);
			int id = -1;
			while(_result.next())
			{
				id = _result.getInt(1);
			}
			uniqueUserId = id;
			
			return uniqueUserId;
		}
		catch (Exception e) {
			System.err.println("[DRIVER]: " + e.getStackTrace());
		}finally {
			close();
		}
		return -1;
	}
	
	public int updateStatistics() {
		try {
		String tableName = "";
		tableName = getTableName(TableEnum.users);
		
		String sqlQuery = "UPDATE " + tableName + " SET postsCreated = postsCreated + 1 WHERE userID = " + UserHandler.getInstance().uniqueId + ";";
		System.out.println("SQL query: " + sqlQuery);
		
		updateDatabase(sqlQuery);
		
		return 0;
		}
		
		catch (Exception e) {
			System.err.println("[DRIVER]: " + e.getStackTrace());
		}
		finally {
			close();
		}
		return 0;
	}
	
	public ResultSet getStats() {
		try {
			String tableName = "";
			tableName = getTableName(TableEnum.users);
			
			//String sqlQuery = "SELECT email AS \"username\", postViewed AS \"Number of posts read\", postsCreated AS \"Number of postsCreated \" FROM " + tableName + " GROUP BY " + UserHandler.getInstance().uniqueId +" ORDER BY postsViewed DESC" + "\";";
			String sqlQuery = "SELECT email , postViewed, postsCreated FROM " + tableName + ";";
			System.out.println("SQL query: " + sqlQuery);
			ResultSet result = queryDatabase(sqlQuery);
			
			return result;
		}
		catch (Exception e) {
			System.err.println("[DRIVER]: " + e.getLocalizedMessage());
			return null;
		}
		finally {
			//close();
		}
	}
	
	public void createNewThread(String threadID, String folderID) {
		String values = "";
		values += threadID; // threadID int
		values += ",";
		values += folderID; //folderID int
		InsertIntoTable(TableEnum.thread,values);
	}

    private void close() {
        try {
            if (_result != null) {
            	_result.close();
            }

            if (_statement != null) {
                _statement.close();
            }

            if (_conn != null) {
                _conn.close();
            }
        } catch (Exception e) {
            System.err.println("[DRIVER]: " + e.getStackTrace());
        }
    }
    
}


