import java.util.ArrayList;

public abstract class AbstractPlayer implements Playerable
{
    private ArrayList<Card> hand;
    private int winCount;
    //constructor
    AbstractPlayer() {
        hand = new ArrayList<>();
    }
    public void addCardToHand(Card card) {
        hand.add(card);
    }
    public  void resetHand(){
        hand.clear();
    }
    //set methods
    @Override
    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }
    //get methods
    @Override
    public int getHandSize() {
        return hand.size();
    }
    @Override
    public int getHandValue()
    {
        int total=0;
        for (Card card : hand) {
            total += card.getValue();
        }
        return total;
    }
    @Override
    public int getWinCount() {
        return winCount;
    }
    @Override
    public String toString()
    {
        return hand.toString() + " - " + getHandValue();
    }
}