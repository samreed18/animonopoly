package com.company;

import java.util.Random;

public class Card {

    p

    public void randomCard()
    {

    }

    //runs through entire array for money
    public String getRandomScenario()
    {

        Random rand = new Random();
        int upperbound = 20;
        int randomInt = (rand.nextInt(upperbound));

        scenario = scenarios[randomInt];
        return scenario;
    }

    // runs through entire array for scenarios
    public int getRandomMoney()
    {
        for(int i=0; i <=20; i++)
        {

            money = money[i];
            // calls player class
            Player p = new Player();
            balance  = p.money();
            //adds or minuses money to player total depending on scenario
            finalBalance = balance.deposit;
            // if finalBalance = balance.deposit then switch
            if (finalBalance == balance.deposit)
            {
                finalBalance = balance.withdraw;
            }
            // if finalBalance = balance.withdraw then switch
            if  else (finalBalance == balance.withdraw)
            {
                finalBalance = balance.deposit;
            }
            // alternates so user can loss and win
            else
            {
                break;
            }
            // breaks if error - possibly could add a error ,essage incase
        }

    }

}
