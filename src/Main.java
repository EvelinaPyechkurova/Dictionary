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
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<File> fileList = new ArrayList<>();
        fileList.add(new File("data/Harry Potter and the Philosophers Stone.txt"));
        fileList.add(new File("data/Harry Potter and the Chamber of Secrets.txt"));
        fileList.add(new File("data/Harry Potter And The Prisoner Of Azkaban.txt"));
        fileList.add(new File("data/Harry Potter and the goblet of fire.txt"));
        fileList.add(new File("data/Harry Potter and the Order of the Phoenix.txt"));
        fileList.add(new File("data/Harry Potter and the Half Blood Prince.txt"));
        fileList.add(new File("data/Harry Potter And The Deathly Hallows.txt"));
        fileList.add(new File("data/Tale of Two Cities.txt"));
        fileList.add(new File("data/Hobbit.txt"));
        fileList.add(new File("data/The fellowship of the ring.txt"));
        Dictionary dictionary = new Dictionary(fileList);
        dictionary.analyzeCollection();
        System.out.println(dictionary);

        System.out.println("Total size of collection is " + dictionary.getCollectionSize() + " KB");
        System.out.println("Number of words in collection: " + dictionary.getWordCount());
        System.out.println("Number of unique words in collection: " + dictionary.getUniqueWordsCount());

        dictionary.saveResultsToFile("Dictionary results");
    }
}