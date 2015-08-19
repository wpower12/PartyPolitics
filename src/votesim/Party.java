/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votesim;

import java.util.ArrayList;

/**
 *
 * @author wpower
 */
public class Party extends OpinionSet {

    private ArrayList<Voter> supporters;
    private int partyid;
    private String partyName;
    private ArrayList<int[]> history;
    
    
    static private int partycount = 0;
    static private ThingNamer partyNamer = new ThingNamer( "parties.txt", 20 );
    
    public Party(int o) {
        super(o);
        supporters = new ArrayList<>();
        history = new ArrayList<>();
        partyid = Party.nextID();
        partyName = partyNamer.getNext();
    }
    
    static private int nextID(){
        return partycount++;
    }
    
    public String name(){
        return partyName;
    }
    
    public void addVoter(Voter v) {
        supporters.add(v);
    }

    public int votes() {
        return supporters.size();
    }

    public void update() {

        int[][] tallies = new int[length][MAXOPINIONVALUE];
        for (Voter sup : supporters) {
            for (int op = 0; op < length; op++) {
                tallies[op][sup.get(op)]++;
            }
        }

        //For each row in tallies, we pick the max and set the opinion to that
        int max = 0;
        int bestVal  = 0;
        for (int op = 0; op < length; op++) {
            for (int val = 0; val < MAXOPINIONVALUE; val++) {
                if( tallies[op][val] > max ) {
                    max = tallies[op][val];
                    bestVal = val;
                }
            }
            opinionList[op] = bestVal;
        }
        
        history.add(opinionList);
    }
    
    public void clearSupporters() {
        supporters.clear();
    }
}
