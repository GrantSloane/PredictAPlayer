package fanduel.predictaplayer.listhandler;

import java.util.ArrayList;
import java.util.List;

import fanduel.predictaplayer.model.Player;

/**
 * This class generates a list of random players for the user to guess
 */
public class PlayerRoundGenerator {

    private List<Player> players ;
    private List<Player> randomPlayers ;
    private int size ;
    private RandomNumberGenerator random ;

    public PlayerRoundGenerator(List<Player> players, int size) {
        this.players = players;
        this.size = size;
        random = new RandomNumberGenerator(size, players.size());
        randomPlayers = new ArrayList<Player>() ;
    }

    public List<Player> generateRandomPlayers()
    {
        randomPlayers.clear();
        List<Integer> randomNumbers = random.generateRandomNumbers() ;
        for(int i=0 ; i<size; i++)
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
            currentFPPG = randomPlayers.get(i).getFppg();
            if(currentFPPG > highestFPPG)
            {
                highestFPPG = currentFPPG ;
                index = i;
            }
        }
        return index;
    }






}
