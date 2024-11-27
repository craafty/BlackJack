import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    public static final int NUM_FACES = 13;
    public static final int NUM_SUITS = 4;
    public static final int NUM_CARDS = 52;

    public static final String SUITS[] = {"CLUBS","SPADES","DIAMONDS","HEARTS"};

    private int topCardIndex;
    private ArrayList<Card> stackOfCards;

    public Deck ()
    {
        //initialize data - stackOfCards - topCardIndex
        stackOfCards = new ArrayList<>();
        topCardIndex = NUM_CARDS-1;
        //loop through suits
        //loop through faces
        //add in a new card
        for(int i = 0; i < NUM_SUITS; i++) {
            for(int j = 0; j < NUM_FACES; j++) {
                stackOfCards.add(new BlackJackCard(j+1, SUITS[i]));
            }
        }
    }

    //modifiers
    public void shuffle ()
    {
        //shuffle the deck
        //reset variables as needed
        Collections.shuffle(stackOfCards);
        topCardIndex = NUM_CARDS-1;
    }

    //accessors
    public int  size ()
    {
        return stackOfCards.size();
    }

    public int numCardsLeft()
    {
        return topCardIndex+1;
    }

    public Card nextCard()
    {
        return stackOfCards.get(topCardIndex--);
    }

    public String toString()
    {
        return stackOfCards + "   topCardIndex = " + topCardIndex;
    }
}