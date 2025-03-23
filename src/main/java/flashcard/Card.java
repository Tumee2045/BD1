package flashcard;

public class Card {
    private final String front; // Mongolian word
    private final String back;  // English translation
    private int wrongCount;
    private int correctCount;
    private long lastMistakeTime;

    public Card(String front, String back) {
        this.front = front;
        this.back = back;
        this.wrongCount = 0;
        this.correctCount = 0;
        this.lastMistakeTime = 0;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public long getLastMistakeTime() {
        return lastMistakeTime;
    }

    public void incrementWrongCount() {
        wrongCount++;
        lastMistakeTime = System.currentTimeMillis();
    }

    public void incrementCorrectCount() {
        correctCount++;
    }
}
