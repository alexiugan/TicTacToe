package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel footer_panel = new JPanel();
    JLabel footerText = new JLabel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    int opponent;
    int gameVal;

    public TicTacToe(int opponent) {
        this.opponent=opponent;
        initElements();
        firstTurn();
    }

    private int minimax(int depth, boolean playerTurn){
        int score = check(true, playerTurn);

        if(score==10 || score==-10 || score==0)
            return score;

        if(playerTurn){
            int best = -1000;
            for(int i =0; i<9;i++)
            {
                if(buttons[i].getText().equals(""))
                {
                    buttons[i].setText("O");
                    best = Math.max(best, minimax(depth+1, false));

                    buttons[i].setText("");
                }
            }
            System.out.println("max: " + best);
            return best;
        }

        else{
            int best =1000;

            for(int i=0; i<9; i++)
            {
                if(buttons[i].getText().equals(""))
                {
                    buttons[i].setText("X");
                    best = Math.min(best, minimax(depth+1, true));

                    buttons[i].setText("");
                }
            }
            System.out.println("min: " + best);
            return best;
        }
    }

    private void moveAI()
    {
        boolean change = false;
        do{
            if(opponent==1){
                int move = random.nextInt(9);
                if(buttons[move].getText().equals(""))
                {
                    buttons[move].setText("O");
                    buttons[move].setForeground(Color.BLUE);
                    change = true;
                }
            }
            else if(opponent==2){
                int bestField = minimaxBestMove();
                buttons[bestField].setText("O");
                buttons[bestField].setForeground(Color.BLUE);
                change = true;
            }
        }while(!change);
        switchToX();
        check(false, player1_turn);
        player1_turn = true;

    }

    private int minimaxBestMove()
    {
        int best = -1000;
        int bestField=-1;
        for(int i=0; i<9; i++)
        {
            if(buttons[i].getText().equals(""))
            {
                buttons[i].setText("O");
                int moveVal = minimax(0, false);
                buttons[i].setText("");

                if(moveVal>best)
                {
                    best = moveVal;
                    bestField = i;
                }
            }
        }
        return bestField;
    }

    private void initElements(){
        if (opponent == 0)
            frame.setTitle("Multiplayer");
        else
            frame.setTitle("Single player");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 55));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 450, 50);


        footerText.setBackground(Color.LIGHT_GRAY);
        footerText.setForeground(Color.BLACK);
        footerText.setFont(new Font("Ink Free", Font.BOLD, 22));
        footerText.setHorizontalAlignment(JLabel.CENTER);
        footerText.setText("test");
        footerText.setOpaque(true);

        footer_panel.setLayout(new BorderLayout());
        footer_panel.setBounds(0, 420, 450, 30);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for(int i=0; i<9; i++)
        {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 90));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textField);
        footer_panel.add(footerText);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(footer_panel, BorderLayout.SOUTH);
        frame.add(button_panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        boolean gameFinished;
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        switchToO();
                        gameVal = check(false, player1_turn);
                        player1_turn = false;
                        gameFinished = gameVal != -1;
                        if (!gameFinished && opponent != 0)
                            moveAI();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        switchToX();
                        gameVal = check(false, player1_turn);
                        player1_turn = true;
                    }
                }
            }
        }
    }

    private void switchToX()
    {
        footerText.setForeground(new Color(255, 0, 0));
        footerText.setText("X turn");
    }

    private void switchToO()
    {
        footerText.setForeground(new Color(0, 0, 255));
        footerText.setText("O turn");
    }

    private void firstTurn() {

        if(random.nextInt(2) == 0){
            player1_turn = true;
            footerText.setForeground(new Color(255, 0, 0));
            footerText.setText("X turn");
        }
        else{
            player1_turn = false;
            footerText.setForeground(new Color(0, 0, 255));
            footerText.setText("O turn");
            if(opponent!=0)
                moveAI();
        }
    }

    public int check(boolean minimax, boolean playerTurn){

        for(int i=0; i<3; i++)
        {
            if(
                    ((buttons[3*i].getText().equals("X") || (buttons[3*i].getText().equals("O")))) &&
                            (buttons[3*i].getText().equals(buttons[1 + 3*i].getText())) &&
                            (buttons[1 + 3*i].getText().equals(buttons[2 + 3*i].getText()))
            ){
                if(!minimax)
                    win(3*i, 1 + 3*i, 2 + 3*i);
                if(playerTurn)
                    return 10;
                else
                    return -10;
            }
        }

        for(int i=0; i<3; i++)
        {
            if(
                    ((buttons[i].getText().equals("X") || (buttons[i].getText().equals("O")))) &&
                            (buttons[i].getText().equals(buttons[i+3].getText())) &&
                            (buttons[3+i].getText().equals(buttons[6 + i].getText()))
            ){
                if(!minimax)
                    win(i, 3 + i, 6 + i);
                if(playerTurn)
                    return 10;
                else
                    return -10;
            }
        }

        if(
                ((buttons[0].getText().equals("X") || (buttons[0].getText().equals("O")))) &&
                        (buttons[0].getText().equals(buttons[4].getText())) &&
                        (buttons[4].getText().equals(buttons[8].getText()))
        ){
            if(!minimax)
                win(0, 4, 8);
            if(playerTurn)
                return 10;
            else
                return -10;
        }

        if(
                ((buttons[2].getText().equals("X") || (buttons[2].getText().equals("O")))) &&
                        (buttons[2].getText().equals(buttons[4].getText())) &&
                        (buttons[4].getText().equals(buttons[6].getText()))
        ){
            if(!minimax)
                win(2, 4, 6);
            if(playerTurn)
                return 10;
            else
                return -10;
        }


        if (!isMoveLeft()) {
            if(!minimax)
                win(-1, -1, -1);
            return 0;
        }

        return -1;
    }

    private boolean isMoveLeft(){
        boolean emptyFields = false;
        for (int i = 0; i < 9; i++)
            if (buttons[i].getText().equals(""))
                emptyFields = true;
        return emptyFields;
    }


    public void win(int a, int b, int c){

        if(a==-1) {
            footerText.setForeground(Color.WHITE);
            footerText.setText("It's a draw!");
        }
        else {
            buttons[a].setBackground(Color.GREEN);
            buttons[b].setBackground(Color.GREEN);
            buttons[c].setBackground(Color.GREEN);
            footerText.setForeground(buttons[a].getText().equals("X") ? Color.RED : Color.BLUE);
            footerText.setText(buttons[a].getText() + " wins!");
        }

        for(int i =0; i<9; i++)
            buttons[i].setEnabled(false);
        endingMenu();
    }

    public void endingMenu(){
        new EndingMenu();
    }

    public class EndingMenu implements ActionListener {
        JFrame endFrame;
        JPanel panel;
        JButton restartBtn;
        JButton exitBtn;

        private EndingMenu() {
            initEndingMenu();
        }

        private void initEndingMenu() {
            endFrame = new JFrame("The End");
            endFrame.setSize(250, 100);
            endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            endFrame.getContentPane().setBackground(new Color(50, 50, 50));
            endFrame.setLayout(new BorderLayout());
            endFrame.setLocationRelativeTo(frame);
            endFrame.setResizable(false);
            endFrame.setVisible(true);

            panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.setBackground(Color.BLACK);

            restartBtn = new JButton("Restart");
            panel.add(restartBtn);
            restartBtn.setFont(new Font("MV Boli", Font.BOLD, 18));
            restartBtn.setForeground(Color.BLACK);
            restartBtn.setFocusable(false);
            restartBtn.addActionListener(this);

            exitBtn = new JButton("Exit");
            panel.add(exitBtn);
            exitBtn.setFont(new Font("MV Boli", Font.BOLD, 18));
            exitBtn.setForeground(Color.BLACK);
            exitBtn.setFocusable(false);
            exitBtn.addActionListener(this);


            endFrame.add(panel, BorderLayout.CENTER);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == exitBtn)
            {
                endFrame.dispose();
                frame.dispose();
            }
            else if (e.getSource() == restartBtn)
            {
                resetFrame();
                endFrame.dispose();
                firstTurn();
            }
        }
    }

    private void resetFrame(){
        JButton buttonHelper = new JButton();

        for(int i=0; i<9; i++)
        {
            buttons[i].setEnabled(true);
            buttons[i].setText("");
            buttons[i].setBackground(buttonHelper.getBackground());
        }
    }
}
