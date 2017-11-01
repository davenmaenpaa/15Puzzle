package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Engine {
    private JTextField[] board = new JTextField[16];
    private final Color FILLED_TILE = Color.LIGHT_GRAY;
    private final Color EMPTY_TILE = Color.WHITE;
    private int width = 4;
    private JLabel label;

    Engine() {
        for(int i = 0; i < board.length; i++) {
            board[i] = new JTextField();
            board[i].setEditable(false);
            board[i].setHorizontalAlignment((JTextField.CENTER));
            board[i].setFont(new Font("Monospaced", Font.BOLD, 20));

            if(i == 0) {
                board[i].setText("");
                board[i].setBackground(EMPTY_TILE);
            } else {
                board[i].setText(String.valueOf(i));
                board[i].setBackground(FILLED_TILE);
            }

            int finalI = i;
            board[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    move(board[finalI].getText());
                }
            });
        }

        label = new JLabel("");
        label.setFont(new Font("Arial", Font.BOLD, 15));
    }

    private int intConvert(JTextField tile) {
        if(tile.getText().equals(""))
            return 0;
        return Integer.parseInt(tile.getText());
    }

    /* http://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html */
    private boolean solvable() {
        int countInversions = 0;
        for (int i = 0; i < board.length - 2; i++) {
            for (int j = 0; j < i; j++) {
                if (intConvert(board[j]) > intConvert(board[i])) {
                    countInversions++;
                }
            }
        }
        return countInversions % 2 == 0;
    }

    void newGame() {
        shuffle();

        while(!solvable() || ifWon()) {
            shuffle();
        }
    }

    /* Durstenfeld shuffle */
    private void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = board.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            swap(board[i], board[index]);
        }
    }

    private void swap(JTextField first, JTextField second) {
        String temp;

        if(first.getText().equals("")) {
            temp = first.getText();
            first.setText(second.getText());
            first.setBackground(FILLED_TILE);

            second.setText(temp);
            second.setBackground(EMPTY_TILE);

        } else if(second.getText().equals("")) {
            temp = first.getText();
            first.setText(second.getText());
            first.setBackground(EMPTY_TILE);

            second.setText(temp);
            second.setBackground(FILLED_TILE);

        } else {
            temp = first.getText();
            first.setText(second.getText());
            second.setText(temp);
        }

        if(ifWon())
            label.setText("Grattis, du vann!");
        else
            label.setText("");
    }

    private boolean ifWon() {
        if(!board[0].getText().equals("1")  || !board[board.length - 1].getText().equals(""))
            return false;
        else {
            for(int i = 1; i < board.length - 1; i++) {
                if(intConvert(board[i]) != i+1) {
                    System.out.println(i);
                    return false;
                }
            }
        }
        return true;
    }

    private void move(String tile) {
        if(tile.equals(""))
            return;

        int pos;
        for(pos = 0; pos < board.length - 1; pos++) {
            if(tile.equals(board[pos].getText()))
                break;
        }

        if (tryAbove(pos)) return;
        if (tryBelow(pos)) return;
        if (tryLeft(pos)) return;
        tryRight(pos);
    }

    private boolean tryAbove(int pos) {
        if (pos < 4) return false;
        if (!board[pos-width].getText().equals(""))
            return false;
        swap(board[pos], board[pos-4]);
        return true;
    }

    private boolean tryBelow(int pos) {
        int height = 4;
        if (pos > width*(height -1)-1)
            return false;

        if (!board[pos+width].getText().equals(""))
            return false;
        swap(board[pos], board[pos+4]);
        return true;
    }

    private boolean tryLeft(int pos) {
        if (pos % width == 0)
            return false;

        if (!board[pos-1].getText().equals(""))
            return false;
        swap(board[pos], board[pos-1]);
        return true;
    }

    private void tryRight(int pos) {
        if (pos % width == width-1)
            return;

        if (!board[pos+1].getText().equals(""))
            return;
        swap(board[pos], board[pos+1]);
    }

    JLabel getLabel() {
        return label;
    }

    JTextField[] getBoard() {
        return board;
    }
}

