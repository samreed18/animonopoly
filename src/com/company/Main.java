package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args){

        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Animal> animals = new ArrayList<>();
        fillArrayWithAnimals(animals);

        Card[] chanceCards = new Card[8];
        fillCardsArray(chanceCards);
        Deck chanceCardsDeck = new Deck(chanceCards);

        System.out.println("Welcome to Animonopoly! First enter player details\n");
        sleep(1000);
        Scanner reader = new Scanner(System.in);
        for (int i=1; i<5;i++){
            System.out.println("Enter player "+i+" name: (Empty to finish) ");
            String name = reader.nextLine();
            if (name.isEmpty()){
                break;
            }
            System.out.println("Enter a character for your playing piece: ");
            char playingPiece = reader.nextLine().charAt(0);
            Player player = new Player(name, playingPiece);
            players.add(player);
        }

        Board gameBoard = new Board(players, animals);
        sleep(1000);
        System.out.println(gameBoard);
        System.out.println("This is the game board, the game is about to start...");
        sleep(2000);

        while(gameBoard.getLastPlayer()==null) {
            for (Player thisPlayer : players) {

                for (int i=0;i<40;i++){
                    System.out.print(".");
                    sleep(50);
                }

                if (thisPlayer.isMissAGo()){
                    System.out.println("\n"+thisPlayer.getID()+" is in jail so misses a go :(");
                    sleep(2000);
                    thisPlayer.setMissAGo(false);
                }
                else {
                    System.out.println("\n" + thisPlayer.getID() + " 's turn: \n");
                    sleep(1000);
                    System.out.println(thisPlayer.money());
                    sleep(1000);
                    thisPlayer.move();
                    sleep(1000);
                    if (thisPlayer.rolledADouble()){
                        System.out.println("\nYou rolled a double! Test out your luck with a chance card");
                        sleep(2000);
                        Card chosenCard = chanceCardsDeck.getRandomCard();
                        System.out.println(chosenCard);
                        sleep(2000);
                        thisPlayer.increaseMoney(chosenCard.getMoney());   //if money is negative it adds on the negative like a subtraction
                        System.out.println(thisPlayer.money());
                        sleep(2000);
                    }
                    System.out.println(thisPlayer);    //location
                    sleep(2000);
                    System.out.println(gameBoard);
                    sleep(3000);
                    System.out.println(thisPlayer.money());
                    sleep(2000);

                    if (thisPlayer.landedOnStart()) {    //space 0
                        System.out.println("\nYou landed start!! +£1000 ");
                        sleep(1000);
                        thisPlayer.increaseMoney(1000);
                        System.out.println(thisPlayer.money());
                        sleep(1000);
                    } else if (thisPlayer.landedOnMissAGo()) { //space 13
                        System.out.println("\nUnlucky you landed in jail D:");
                        sleep(2000);
                        thisPlayer.setMissAGo(true);

                    } else {    //any other space
                        if (thisPlayer.passedStart()){
                            System.out.println("\nYou passed start! +£500 ");
                            sleep(1000);
                            thisPlayer.increaseMoney(500);
                            System.out.println(thisPlayer.money());
                            sleep(1000);
                        }
                        // Animal animalLandedOn= animals.get(thisPlayer.getLocation());
                        Animal animalLandedOn = (thisPlayer.getLocation() < 13) ? (animals.get(thisPlayer.getLocation() - 1)) : (animals.get(thisPlayer.getLocation() - 2));    //gets the animal it lands on - adjusts for the array index difference because of missing 0 and 13 card
                        System.out.println(animalLandedOn);   //prints the animal card it lands on
                        sleep(2500);
                        if (animalLandedOn.hasOwner()) {
                            if (animalLandedOn.getOwner().equals(thisPlayer)) {       //your own card
                                System.out.println("Would you like to level up your own animal? (Y/N)");
                                char levelUp = reader.nextLine().charAt(0);
                                // char levelUp='Y';
                                if (levelUp == 'Y') {
                                    animalLandedOn.increaseLevel();
                                    System.out.println(animalLandedOn);
                                }
                            } else {
                                animalLandedOn.visitBy(thisPlayer);     //someone else's card
                            }
                        } else {
                            System.out.println("Would you like to buy this animal? (Y/N)");
                            char answer = reader.nextLine().charAt(0);
                            // char answer='Y';
                            if (answer == 'Y') {
                                animalLandedOn.purchaseBy(thisPlayer);
                                System.out.println(animalLandedOn);
                            }
                        }
                        sleep(1000);
                        System.out.println(thisPlayer.money());
                        sleep(2000);
                    }
                }
                if (thisPlayer.hasNoMoney()){
                    gameBoard.removePlayer(thisPlayer);
                    System.out.println(thisPlayer.getID()+" is bankrupt! You are out of the game :(");
                    break;   //breaks out of current for each loop?
                }
            }
        }

        Player winner = gameBoard.getLastPlayer();
        System.out.println(winner.getID() + " is the winner of Animonopoly! ");




       /* Animal flamingo = new Animal("Flamingo",20,30,40,50,60);
        Animal tiger = new Animal("Tiger", 40,50,60,70, 100);
        Animal lion = new Animal("Lion",35,55,60,70,300);
        ArrayList<Animal> animals = new ArrayList<>(Arrays.asList(flamingo,tiger,lion));
        Board gameBoard = new Board(players,animals);
        System.out.println(gameBoard);
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(flamingo);
        flamingo.purchase(player1);
        tiger.purchase(player2);
        System.out.println(gameBoard.animalsString());
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(flamingo);  */
    }

    public static void fillArrayWithAnimals(ArrayList<Animal> animals){
        Animal flamingo = new Animal("Flamingo",20,30,40,50,60);
        Animal tiger = new Animal("Tiger", 40,50,60,70, 100);
        Animal dingo = new Animal("Dingo",35,55,60,70,300);
        //    Animal turtle = new Animal ("Turtle",15, 20,27,31,60);
        Animal penguin = new Animal("Penguin", 90,100,111,130,200);
        Animal jaguar = new Animal("Jaguar", 70,77,90,98,200);
        Animal panda = new Animal("Panda", 6,86,88,106,156);
        Animal muskox = new Animal("Muskox", 40, 46, 57, 85,99);
        Animal camel = new Animal("Camel", 56,66,70,90,102);
        Animal capybara = new Animal("Capybara", 60,76,79,84,200);
        //  Animal chipmunk = new Animal ("Chipmunk", 30, 49, 54, 69, 98);
        Animal rhino = new Animal("Rhino", 88,96,108,130,300);
        Animal cuckoo = new Animal("Cuckoo", 12, 30, 45, 57, 78);
        Animal whale = new Animal("Whale", 60, 78, 92, 103, 150);
        Animal albatross = new Animal("Albatross", 34, 45, 65, 73, 81);
        Animal opossum = new Animal("Opossum",49, 52, 65, 77, 90);
        Animal alpaca = new Animal("Alpaca", 39, 45, 67, 84, 102);
        Animal llama = new Animal("Llama", 43, 55, 61, 73, 114);
        Animal greyhound = new Animal("Greyhound", 44, 51, 63, 79, 85);
        Animal nightingale = new Animal("Nightingale", 20, 33, 41, 53, 67);
        Animal peacock = new Animal("Peacock",44, 54, 60, 63, 101);
        Animal narwhal = new Animal("Narwhal", 61, 70, 83, 87, 140);
        Animal porpoise = new Animal("Porpoise", 23, 37, 43, 51, 70);
        Animal pangolin = new Animal("Pangolin", 60, 74, 89, 92, 136);
        Animal ostrich = new Animal("Ostrich", 42, 56, 59,72, 85);
        Animal sheepadoodle = new Animal("Sheepadoodle", 46, 63, 77, 83, 100);

        Collections.addAll(animals,flamingo,tiger, dingo, penguin, jaguar, panda, muskox, camel, capybara, rhino, cuckoo, whale, albatross, opossum,alpaca,llama, greyhound, nightingale, peacock, narwhal, porpoise, pangolin, ostrich,sheepadoodle);

    }

    public static void fillCardsArray(Card[] cards){
        cards[0] = new Card("You spend your money to go on a beautiful trip to sub-Saharan Africa and see the wildlife there",1000);
        cards[1] = new Card("A fox broke in and killed some of your prized chickens last night :(",-300);
        cards[2] = new Card("Deforestation in the Amazon Rainforest has destroyed many habitats, you use funds to set up a conservation scheme",-600);
        cards[3] = new Card("A conservation scheme you set up had helped rare animals face extinction, you are rewarded for your hard work",800);
        cards[4] = new Card("Buying food for your livestock", -250);
        cards[5] = new Card("Your chickens laid eggs, which you sell at a local market", 180);
        cards[6] = new Card("You find someones missing cat and claim your reward money",210);
        cards[7] = new Card("Your dog won the dog beauty pageant contest!", 700);

    }

    public static void sleep(int time){
        try {
            Thread.sleep(time);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
