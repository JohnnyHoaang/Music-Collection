/**
 * 
 * Maybe not used
 * Reads from JSON files
*/
package src;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

public class ReadFromJSON { 

    public static void main(String[] args) {

        try{
            ArrayList<String> loaded = new ArrayList<>();
        
            loaded = loadJSON();
    
            for (int i = 0; i< loaded.size(); i++){
                System.out.println(loaded.get(i));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
      
    }

    public static ArrayList<String> loadJSON() throws IOException{
        String path = "sample/Hoang_2036759_Create_Music_Samples_Submitted_on_2021-11-24_21h49m08s.json";

        Path p = Paths.get(path);
        List<String> lines = Files.readAllLines(p);

        ArrayList<String> json = new ArrayList<>();

        for(String line : lines){
            json.add(line);
        }
        return json;
    }
}
