/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votesim;

/**
 *
 * @author wpower
 */
public class PartyNamer {
    
    String[] nameBuffer;
    
    public PartyNamer( int buffersize ){
        //Create a buffer of random names, sized according to input
        nameBuffer = new String[buffersize];
        
        //Use resovoir sampling to fill name buffer
    }
    
    public String getNext(){
        
        return "hi";
    }
    
    public static void main( String[] args ){
        
        final int COUNT = 10;
        
        PartyNamer pn = new PartyNamer( COUNT );
        
        for( int i = 0; i < COUNT; i++ ){
            System.out.println( pn.getNext() );
        }
    }
}
