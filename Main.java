import java.util.*;

public class Main {
    private static int time = 0;
    public static void main(String[] args){
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Animal> animals = new ArrayList<>();
        fillArrayWithAnimals(animals);
        Card[] chanceCards = new Card[20];
        fillCardsArray(chanceCards);
        Deck chanceCardsDeck = new Deck(chanceCards);
        System.out.println("Welcome to Animonopoly! First enter player details\n");
        sleep(time * 1000);
        Scanner reader = new Scanner(System.in);
        for (int i=1; i<5;i++){
            String name;
            do {
                System.out.println("Enter Player " + i + " name: (Enter to stop) ");
                name = reader.nextLine();
                if (name.isEmpty()) {
                    if (players.size() > 1) {
                        break;
                    }
                }
            }while(name.isEmpty());
            if (name.isEmpty()){
                break;
            }
            char playingPiece;
            boolean playingSymbolTaken;
            do {
                String playingPieceInput;
                do {
                    System.out.println("Enter a character for your playing piece: ");
                    playingPieceInput= reader.nextLine();
                }while (playingPieceInput.isEmpty());
                playingPiece = playingPieceInput.charAt(0);
                playingSymbolTaken = false;
                for (Player aPlayer : players) {
                    if (aPlayer.getPlayingPiece() == playingPiece) {
                        playingSymbolTaken = true;
                        System.out.println("This token has already been taken..");
                    }
                }
            }while (playingSymbolTaken);
            Player player = new Player(name, playingPiece);
            players.add(player);
        }
        Board gameBoard = new Board(players, animals);
        sleep(time * 1000);
        System.out.println(gameBoard);
        System.out.println("This is the game board, the game is about to start...");
        sleep(time * 2000);
        while(gameBoard.getLastPlayer()==null) {
            for (Player thisPlayer : players) {
                for (int i=0;i<100;i++){
                    System.out.print(".");
                    sleep(time * 10);
                }
                if (thisPlayer.isMissAGo()){
                    System.out.println("\n"+thisPlayer.getID()+" is in jail so misses a go :(");
                    sleep(time * 2000);
                    thisPlayer.setMissAGo(false);
                }
                else {
                    System.out.println("\n" + thisPlayer.getID() + " 's turn: \n");
                    sleep(time * 1000);
                    System.out.println(thisPlayer.money());
                    sleep(time * 1000);
                    thisPlayer.move();
                    sleep(time * 1000);
                    if (thisPlayer.rolledADouble()){
                        System.out.println("\nYou rolled a double! Test out your luck with a chance card");
                        sleep(time * 2000);
                        Card chosenCard = chanceCardsDeck.getRandomCard();
                        System.out.println(chosenCard);
                        sleep(time * 3000);
                        thisPlayer.increaseMoney(chosenCard.getMoney());   //if money is negative it adds on the negative like a subtraction
                        System.out.println(thisPlayer.money());
                        sleep(time * 2000);
                    }
                    System.out.println(thisPlayer);    //location
                    sleep(time * 2000);
                    System.out.println(gameBoard);
                    sleep(time * 3000);
                    System.out.println(thisPlayer.money());
                    sleep(time * 2000);
                    if (thisPlayer.landedOnStart()) {    //space 0
                        System.out.println("\nYou landed start!! +£1000 ");
                        sleep(time * 1000);
                        thisPlayer.increaseMoney(1000);
                        System.out.println(thisPlayer.money());
                        sleep(time * 1000);
                    } else if (thisPlayer.landedOnMissAGo()) { //space 13
                        System.out.println("\nUnlucky you landed in jail D:");
                        sleep(time * 2000);
                        thisPlayer.setMissAGo(true);
                    } else {    //any other space
                        if (thisPlayer.passedStart()){
                            System.out.println("\nYou passed start! +£500 ");
                            sleep(time * 1000);
                            thisPlayer.increaseMoney(500);
                            System.out.println(thisPlayer.money());
                            sleep(time * 1000);
                        }
                        // Animal animalLandedOn= animals.get(thisPlayer.getLocation());
                        Animal animalLandedOn = (thisPlayer.getLocation() < 13) ? (animals.get(thisPlayer.getLocation() - 1)) : (animals.get(thisPlayer.getLocation() - 2));    //gets the animal it lands on - adjusts for the array index difference because of missing 0 and 13 card
                        System.out.println(animalLandedOn);   //prints the animal card it lands on
                        sleep(time * 2500);
                        if (animalLandedOn.hasOwner()) {
                            if (animalLandedOn.getOwner().equals(thisPlayer)) {       //your own card
                                String input;
                                do {
                                    System.out.println("Cost to Upgrade:" + animalLandedOn.getIncreaseLevelCost() + "\n");
                                    System.out.println("Would you like to level up your own animal? (Y/N)");
                                    input = reader.nextLine();
                                }while (input.isEmpty());
                                input = input.toUpperCase();
                                char levelUp = input.charAt(0);
                                // char levelUp='Y';
                                if (levelUp == 'Y') {
                                    animalLandedOn.increaseLevel();
                                    System.out.println(animalLandedOn);
                                }
                            } else {
                                animalLandedOn.visitBy(thisPlayer);     //someone else's card
                            }
                        } else {
                            String input;
                            do {
                                System.out.println("Would you like to buy this animal? (Y/N)");
                                input = reader.nextLine();
                            }while(input.isEmpty());
                            input = input.toUpperCase();
                            char answer = input.charAt(0);
                            // char answer='Y';
                            if (answer == 'Y') {
                                animalLandedOn.purchaseBy(thisPlayer);
                                System.out.println(animalLandedOn);
                                sleep(time * 1000);
                            }
                        }
                        System.out.println(thisPlayer.money());
                        sleep(time * 2000);
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
        Animal flamingo = new Animal("Flamingo",10,20,30,40,60);
        Animal tiger = new Animal("Tiger", 10,20,30,40, 60);
        Animal dingo = new Animal("Dingo",10,20,30,40,60);
        //    Animal turtle = new Animal ("Turtle",15, 20,27,31,60);
        Animal penguin = new Animal("Penguin", 15,25,35,45,80);
        Animal jaguar = new Animal("Jaguar", 15,25,35,45,80);
        Animal panda = new Animal("Panda", 15,25,35,45,80);
        Animal muskox = new Animal("Muskox", 20, 35, 50, 65,100);
        Animal camel = new Animal("Camel", 20,35,50,65,100);
        Animal capybara = new Animal("Capybara", 20,35,50,65,100);
        //  Animal chipmunk = new Animal ("Chipmunk", 30, 49, 54, 69, 98);
        Animal rhino = new Animal("Rhino", 30,45,60,75,150);
        Animal cuckoo = new Animal("Cuckoo", 30, 45, 60, 75, 150);
        Animal whale = new Animal("Whale", 30, 45, 60, 75, 150);
        Animal albatross = new Animal("Albatross", 40, 55, 70, 85, 200);
        Animal opossum = new Animal("Opossum",40, 55, 70, 85, 200);
        Animal alpaca = new Animal("Alpaca", 40, 55, 70, 85, 200);
        Animal llama = new Animal("Llama", 60, 110, 150, 190, 250);
        Animal greyhound = new Animal("Greyhound", 60, 110, 150, 190, 250);
        Animal nightingale = new Animal("Nightingale", 60, 110, 150, 190, 250);
        Animal peacock = new Animal("Peacock",80, 130, 170, 210, 300);
        Animal narwhal = new Animal("Narwhal", 80, 130, 170, 210, 300);
        Animal porpoise = new Animal("Porpoise", 80, 130, 170, 210, 300);
        Animal pangolin = new Animal("Pangolin", 100, 150, 200, 250, 350);
        Animal ostrich = new Animal("Ostrich", 100, 150, 200,250, 350);
        Animal sheepadoodle = new Animal("Sheepadoodle", 100, 150, 200, 250, 350);

        Collections.addAll(animals,flamingo,tiger, dingo, penguin, jaguar, panda, muskox, camel, capybara, rhino, cuckoo, whale, albatross, opossum,alpaca,llama, greyhound, nightingale, peacock, narwhal, porpoise, pangolin, ostrich,sheepadoodle);

    }

    public static void fillCardsArray(Card[] cards){
        cards[0] = new Card("You spend your money to go on a beautiful trip to sub-Saharan Africa and see the wildlife there",-1000);
        cards[1] = new Card("A fox broke in and killed some of your prized chickens last night :(",-300);
        cards[2] = new Card("Deforestation in the Amazon Rainforest has destroyed many habitats, you use funds to set up a conservation scheme",-600);
        cards[3] = new Card("A conservation scheme you set up had helped rare animals face extinction, you are rewarded for your hard work",800);
        cards[4] = new Card("Buying food for your livestock", -250);
        cards[5] = new Card("Your chickens laid eggs, which you sell at a local market", 180);
        cards[6] = new Card("You find someones missing cat and claim your reward money",210);
        cards[7] = new Card("Your dog won the dog beauty pageant contest!", 700);
        cards[8] = new Card("You stole the neighbours' pet monkey now you getting sued! Shame on you...", -320);
        cards[9] = new Card("Up in the early morning to feed the pigeons... kudos to you", -20);
        cards[10]= new Card("You helped the dolphin with its' broken fin and you're compensated for its' surgery", 560);
        cards[11]= new Card("You angered your boss now you're getting a reduction on your pay", -100);
        cards[12]= new Card("You won the lottery!", 1000);
        cards[13]= new Card("You went on a trip to LA but broke you arm, now you have to pay to be seen to", -1350);
        cards[14]= new Card("You got robbed!", -50);
        cards[15]= new Card("A fake psychic reels you in, and he tells that you're going to loose money in the near future...\nsounds about right", -500);
        cards[16]= new Card("You've seduced a millionare and now they're sharing their lavish riches with you!", 1500);
        cards[17]= new Card("You're staring a resvoir for endangered animals, your local council approves and has given you some funding money", 1000);
        cards[18]= new Card("You decided to go to a  pawnbroker to try your luck on some old belongings", 450);
        cards[19]= new Card("You went to the bank to check on your savings account, and decided to claim your interest", 200);
    }

    public static void sleep(int time){
        try {
            Thread.sleep(time);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
