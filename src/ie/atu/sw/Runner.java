package ie.atu.sw;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * This class is responsible for running the main application. It handles user
 * input and coordinates
 * the flow of the sentiment analysis process.
 */

public class Runner {

    private static LexiconLoader lexiconLoader = new LexiconLoader();
    private static String textData = "";
    private static String outputPath;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
            System.out.print("Select Option [1-7]>");

            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        specifyTextFile(scanner);
                        break;
                    case 2:
                        specifyURL(scanner);
                        break;
                    case 3:
                        specifyOutputFile(scanner);
                        break;
                    case 4:
                        configureLexicons(scanner);
                        break;
                    case 5:
                        if (!textData.isEmpty()) {
                            performSentimentAnalysis(textData);
                        } else {
                            System.out.println("No text data available for analysis. Please load text data first.");
                        }
                    case 6:
                        running = false;
                        System.out.println("Quitting the program.");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    /**
     * Prompts the user to specify the path to a text file and reads its contents
     * into textData.
     *
     * @param scanner A Scanner object for reading user input.
     */

    private static void specifyTextFile(Scanner scanner) {
        System.out.println("Enter the full path to the text file:");
        String filePath = scanner.next();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            textData = String.join("\n", lines);
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    /**
     * Prompts the user to specify a URL and fetches its content, storing it in
     * textData.
     *
     * @param scanner A Scanner object for reading user input.
     */

    private static void specifyURL(Scanner scanner) {
        System.out.println("Enter the URL:");
        String urlString = scanner.next();

        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } finally {
                connection.disconnect();
            }

            textData = content.toString(); // Store the fetched content in textData
        } catch (IOException e) {
            System.out.println("Error reading from URL: " + e.getMessage());
        }
    }

    /**
     * Starts the sentiment analysis process using the provided text data.
     *
     * @param textData The text data to be analysed for sentiment.
     */

    private static void performSentimentAnalysis(String textData) {
        if (outputPath == null || outputPath.isEmpty()) {
            System.out.println("Output path is not set. Please specify the output file first.");
            return;
        }

        SentimentAnalyzer analyzer = new SentimentAnalyzer(lexiconLoader);
        double sentimentScore = analyzer.analyzeText(textData);
        String report = generateReport(sentimentScore, textData);

        outputReport(report);
    }

    /**
     * Outputs the sentiment analysis report to either a specified file or the
     * console.
     *
     * @param report The sentiment analysis report to be outputted.
     */

    private static void outputReport(String report) {
        if (outputPath != null && !outputPath.isEmpty()) {
            try (PrintWriter out = new PrintWriter(outputPath)) {
                out.println(report);
                System.out.println("Report written to " + outputPath);
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("Report:\n" + report);
        }
    }

    /**
     * Generates a report from the sentiment analysis, including the analyzed text
     * and the sentiment score.
     *
     * @param sentimentScore The calculated sentiment score of the text.
     * @param textData       The text data that was analyzed.
     * @return A string containing the formatted report.
     */

    private static String generateReport(double sentimentScore, String textData) {
        StringBuilder sb = new StringBuilder();
        sb.append("Analyzed Text: ").append(textData).append("\n");
        sb.append("Average Sentiment Score: ").append(sentimentScore).append("\n");

        return sb.toString();
    }

    /**
     * Prompts the user to specify the path to a lexicon file and loads it using
     * LexiconLoader.
     *
     * @param scanner A Scanner object for reading user input.
     */

    private static void configureLexicons(Scanner scanner) {
        System.out.println("Enter the full path to the lexicon file:");
        String lexiconPath = scanner.next();

        try {
            lexiconLoader.loadLexicon(lexiconPath);
            System.out.println("Lexicon loaded successfully from " + lexiconPath);
        } catch (IOException e) {
            System.out.println("Error loading lexicon from " + lexiconPath + ": " + e.getMessage());
        }
    }

    /**
     * Prompts the user to specify the path for the output file where the report
     * will be saved.
     *
     * @param scanner A Scanner object for reading user input.
     */

    private static void specifyOutputFile(Scanner scanner) {
        System.out.println("Enter the full path for the output file:");
        outputPath = scanner.next();

        try {
            File file = new File(outputPath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    /**
     * Displays the main menu and handles user selections.
     */

    public static void displayMenu() {
        System.out.println(ConsoleColour.WHITE);
        System.out.println("************************************************************");
        System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
        System.out.println("*             Virtual Threaded Sentiment Analyser          *");
        System.out.println("************************************************************");
        System.out.println("(1) Specify a Text File");
        System.out.println("(2) Specify a URL");
        System.out.println("(3) Specify an Output File (default: ./out.txt)");
        System.out.println("(4) Configure Lexicons");
        System.out.println("(5) Execute, Analyse and Report");
        System.out.println("(6) Quit");
        System.out.println();
    }
}


//    public static void printProgress(int index, int total) {
//         if (index > total)
//             return; // Out of range
//         int size = 50;
//         char done = '█';
//         char todo = '░';

//         int complete = (100 * index) / total;
//         int completeLen = size * complete / 100;

//         StringBuilder sb = new StringBuilder();
//         sb.append("[");
//         for (int i = 0; i < size; i++) {
//             sb.append((i < completeLen) ? done : todo);
//         }

//         System.out.print("\r" + sb + "] " + complete + "%");

//         if (index == total)
//             System.out.println();
//     }
// }
