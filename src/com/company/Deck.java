package com.company;

public class Deck
{
    private String scenario;
    private int balance;
    private int i;
    private int finalBalance;
    private String scenarios[] = {"\n Well done!\nyou won the lottery!\n","Oh no!\nyou've been robbed!\n","\n "};
    private int money[] = {100,20,200,350,80,45,1,5,75,50,450,375,205,210,410,715,670,600,720,420};
    private Card cards[];

    public Deck(){
        for(i=0;i<money.length;i++){
            Card cards[i] = Card(scenarios[i], money[i]);
            cards[i];
        }
    }
}
// Cards class

