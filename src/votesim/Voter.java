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

public class Voter extends Opinions {

    public Voter(int o) {
        super(o);
    }
    
    public int score( Opinions party ){
        
        int ret = 0;
        
        for( int i = 0; i < party.length; i++ ){
            if( opinionList[i] == party.get(i) ){
                ret++;
            }
        }
        
        return ret;
    }
}
