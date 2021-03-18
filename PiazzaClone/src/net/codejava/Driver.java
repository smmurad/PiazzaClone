package net.codejava;

import java.sql.*;

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
		default:
			return "";
		}
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


