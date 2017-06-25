package fanduel.predictaplayer.listhandler;

import android.content.Context;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fanduel.predictaplayer.helper.SharedPreferenceManager;
import fanduel.predictaplayer.model.Player;

/**
 * This class generates a list of random players for the user to guess
 */
public class PlayerRoundGenerator{

    private List<Player> players ;
    private List<Player> randomPlayers ;
    private int size ;
    private int correctScoreCount = 0 ;
    private int incorrectScoreCount = 0 ;
    private RandomNumberGenerator random ;
    private Context context ;


    public PlayerRoundGenerator(Context context, List<Player> players) {
        this.players = players;
        this.size = SharedPreferenceManager.getDifficulty(context);
        System.out.println("DIFFICULTY SET TO: " + Integer.toString(size)) ;
        random = new RandomNumberGenerator(players.size(),size);
        randomPlayers = new ArrayList<Player>() ;
        this.context = context ;
    }

    public List<Player> generateRandomPlayers()
    {

        System.out.println("Generating Players");
        randomPlayers.clear();
        List<Integer> randomNumbers = random.generateRandomNumbers() ;
        for(int i=0 ; i<randomNumbers.size(); i++)
        {
            randomPlayers.add(players.get(randomNumbers.get(i))) ;
            System.out.println("PLAYER: " + randomPlayers.get(i).getFirstName() + " " + String.format("%.2f", randomPlayers.get(i).getFppg()));
        }
        return randomPlayers ;
    }


    public Integer determineHighestFPPG()
    {
        //Returns in the index of the player with the highest fppg
        int index = 0 ;
        float currentFPPG = 0 ;
        float highestFPPG = 0 ;

        for(int i=0 ; i<randomPlayers.size();i++)
        {

            if(randomPlayers.get(i).getFppg() != null) {
                currentFPPG = randomPlayers.get(i).getFppg();
                if (currentFPPG > highestFPPG) {
                    highestFPPG = currentFPPG;
                    index = i;
                }
            }
        }
        return index;
    }

    public int getCorrectScoreCount() {
        return correctScoreCount;
    }

    public void setCorrectScore(int gameScore) {
        this.correctScoreCount = gameScore;
    }

    public int getIncorrectScoreCount() {
        return incorrectScoreCount;
    }

    public void setIncorrectScore(int incorrectScoreCount) {
        this.incorrectScoreCount = incorrectScoreCount;
    }

    public int getSize() {
        return size;
    }

    public void resetDifficulty() {
        size = SharedPreferenceManager.getDifficulty(context);
        random.setSize(size);
    }






}
