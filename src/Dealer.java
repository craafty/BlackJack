public class Dealer extends AbstractPlayer{
    private Deck deck;
    Dealer() {
        deck = new Deck();
    }
    public void shuffle() {
        deck.shuffle();
    }
    public Card deal() {
        return deck.nextCard();
    }
    @Override
    public boolean hit() {
        return false;
    }
}
