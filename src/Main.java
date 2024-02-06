/*
Write a program that builds a dictionary of terms based on a given collection of text files
(a collection of all unique words found in the texts).
1. Text files can be submitted in any format.
2. The size of text files is at least 150 K.
3. The number of text files is at least 10.
4. Save the glossary to disk (check that it can be read from disk).
5. Estimate the size of the collection, the total number of words in the collection
   and the size of the dictionary.
6. Try several formats of saving the dictionary
   (dictionary serialization, saving to a text file, etc.) and compare the results.
*/

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File data = new File("data");
        List<File> fileList = new ArrayList<>();

        if(data.exists() && data.isDirectory()) {
            File[] dataFiles = data.listFiles();
            if(dataFiles != null)
                fileList.addAll(Arrays.asList(dataFiles));

            Dictionary dictionary = new Dictionary(fileList);
            dictionary.analyzeCollection();
            System.out.println(dictionary);

            System.out.println("Total size of collection is " + dictionary.getCollectionSize() + " KB");
            System.out.println("Number of words in collection: " + dictionary.getWordCount());
            System.out.println("Number of unique words in collection: " + dictionary.getUniqueWordsCount());

            dictionary.saveResultsToFile("Dictionary results");
        }
    }
}