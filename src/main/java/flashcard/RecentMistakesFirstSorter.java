package flashcard;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecentMistakesFirstSorter implements CardOrganizer {
    @Override
    public List<Card> organize(List<Card> cards) {
        Collections.sort(cards, Comparator.comparingLong(Card::getLastMistakeTime).reversed());
        return cards;
    }
}
