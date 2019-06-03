import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Player {

    private String name;
    private ArrayList<Integer> playerCards;
    private boolean isWinner = true;
    private Dealer dealer;

    public Player(String name, Dealer dealer) {
        this.name = name;
        this.dealer = dealer;
        isWinner = false;
        playerCards = new ArrayList<>();
        playerCards.add(Dealer.drawCard());
        playerCards.add(Dealer.drawCard());
        BlackJack.dealerInstruction.append("Welcome to BlackJack " + name + "\n");
        BlackJack.dealerInstruction.append("You have been drawn cards " +
                playerCards.get(0) + " " + playerCards.get(1) + "\n");
        BlackJack.dealerInstruction.append("The dealer's card is " + dealer.getFirstCard() +
                " and the other card is hidden\n");
    }

    public Player() {
        this(new Dealer());
    }

    public Player(Dealer dealer) {
        this("Player", dealer);
    }

    public int getCurrSum() {
        int sum = 0;
        for(int card : playerCards)
            sum += card;
        return sum;
    }

    public ArrayList<Integer> getPlayerCards() {
        return playerCards;
    }

    public void hit() {
        int card = Dealer.drawCard();
        BlackJack.dealerInstruction.append("Card Drawn " + card + "\n");
        playerCards.add(card);
        BlackJack.dealerInstruction.append("Current Total " + getCurrSum() + "\n");
        if(getCurrSum()>Dealer.THRESHOLD) {
            dropGame();
        }
    }

    public void dropGame() {
        dealer.endGame();
        BlackJack.dealerInstruction.append("Player Cards :");
        for(int card : playerCards) {
            BlackJack.dealerInstruction.append(card + " ");
        }
        BlackJack.dealerInstruction.append("\nDealer Cards : ");
        try {
            dealer.printDealerCards();
            if(dealer.checkWin(getCurrSum())) {
                BlackJack.dealerInstruction.append("\nWinner\n");
                wait(5000);
                BlackJack.dealerInstruction.setText(null);
            } else {
                BlackJack.dealerInstruction.append("\nLoser\n");

            }
        } catch (IllegalAccessException e) {
            System.out.println("End Game first before seeing dealer's card");
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }

}
