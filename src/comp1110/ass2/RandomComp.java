package comp1110.ass2;

import java.util.*;

public class RandomComp {

//    public static String totalHands="";
//    public static String humanMoved="";

    public static String generateHumanTotalHands(){
        // totalHands=""+ other totalHands already assigned
        String totalHands="";
        Map<String, Integer> total = getDeckMap();
        //i< (60 / number of player)
        for (int i=0;i<30;i++){
            for (int j = 0; j < totalHands.length(); j += 4) {
                String tile = totalHands.substring(j, j + 4);
                if (total.get(tile) > 0) {
                    total.put(tile, total.get(tile) - 1);
                }
            }
            List<String> aList = new ArrayList<>();
            for (String tile : total.keySet()) {
                if (total.get(tile) > 0) { aList.add(tile); }
            }
            totalHands= totalHands+aList.get(new Random().nextInt(aList.size()));
        }
        return totalHands;
    }

    public static Map<String, Integer> generateTotalHandsAfterMove(String originalTotalHand, String humanMoved){
        Map<String, Integer> total = getEmptyMap();
        for (int j = 0; j < originalTotalHand.length(); j += 4) {
            String tile = originalTotalHand.substring(j, j + 4);
            total.put(tile, total.get(tile) + 1);
        }
        for (int k = 0; k < humanMoved.length(); k += 6) {
            String tile = humanMoved.substring(k, k + 4);
            total.put(tile, total.get(tile) + 1);
        }
        return total;
    }

    public static String computerMove(String placementAlreadyHave, String originalTotalHand){
        // humanMoved for only one perosn vs computer, human first
        String humanMoved= "";
        for (int j = 0; j < placementAlreadyHave.length(); j += 12) {
            String tileplace = placementAlreadyHave.substring(j, j + 6);
            humanMoved= humanMoved+tileplace;
        }
        return Metro.generateMove(placementAlreadyHave,
                drawFromDeck(originalTotalHand,humanMoved), 2);
    }

    public static void main(String[] args) {
        System.out.println(generateHumanTotalHands());;
    }

    public static String drawFromDeck(String originalTotalHand, String humanMoved) {
        Map<String, Integer> draw = generateTotalHandsAfterMove(originalTotalHand,humanMoved);
        List<String> randomList = new ArrayList<>();
        for (String key : draw.keySet()) {
            if (draw.get(key) > 0) {
                randomList.add(key);
            }
        }
        return randomList.get(new Random().nextInt(randomList.size()));
    }

    private static Map<String, Integer> getEmptyMap() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("aacb", 0);
        counts.put("cbaa", 0);
        counts.put("acba", 0);
        counts.put("baac", 0);
        counts.put("aaaa", 0);
        counts.put("cbcb", 0);
        counts.put("bcbc", 0);
        counts.put("cccc", 0);
        counts.put("bbbb", 0);
        counts.put("dacc", 0);
        counts.put("cdac", 0);
        counts.put("ccda", 0);
        counts.put("accd", 0);
        counts.put("dbba", 0);
        counts.put("adbb", 0);
        counts.put("badb", 0);
        counts.put("bbad", 0);
        counts.put("ddbc", 0);
        counts.put("cddb", 0);
        counts.put("bcdd", 0);
        counts.put("dbcd", 0);
        counts.put("adad", 0);
        counts.put("dada", 0);
        counts.put("dddd", 0);
        return counts;
    }



    private static Map<String, Integer> getDeckMap() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("aacb", 4);
        counts.put("cbaa", 4);
        counts.put("acba", 4);
        counts.put("baac", 4);
        counts.put("aaaa", 4);

        counts.put("cbcb", 3);
        counts.put("bcbc", 3);

        counts.put("cccc", 2);
        counts.put("bbbb", 2);
        counts.put("dacc", 2);
        counts.put("cdac", 2);
        counts.put("ccda", 2);
        counts.put("accd", 2);
        counts.put("dbba", 2);
        counts.put("adbb", 2);
        counts.put("badb", 2);
        counts.put("bbad", 2);
        counts.put("ddbc", 2);
        counts.put("cddb", 2);
        counts.put("bcdd", 2);
        counts.put("dbcd", 2);
        counts.put("adad", 2);
        counts.put("dada", 2);
        counts.put("dddd", 2);
        return counts;
    }
}






