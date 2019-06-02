import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player name");
        String playerName = scanner.next();
        Dealer dealer = new Dealer();
        Player player = new Player(playerName, dealer);
        ArrayList<Integer> playedCard = player.getPlayerCards();
        System.out.println("Dealer is dealt with " + dealer.getFirstCard());
        System.out.print("The player is dealt ");
        for(int card : playedCard) {
            System.out.print(card + " ");
        }

        while(!dealer.isGameEnded()) {
            System.out.println();
            System.out.println("Total sum = " + player.getCurrSum());
            System.out.println("Enter whether to hit or stay");
            String input = scanner.next();
            if(input.equalsIgnoreCase("hit")) {
                player.hit();
            } else if(input.equalsIgnoreCase("stay")) {
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        player.dropGame();
    }

}
