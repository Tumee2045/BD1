package flashcard;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WorstFirstSorter implements CardOrganizer {
    @Override
    public List<Card> organize(List<Card> cards) {
        Collections.sort(cards, Comparator.comparingInt(Card::getWrongCount).reversed());
        return cards;
    }
}
