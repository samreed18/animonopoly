package com.company;
import java.util.Random;

public class Dice {

    private int value;

    public void getRandomNumber(){
        Random rand = new Random();
        int upperbound = 6;
        value = rand.nextInt(upperbound)+1;

    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object compared){
        if (this == compared){
            return true;
        }
        if (!(compared instanceof  Dice)){
            return false;
        }
        Dice comparedDice = (Dice) compared;

        return (this.value == ((Dice) compared).value);
    }

    @Override
    public String toString(){
        return "## You rolled a "+value+" ##";
    }
}