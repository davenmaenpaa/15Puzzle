package com.game;

import javax.swing.*;
import java.awt.*;

class Board extends JFrame{
    private final Engine engine = new Engine();

    Board() {
         /* Frame */
        setSize(450,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("15 Puzzle game");
        setResizable(false);
        setLocationRelativeTo(null);

        /* Main panel */
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        /* Board panel */
        JPanel boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(450, 450));
        boardPanel.setLayout(new GridLayout(4,4));

        for(JTextField tiles : engine.getBoard())
            boardPanel.add(tiles);

        /* Button */
        JButton newGameButton = new JButton("Nytt spel");
        newGameButton.addActionListener(e -> engine.newGame());

        /* Building */
        add(content);
        content.add(topPanel);
        content.add(boardPanel);

        topPanel.add(newGameButton);
        topPanel.add(Box.createRigidArea(new Dimension(50, 25)));
        topPanel.add(engine.getIfWonLabel());

        engine.newGame();
        setVisible(true);
    }

}

