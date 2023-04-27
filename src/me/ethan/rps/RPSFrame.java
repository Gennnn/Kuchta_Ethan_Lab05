package me.ethan.rps;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;

public class RPSFrame extends JFrame{
    JPanel mainPnl;
    JPanel actionPnl;
    JPanel logPnl;
    JPanel statsPnl;
    public static JPanel displayPnl;
    JButton rockBtn;
    JButton scissorsBtn;
    JButton paperBtn;
    JButton quitBtn;
    public static JLabel pMove = new JLabel();
    JLabel vsIcon;
    public static JLabel cMove = new JLabel();
    public static JTextArea logText;
    public static JScrollPane logScroller;
    JTextField pWins;
    JTextField cWins;
    JTextField ties;
    int cWinsInt;
    int pWinsInt;
    int tiesInt;
    public static ImageIcon rockIcon;
    public static ImageIcon paperIcon;
    public static ImageIcon scissorsIcon;
    public static ImageIcon blankIcon;
    public static int rocksUsed;
    public static int paperUsed;
    public static int scissorsUsed;
    public static String lastUsed;


    public RPSFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        cWinsInt = 0;
        pWinsInt = 0;
        tiesInt = 0;
        createDisplayPanel();
        createActionPanel();
        createStatsPanel();
        createLogPanel();
        c.weightx = 0.5;
        c.weighty = 0.5;
        //c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.ipadx = 290;
        c.ipady = 250;
        mainPnl.add(displayPnl, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = GridBagConstraints.REMAINDER;
        c.gridwidth = 2;
        c.ipady = 0;
        c.ipadx = 700;
        c.ipady = 300;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        mainPnl.add(logPnl, c);
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.ipadx = 300;
        c.ipady = 240;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        mainPnl.add(actionPnl, c);
        c.gridx = 3;
        c.gridy = 3;
        c.gridheight = GridBagConstraints.REMAINDER;
        c.gridwidth = 1;
        c.ipadx = 400;
        c.ipady = 300;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        mainPnl.add(statsPnl, c);
        mainPnl.setBackground(Color.darkGray);
        add(mainPnl);
        setSize(1000,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void createDisplayPanel() {
        displayPnl = new JPanel();
        displayPnl.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        rockIcon = transformIcon(new ImageIcon("rock.png"));
        paperIcon = transformIcon(new ImageIcon("paper.png"));
        scissorsIcon = transformIcon(new ImageIcon("scissors.png"));
        blankIcon = transformIcon(new ImageIcon("blank.png"));
        pMove.setIcon(blankIcon);
        cMove.setIcon(blankIcon);
        JLabel vsIcon = new JLabel();
        vsIcon.setText("VS");
        vsIcon.setForeground(Color.white);
        vsIcon.setFont(new Font("SansSerif", Font.ITALIC, 30));
        c.insets = new Insets(2,2,2,2);
        c.ipady = 20;
        c.ipadx = 40;
        c.gridx = 1;
        c.gridy = 1;
        displayPnl.add(pMove, c);
        c.gridx = 5;
        displayPnl.add(vsIcon,c);
        c.gridx = 9;
        displayPnl.add(cMove, c);

        displayPnl.setBackground(Color.darkGray);

    }
    private void createActionPanel() {
        actionPnl = new JPanel();
        JPanel actionPnlInternal = new JPanel();
        actionPnlInternal.setLayout(new GridBagLayout());
        rockBtn = new JButton("Rock");
        paperBtn = new JButton("Paper");
        scissorsBtn = new JButton("Scissors");
        quitBtn = new JButton("Quit");
        GridBagConstraints c = new GridBagConstraints();

        rockBtn.addActionListener(
                (ActionEvent ae) ->
                {
                    GameEvent.setGameIcon(pMove, rockIcon);
                    rocksUsed =+ 1;
                    rockBtn.setBorder(new BevelBorder(BevelBorder.LOWERED));
                    int result = 0;
                    try {
                        result = GameEvent.results("r");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (result == 0) {
                        cWinsInt++;
                        cWins.setText("Computer Wins: " + Integer.toString(cWinsInt));
                    } else if (result == 1) {
                        pWinsInt++;
                        pWins.setText("Player Wins: " + Integer.toString(pWinsInt));
                    } else if (result == 2) {
                        tiesInt++;
                        ties.setText("Ties: " + Integer.toString(tiesInt));
                    }

                }
        );
        scissorsBtn.addActionListener(
                (ActionEvent ae) ->
                {
                    GameEvent.setGameIcon(pMove, scissorsIcon);
                    scissorsUsed =+ 1;
                    int result = 0;
                    try {
                        result = GameEvent.results("s");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (result == 0) {
                        cWinsInt++;
                        cWins.setText("Computer Wins: " + Integer.toString(cWinsInt));
                    } else if (result == 1) {
                        pWinsInt++;
                        pWins.setText("Player Wins: " + Integer.toString(pWinsInt));
                    } else if (result == 2) {
                        tiesInt++;
                        ties.setText("Ties: " + Integer.toString(tiesInt));
                    }
                }
        );
        paperBtn.addActionListener(
                (ActionEvent ae) ->
                {
                    GameEvent.setGameIcon(pMove, paperIcon);
                    paperUsed =+ 1;
                    int result = 0;
                    try {
                        result = GameEvent.results("p");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (result == 0) {
                        cWinsInt++;
                        cWins.setText("Computer Wins: " + Integer.toString(cWinsInt));
                    } else if (result == 1) {
                        pWinsInt++;
                        pWins.setText("Player Wins: " + Integer.toString(pWinsInt));
                    } else if (result == 2) {
                        tiesInt++;
                        ties.setText("Ties: " + Integer.toString(tiesInt));
                    }
                }
        );
        quitBtn.addActionListener(
                (ActionEvent ae) -> {
                    System.exit(0);
                }
        );
        rockBtn.addChangeListener(e -> {
            ButtonModel model = rockBtn.getModel();
            if (model.isPressed()) {
                rockBtn.setBorder(new BevelBorder(BevelBorder.LOWERED));
            } else {
                rockBtn.setBorder(new BevelBorder(BevelBorder.RAISED));
            }
        });

        paperBtn.addChangeListener(e -> {
            ButtonModel model = paperBtn.getModel();
            if (model.isPressed()) {
                paperBtn.setBorder(new BevelBorder(BevelBorder.LOWERED));
            } else {
                paperBtn.setBorder(new BevelBorder(BevelBorder.RAISED));
            }
        });
        scissorsBtn.addChangeListener(e -> {
            ButtonModel model = scissorsBtn.getModel();
            if (model.isPressed()) {
                scissorsBtn.setBorder(new BevelBorder(BevelBorder.LOWERED));
            } else {
                scissorsBtn.setBorder(new BevelBorder(BevelBorder.RAISED));
            }
        });
        quitBtn.addChangeListener(e -> {
            ButtonModel model = quitBtn.getModel();
            if (model.isPressed()) {
                quitBtn.setBorder(new BevelBorder(BevelBorder.LOWERED));
            } else {
                quitBtn.setBorder(new BevelBorder(BevelBorder.RAISED));
            }
        });
        rockBtn.setPreferredSize(new Dimension(150, 40));
        paperBtn.setPreferredSize(new Dimension(150, 40));
        scissorsBtn.setPreferredSize(new Dimension(150, 40));
        quitBtn.setPreferredSize(new Dimension(150, 40));
        rockBtn.setForeground(Color.white);
        paperBtn.setForeground(Color.white);
        scissorsBtn.setForeground(Color.white);
        quitBtn.setForeground(Color.white);

        rockBtn.setFont(new Font("Helvetica", Font.PLAIN, 16));
        paperBtn.setFont(new Font("Helvetica", Font.PLAIN, 16));
        scissorsBtn.setFont(new Font("Helvetica", Font.PLAIN, 16));
        quitBtn.setFont(new Font("Helvetica", Font.PLAIN, 16));
        rockBtn.setBackground(Color.darkGray);
        paperBtn.setBackground(Color.darkGray);
        scissorsBtn.setBackground(Color.darkGray);
        quitBtn.setBackground(Color.darkGray);
        rockBtn.setBorder(new BevelBorder(BevelBorder.RAISED));
        paperBtn.setBorder(new BevelBorder(BevelBorder.RAISED));
        scissorsBtn.setBorder(new BevelBorder(BevelBorder.RAISED));
        quitBtn.setBorder(new BevelBorder(BevelBorder.RAISED));

        c.gridy = 1;
        c.gridheight = 2;
        c.gridwidth = 3;
        c.insets = new Insets(15,1,15,1);
        c.ipadx = 20;
        c.ipady = 10;
        actionPnlInternal.add(rockBtn, c);
        c.gridy = 5;
        actionPnlInternal.add(paperBtn,c );
        c.gridy = 9;
        //c.ipadx = 0;
        actionPnlInternal.add(scissorsBtn,c );
        c.ipadx = 18;
        c.gridy = 13;
        actionPnlInternal.add(quitBtn,c);
        actionPnlInternal.setBorder(new TitledBorder(""));
        actionPnl.add(actionPnlInternal);
        actionPnl.setBackground(Color.DARK_GRAY);
        actionPnlInternal.setBackground(Color.DARK_GRAY);
        actionPnlInternal.setPreferredSize(new Dimension(250, 350));
        //actionPnl.setPreferredSize(new Dimension(750,250));
    }
    private void createStatsPanel() {
        statsPnl = new JPanel();
        statsPnl.setLayout(new GridBagLayout());
        JPanel statsPnlInternal = new JPanel();
        statsPnlInternal.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        statsPnlInternal.setBorder(new TitledBorder("Stats"));
        TitledBorder border = (TitledBorder) statsPnlInternal.getBorder();
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleFont(new Font("SansSerif", Font.BOLD, 24));
        border.setTitleColor(Color.WHITE);
        statsPnlInternal.setBackground(Color.darkGray);
        statsPnlInternal.setOpaque(true);
        //statsPnl.setPreferredSize(new Dimension(250,250));
        JLabel temp = new JLabel("Stats");
        pWins = new JTextField();
        cWins = new JTextField();
        ties = new JTextField();
        pWins.setEditable(false);
        cWins.setEditable(false);
        ties.setEditable(false);
        pWins.setBackground(Color.darkGray);
        cWins.setBackground(Color.darkGray);
        ties.setBackground(Color.darkGray);
        pWins.setForeground(Color.WHITE);
        pWins.setFont(new Font("Helvetica", Font.PLAIN, 16));
        cWins.setForeground(Color.WHITE);
        cWins.setFont(new Font("Helvetica", Font.PLAIN, 16));
        ties.setForeground(Color.WHITE);
        ties.setFont(new Font("Helvetica", Font.PLAIN, 16));
        pWins.setBorder(new TitledBorder(""));
        cWins.setBorder(new TitledBorder(""));
        ties.setBorder(new TitledBorder(""));
        pWins.setHorizontalAlignment(JLabel.CENTER);
        cWins.setHorizontalAlignment(JLabel.CENTER);
        ties.setHorizontalAlignment(JLabel.CENTER);
        pWins.setPreferredSize(new Dimension(150, 40));
        cWins.setPreferredSize(new Dimension(150, 40));
        ties.setPreferredSize(new Dimension(150, 40));
        pWins.setText("Player Wins: " +  Integer.toString(pWinsInt));
        cWins.setText("Computer Wins: " + Integer.toString(cWinsInt));
        ties.setText("Ties: " + Integer.toString(tiesInt));
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        statsPnlInternal.add(pWins, c);
        c.gridy = 2;
        statsPnlInternal.add(cWins,c );
        c.gridy = 4;
        statsPnlInternal.add(ties,c );
        statsPnlInternal.setPreferredSize(new Dimension(250, 250));
        c.fill = HEIGHT;
        c.gridheight= 5;
        c.anchor = GridBagConstraints.CENTER;
        c.gridheight = 0;
        c.gridx = 0;
        c.gridy = 0;

        statsPnl.add(statsPnlInternal, c);
        statsPnl.setBackground(Color.darkGray);
    }
    private void createLogPanel() {
        logPnl = new JPanel();
        logPnl.setLayout(new GridBagLayout());
        //logPnl.setPreferredSize(new Dimension(250,750));
        logText = new JTextArea();
        logScroller = new JScrollPane(logText);
        logScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        logScroller.setPreferredSize(new Dimension(640, 250));
        logScroller.setBorder(new TitledBorder("Move Log"));
        TitledBorder border = (TitledBorder) logScroller.getBorder();
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleFont(new Font("SansSerif", Font.BOLD, 24));
        border.setTitleColor(Color.WHITE);
        logScroller.setBackground(Color.darkGray);
        logScroller.setOpaque(true);
        logScroller.getVerticalScrollBar().setBackground(Color.DARK_GRAY);
        logScroller.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.WHITE;
            }
        });
        logText.setBackground(Color.DARK_GRAY);
        logText.setForeground(Color.WHITE);
        logText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;

        logPnl.add(logScroller, c);

        logPnl.setBackground(Color.darkGray);
    }



    private ImageIcon transformIcon(ImageIcon icon) {
        Image rawImg = icon.getImage();
        Image transImg = rawImg.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        ImageIcon retImg = new ImageIcon(transImg);
        return retImg;
    }


}



