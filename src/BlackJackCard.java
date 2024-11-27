public class BlackJackCard extends Card
{
    BlackJackCard() {
        super();
    }
    BlackJackCard(int face, String suit) {
        super(face, suit);
    }
    public int getValue()
    {
        //enables you to build the value for the game into the card
        //this makes writing the whole program a little easier
        if(getFace() == 1) {
            return 11;
        }else return Math.min(getFace(), 10);
    }
}