/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votesim;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wpower
 */
public class VoteSim {

    private int VOTERCOUNT = 1000;
    private int OPINIONCOUNT = 10;
    private int PARTYCOUNT = 10;
    private int CYCLECOUNT = 10;
    
    ArrayList<Voter> voters;
    ArrayList<Party> parties;

    private static PrintWriter out;
    private static PrintWriter opout;

    public VoteSim() {
        //CONFIG FILE STUFF SHOULD HAPPEN

        //set up printwriters
    }
    
    public void setPartyCount(int p){
        this.PARTYCOUNT = p;
    }
    public void setOpinionCount(int o){
        this.OPINIONCOUNT = o;
    }
    public void setVoterCount(int v){
        this.VOTERCOUNT = v;
    }
    public void setCycleCount( int c ){
        this.CYCLECOUNT = c;
    }
    
    public Party party( int p ){
        return this.parties.get(p);
    }
    
    public void generateParties() {
        parties = new ArrayList<Party>();
        for (int p = 0; p < PARTYCOUNT; p++) {
            parties.add(new Party(OPINIONCOUNT));
        }
    }

    public void generateVoters() {
        voters = new ArrayList<Voter>();
        for (int v = 0; v < VOTERCOUNT; v++) {
            voters.add(new Voter(OPINIONCOUNT));
        }
    }

    public void runCycles(int cycles) {
        initPrintOut("tmp.txt");
        printHeader(parties);
        printPartyOpinions(parties);
        
        for (int c = 0; c < cycles; c++) {
            vote(voters, parties);        //Sets each voters party
            updateParties(parties);       //Change parties opinions based on its voters
            evaluateParties(parties);     //Combine similar parties

            printStats(parties);

            clearPartySupporters(parties);
        }
    }

    public void printResults(String fn) {
        //Just renames tmp to the fn right now cause im terrible
        
        printClose();
    }

    private void printHeader(ArrayList<Party> parties) {
        //CSV header - comma seperated list of party names
        String header = "";
        for (Party p : parties) {
            header += p.name() + ", ";
        }
        header = header.replaceAll(", $", "");
        out.println(header);
    }

    private void initPrintOut(String fn) {
        try {
            out = new PrintWriter(fn);
            opout = new PrintWriter("opinionoverview-" + fn);
        } catch (FileNotFoundException ex) {
            System.out.println("Cant write to file??");
            Logger.getLogger(VoteSim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void vote(ArrayList<Voter> v, ArrayList<Party> p) {
        ArrayList<Party> bestParties = new ArrayList<Party>();
        int winner;
        int bestScore;

        //For each voter, compare to each party
        for (Voter voter : v) {
            bestScore = 0;
            for (Party party : p) {
                if (voter.score(party) > bestScore) {
                    bestParties.clear();
                    bestParties.add(party);
                    bestScore = voter.score(party);
                } else if (voter.score(party) == bestScore) {
                    bestParties.add(party);
                }
            }

            //Select a random party from the best parties and add the voter
            winner = (int) Math.floor(Math.random() * bestParties.size());
            bestParties.get(winner).addVoter(voter);
        }
    }

    private void updateParties(ArrayList<Party> parties) {
        for (Party p : parties) {
            p.update();
        }
    }

    private void evaluateParties(ArrayList<Party> parties) {
        //For each pair of parties

        //If similar enough, remove the party with the least votes
    }

    private void clearPartySupporters(ArrayList<Party> parties) {
        //For now just clear that shit.
        for (Party p : parties) {
            p.clearSupporters();
        }
    }

    private void printStats(ArrayList<Party> parties) {
        int total = 0;
        int winner = 0;
        int max = 0;
        int votes;

        String resultsString = "";

        for (int p = 0; p < parties.size(); p++) {
            votes = parties.get(p).votes();
            //System.out.println("Party: " + p + " Votes: " + votes);
            total += votes;
            if (votes > max) {
                winner = p;
                max = votes;
            }
            resultsString += "" + votes + ", ";
        }

        resultsString = resultsString.replaceAll(", $", "");
        out.println(resultsString);

        System.out.println("Total Votes: " + total + " Winner: " + winner);
    }

    private void printPartyOpinions(ArrayList<Party> parties) {

    }

    private void printClose() {
        out.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        VoteSim sim = new VoteSim();

        sim.generateParties();
        sim.generateVoters();
        sim.runCycles(20);
        sim.printResults("testingbjects.txt");

    }

}
