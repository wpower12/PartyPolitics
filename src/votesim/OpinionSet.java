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
public class OpinionSet {

    protected int[] opinionList;
    protected final int MAXOPINIONVALUE = 3;
    
    public int length;
    
    public OpinionSet(int o) {
        opinionList = new int[o];
        for (int i = 0; i < o; i++) {
            opinionList[i] = (int) Math.floor(Math.random() * MAXOPINIONVALUE);
        }
        length = o;
    }
    public int get(int i){ return opinionList[i]; }
}
