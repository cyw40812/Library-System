import java.io.*;
import java.util.*;
@SuppressWarnings("rawtypes")
public class ReadCSVtoArrayList {
    
	public static ArrayList<ArrayList> readCSVToArrayList(String csvpath,String code) 
    {
        ArrayList<ArrayList> dataAL = new ArrayList<ArrayList>();
        BufferedReader reader;
        File f = null; 
        InputStreamReader read = null; 
        
        try 
        {
        	f = new File(csvpath); 
        	read = new InputStreamReader(new FileInputStream(f),code); 
        	reader = new BufferedReader(read);
            reader.readLine();
            String line = null;
            ArrayList<String> ticketStr = null;
            		
            while ((line = reader.readLine()) != null ) 
            {
                ticketStr = new ArrayList<String>();
                String item[] = line.split(",");
                for(int i=0; i<item.length; i++){ticketStr.add(i, item[i]);}
                dataAL.add(ticketStr); 
            }
        } 
        catch (FileNotFoundException e){e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}
        return dataAL;
    }
}