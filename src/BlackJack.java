import static java.lang.System.*;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack
{
    private static final int DEALER = 0;
    private final ArrayList<Playerable> players;
    private int playerAmount = 0;
    public BlackJack()
    {
        players = new ArrayList<>();
        players.add(new Dealer());
        getPlayerAmount();
        for(int i = 0; i < playerAmount; i++) {
            players.add(new Player());
        }
    }
    public Card deal() {
        return ((Dealer)players.get(DEALER)).deal();
    }
    public void getPlayerAmount() {
        Scanner scanner = new Scanner(System.in);
        out.print("How many players [1-10] :: ");
        playerAmount = scanner.nextInt();
        scanner.nextLine();
        out.println();
    }
    public void playGame()
    {
        Scanner scanner = new Scanner(System.in);
        char choice;
        Dealer dealer = (Dealer) players.get(DEALER);
        dealer.shuffle();
        do{
            //deal two cards to players first and dealer last
            for (int i = 1; i < players.size(); i++) {
                Player player = (Player) players.get(i);
                player.addCardToHand(deal());
                player.addCardToHand(deal());
            }
            dealer.addCardToHand(deal());
            dealer.addCardToHand(deal());

            for(int i = 1; i < players.size(); i++) {
                Player player = (Player) players.get(i);
                out.println("PLAYER " + i);
                out.println("Current hand: " + player);
                while (player.getHandValue() < 21) {
                    out.print("Do you want to hit? [Y,y,N,n] :: ");
                    choice = scanner.nextLine().charAt(0);
                    if(choice == 'Y' || choice == 'y') {
                        player.addCardToHand(deal());
                        out.println("Cards in Hand: " + player);
                    }else {
                        break;
                    }
                }
                out.println("\nPLAYER " + i + " FINAL VALUES");
                out.println("Hand Value: " + player.getHandValue());
                out.println("Hand Size: " + player.getHandSize());
                out.println("Cards in Hand: " + player + "\n");
            }
            //dealer always hits if less than 17
            while (dealer.getHandValue() < 17) {
                dealer.addCardToHand(deal());
            }
            out.println("-------------------");
            out.println("DEALER FINAL VALUES");
            out.println("Hand Value: " + dealer.getHandValue());
            out.println("Hand Size: " + dealer.getHandSize());
            out.println("Cards in Hand: " + dealer + "\n");
            for(int i = 1; i < players.size(); i++) {
                Player player = (Player) players.get(i);
                out.println("PLAYER " + i + " FINAL VALUES");
                out.println("Hand Value: " + player.getHandValue());
                out.println("Hand Size: " + player.getHandSize());
                out.println("Cards in Hand: " + player + "\n");
            }
            //get highest within 21
            int playerHighest = 0;
            for(int i = 1; i < players.size(); i++) {
                int temp = players.get(i).getHandValue();
                if (temp > playerHighest && temp <= 21) {
                    playerHighest = players.get(i).getHandValue();
                }
            }
            ArrayList<Integer> winnerIndexes = new ArrayList<>();
            for(int i = 1; i < players.size(); i++) {
                if (players.get(i).getHandValue() == playerHighest) {
                    winnerIndexes.add(i);
                }
            }

            int dealerTotal = dealer.getHandValue();
            if((playerHighest == dealerTotal)) {
                out.println("TIE!");
            }else if(playerHighest > 21 && dealerTotal <= 21){
                out.println("DEALER wins - All PLAYERS busted!");
                dealer.setWinCount(dealer.getWinCount()+1);
            }else if(playerHighest <= 21 && dealerTotal > 21){
                out.println("PLAYERS win - DEALER busted!");
                out.print("\tWinners: ");
                for(int i = 0; i < winnerIndexes.size(); i++) {
                    if(i == winnerIndexes.size()-1) {
                        out.println("PLAYER " + winnerIndexes.get(i));
                    }else {
                        out.print("PLAYER " + winnerIndexes.get(i) + ", ");
                    }
                    Player temp = (Player) players.get(winnerIndexes.get(i));
                    temp.setWinCount(temp.getWinCount() + 1);
                }
            }else if(playerHighest > 21){
                out.println("Everyone busted!");
            }else if(playerHighest < dealerTotal){
                out.println("DEALER has bigger hand value!");
                dealer.setWinCount(dealer.getWinCount()+1);
            }else{
                out.println("At least one PLAYER has a bigger hand value!");
                out.print("\tWinners: ");
                for(int i = 0; i < winnerIndexes.size(); i++) {
                    Playerable temp = players.get(winnerIndexes.get(i));
                    temp.setWinCount(temp.getWinCount() + 1);
                    if(i == winnerIndexes.size()-1) {
                        out.println("PLAYER " + winnerIndexes.get(i));
                    }else {
                        out.print("PLAYER " + winnerIndexes.get(i) + ", ");
                    }
                }
            }

            out.println("\nDEALER has won " + dealer.getWinCount() + " times");
            for (int i = 1; i < players.size(); i++) {
                out.println("PLAYER " + i + " has won " + players.get(i).getWinCount() + " times");
            }

            out.print("\nDo you want to play again [Y,y,N,n] :: ");
            choice = scanner.nextLine().charAt(0);
            if(choice == 'Y' || choice == 'y') {
                for(Playerable player : players) {
                    player.resetHand();
                }
                out.println("\n");
            }else if(choice == 'N' || choice == 'n') {
                out.println("\n");
                break;
            }
        }while (true);
    }

    public static void main(String[] args)
    {
        BlackJack game = new BlackJack();
        game.playGame();
    }
}