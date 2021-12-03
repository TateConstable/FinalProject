/*

Description:
This class allows users to create a player object. 
Each player object contains a name and points. 

@authors: Vikas, Tate, and Joao.
*/

public class Player {

    public int points;
    private String name;

    public Player(){
        points = 0;
        name = " ";
    }
    public Player(String name){
        points = 0;
        this.name = name;
    }

    public int getPoints(){
        return points;
    }
    public void setPoints(int points){
        this.points = points;
    }
}