package flashcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private final List<Card> cards;
    private final Scanner scanner;
    private final Achievements achievements;
    private int repetitions;
    private boolean invertCards;
    private CardOrganizer organizer;

    public Quiz(List<Card> cards, int repetitions, boolean invertCards) {
        this.cards = new ArrayList<>(cards);
        this.scanner = new Scanner(System.in);
        this.achievements = new Achievements();
        this.repetitions = Math.max(1, repetitions);
        this.invertCards = invertCards;
        this.organizer = new RandomSorter();
    }

    public void start() {
        if (cards.isEmpty()) {
            System.out.println("No cards available!");
            return;
        }
        
        List<Card> activeCards = new ArrayList<>(cards);
        long startTime = System.currentTimeMillis();
        
        while (!activeCards.isEmpty()) {
            organizer.organize(activeCards);
            Card card = activeCards.get(0);
            String question = invertCards ? card.getBack() : card.getFront();
            String answer = invertCards ? card.getFront() : card.getBack();
            
            System.out.print("\n" + question + " -> ");
            String userAnswer = scanner.nextLine().trim();
            
            if (userAnswer.equalsIgnoreCase(answer)) {
                card.incrementCorrectCount();
                if (card.getCorrectCount() >= repetitions) {
                    activeCards.remove(card);
                    System.out.println("Correct! " + activeCards.size() + " cards left");
                } else {
                    System.out.println("Correct! " + (repetitions - card.getCorrectCount()) + " more needed");
                }
            } else {
                card.incrementWrongCount();
                System.out.println("Wrong! Correct: " + answer);
            }
        }
        
        long roundTime = System.currentTimeMillis() - startTime;
        achievements.checkAchievements(cards, roundTime);
        achievements.displayAchievements();
    }
    
    public void setOrderMode(int mode) {
        switch (mode) {
            case 2: organizer = new WorstFirstSorter(); break;
            case 3: organizer = new RecentMistakesFirstSorter(); break;
            default: organizer = new RandomSorter(); break;
        }
    }
    
    public void setRepetitions(int reps) {
        this.repetitions = Math.max(1, reps);
    }
    
    public void toggleInvert() {
        this.invertCards = !invertCards;
    }
}