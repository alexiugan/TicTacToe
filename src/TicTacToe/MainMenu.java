package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {
    JFrame frame;
    JPanel mainPanel;
    JButton single;
    JButton multi;
    JButton exit;

    JPanel difficultyPanel;
    JButton normal;
    JButton impossible;
    JButton back;

    JPanel holder;

    public MainMenu(){
        initMainMenu();
    }

    private void initMainMenu(){
        //Main Frame
        frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);


        //Card Panel
        holder =new JPanel();
        holder.setLayout(new CardLayout());

        //Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(150, 150, 150));

        single = new JButton("Single player");
        single.setBounds(90, 30, 200, 60);
        mainPanel.add(single);
        single.setFont(new Font("MV Boli", Font.BOLD, 18));
        single.setForeground(Color.BLACK);
        single.setFocusable(false);
        single.addActionListener(this);

        multi = new JButton("Multiplayer");
        multi.setBounds(90, 100, 200, 60);
        mainPanel.add(multi);
        multi.setFont(new Font("MV Boli", Font.BOLD, 18));
        multi.setForeground(Color.BLACK);
        multi.setFocusable(false);
        multi.addActionListener(this);

        exit = new JButton("Exit");
        exit.setBounds(90, 170, 200, 60);
        mainPanel.add(exit);
        exit.setFont(new Font("MV Boli", Font.BOLD, 18));
        exit.setForeground(Color.BLACK);
        exit.setFocusable(false);
        exit.addActionListener(this);

        //Difficulty Panel
        initDifficultyPanel();

        holder.add(mainPanel, "main");
        holder.add(difficultyPanel, "difficulty");

        frame.add(holder);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==single) {
            ((CardLayout) holder.getLayout()).show(holder, "difficulty");
        }
        else if(e.getSource()==multi)
            new TicTacToe(0);
        else if(e.getSource()==exit)
            System.exit(1);

        else if(e.getSource()==normal)
            new TicTacToe(1);
        else if(e.getSource()==impossible)
            new TicTacToe(2);
        else if(e.getSource()==back)
        {
            ((CardLayout) holder.getLayout()).show(holder, "main");
        }
    }


    private void initDifficultyPanel(){

        difficultyPanel = new JPanel();
        difficultyPanel.setLayout(null);
        difficultyPanel.setBackground(new Color(150, 150, 150));

        normal = new JButton("Normal");
        normal.setBounds(90, 45, 200, 60);
        difficultyPanel.add(normal);
        normal.setFont(new Font("MV Boli", Font.BOLD, 18));
        normal.setForeground(Color.BLACK);
        normal.setFocusable(false);
        normal.addActionListener(this);

        impossible = new JButton("Impossible");
        impossible.setBounds(90, 115, 200, 60);
        difficultyPanel.add(impossible);
        impossible.setFont(new Font("MV Boli", Font.BOLD, 18));
        impossible.setForeground(Color.BLACK);
        impossible.setFocusable(false);
        impossible.addActionListener(this);

        back = new JButton("Back");
        back.setBounds(20, 210, 100, 40);
        difficultyPanel.add(back);
        back.setFont(new Font("MV Boli", Font.BOLD, 14));
        back.setForeground(Color.BLACK);
        back.setFocusable(false);
        back.addActionListener(this);

        difficultyPanel.setVisible(false);
        frame.add(difficultyPanel, BorderLayout.CENTER);

    }


}
