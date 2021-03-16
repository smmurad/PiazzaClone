package net.codejava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		try 
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/piazza", "root", "12345678");
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery("SELECT * FROM colorcode;");
			System.out.println("test");
			
			while(result.next()) {
				System.out.println(result.getString(1) + ", " + result.getString(2));
			}
			
		}
		catch(Exception e)
		{
			
		}
	}
}
