package ie.atu.sw;

/**
 * The SentimentAnalyzer class is responsible for analyzing text for sentiment.
 * It utilizes a LexiconLoader to assess the sentiment value of individual words
 * and computes an overall sentiment score for the given text.
 */

public class SentimentAnalyzer {

    private LexiconLoader lexiconLoader;

    /**
     * Constructs a SentimentAnalyzer with the specified LexiconLoader.
     *
     * @param lexiconLoader The LexiconLoader to be used for fetching sentiment
     *                      values of words.
     */

    public SentimentAnalyzer(LexiconLoader lexiconLoader) {
        this.lexiconLoader = lexiconLoader;
    }

    /**
     * Analyzes the given text and calculates an average sentiment score based on
     * the words in the text.
     * The sentiment score for each word is fetched from the LexiconLoader, and an
     * average score is computed.
     *
     * @param text The text to be analyzed for sentiment.
     * @return The average sentiment score of the text. Returns 0 if no words in the
     *         text are found in the lexicon.
     */

    public double analyzeText(String text) {
        double totalScore = 0;
        int wordCount = 0;

        String[] words = text.split("\\s+");
        for (String word : words) {
            Double score = lexiconLoader.getSentiment(word.toLowerCase());
            if (score != null) {
                totalScore += score;
                wordCount++;
            }
        }

        return wordCount > 0 ? totalScore / wordCount : 0; // Average sentiment score
    }
}
