import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class BlackJack implements ActionListener {

    JTextArea nameField;
    JFrame mainInterface;
    JButton hit;
    JButton stay;
    JButton enter;
    public static JTextArea dealerInstruction;
    Dealer dealer;
    Player player;

    public BlackJack() {
        dealer = new Dealer();

        displayGUI();
    }

    public void displayGUI() {
        mainInterface = new JFrame();
        nameField = new JTextArea(1,15);

        hit = new JButton("Hit");
        stay = new JButton("Stay");
        enter = new JButton("Enter");
        dealerInstruction = new JTextArea(15,50);

        JPanel instructions = new JPanel();
        instructions.setLayout(new BorderLayout());
        instructions.add(new JLabel("Welcome to BlackJack!", JLabel.CENTER), "North");
        instructions.add(new JScrollPane(dealerInstruction),"Center");

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        namePanel.add(nameField);
        namePanel.add(enter);

        JPanel movePanel = new JPanel();
        movePanel.setLayout(new FlowLayout());
        movePanel.add(hit);
        movePanel.add(stay);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2,1));
        southPanel.add(movePanel);
        southPanel.add(namePanel);

        mainInterface.add(instructions,"Center");
        mainInterface.add(southPanel,"South");

        mainInterface.pack();
        mainInterface.setTitle("BLACKJACK");
        mainInterface.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainInterface.setVisible(true);

        hit.addActionListener(this);
        stay.addActionListener(this);
        enter.addActionListener(this);

        mainInterface.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();

        if(button==enter) {
            if(nameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Enter Name");
            }
            player = new Player(nameField.getText(), dealer);

        }

        else if(button == hit) {
            player.hit();
            if(dealer.isGameEnded())
                player.dropGame();
        }

        else if(button == stay) {
            player.dropGame();
        }



    }


    public static void main(String[] args) {
        new BlackJack();
    }

}
