import java.util.ArrayList;
import java.util.Random;

public class Dealer {

    private ArrayList<Integer> dealerCards;
    private int sum;
    public static final int THRESHOLD = 21;
    private boolean isGameEnded = false;

    public boolean isGameEnded() {
        return isGameEnded;
    }

    public Dealer() {
        dealerCards = new ArrayList<>();
        dealerCards.add(drawCard());
        dealerCards.add(drawCard());
        sum = dealerCards.get(0) + dealerCards.get(1);
    }

    public void endGame() {
        isGameEnded = true;
    }

    public int getFirstCard() {
        return dealerCards.get(0);
    }

    public boolean checkWin(int score) {
        if(score>21)
            return false;
        while(sum<=17) {
            int card = drawCard();
            dealerCards.add(card);
            System.out.println("Dealer draws " + card );
            sum += card;
        }
        if(sum>THRESHOLD)
            return true;
        return score>sum;
    }

    public void printDealerCards() throws IllegalAccessException{
        if(isGameEnded) {
            for(int card : dealerCards) {
                System.out.print(card + " ");
            }
        } else throw new IllegalAccessException();
    }

    public static int drawCard() {
        return new Random().nextInt(11) + 1;
    }

}
