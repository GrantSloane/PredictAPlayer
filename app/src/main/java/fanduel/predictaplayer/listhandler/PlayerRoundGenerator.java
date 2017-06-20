package fanduel.predictaplayer.listhandler;

import java.util.ArrayList;
import java.util.List;

import fanduel.predictaplayer.model.Player;

/**
 * Created by gcslo on 6/20/2017.
 */
public class PlayerRoundGenerator {

    private List<Player> players ;
    private int size ;
    private RandomNumberGenerator random ;

    public PlayerRoundGenerator(List<Player> players, int size) {
        this.players = players;
        this.size = size;
        random = new RandomNumberGenerator(size, players.size());
    }

    public List<Player> generateRandomPlayers()
    {
        List<Integer> randomNumbers = random.generateRandomNumbers() ;
        List<Player> randomPlayers = new ArrayList<Player>() ;
        for(int i=0 ; i<size; i++)
        {
            randomPlayers.add(players.get(randomNumbers.get(i))) ;
        }
        return randomPlayers ;
    }






}
