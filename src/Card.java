import java.awt.*;
/*

Description:
This class maintains the state (x, y coordinates of the card, card visibility and ID)
of each card on the board. It is also responsible for displaying and hiding a card when selected
eliminating the need to redraw the board after every turn.

@authors: Vikas, Tate, and Joao.
*/

public class Card {

    private int ID;
    private boolean visible;
    private int row;
    private int column;


    public Card(int id, int row, int column) {

        ID = id;
        visible = false;
        this.row = row;
        this.column = column;
    }
    public int getID(){
        return ID;
    }

    public void hideCard() {
        visible = false;
    }
    public void makeVisible() {
        visible = true;
    }
    public boolean isVisible() {
        return visible;
    }

    public void drawCard() {
        if (isVisible()) {

            StdDraw.setPenColor(Color.white);

            StdDraw.text(row + 0.4, column + 0.4, Integer.toString(ID));
        }
        else {
            StdDraw.setPenColor(Color.black);
            StdDraw.filledSquare(row + 0.4, column + 0.4, 0.4);
        }
    }
}

