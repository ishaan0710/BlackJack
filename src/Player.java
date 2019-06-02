import java.util.ArrayList;

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
        System.out.println("Card Drawn " + card);
        playerCards.add(card);
        if(getCurrSum()>Dealer.THRESHOLD) {
            dropGame();
        }
    }

    public void dropGame() {
        dealer.endGame();
        System.out.println("Player Cards : ");
        for(int card : playerCards) {
            System.out.print(card + " ");
        }
        System.out.println("Dealer Cards : ");
        try {
            dealer.printDealerCards();
            if(dealer.checkWin(getCurrSum())) {
                System.out.println("Winner");
            } else {
                System.out.println("Loser");
            }
        } catch (IllegalAccessException e) {
            System.out.println("End Game first before seeing dealer's card");
        }

    }

}
