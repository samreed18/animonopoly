import java.util.Random;

public class Deck {
    private final Card[] cards;

    public Deck(Card[] cards){
        this.cards = cards;
    }

    public Card getRandomCard(){
        Random random = new Random();
        int randomCardIndex = random.nextInt(cards.length);
        return cards[randomCardIndex];
    }
}
