package comp1110.ass2;

public class Piece {
    //this class is used to place the pieces into the board
    //the string of the tiles corresponding the type of each tiles and placed them into the right corresponding coordinate
    private String tile;
    private int x;
    private int y;
    private String tileplacement;

    public Piece(String tileplacement) {
        this.tile = tileplacement.substring(0,3);
        this.x = tileplacement.charAt(4) - '0';
        this.y = tileplacement.charAt(5) - '0';


    }


    public String getTile() {
        return tile;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


}
