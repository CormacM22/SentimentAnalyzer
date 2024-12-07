package ie.atu.sw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * The LexiconLoader class is responsible for loading a sentiment lexicon from a
 * file.
 * The lexicon is a mapping between words and their associated sentiment scores.
 * This class
 * provides functionality to load this data and retrieve sentiment scores for
 * specific words.
 * 
 * @author Cormac Mangan
 * @version Visual Studio 1.85.1
 * @since 1.8
 */

public class LexiconLoader {

    /**
     * Constructs a new LexiconLoader with an empty lexicon.
     */

    private ConcurrentSkipListMap<String, Double> lexicon;

    public LexiconLoader() {
        lexicon = new ConcurrentSkipListMap<>();
    }

    /**
     * Loads sentiment data from a specified file into the lexicon.
     *
     * @param filePath The path to the file containing sentiment data.
     * @throws IOException If an I/O error occurs during reading from the file.
     */

     public void loadLexicon(String filePath) throws IOException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        executor.submit(() -> {
            try {
                List<String> lines = Files.readAllLines(Path.of(filePath));
                for (String line : lines) {
                    String[] parts = line.split(","); // Assuming the file is comma-separated
                    if (parts.length >= 2) {
                        String word = parts[0];
                        double sentimentValue = Double.parseDouble(parts[1]);
                        lexicon.put(word, sentimentValue);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
    }

    /**
     * Retrieves the sentiment score for a given word. If the word is not present in
     * the lexicon,
     * a default score of 0.0 is returned.
     *
     * @param word The word for which the sentiment score is to be retrieved.
     * @return The sentiment score of the word, or 0.0 if the word is not in the
     *         lexicon.
     */

    public Double getSentiment(String word) {
        return lexicon.getOrDefault(word, 0.0);
    }

}
