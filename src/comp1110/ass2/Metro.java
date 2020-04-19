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
     *
     * @param piecePlacement A String representing the piece to be placed
     * @return True if this string is well-formed
     *  @author Ruiqiao Jiang
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
     * <p>
     * For a placement sequence to be well-formed, it must satisfy the
     * following criteria:
     * <p>
     * - It must be composed of well-formed tile placements.
     * <p>
     * - For any piece x, there can exist no more instances of x on the board
     * than instances of x in the deck.
     *  @author Ruiqiao Jiang
     * @param placement A String representing the placement of all tiles on the
     * @return true if this placement sequence is well-formed
     */
    public static boolean isPlacementSequenceWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement sequence is well-formed
        Map<String, Integer> counts = getDeckMap();
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < placement.length(); i += 6) {
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
     * @author Ruiqiao Jiang
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
     * <p>
     * These rules are:
     * <p>
     * - No tile can overlap another tile, or any of the central stations.
     * <p>
     * - A tile cannot be placed next to one of the central squares unless it
     * continues or completes an existing track.
     * <p>
     * - If a tile is on an edge of the board, it cannot contain a track that
     * results in a station looping back to itself, UNLESS that tile could not
     * have been placed elsewhere.
     * <p>
     * - If a tile is on a corner of the board, it cannot contain a track that
     * links the two stations adjacent to that corner, UNLESS that tile could
     * not have been placed elsewhere.
     *@author Ruiqiao Jiang
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

                // judge if it is the centre station
                if (Arrays.asList(new String[]{"33", "34", "43", "44"}).contains(position)) {
                    return false;
                } else if (rowNumber == 0 || rowNumber == 7 || colNumber == 0 || colNumber == 7) {

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

    /**
     * Task 9
     * Given a placement sequence string, generate a valid next move.
     *
     * @param placementSequence a String representing the sequence of piece placements made so far in the game
     * @param piece             a four-character String representing the tile just drawn
     * @param hand              A tile in the player's hand, which they can choose to play instead of the drawn tile.
     *                          If the player does not currently hold a tile, this parameter will be null.
     * @param numberOfPlayers   The number of players in the game
     * @return A valid placement of other the drawn tile or the tile from the player's hand (if it is not empty).
     * @author:Ruiqiao Jiang
     */
    public static String generateMove(String placementSequence, String piece, String hand, int numberOfPlayers) {
        // FIXME Task 9: generate a valid move

        Map[][] desk = new HashMap[8][8];
        putData(placementSequence, desk);
        int[] twoPlayers1 = new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};
        int[] twoPlayers2 = new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31};
        int[] threePlayers1 = new int[]{0, 3, 5, 10, 14, 19, 22, 24, 27, 30};
        int[] threePlayers2 = new int[]{1, 6, 8, 11, 13, 18, 21, 26, 28, 31};
        int[] threePlayers3 = new int[]{2, 4, 7, 9, 12, 17, 20, 23, 25, 29};

        int[] fourPlayers1 = new int[]{3, 6, 10, 15, 19, 22, 26, 31};
        int[] fourPlayers2 = new int[]{2, 7, 11, 14, 18, 23, 27, 30};
        int[] fourPlayers3 = new int[]{0, 5, 9, 12, 17, 20, 24, 29};
        int[] fourPlayers4 = new int[]{1, 4, 8, 13, 16, 21, 25, 28};

        int[] fivelayers1 = new int[]{0, 4, 9, 13, 21, 27};
        int[] fivelayers2 = new int[]{5, 11, 17, 22, 26, 31};
        int[] fivelayers3 = new int[]{2, 6, 14, 18, 24, 28};
        int[] fivelayers4 = new int[]{1, 8, 12, 20, 25, 29};
        int[] fivelayers5 = new int[]{3, 7, 10, 19, 23, 30};

        int[] sixlayers1 = new int[]{0, 4, 9, 18, 26};
        int[] sixlayers2 = new int[]{1, 10, 17, 24, 28};
        int[] sixlayers3 = new int[]{3, 7, 13, 20, 25};
        int[] sixlayers4 = new int[]{5, 14, 19, 23, 30};
        int[] sixlayers5 = new int[]{2, 8, 12, 22, 29};
        int[] sixlayers6 = new int[]{6, 11, 21, 27, 31};

        String rtnStr = "";

        Map map = getMapByDirection(piece);

        List<String> put = new ArrayList<>();
        int count = 0;
        for (int j = 0; j < placementSequence.length(); j += 6) {
            put.add(placementSequence.substring(j + 4, j + 6));
            count++;
        }

        int nowPlayer = count % numberOfPlayers;
        int[] stations = null;
        switch (numberOfPlayers) {
            case 2:
                if (nowPlayer == 0) {
                    stations = twoPlayers1;
                } else {
                    stations = twoPlayers2;
                }
                break;
            case 3:
                if (nowPlayer == 0) {
                    stations = threePlayers1;
                } else if (nowPlayer == 1) {
                    stations = threePlayers2;
                } else if (nowPlayer == 2) {
                    stations = threePlayers3;
                }
                break;
            case 4:
                if (nowPlayer == 0) {
                    stations = fourPlayers1;
                } else if (nowPlayer == 1) {
                    stations = fourPlayers2;
                } else if (nowPlayer == 2) {
                    stations = fourPlayers3;
                } else if (nowPlayer == 3) {
                    stations = fourPlayers4;
                }
                break;
            case 5:
                if (nowPlayer == 0) {
                    stations = fivelayers1;
                } else if (nowPlayer == 1) {
                    stations = fivelayers2;
                } else if (nowPlayer == 2) {
                    stations = fivelayers3;
                } else if (nowPlayer == 3) {
                    stations = fivelayers4;
                } else if (nowPlayer == 4) {
                    stations = fivelayers5;
                }
                break;
            case 6:
                if (nowPlayer == 0) {
                    stations = sixlayers1;
                } else if (nowPlayer == 1) {
                    stations = sixlayers2;
                } else if (nowPlayer == 2) {
                    stations = sixlayers3;
                } else if (nowPlayer == 3) {
                    stations = sixlayers4;
                } else if (nowPlayer == 4) {
                    stations = sixlayers5;
                } else if (nowPlayer == 5) {
                    stations = sixlayers6;
                }
                break;
        }


        for (int station : stations) {
            int start = station + 1;
            String number = null;
            if (start >= 1 && start <= 8) {
                number = "0" + (8 - start);
            } else if (start >= 9 && start <= 16) {
                number = (start - 9) + "0";
            } else if (start >= 17 && start <= 24) {
                number = "7" + (start - 17);
            } else if (start >= 25 && start <= 32) {
                number = (32 - start) + "7";
            }
            if (put.contains(number)) {
                continue;
            }
            if (start == 1 || start == 32) {
                if (!Arrays.asList(new String[]{"2", "3", "4"}).contains(map.get("1")) &&
                        !Arrays.asList(new String[]{"1", "3", "4"}).contains(map.get("2"))) {
                    rtnStr = piece + "07";
                }
            } else if (start == 8 || start == 9) {
                if (!Arrays.asList(new String[]{"7", "8", "2"}).contains(map.get("1")) &&
                        !Arrays.asList(new String[]{"1", "7", "8"}).contains(map.get("2"))) {
                    rtnStr = piece + "00";
                }
            } else if (start == 16 || start == 17) {
                if (!Arrays.asList(new String[]{"7", "6", "5"}).contains(map.get("8")) &&
                        !Arrays.asList(new String[]{"8", "6", "5"}).contains(map.get("7"))) {
                    rtnStr = piece + "70";
                }
            } else if (start == 24 || start == 25) {
                if (!Arrays.asList(new String[]{"4", "5", "6"}).contains(map.get("3")) &&
                        !Arrays.asList(new String[]{"3", "5", "6"}).contains(map.get("4"))) {
                    rtnStr = piece + "77";
                }
            } else if (start >= 2 && start <= 7) {
                if (!map.get("1").equals("2")) {
                    rtnStr = piece + number;
                }
            } else if (start >= 10 && start <= 15) {
                if (!map.get("7").equals("8")) {
                    rtnStr = piece + number;
                }
            } else if (start >= 18 && start <= 23) {
                if (!map.get("5").equals("6")) {
                    rtnStr = piece + number;
                }
            } else if (start >= 26 && start <= 31) {
                if (!map.get("3").equals("4")) {
                    rtnStr = piece + number;
                }
            }


        }
        if (rtnStr.length() == 0) {
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 6; j++) {
                    if (Arrays.asList(new String[]{"33", "34", "43", "44"}).contains(i + "" + j)) {
                        continue;
                    }
                    if (!put.contains(i + "" + j)) {
                        rtnStr = piece + i + "" + j;
                    }
                }
            }
        }
        if (rtnStr.length() == 0) {
            for (int station : stations) {
                int start = station + 1;
                String number = null;
                if (start >= 1 && start <= 8) {
                    number = "0" + (8 - start);
                } else if (start >= 9 && start <= 16) {
                    number = (start - 9) + "0";
                } else if (start >= 17 && start <= 24) {
                    number = "7" + (start - 17);
                } else if (start >= 25 && start <= 32) {
                    number = (32 - start) + "7";
                }
                if (!put.contains(number)) {
                    rtnStr = piece + number;
                }
            }
        }
        if (rtnStr.length() == 0) {
            for (int station = 0; station < 32; station++) {
                int start = station + 1;
                String number = null;
                if (start >= 1 && start <= 8) {
                    number = "0" + (8 - start);
                } else if (start >= 9 && start <= 16) {
                    number = (start - 9) + "0";
                } else if (start >= 17 && start <= 24) {
                    number = "7" + (start - 17);
                } else if (start >= 25 && start <= 32) {
                    number = (32 - start) + "7";
                }
                if (!put.contains(number)) {
                    rtnStr = piece + number;
                }
            }
        }
        return rtnStr;
    }
}




