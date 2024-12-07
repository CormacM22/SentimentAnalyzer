# Sentiment Analyzer

The **Sentiment Analyzer** is a Java application designed to analyze text sentiment using a lexicon-based approach. It processes input text, calculates sentiment scores, and provides a user-friendly console output with optional colored displays.

---

## Key Features

- **Text Data Input**:
  - **File Input**: Analyze text data from a specified file.
  - **URL Input**: Extract and analyze text from web-based content.
  - **Manual Input**: Enter text directly through the console.

- **Lexicon Configuration**:
  - Load sentiment data using `LexiconLoader.java` to map words to sentiment values.

- **Sentiment Analysis**:
  - Calculate sentiment scores for words and provide an overall sentiment score.
  - Support for analyzing multiple text inputs simultaneously.

- **Output and Reporting**:
  - Display sentiment scores directly in the console.
  - Option to save the results to a specified output file.

- **Console Colors**:
  - Uses `ConsoleColour.java` to enhance output with color-coded text.

---

## Project Structure

