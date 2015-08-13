package votesim;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ThingNamer - creates a random set of names by reading a file line by line
 *            - Should give unqiue, random names. 
 *            - Assumes that the fn points to a text file of 'names' one on each line
 *            - The buffersize is the number of names to init the random list with
 * 
 * @author wpower
 */
public class ThingNamer {
    
    String[] nameBuffer;
    BufferedReader reader;
    int current = 0;
    
    public ThingNamer( String fn, int buffersize ){
        try {
            nameBuffer = new String[buffersize];
            reader = new BufferedReader( new FileReader(fn) );
            
            //Resovouir Sampling of input file
            for( int i = 0; i < buffersize; i++ ){
                nameBuffer[i]  = reader.readLine();
            }
            
            String line;
            int count = 1;
            int j;
            while( (line = reader.readLine()) != null ){
                j = (int)Math.floor(Math.random()*count);
                if( j < buffersize ){
                    nameBuffer[j] = line;
                }
                count++;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThingNamer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ThingNamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String getNext(){
        String ret = nameBuffer[current];
        current++;
        return ret;
    }
    
    public static void main( String[] args ){
        
        final int COUNT = 10;
        
        ThingNamer namer = new ThingNamer( "parties.txt", COUNT );
        
        for( int i = 0; i < COUNT; i++ ){
            System.out.println( namer.getNext() );
        }
    }
}
