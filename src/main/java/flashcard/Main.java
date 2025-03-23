package flashcard;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "cards.txt";
        List<Card> cards = Utils.loadCards(filePath);
        Scanner scanner = new Scanner(System.in);
        Quiz quiz = new Quiz(cards, 1, false);
        
        quiz.start();  // Start game immediately
        
        while (true) {
            System.out.print("\nCommand (--startgame/--help/--order/--repetitions/--invertcards): ");
            String command = scanner.nextLine().trim();
            
            switch (command) {
                case "--startgame": quiz.start(); break;
                case "--help": printHelp(); break;
                case "--order": setOrder(quiz, scanner); break;
                case "--repetitions": setRepetitions(quiz, scanner); break;
                case "--invertcards": quiz.toggleInvert(); quiz.start(); break;
                default: System.out.println("Unknown command"); break;
            }
        }
    }
    
    private static void printHelp() {
        System.out.println("\nCommands:");
        System.out.println("--startgame: Start new game");
        System.out.println("--help: Show this help");
        System.out.println("--order: Set card order (1:Random, 2:Worst, 3:Recent)");
        System.out.println("--repetitions: Set required correct answers");
        System.out.println("--invertcards: Invert cards and start");
    }
    
    private static void setOrder(Quiz quiz, Scanner scanner) {
        System.out.print("Order (1:Random, 2:Worst, 3:Recent): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            quiz.setOrderMode(choice);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, using random order");
        }
    }
    
    private static void setRepetitions(Quiz quiz, Scanner scanner) {
        System.out.print("Repetitions: ");
        try {
            int reps = Integer.parseInt(scanner.nextLine().trim());
            quiz.setRepetitions(Math.max(1, reps));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, using 1 repetition");
        }
    }
}