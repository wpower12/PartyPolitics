/*
 * ListFixer - Utilityclass to parse some names. 
 */
package votesim;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wpower
 */
public class ListFixer {

    PrintWriter outFile;
    BufferedReader inFile;

    public ListFixer(String in, String out) {
        try {
            inFile = new BufferedReader(new FileReader(in));
            outFile = new PrintWriter(out);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListFixer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void process() {
        String line;
        try {
            while ((line = inFile.readLine()) != null) {
                //Replace shit at start
                //line = line.replaceFirst("^\\*\\** \\'*\\[*", "");
                //line = line.replaceFirst("].*$", "");
                //line = line.replaceFirst("\\(.*$", "");

                line = line.replaceFirst("^\\**\\[*", "");
                //line = line.replaceFirst("].*$", "");
                line = line.replaceFirst("\\(.*$", "");

                //Replace shit at end
                //Save to output
                outFile.println(line);
            }
            outFile.close();
            inFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ListFixer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        ListFixer lf = new ListFixer("politicalpartynames.txt", "parties.txt");
        lf.process();
    }

}
