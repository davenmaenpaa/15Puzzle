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
        JPanel topPanel = new JPanel();

        /* Board panel */
        JPanel boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(450, 450));
        boardPanel.setLayout(new GridLayout(4,4));


    }

}

