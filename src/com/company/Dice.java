package com.company;
import java.util.Random;

public class Dice {
    public int getRandomNumber(){
        Random rand = new Random();
        int upperbound = 6;
        int randomInt = (rand.nextInt(upperbound)) + 1;
        return randomInt;
    }
}
