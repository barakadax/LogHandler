import java.io.*;
import java.util.*;

public class FileHandler {
	//constructor:
    public FileHandler() {}
    
    //Others:
    public boolean doesFileExists(String dir) {
        File isReal = new File(dir);
        if (isReal.exists()) {
            isReal = null;
            return false;
        }
        return true;
    }//O(1)
    
    public boolean appendToFiLE(String dir, String text) {
        if (doesFileExists(dir))
            return true;
        try {
            FileWriter into = new FileWriter(dir, true);
            into.write(text + System.getProperty("line.separator"));
            into.close();
        }
        catch (Exception e) {
        	return true;
        }
        return false;
    }//O(1)
    
	public String readFile(String dir) {
    	File readFile = new File(dir);
    	Scanner reading = null;;
    	try {
    		reading = new Scanner(readFile);
		} 
    	catch (Exception e) {
			return null;
		}
    	String answer = "";
    	while (reading.hasNextLine())
    		answer += reading.nextLine() + System.getProperty("line.separator");
    	reading.close();
    	return answer;
    }//O(N)
}