import java.io.*;
import java.util.*;

public class Dictionary {
    private final List<File> fileCollection;
    private List<String> wordsList;
    private Set<String> uniqueWordsSet;
    private double collectionSize;
    private int wordCount;
    private int uniqueWordsCount;


    public Dictionary(List<File> fileCollection){
        this.fileCollection = fileCollection;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getUniqueWordsCount(){
        return uniqueWordsCount;
    }

    public double getCollectionSize() {
        return collectionSize;
    }

    /** Analyzes given collection:
     * Creates list of all words from all documents in the collection,
     * finds out the amount of all words in the collection,
     * creates set of unique words in the collection, finds out the amount of unique words
     */
    public Set<String> analyzeCollection() {
        wordsList = new ArrayList<>();
        for (File file : fileCollection) {
            readWords(file);
        }
        wordCount = wordsList.size();
        uniqueWordsSet = new TreeSet<>(wordsList);
        uniqueWordsCount = uniqueWordsSet.size();
        return uniqueWordsSet;
    }

    /** @return List of all words read from the input file */
    private void readWords(File file){
        StringTokenizer tokenizer;

        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            String delimiters = ",;:()|.!?\"”“‘‘—’'\f1234567890/&*- ";
            while((line = reader.readLine()) != null){
                tokenizer = new StringTokenizer(line, delimiters);
                while (tokenizer.hasMoreTokens())
                    wordsList.add(tokenizer.nextToken());
            }
            calculateFileSizeInKB();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private void calculateFileSizeInKB() {
        long totalSizeInBytes = 0;

        for (File file : fileCollection) {
            if (file.exists() && file.isFile())
                totalSizeInBytes += file.length();
        }

        double totalSizeInKB = (double) totalSizeInBytes / 1024; // 1 KB = 1024 bytes
        collectionSize = totalSizeInKB;
    }

    public void saveResultsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("filename"))) {
            writer.write("Unique Words:\n");
            for (String word : uniqueWordsSet) {
                writer.write(word + "\n");
            }
            writer.write("\nCollection Size (KB): " + collectionSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return uniqueWordsSet.toString();
    }
}
