/*
Description:
This class contains the memory game logic that
allows one player to play the memory game.
Currently, the board size is set to 6x6, but can be easily changed.

@authors: Vikas, Tate, and J.
*/

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class MemoryGame {
    private Card[][] board;
    private Player player;
    private int counter;

    private Card c1, c2;

    public MemoryGame(String p1) {
        board = new Card[6][6];
        player = new Player(p1);
        c1 = null;
        c2 = null;
        createBoard();
    }

    // This method creates a new board by shuffling the card IDs
    // and randomly populating the board.
    public void createBoard(){
        Random rand = new Random();
        int[] cards = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
        int index = 0;

        for (int i = 0; i < cards.length; i++) {
            int randIndex = rand.nextInt(cards.length);
            int temp = cards[randIndex];
            cards[randIndex] = cards[i];
            cards[i] = temp;
        }
       System.out.println(Arrays.toString(cards));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++){
                board[i][j] = new Card(cards[index++], i, j);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Player 1, what is your name? ");
        String playerName = scnr.nextLine();

        MemoryGame game = new MemoryGame(playerName);
        game.playGame();
    }

    // This method should be called after creating a MemoryGame object.
    // The playGame() method only returns when the game is completed.
    // When the game begins, the playGame() method displays the board using
    // StdDraw methods and allows a player to play the game.
    // It also allows the player to start a new game or exit.
    public void playGame() {
        Scanner scnr = new Scanner(System.in);
        int nx;
        int ny;
        double x = 0, y = 0;
        StdDraw.setScale(-0.1, 6.1);

        drawBoard();

        boolean game = true;
        while (game) {

            if (StdDraw.isMousePressed()) {
                x = StdDraw.mouseX();
                y = StdDraw.mouseY();
                System.out.printf("Mouse click @ %.2f, %.2f\n", x, y);

                // Is (x,y) within the board?
                if (x >= 0 && x <= 6 && y >= 0 && y <= 6) {
                    // Is board element corresponding to (x,y) EMPTY?
                    nx = (int) x;
                    ny = (int) y;
                    if (c1 == null) {
                        c1 = board[nx][ny];
                        c1.makeVisible();
                        c1.drawCard();
                    }
                    else if (c2 == null) {
                        c2 = board[nx][ny];
                        c2.makeVisible();
                        c2.drawCard();
                        if (c1.getID() == c2.getID()) {
                            c2.makeVisible();
                            c2.drawCard();
                            player.points++;
                        }
                        else {
                            c2.makeVisible();
                            StdDraw.pause(200);
                            c1.hideCard();
                            c2.hideCard();
                            c1.drawCard();
                            c2.drawCard();
                        }
                        // Set c1 and c2 to null after a match or mismatch.
                        c1 = null;
                        c2 = null;
                    }
                }
                // Pauses a short time for better playing experience.
                StdDraw.pause(150);
            }
            if (player.getPoints() == 18) {
                StdDraw.pause(100);
                StdDraw.clear();
                StdDraw.setPenColor(Color.green);
                StdDraw.text(3, 4, "You Won!");
                StdDraw.text(3, 3, "Do you want to play again? [y/n]");
                System.out.println("Do you want to play again? [y/n]");
                char uc = scnr.next().charAt(0);
                while (uc != 'y' && uc != 'n'){
                    uc = scnr.next().charAt(0);
                }
                if (uc == 'y'){
                    player.setPoints(0);
                    StdDraw.clear();
                    createBoard();
                    drawBoard();
                }

                else {
                    game = false;
                    StdDraw.clear();
                    StdDraw.text(3, 3, "Thanks For Playing!");
                    System.out.println("Thanks for Playing!");
                    StdDraw.pause(10000);
                    System.exit(0);
                }
            }
        }
    }

    // This method is called after creating a game to display the board on a canvas.
    // The board is displayed with black squares covering the numbers.
    public void drawBoard () {
        for (int j = 0; j < 7; j++) {
            StdDraw.setPenColor(Color.white);
            StdDraw.line(0, j, 6, j);
            StdDraw.line(j, 0, j, 6);
        }
        for (int i = 0; i < 6; i++) {
            for (int k = 0; k < 6; k++) {

                if (board[i][k].isVisible()) {
                    int cardID = board[i][k].getID();
                    StdDraw.setPenColor(Color.white);

                    StdDraw.text(i + 0.4, k + 0.4, Integer.toString(cardID));
                }
                else {
                    StdDraw.setPenColor(Color.black);
                    StdDraw.filledSquare(i + 0.4, k + 0.4, 0.4);
                }
            }
        }
    }
}