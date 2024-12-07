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
src/ └── ie/atu/sw/ ├── ConsoleColour.java # Handles colored console output ├── LexiconLoader.java # Loads sentiment lexicon ├── Runner.java # Main application entry point ├── SentimentAnalyzer.java # Performs sentiment analysis


---

## Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher.
- A Java-compatible IDE such as IntelliJ IDEA, Eclipse, or VS Code with Java extensions.

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/CormacM22/SentimentAnalyzer
```

### 2. Open in Your IDE
- Import the project into your preferred IDE.
- Ensure the ```src/``` directory is marked as the source folder.

### 3. Build and Run
- Compile and run the ```Runner.java``` file, which acts as the main entry point

## Usage 

### 1. Run the Application:
- Execute the Runner.java file from your IDE or use the command line:
```bash
javac src/ie/atu/sw/Runner.java
java -cp src ie.atu.sw.Runne
```

### 2. Input Options
- Choose to input text manually, read from a file, or fetch from a URL.

### 3. View Results
- View the sentiment results in the console.

### 4. Save Results
- Specify an output file to save the sentiment analysis results.

## Classes and Responsibilities
1. ```ConsoleColour.java```:
   - Adds colored output for better readability.
2. ```LexiconLoader.java```:
   - Loads a lexicon file to map words to sentiment scores.
4. ```SentimentAnalyzer.java```:
   - Analyzes text sentiment using the lexicon and calculates scores.
5. ```Runner.java```:
   - Handles input selection and orchestrates the analysis process.

## Example Output
### Console Example
```bash
Text: "I love this product! It's amazing."
Sentiment Score: 4.5 (Positive)
```

### File Output Example
- Saved to ```output.txt```:
```bash
Text: "I love this product! It's amazing."
Sentiment Score: 4.5 (Positive)
```





