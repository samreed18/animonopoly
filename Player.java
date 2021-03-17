public class Player {
    private int currentSpaceOnBoard;   //0-25
    private int lastMove;   //used to find if they've passed start
    private int money;
    private final char playingPiece;
    private boolean missAGo;
    private final Dice dice1;
    private final Dice dice2;
    private final String name;


    Player(String playerName, char playingPiece){
        this.playingPiece = playingPiece;
        money = 500; //initial money
        currentSpaceOnBoard =0;
        lastMove= currentSpaceOnBoard;
        dice1 = new Dice();
        dice2 = new Dice();
        name = playerName;
        missAGo=false;
    }

    public void setMissAGo(boolean bool){
        missAGo=bool;
    }

    public boolean isMissAGo() {
        return missAGo;
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

        dice1.getRandomNumber();
        System.out.println();
        System.out.println(dice1);
        sleep(1000);
        dice2.getRandomNumber();
        System.out.println(dice2);
        sleep(1000);
        int numberOfMoves = dice1.getValue() +dice2.getValue();
        System.out.println("\nMove "+numberOfMoves+ " spaces");

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

    public boolean hasNoMoney(){
        return (money<=0);
    }

    public boolean rolledADouble(){
        // return (dice1.getValue() == dice2.getValue());

        return dice1.equals(dice2);
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

        return "\n"+getID()+" is on space #"+currentSpaceOnBoard;
    }

    public String money(){
        if (money<0){
            return "You have -£"+(-money);
        }
        return "You have £"+money ;
    }

    public String getID(){
        return name +" ("+playingPiece+")";
    }

    @Override
    public boolean equals(Object compared){
        if (this == compared){
            return true;
        }

        if (!(compared instanceof Player)){
            return false;
        }

        Player comparedPlayer = (Player)compared;

        return (this.name.equals(comparedPlayer.name) && this.playingPiece == comparedPlayer.playingPiece);
    }


    public static void sleep(int time){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
