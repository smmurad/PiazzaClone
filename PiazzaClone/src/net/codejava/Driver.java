package net.codejava;

import java.sql.*;

public class Driver {
	
	private String _dbUrl = "jdbc:mysql://localhost:3306/piazza";
	private String _dbUserName = "root";
	private String _dbPassword = "12345678";
	

	private Connection _conn;
	private ResultSet _result;
	private Statement _statement;
		
	// This will be changed into an enum
	public static int UserTableName = 0;  
	
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
	public String getSingleColElement(int tableNameEnum, String colName, String attributeToSearch, String specificSearchword)
	{
		try 
		{

			String tableName = "";
			
			if(tableNameEnum == UserTableName)
			{
				tableName = "users";
			}
			
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
		// TODO Auto-generated method stub
		
		try {
			_conn = DriverManager.getConnection(_dbUrl, _dbUserName, _dbPassword);
			
			_statement = _conn.createStatement();
			
			_result = _statement.executeQuery(sqlQuery);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return _result;
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


