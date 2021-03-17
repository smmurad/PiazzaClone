package net.codejava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputHandler {
	
	// Singleton instance
	private static InputHandler instance;
	
	private InputHandler() {}
	
	public static InputHandler getInstance() {
		if(instance == null)
		{
			instance = new InputHandler();
		}
		return instance;
	}
 
	
    public static String getStringInput(String textToUser) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(textToUser);
        String s = br.readLine();
        return s;
    }
    
    public static int getIntInput(String textToUser) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(textToUser);
        try {
            int i = Integer.parseInt(br.readLine());
            return i;
        } catch(NumberFormatException nfe) {
            System.err.println("Invalid Format!");
            return 0;
        }
    }
}
