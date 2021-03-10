package com.company;

public class Player {
    private int currentSpaceOnBoard;   //0-25
    private int lastMove;   //used to find if they've passed start
    private int money;
    private final char playingPiece;
    //private boolean missAGo;
    private Dice dice1;
    private Dice dice2;

    Player(char playingPiece){
        this.playingPiece = playingPiece;
        money = 500; //initial money
        currentSpaceOnBoard =0;
        lastMove= currentSpaceOnBoard;
        dice1 = new Dice();
        dice2 = new Dice();
        //missAGo=false;
    }
    public int getLocation(){
        return this.currentSpaceOnBoard;
    }
    public void setLocation(int space){
        this.currentSpaceOnBoard =space;
    }
    public char getPlayingPiece(){
        return playingPiece;
    }
    public void move(){//int spaces){
        // dice1.getRandomNumber();
        // dice2.getRandomNumber();
        int numberOfMoves = dice1.getRandomNumber() +dice2.getRandomNumber();
        currentSpaceOnBoard+= numberOfMoves;
        lastMove= currentSpaceOnBoard;
        //  currentSpaceOnBoard +=spaces;
        if (currentSpaceOnBoard >=26){     //spaces go from 0-25
            currentSpaceOnBoard -= 26;
        }
    }
    public void increaseMoney(int amount){
        money = money+amount;
    }
    public void decreaseMoney(int amount){
        money = money - amount;
    }
    public boolean checkNoMoney(){
        return (money<=0);
    }
    public boolean rolledADouble(){
        // return (dice1.getValue() == dice2.getValue());
        return true;
    }
    public boolean passedStart(){
        return (lastMove>=13 && currentSpaceOnBoard <13 && currentSpaceOnBoard !=0); //maximum roll two dice is 12
    }
    public boolean landedOnStart(){
        return(currentSpaceOnBoard ==0 &&lastMove!=0);
    }
    public boolean landedOnMissAGo(){    //in the main do if!landedOnThirteen{ move()}
        return(currentSpaceOnBoard ==13);
    }
    public String toString(){
        return playingPiece+" has "+money+" money";
    }

}
