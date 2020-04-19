package comp1110.ass2;

import java.util.*;
import java.util.regex.Pattern;


public class Metro {
    /**
     * Task 2
     * Determine whether a piece placement is well-formed. For a piece
     * placement to be well-formed, it must:
     * - contain exactly six characters;
     * - have as its first, second, third and fourth characters letters between
     * 'a' and 'd' inclusive (tracks); and
     * - have as its fifth and sixth characters digits between 0 and 7 inclusive
     * (column and row respectively).


     * @param piecePlacement A String representing the piece to be placed
     * @return True if this string is well-formed
     */
    public static boolean isPiecePlacementWellFormed(String piecePlacement) {


        // FIXME Task 2: determine whether a piece placement is well-formed
        if (!Pattern.matches("[a-d]{4}[0-7]{2}", piecePlacement))
            return false;
        return true;
    }

    /**
     * Task 3
     * Determine whether a placement sequence string is well-formed.

     * For a placement sequence to be well-formed, it must satisfy the
     * following criteria:

     * - It must be composed of well-formed tile placements.

     * - For any piece x, there can exist no more instances of x on the board
     * than instances of x in the deck.

     *
     * @param placement A String representing the placement of all tiles on the

     * @return true if this placement sequence is well-formed

     */
    public static boolean isPlacementSequenceWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement sequence is well-formed
        Map<String, Integer> counts = getDeckMap();
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < placement.length(); i+=6) {
            if (placement.substring(i).length() < 6) {
                return false;
            }
            if (!isPiecePlacementWellFormed(placement.substring(i, i + 6))) {
                return false;
            }
            int posistion = Integer.parseInt(placement.substring(i + 4, i + 6));
            if (!positions.contains(posistion)) {
                positions.add(posistion);
            } else {
                return false;
            }
            String key = placement.substring(i, i + 4);
            if ((counts.put(key, counts.get(key) - 1)) == -1) {
                return false;
            }
        }
        return true;
    }
    /**
     * Task 5
     * Draw a random tile from the deck.

     *
     * @param placementSequence a String representing the sequence of tiles
     *                          that have already been played

     * @param totalHands        a String representing all tiles (if any) in
     *                          all players' hands

     * @return a random tile from the deck

     */
    public static String drawFromDeck(String placementSequence, String totalHands) {
        // FIXME Task 5: draw a random tile from the deck

    Map<String, Integer> counts = getDeckMap();
     for (int i = 0; i < placementSequence.length(); i += 6) {
     String key = placementSequence.substring(i, i + 4);
     counts.put(key, counts.get(key) - 1);
     }
     for (int i = 0; i < totalHands.length(); i += 4) {
     String key = totalHands.substring(i, i + 4);
     counts.put(key, counts.get(key) - 1);
     }
     List<String> randomList = new ArrayList<>();
     for (String key : counts.keySet()) {
     if (counts.get(key) > 0) {
     randomList.add(key);
     }
     }
     return randomList.get(new Random().nextInt(randomList.size()));
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


    /**
     * Task 6
     * Determine if a given placement sequence follows the rules of the game.

     * These rules are:

     * - No tile can overlap another tile, or any of the central stations.

     * - A tile cannot be placed next to one of the central squares unless it
     * continues or completes an existing track.

     * - If a tile is on an edge of the board, it cannot contain a track that
     * results in a station looping back to itself, UNLESS that tile could not
     * have been placed elsewhere.

     * - If a tile is on a corner of the board, it cannot contain a track that
     * links the two stations adjacent to that corner, UNLESS that tile could
     * not have been placed elsewhere.

     *
     * @param placementSequence A sequence of placements on the board.

     * @return Whether this placement string is valid.


     */
    public static boolean isPlacementSequenceValid(String placementSequence) {
        // FIXME Task 6: determine whether a placement sequence is valid
        if (!isPlacementSequenceWellFormed(placementSequence)) {
            return false;
        }

        Set alreadyPositions = new HashSet();
        for (int i = 0; i < placementSequence.length(); i += 6) {
            String position = placementSequence.substring(i + 4, i + 6);
            String direction = placementSequence.substring(i, i + 4);
            // Condition Duplication
            if (alreadyPositions.contains(position)) {
                return false;
            } else {
                int rowNumber = Integer.parseInt(placementSequence.substring(i + 4, i + 5));
                int colNumber = Integer.parseInt(placementSequence.substring(i + 5, i + 6));
                String oneDirection = placementSequence.substring(i, i + 1);
                String twoDirection = placementSequence.substring(i + 1, i + 2);
                String threeDirection = placementSequence.substring(i + 2, i + 3);
                String fourDirection = placementSequence.substring(i + 3, i + 4);

                // 中央车站判断
                if (Arrays.asList(new String[]{"33", "34", "43", "44"}).contains(position)) {
                    return false;
                } else if (rowNumber == 0|| rowNumber == 7 || colNumber == 0 || colNumber == 7) {

                    if (direction.equals("dddd") && alreadyPositions.size() == 0) {
                        alreadyPositions.add(position);
                        continue;
                    }
                    if (rowNumber == 0 && colNumber != 0 && colNumber != 7) {
                        if (oneDirection.equals("d")) {
                            return false;
                        }
                    } else if (rowNumber == 7 && colNumber != 0 && colNumber != 7) {
                        if (threeDirection.equals("d")) {
                            return false;
                        }
                    } else if (colNumber == 0 && rowNumber != 0 && rowNumber != 7) {
                        if (fourDirection.equals("d")) {
                            return false;
                        }
                    } else if (colNumber == 7 && rowNumber != 0 && rowNumber != 7) {
                        if (twoDirection.equals("d")) {
                            return false;
                        }
                    }
                    if (rowNumber == 7 && colNumber == 7) {
                        if (twoDirection.equals("d") && threeDirection.equals("d")) {
                            return false;
                        }
                    }
                    if (rowNumber == 0 && colNumber == 0) {
                        if (oneDirection.equals("d") && fourDirection.equals("d")) {
                            return false;
                        }
                    }
                    if (rowNumber == 7 && colNumber == 0) {
                        if (threeDirection.equals("d") && fourDirection.equals("d")) {
                            return false;
                        }
                    }
                    if (rowNumber == 0 && colNumber == 7) {
                        if (oneDirection.equals("d") && twoDirection.equals("d")) {
                            return false;
                        }
                    }

                    alreadyPositions.add(position);
                } else {
                    boolean flag = false;
                    if ((rowNumber - 1) != -1 && alreadyPositions.contains((rowNumber - 1) + "" + colNumber)) {
                        flag = true;
                    } else if ((rowNumber + 1) != 8 && alreadyPositions.contains((rowNumber + 1) + "" + colNumber)) {
                        flag = true;
                    } else if ((colNumber - 1) != -1 && alreadyPositions.contains(rowNumber + "" + (colNumber - 1))) {
                        flag = true;
                    } else if ((colNumber + 1) != 8 && alreadyPositions.contains(rowNumber + "" + (colNumber + 1))) {
                        flag = true;
                    }
                    if (!flag) {
                        return false;
                    }
                    alreadyPositions.add(position);
                }
            }
        }
        return true;
    }

    /**
     * Task 7
     * Determine the current score for the game.
     *
     * @param placementSequence a String representing the sequence of piece placements made so far in the game
     * @param numberOfPlayers   The number of players in the game
     * @return an array containing the scores for all players

     */
    public static int[] getScore(String placementSequence, int numberOfPlayers) {
        // FIXME Task 7: determine the current score for the game
        return new int[0];
    }

    /**
     * Task 9
     * Given a placement sequence string, generate a valid next move.

     * @param placementSequence a String representing the sequence of piece placements made so far in the game
     * @param piece             a four-character String representing the tile just drawn
     * @param hand              A tile in the player's hand, which they can choose to play instead of the drawn tile.
     *                          If the player does not currently hold a tile, this parameter will be null.
     * @param numberOfPlayers   The number of players in the game
     * @return A valid placement of other the drawn tile or the tile from the player's hand (if it is not empty).

     */
    public static String generateMove(String placementSequence, String piece, String hand, int numberOfPlayers) {
        // FIXME Task 9: generate a valid move
        return "";
    }
}
