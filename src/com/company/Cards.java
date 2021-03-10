package com.company;

public class Cards {

    int balance;
    int finalBalance;

    //array holds scenarios
    private String scenarios[] = {"\n Well done!\nyou won the lottery!\n","Oh no!\nyou've been robbed!\n","\n "};
    // another money array for cards deck
    private int money[] = {100,20,200,350,80,45,1,5,75,50,450,375,205,210,410,715,670,600,720,420};
    public static void randomCard()
    {

    }

    //runs through entire array for money
    public static void getRandomScenario(String scenarios)
    {
        for(int i=0; i <=20; i++)
        {
            scenarios = scenarios[i];

        }
    }
    // runs through entire array for scenarios
    public static void getRandomMoney(double money)
    {
        for(int i=0; i <=20; i++;)
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
