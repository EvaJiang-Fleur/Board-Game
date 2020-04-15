package comp1110.ass2;

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
     *task2
     * * 判断一个图块的放置是不是well-formed。一个图块的放置是well-formed，他必须：
     * * 包括六个character
     * * 首字母、第二字母、第三字母和第四字母在“a”和“d”之间（包括track）；
     * * 第五和第六个字符的数字介于0和7之间（包括*）（分别为列和行）。
     * @param piecePlacement A String representing the piece to be placed表示要放置的字符串
     * @return True if this string is well-formed如果这个string是well-formed
     */
    public static boolean isPiecePlacementWellFormed(String piecePlacement) {
        if (piecePlacement == null || piecePlacement.length() != 6) {
            return false;
        } else if (piecePlacement.charAt(0) < 'a' || piecePlacement.charAt(0) > 'd'
                ||piecePlacement.charAt(1) < 'a' || piecePlacement.charAt(1) > 'd'
                ||piecePlacement.charAt(2) < 'a' || piecePlacement.charAt(2) > 'd'
                ||piecePlacement.charAt(3) < 'a' || piecePlacement.charAt(3) > 'd'
                ||piecePlacement.charAt(4) < '0' || piecePlacement.charAt(4) > '7'
                ||piecePlacement.charAt(5) < '0' || piecePlacement.charAt(5) > '7'
                ||((piecePlacement.charAt(4) == '3' ||piecePlacement.charAt(4) == '4')
                &&(piecePlacement.charAt(5) == '3' ||piecePlacement.charAt(5) == '4'))
        ) {return false;}
        else  { return true;}

        // FIXME Task 2: determine whether a piece placement is well-formed判断一个图块的放置是不是well-formed

    }

    /**
     * Task 3
     * Determine whether a placement sequence string is well-formed.
     * 判断一个placement sequence string是否是well formed
     * For a placement sequence to be well-formed, it must satisfy the
     * following criteria:
     * 对于一个well formed的placement sequence，它必须满足以下条件：
     * - It must be composed of well-formed tile placements.
     * 它必须由well-formed的图块放置组成。
     * - For any piece x, there can exist no more instances of x on the board
     * than instances of x in the deck.
     * 对于任何图块x，板上的x实例不能多于deck上的x实例。???
     *
     * @param placement A String representing the placement of all tiles on the
     *                  board
     * @参数 placement 一个string代表表示板上所有图块位置的字符串
     * @return true if this placement sequence is well-formed
     * @return true如果这个placement sequence 是well-formed。
     */
    public static boolean isPlacementSequenceWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement sequence is well-formed
        return false;
    }

    /**
     * Task 5
     * Draw a random tile from the deck.
     * 从deck上随机抽取一个图块
     *
     * @param placementSequence a String representing the sequence of tiles
     *                          that have already been played
     * @参数 placementSequence一个string 代表着 已经玩过的图块序列
     * @param totalHands        a String representing all tiles (if any) in
     *                          all players' hands
     * @参数 totalHands    一个string代表在所有玩家手中的所有图块
     * @return a random tile from the deck
     * @return deck上的一个图块
     */
    public static String drawFromDeck(String placementSequence, String totalHands) {
        // FIXME Task 5: draw a random tile from the deck
        return "";
    }

    /**
     * Task 6
     * Determine if a given placement sequence follows the rules of the game.
     * 给定的放置顺序是否遵循游戏规则。
     * These rules are:
     * 规则是
     * - No tile can overlap another tile, or any of the central stations.
     * 任何一块都不能与另一块或任何中心工作站重叠
     * - A tile cannot be placed next to one of the central squares unless it
     * continues or completes an existing track.
     * 一个图块不能在一个中心广场旁边被放置 除非它要继续或者完成一个已经存在的track
     * - If a tile is on an edge of the board, it cannot contain a track that
     * results in a station looping back to itself, UNLESS that tile could not
     * have been placed elsewhere.
     * 如果一个图块在棋盘板子的边缘，除非它没有其他地方放置，否则它不能包含导致站循环回到自身的轨道。
     * - If a tile is on a corner of the board, it cannot contain a track that
     * links the two stations adjacent to that corner, UNLESS that tile could
     * not have been placed elsewhere.
     * 如果图块在棋盘板子的一个角上，除非它没有其他地方放置，否则它不能包含连接该角附近两个站的轨道。
     *
     * @param placementSequence A sequence of placements on the board.
     * @参数 placementSequence 在棋盘上的放置序列
     * @return Whether this placement string is valid.
     * @return 这个放置string是否是有效的

     */
    public static boolean isPlacementSequenceValid(String placementSequence) {
        // FIXME Task 6: determine whether a placement sequence is valid
        return false;
    }

    /**
     * Task 7
     * Determine the current score for the game.
     *
     * @param placementSequence a String representing the sequence of piece placements made so far in the game
     * @param numberOfPlayers   The number of players in the game
     * @return an array containing the scores for all players
     * Task 7
     *判断游戏现在的分数
     *@参数 placementSequence 一个字符串，表示到目前为止在游戏中放置的图块的顺序
     *@参数 numberOfPlayers 游戏中的players数量
     *@return 一个数组包括所有players的分数
     */
    public static int[] getScore(String placementSequence, int numberOfPlayers) {
        // FIXME Task 7: determine the current score for the game
        return new int[0];
    }

    /**
     * Task 9
     * Given a placement sequence string, generate a valid next move.
     *给定放置序列字符串，生成有效的下一步移动。
     * @param placementSequence a String representing the sequence of piece placements made so far in the game
     * @param piece             a four-character String representing the tile just drawn
     * @param hand              A tile in the player's hand, which they can choose to play instead of the drawn tile.
     *                          If the player does not currently hold a tile, this parameter will be null.
     * @param numberOfPlayers   The number of players in the game
     * @return A valid placement of other the drawn tile or the tile from the player's hand (if it is not empty).
     *
     * @参数  placementSequence 一个字符串，表示到目前为止在游戏中放置的片段的顺序
     * @参数  piece             一个四个字符的字符串，表示刚刚抽取的图块
     * @参数  hand              玩家手中的一个图块，玩家可以选择用它来代替抽取的图块。
     * @参数  numberOfPlayers   游戏中的player的数量
     * @return 抽取的图块或者玩家手里的土块的有效的放置（如果不是空的话）。
     */
    public static String generateMove(String placementSequence, String piece, String hand, int numberOfPlayers) {
        // FIXME Task 9: generate a valid move
        return "";
    }
}
