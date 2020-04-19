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

        int[] scores = new int[32];

        Map[][] desk = new HashMap[8][8];
        putData(placementSequence, desk);
        for (int i = 1; i <= scores.length; i++) {
            Map map;
            int nowStart = 0;
            int nowRow = 0;
            int nowCol = 0;
            int count = 1;
            boolean isDouble = false;
            if (i >= 1 && i <= 8) {
                map = desk[0][8 - i];
                if (map == null) {
                    continue;
                }
                nowRow = 0;
                nowCol = 8 - i;
                nowStart = Integer.parseInt(map.get("1").toString());
            } else if (i >= 9 && i <= 16) {
                map = desk[i - 9][0];
                if (map == null) {
                    continue;
                }
                nowRow = i - 9;
                nowCol = 0;
                nowStart = Integer.parseInt(map.get("7").toString());
            } else if (i >= 17 && i <= 24) {
                map = desk[7][i - 17];
                if (map == null) {
                    continue;
                }
                nowRow = 7;
                nowCol = i - 17;
                nowStart = Integer.parseInt(map.get("5").toString());
            } else if (i >= 25 && i <= 32) {
                map = desk[32 - i][7];
                if (map == null) {
                    continue;
                }
                nowRow = 32 - i;
                nowCol = 7;
                nowStart = Integer.parseInt(map.get("3").toString());
            }


            // 1 -- > 6
            // 2 -- > 5

            // 3 -- > 8
            // 4 -- > 7

            // 5 -- > 2
            // 6 -- > 1

            // 7 -- > 4
            // 8 -- > 3

            while (true) {
                if (nowStart == 1 || nowStart == 2) {
                    nowRow--;

                    if (nowRow == -1) {
                        break;
                    } else {
                        Map nowMap = desk[nowRow][nowCol];
                        if (nowMap == null) {
                            count = 1;
                            break;
                        } else if (nowRow == 5 && (nowCol == 3 || nowCol == 4)) {
                            if (nowStart == 1) {
                                if (nowMap.get("6").equals("1") || nowMap.get("6").equals("2")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            } else {
                                if (nowMap.get("5").equals("1") || nowMap.get("5").equals("2")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            }
                        } else if ((nowRow == 3 || nowRow == 4) && nowCol == 2) {
                            if (nowStart == 1) {
                                if (nowMap.get("6").equals("3") || nowMap.get("6").equals("4")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            } else {
                                if (nowMap.get("5").equals("3") || nowMap.get("5").equals("4")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            }
                        } else if ((nowRow == 3 || nowRow == 4) && nowCol == 5) {
                            if (nowStart == 1) {
                                if (nowMap.get("6").equals("7") || nowMap.get("6").equals("8")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            } else {
                                if (nowMap.get("5").equals("7") || nowMap.get("5").equals("8")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (nowStart == 1) {
                            nowStart = Integer.parseInt(nowMap.get("6").toString());
                        } else {
                            nowStart = Integer.parseInt(nowMap.get("5").toString());
                        }

                    }
                    count++;
                } else if (nowStart == 3 || nowStart == 4) {
                    nowCol++;

                    if (nowCol == 8) {

                        break;
                    } else {
                        Map nowMap = desk[nowRow][nowCol];
                        if (nowMap == null) {
                            count = 1;
                            break;
                        } else if ((nowRow == 3 || nowRow == 4) && nowCol == 2) {
                            if (nowStart == 3) {
                                if (nowMap.get("8").equals("3") || nowMap.get("8").equals("4")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            } else {
                                if (nowMap.get("7").equals("3") || nowMap.get("7").equals("4")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            }
                        } else if (nowRow == 2 && (nowCol == 3 || nowCol == 4)) {
                            if (nowStart == 3) {
                                if (nowMap.get("8").equals("5") || nowMap.get("8").equals("6")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            } else {
                                if (nowMap.get("7").equals("5") || nowMap.get("7").equals("6")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            }
                        } else if (nowRow == 5 && (nowCol == 3 || nowCol == 4)) {
                            if (nowStart == 3) {
                                if (nowMap.get("8").equals("1") || nowMap.get("8").equals("2")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            } else {
                                if (nowMap.get("7").equals("1") || nowMap.get("7").equals("2")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (nowStart == 3) {
                            nowStart = Integer.parseInt(nowMap.get("8").toString());
                        } else {
                            nowStart = Integer.parseInt(nowMap.get("7").toString());
                        }


                    }
                    count++;
                } else if (nowStart == 5 || nowStart == 6) {
                    nowRow++;

                    if (nowRow == 8) {
                        break;
                    } else {
                        Map nowMap = desk[nowRow][nowCol];
                        if (nowMap == null) {
                            count = 1;
                            break;
                        } else if ((nowRow == 3 || nowRow == 4) && nowCol == 2) {
                            if (nowStart == 5) {
                                if (nowMap.get("2").equals("3") || nowMap.get("2").equals("4")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            } else {
                                if (nowMap.get("1").equals("3") || nowMap.get("1").equals("4")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            }
                        } else if (nowRow == 2 && (nowCol == 3 || nowCol == 4)) {
                            if (nowStart == 5) {
                                if (nowMap.get("2").equals("5") || nowMap.get("2").equals("6")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            } else {
                                if (nowMap.get("1").equals("5") || nowMap.get("1").equals("6")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            }
                        } else if ((nowRow == 3 || nowRow == 4) && nowCol == 5) {
                            if (nowStart == 5) {
                                if (nowMap.get("2").equals("7") || nowMap.get("2").equals("8")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            } else {
                                if (nowMap.get("1").equals("7") || nowMap.get("1").equals("8")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (nowStart == 5) {
                            nowStart = Integer.parseInt(nowMap.get("2").toString());
                        } else {
                            nowStart = Integer.parseInt(nowMap.get("1").toString());
                        }

                    }
                    count++;
                } else if (nowStart == 7 || nowStart == 8) {
                    nowCol--;

                    if (nowCol == -1) {

                        break;
                    } else {
                        Map nowMap = desk[nowRow][nowCol];
                        if (nowMap == null) {
                            count = 1;
                            break;
                        } else if ((nowRow == 3 || nowRow == 4) && nowCol == 5) {
                            if (nowStart == 7) {
                                if (nowMap.get("4").equals("7") || nowMap.get("4").equals("8")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            } else {
                                if (nowMap.get("3").equals("7") || nowMap.get("3").equals("8")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            }
                        } else if (nowRow == 2 && (nowCol == 3 || nowCol == 4)) {
                            if (nowStart == 7) {
                                if (nowMap.get("4").equals("5") || nowMap.get("4").equals("6")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            } else {
                                if (nowMap.get("3").equals("5") || nowMap.get("3").equals("6")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            }
                        } else if (nowRow == 5 && (nowCol == 3 || nowCol == 4)) {
                            if (nowStart == 7) {
                                if (nowMap.get("4").equals("1") || nowMap.get("4").equals("2")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            } else {
                                if (nowMap.get("3").equals("1") || nowMap.get("3").equals("2")) {
                                    isDouble = true;
                                    count++;
                                    break;
                                }
                            }
                        }

                        if (nowStart == 7) {
                            nowStart = Integer.parseInt(nowMap.get("4").toString());
                        } else {
                            nowStart = Integer.parseInt(nowMap.get("3").toString());
                        }


                    }
                    count++;
                }
            }
            if (count >= 1) {

            }
            if (count != 0 && count != 1) {
                if (isDouble) {
                    scores[i - 1] = count * 2;
                } else {
                    scores[i - 1] = count;
                }
            }
        }

        int row = Integer.parseInt(placementSequence.substring(placementSequence.length() - 2, placementSequence.length() - 1));
        int col = Integer.parseInt(placementSequence.substring(placementSequence.length() - 1));

        List numbers = new ArrayList();
        numbers.add("07");
        numbers.add("06");
        numbers.add("05");
        numbers.add("04");
        numbers.add("03");
        numbers.add("02");
        numbers.add("01");
        numbers.add("00");
        numbers.add("00");
        numbers.add("10");
        numbers.add("20");
        numbers.add("30");
        numbers.add("40");
        numbers.add("50");
        numbers.add("60");
        numbers.add("70");
        numbers.add("70");
        numbers.add("71");
        numbers.add("72");
        numbers.add("73");
        numbers.add("74");
        numbers.add("75");
        numbers.add("76");
        numbers.add("77");
        numbers.add("77");
        numbers.add("67");
        numbers.add("57");
        numbers.add("47");
        numbers.add("37");
        numbers.add("27");
        numbers.add("17");
        numbers.add("07");

        for (int i = 1; i <= scores.length; i++) {
            if (scores[i - 1] == 0) {
                int index = placementSequence.indexOf(numbers.get(i - 1).toString());
                if (index == -1) {
                    continue;
                }
                List<String> put = new ArrayList<>();
                for (int j = 0; j < index; j += 6) {
                    put.add(placementSequence.substring(j + 4, j + 6));
                }
                boolean flag = true;
                for (String str : Arrays.asList(new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "10", "20", "30", "40", "50", "60", "70", "71", "72", "73", "74",
                        "75", "76", "77", "67", "57", "47", "37", "27", "17"})) {
                    if (put.contains(str)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    scores[i - 1] = 1;
                }
                flag = true;
                for (String str : Arrays.asList(new String[]{"11", "12", "13", "14", "15", "16", "21", "22", "23", "24", "25", "26", "31", "32", "35", "36", "41", "42", "45",
                        "46", "51", "52", "53", "54", "55", "56", "61", "62", "63", "64", "65", "66"})) {
                    if (!put.contains(str)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    scores[i - 1] = 1;
                }

            }
        }

        if (row == 0) {
            String position = (String) desk[row][col].get("1");
            if (position.equals("2") || (col == 0 && (position.equals("7") || position.equals("8"))) ||
                    (col == 7 && (position.equals("3") || position.equals("4")))) {
                if (scores[7 - col] == 0) {
                    scores[7 - col] = 1;
                }
            }
        } else if (row == 7) {
            String position = (String) desk[row][col].get("5");
            if (position.equals("6") || (col == 0 && (position.equals("7") || position.equals("8"))) ||
                    (col == 7 && (position.equals("3") || position.equals("4")))) {
                if (scores[16 + col] == 0) {
                    scores[16 + col] = 1;
                }
            }

        }
        if (col == 0) {
            String position = (String) desk[row][col].get("7");
            if (position.equals("8") || (row == 0 && (position.equals("1") || position.equals("2"))) ||
                    (row == 7 && (position.equals("5") || position.equals("6")))) {
                if (scores[8 + row] == 0) {
                    scores[8 + row] = 1;
                }
            }

        } else if (col == 7) {
            String position = (String) desk[row][col].get("3");
            if (position.equals("4") || (row == 0 && (position.equals("1") || position.equals("2"))) ||
                    (row == 7 && (position.equals("5") || position.equals("6")))) {
                if (scores[31 - row] == 0) {
                    scores[31 - row] = 1;
                }
            }
        }

        int[] rtnScores = new int[numberOfPlayers];

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

        if (numberOfPlayers == 2) {
            for (int i = 0; i < twoPlayers1.length; i++) {
                rtnScores[0] += scores[twoPlayers1[i]];
                rtnScores[1] += scores[twoPlayers2[i]];
            }
        }
        if (numberOfPlayers == 3) {
            for (int i = 0; i < threePlayers1.length; i++) {
                rtnScores[0] += scores[threePlayers1[i]];
                rtnScores[1] += scores[threePlayers2[i]];
                rtnScores[2] += scores[threePlayers3[i]];
            }
        }
        if (numberOfPlayers == 4) {
            for (int i = 0; i < fourPlayers1.length; i++) {
                rtnScores[0] += scores[fourPlayers1[i]];
                rtnScores[1] += scores[fourPlayers2[i]];
                rtnScores[2] += scores[fourPlayers3[i]];
                rtnScores[3] += scores[fourPlayers4[i]];
            }
        }
        if (numberOfPlayers == 5) {
            for (int i = 0; i < fivelayers1.length; i++) {
                rtnScores[0] += scores[fivelayers1[i]];
                rtnScores[1] += scores[fivelayers2[i]];
                rtnScores[2] += scores[fivelayers3[i]];
                rtnScores[3] += scores[fivelayers4[i]];
                rtnScores[4] += scores[fivelayers5[i]];
            }
        }
        if (numberOfPlayers == 6) {
            for (int i = 0; i < sixlayers1.length; i++) {
                rtnScores[0] += scores[sixlayers1[i]];
                rtnScores[1] += scores[sixlayers2[i]];
                rtnScores[2] += scores[sixlayers3[i]];
                rtnScores[3] += scores[sixlayers4[i]];
                rtnScores[4] += scores[sixlayers5[i]];
                rtnScores[5] += scores[sixlayers6[i]];
            }
        }
        return rtnScores;
    }

    private static Map getMapByDirection(String direction) {
        Map map = new HashMap();
        switch (direction) {
            case "aaaa":
                // 12345678
                map.put("1", "6");
                map.put("2", "5");
                map.put("3", "8");
                map.put("4", "7");
                map.put("5", "2");
                map.put("6", "1");
                map.put("7", "4");
                map.put("8", "3");
                break;
            case "aacb":
                map.put("1", "6");
                map.put("2", "7");
                map.put("3", "8");
                map.put("4", "5");
                map.put("5", "4");
                map.put("6", "1");
                map.put("7", "2");
                map.put("8", "3");
                break;
            case "acba":
                map.put("1", "6");
                map.put("2", "3");
                map.put("3", "2");
                map.put("4", "7");
                map.put("5", "8");
                map.put("6", "1");
                map.put("7", "4");
                map.put("8", "5");
                break;
            case "accd":
                map.put("1", "6");
                map.put("2", "3");
                map.put("3", "2");
                map.put("4", "5");
                map.put("5", "4");
                map.put("6", "1");
                map.put("7", "8");
                map.put("8", "7");
                break;
            case "adad":
                map.put("1", "6");
                map.put("2", "5");
                map.put("3", "4");
                map.put("4", "3");
                map.put("5", "2");
                map.put("6", "1");
                map.put("7", "8");
                map.put("8", "7");
                break;
            case "adbb":
                map.put("1", "6");
                map.put("2", "7");
                map.put("3", "4");
                map.put("4", "3");
                map.put("5", "8");
                map.put("6", "1");
                map.put("7", "2");
                map.put("8", "5");
                break;
            case "baac":
                map.put("1", "4");
                map.put("2", "5");
                map.put("3", "8");
                map.put("4", "1");
                map.put("5", "2");
                map.put("6", "7");
                map.put("7", "6");
                map.put("8", "3");
                break;
            case "badb":
                map.put("1", "4");
                map.put("2", "7");
                map.put("3", "8");
                map.put("4", "1");
                map.put("5", "6");
                map.put("6", "5");
                map.put("7", "2");
                map.put("8", "3");
                break;
            case "bbad":
                map.put("1", "4");
                map.put("2", "5");
                map.put("3", "6");
                map.put("4", "1");
                map.put("5", "2");
                map.put("6", "3");
                map.put("7", "8");
                map.put("8", "7");
                break;
            case "bbbb":
                map.put("1", "4");
                map.put("2", "7");
                map.put("3", "6");
                map.put("4", "1");
                map.put("5", "8");
                map.put("6", "3");
                map.put("7", "2");
                map.put("8", "5");
                break;
            case "bcbc":
                map.put("1", "4");
                map.put("2", "3");
                map.put("3", "2");
                map.put("4", "1");
                map.put("5", "8");
                map.put("6", "7");
                map.put("7", "6");
                map.put("8", "5");
                break;
            case "bcdd":
                map.put("1", "4");
                map.put("2", "3");
                map.put("3", "2");
                map.put("4", "1");
                map.put("5", "6");
                map.put("6", "5");
                map.put("7", "8");
                map.put("8", "7");
                break;
            case "cbaa":
                map.put("1", "8");
                map.put("2", "5");
                map.put("3", "6");
                map.put("4", "7");
                map.put("5", "2");
                map.put("6", "3");
                map.put("7", "4");
                map.put("8", "1");
                break;
            case "cbcb":
                map.put("1", "8");
                map.put("2", "7");
                map.put("3", "6");
                map.put("4", "5");
                map.put("5", "4");
                map.put("6", "3");
                map.put("7", "2");
                map.put("8", "1");
                break;
            case "cccc":
                map.put("1", "8");
                map.put("2", "3");
                map.put("3", "2");
                map.put("4", "5");
                map.put("5", "4");
                map.put("6", "7");
                map.put("7", "6");
                map.put("8", "1");
                break;
            case "ccda":
                map.put("1", "8");
                map.put("2", "3");
                map.put("3", "2");
                map.put("4", "7");
                map.put("5", "6");
                map.put("6", "5");
                map.put("7", "4");
                map.put("8", "1");
                break;
            case "cdac":
                map.put("1", "8");
                map.put("2", "5");
                map.put("3", "4");
                map.put("4", "3");
                map.put("5", "2");
                map.put("6", "7");
                map.put("7", "6");
                map.put("8", "1");
                break;
            case "cddb":
                map.put("1", "8");
                map.put("2", "7");
                map.put("3", "4");
                map.put("4", "3");
                map.put("5", "6");
                map.put("6", "5");
                map.put("7", "2");
                map.put("8", "1");
                break;
            case "dacc":
                map.put("1", "2");
                map.put("2", "1");
                map.put("3", "8");
                map.put("4", "5");
                map.put("5", "4");
                map.put("6", "7");
                map.put("7", "6");
                map.put("8", "3");
                break;
            case "dada":
                map.put("1", "2");
                map.put("2", "1");
                map.put("3", "8");
                map.put("4", "7");
                map.put("5", "6");
                map.put("6", "5");
                map.put("7", "4");
                map.put("8", "3");
                break;
            case "dbba":
                map.put("1", "2");
                map.put("2", "1");
                map.put("3", "6");
                map.put("4", "7");
                map.put("5", "8");
                map.put("6", "3");
                map.put("7", "4");
                map.put("8", "5");
                break;
            case "dbcd":
                map.put("1", "2");
                map.put("2", "1");
                map.put("3", "6");
                map.put("4", "5");
                map.put("5", "4");
                map.put("6", "3");
                map.put("7", "8");
                map.put("8", "7");
                break;
            case "ddbc":
                map.put("1", "2");
                map.put("2", "1");
                map.put("3", "4");
                map.put("4", "3");
                map.put("5", "8");
                map.put("6", "7");
                map.put("7", "6");
                map.put("8", "5");
                break;
            case "dddd":
                map.put("1", "2");
                map.put("2", "1");
                map.put("3", "4");
                map.put("4", "3");
                map.put("5", "6");
                map.put("6", "5");
                map.put("7", "8");
                map.put("8", "7");
                break;
        }
        return map;
    }

    private static void putData(String placementSequence, Map[][] desk) {
//        给定方向代表
//          1 2
//         8   3
//         7   4
//          6 5
        desk[3][3] = new HashMap();
        desk[3][3].put("direction", "1278");
        desk[3][4] = new HashMap();
        desk[3][4].put("direction", "1234");
        desk[4][3] = new HashMap();
        desk[4][3].put("direction", "5678");
        desk[4][4] = new HashMap();
        desk[4][4].put("direction", "3456");

        for (int i = 0; i < placementSequence.length(); i += 6) {
            // 获取行和列
            int row = Integer.parseInt(placementSequence.substring(i + 4, i + 5));
            int col = Integer.parseInt(placementSequence.substring(i + 5, i + 6));
            String direction = placementSequence.substring(i, i + 4);
            desk[row][col] = getMapByDirection(direction);

        }
    }

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




