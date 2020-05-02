package comp1110.ass2;

public class VerifyPlacement {
    //This class checks whether the place of each pieces is overlap
    //this class can also check each tiles is in the board or not(the tiles cannot be out of the boundaries)

    public static boolean isPlacementNotOverlap(String placement) {
        int a=0;
        int b=0;
        for (int i=0;i<placement.length();i+=6){
            a = Integer.parseInt(placement.substring(i + 4, i + 6));
            for (int j=i+6;j<placement.length();j+=6){
                b = Integer.parseInt(placement.substring(j + 4, j + 6));
                if (b==a) return false;
            }
        }return true;
    }


    public static boolean isPlacementInTheBound(String placement) {
        for (int i=0;i<placement.length();i+=6){
            int a = Integer.parseInt(placement.substring(i + 4, i + 6));
            if (a<00||a>77||a==33||a==34||a==43||a==44)return false;
        }return true;
    }

}
