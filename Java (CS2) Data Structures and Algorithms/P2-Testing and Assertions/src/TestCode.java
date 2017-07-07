// TestCode.java - Test code for grades assignment
// Author: Chris Wilcox
// Date: 1/25/2017
// Class: CS165
// Email: wilcox@cs.colostate.edu

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestCode {

    public static void main(String[] args) {

        // Instantiate object
        ProcessGrades process = new ProcessGrades();

        // Check usage
        if (args.length != 2) {
            System.out.println("usages: java ProcessGrades <roster file> <grades file>");
            System.exit(-1);
        }

        // Process arguments
        String rosterName = args[0];
        String gradesName = args[1];

        // Read roster file
        ArrayList<Interface.Entry> entries = process.readRoster(rosterName);

        // Assertion for post-condition for readRoster
        // Post-condition: ArrayList contains all data from roster file
        // YOUR CODE HERE
        File f = new File(rosterName);
        int fileLength = (int) f.length();
        int dataLength = 0;
        for (Interface.Entry e : entries)
        	dataLength += e.eid.length() + e.first.length() + e.last.length() + 3; 
        assert fileLength == dataLength : "File has " + fileLength + " bytes, arraylist has " + dataLength + "!"; 
        
        // Read grades file
        int numberMatched = process.readGrades(gradesName, entries);

        // Assertion for post-condition for readGrades
        // Post-condition: All students in roster are matched with grades
        // YOUR CODE HERE
        int numberStudents = entries.size();
        assert numberStudents == numberMatched : "Students in roster: " + numberStudents + ", grades matched: " + numberMatched;
        
        // Compute grades
        process.computeGrades(entries);

        // Assertion for post-condition for computeGrades
        // Post-condition: All grades are in the range zero to 100
        // YOUR CODE HERE
        for (Interface.Entry e : entries) {
        	assert e.examAverage >= 0 && e.examAverage <= 100 : "One or more grades are out of range!";
        	assert e.assignmentAverage >= 0 && e.assignmentAverage <= 100 : "One or more grades are out of range!";
        	assert e.quizAverage >= 0 && e.quizAverage <= 100 : "One or more grades are out of range!";
        	assert e.labAverage >= 0 && e.labAverage <= 100 : "One or more grades are out of range!";
        	assert e.peerAverage >= 0 && e.peerAverage <= 100 : "One or more grades are out of range!";
        	assert e.overallAverage >= 0 && e.overallAverage <= 100 : "One or more grades are out of range!";
        }
        
        // Write upload file
        process.writeUpload("upload.csv", entries);

        // Assertion for post-condition for writeUpload
        // Post-condition: Upload file format is correct
        ArrayList<String> contents = readFile("upload.csv");
        // YOUR CODE HERE
        for (String s : contents) {
        	String[] field = s.split(",");
        	assert field.length == 10 : "Upload file has incorrect format!";
        }
    }

    // Test code to read file
    private static ArrayList<String> readFile(String filename) {

        ArrayList<String> contents = new ArrayList<>();
        try {

            Scanner s = new Scanner(new File(filename));
            while (s.hasNextLine())
                contents.add(s.nextLine());
            s.close();

        } catch (IOException e) {
            System.out.println("readGrades cannot read: " + filename);
        }
        return contents;
    }
}


