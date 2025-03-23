package flashcard;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Achievements {
    private final Set<String> achieved;

    public Achievements() {
        this.achieved = new HashSet<>();
    }

    public void checkAchievements(List<Card> cards, long roundTimeMillis) {
        achieved.clear();
        
        if (!cards.isEmpty() && roundTimeMillis / cards.size() < 5000) {
            achieved.add("FAST RESPONSE");
        }
        if (allCorrectInLastRound(cards)) {
            achieved.add("CORRECT");
        }
        if (anyCardRepeated(cards)) {
            achieved.add("REPEAT");
        }
        if (anyCardConfident(cards)) {
            achieved.add("CONFIDENT");
        }
    }

    private boolean allCorrectInLastRound(List<Card> cards) {
        return !cards.isEmpty() && cards.stream().allMatch(card -> card.getWrongCount() == 0);
    }

    private boolean anyCardRepeated(List<Card> cards) {
        return cards.stream().anyMatch(card -> card.getWrongCount() > 5);
    }

    private boolean anyCardConfident(List<Card> cards) {
        return cards.stream().anyMatch(card -> card.getCorrectCount() >= 3);
    }

    public void displayAchievements() {
        if (achieved.isEmpty()) {
            System.out.println("No achievements unlocked.");
        } else {
            System.out.println("Achievements:");
            achieved.forEach(achievement -> System.out.println("- " + achievement));
        }
    }
}