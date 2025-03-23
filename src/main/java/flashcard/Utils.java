package flashcard;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Utils {
    public static List<Card> loadCards(String fileName) {
        List<Card> cards = new ArrayList<>();
        
        URL resource = Utils.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(new File(resource.getFile())))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    cards.add(new Card(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        return cards;
    }
}
