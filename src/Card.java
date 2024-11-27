public abstract class Card
{
    public static final String FACES[] = {"ZERO","ACE","TWO","THREE","FOUR",
            "FIVE","SIX","SEVEN","EIGHT","NINE","TEN","JACK","QUEEN","KING"};

    private String suit;
    private int face;

    //constructors
    Card() {
        face = 0;
        suit = "";
    }
    Card(int face, String suit) {
        this.face = face;
        this.suit = suit;
    }

    // modifiers
    public void setSuit(String suit) {
        this.suit = suit;
    }
    public void setFace(int face) {
        this.face = face;
    }

    //accessors
    public String getSuit() {
        return suit;
    }
    public int getFace() {
        return face;
    }
    public abstract int getValue();

    public boolean equals(Object obj)
    {
        BlackJackCard objCard = (BlackJackCard) obj;
        return getValue() == objCard.getValue() && suit.equals(objCard.getSuit());
    }

    //toString
    @Override
    public String toString() {
        return FACES[face] + " of " + getSuit();
    }
}