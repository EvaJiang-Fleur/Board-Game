package comp1110.ass2.gui;


import comp1110.ass2.Metro;

public class metrogame  {
    Metro[] player;
    Board board=new Board();
    int scores;

    public void newGame(String[] playOption, int[] computerOption) {
        if (this.player != null) {
            for (int i = 0; i < this.player.length; ++i) {
                if (this.player[i] != null) {

                }
            }
        }


        this.player = new Metro[playOption.length];

        for (int i = 0; i < this.player.length; ++i) {
            if (!playOption[i].equals("Human")) {
                this.player[i] = new Metro( );
            }
        }

    }

    int getscorefieldx(int i) {
        if (i < 25) {
            return i * this.scores + this.scores / 2;
        } else if (i < 50) {
            return 25 * this.scores + this.scores / 2;
        } else {
            return i < 75 ? (75 - i) * this.scores + this.scores / 2 : this.scores / 2;
        }
    }

    int getscorefieldy(int i) {
        if (i < 25) {
            return this.scores / 2;
        } else if (i < 50) {
            return (i - 25) * this.scores + this.scores/ 2;
        } else {
            return i < 75 ? 25 * this.scores + this.scores / 2 : (100 - i) * this.scores + this.scores / 2;
        }
    }
}