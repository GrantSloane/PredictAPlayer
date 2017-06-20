package fanduel.predictaplayer.listhandler;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by gcslo on 6/20/2017.
 */
public class RandomNumberGenerator
{
    private int size ;
    private int range ;

    public RandomNumberGenerator(int size, int range) {
        this.size = size;
        this.range = range ;
    }

    public ArrayList<Integer> generateRandomNumbers()
    {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        while (numbers.size() < size) {
            int random = randomGenerator.nextInt((range)) ;
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }

        return numbers;
    }


}
