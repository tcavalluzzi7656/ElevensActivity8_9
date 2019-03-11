import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 9;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
            {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
            {"spades", "hearts", "diamonds", "clubs"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = false;


    /**
     * Creates a new <code>ElevensBoard</code> instance.
     */
    public ElevensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Elevens, the legal groups are (1) a pair of non-face cards
     * whose values add to 11, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     *         false otherwise.
     */
    @Override
    public boolean isLegal(List<Integer> selectedCards) {
        if(selectedCards.size()==2){
        return containsPairSum11(selectedCards);
        }
        else if(selectedCards.size()==3){
            return containsJQK(selectedCards);
        }
        else{return false;}
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Elevens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 11, or (2) a group
     * of three cards consisting of a jack, a queen, and a king in some order.
     * @return true if there is a legal play left on the board;
     *         false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible() {
		List<Integer> indexes = cardIndexes();
        int two=0;
        int three=0;
        int four=0;
        int five=0;
        int six=0;
        int seven=0;
        int eight=0;
        int nine=0;
        int ten=0;
        int jack=0;
        int queen=0;
        int king=0;
        int ace=0;
		for(int i=0;i<indexes.size();i++)
        {
            if(cardAt(indexes.get(i)).rank().equals("ace"))
            {
                ace++;
            }
            if(cardAt(indexes.get(i)).rank().equals("2"))
            {
                two++;
            }
            if(cardAt(indexes.get(i)).rank().equals("3"))
            {
                three++;
            }
            if(cardAt(indexes.get(i)).rank().equals("4"))
            {
                four++;
            }
            if(cardAt(indexes.get(i)).rank().equals("5"))
            {
                five++;
            }
            if(cardAt(indexes.get(i)).rank().equals("6"))
            {
                six++;
            }
            if(cardAt(indexes.get(i)).rank().equals("7"))
            {
                seven++;
            }
            if(cardAt(indexes.get(i)).rank().equals("8"))
            {
                eight++;
            }
            if(cardAt(indexes.get(i)).rank().equals("9"))
            {
                nine++;
            }
            if(cardAt(indexes.get(i)).rank().equals("10"))
            {
                ten++;
            }
            if(cardAt(indexes.get(i)).rank().equals("jack"))
            {
                jack++;
            }
            if(cardAt(indexes.get(i)).rank().equals("queen"))
            {
                queen++;
            }
            if(cardAt(indexes.get(i)).rank().equals("king"))
            {
                king++;
            }

        }


        if(two>0&&nine>0)
        {
            return true;
        }
        if(three>0&&eight>0)
        {
            return true;
        }
        if(four>0&&seven>0)
        {
            return true;
        }
        if(five>0&&six>0)
        {
            return true;
        }
        if(jack>0&&queen>0&&king>0)
        {
            return true;
        }
        if(ten>0&&ace>0)
        {
            return true;
        }
        else{return false;}
    }

    /**
     * Check for an 11-pair in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 11-pair.
     * @return true if the board entries in selectedCards
     *              contain an 11-pair; false otherwise.
     */
    private boolean containsPairSum11(List<Integer> selectedCards) {
        if(selectedCards.size()==2){
            if((cardAt(selectedCards.get(0)).pointValue()+cardAt(selectedCards.get(1)).pointValue())==11)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check for a JQK in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a JQK group.
     * @return true if the board entries in selectedCards
     *              include a jack, a queen, and a king; false otherwise.
     */
    private boolean containsJQK(List<Integer> selectedCards) {
        int numJack = 0;
        int numQueen = 0;
        int numKing = 0;
		if(selectedCards.size()==3)
        {
            for(int k = 0; k < 3; k++){
                if(cardAt(selectedCards.get(k)).rank().equals("king"))
                {
                    numKing++;
                }
                if(cardAt(selectedCards.get(k)).rank().equals("queen"))
                {
                    numQueen++;
                }
                if(cardAt(selectedCards.get(k)).rank().equals("jack"))
                {
                    numJack++;
                }
            }
        }
        if(numJack==1){
            if(numKing==1){
                if(numQueen==1){
                    return true;
                }
            }
        }
    return false;
    }



}
