// ProcessGrades.java - Solution for grades assignment
// Author: Chris Wilcox
// Date: 1/25/2017
// Class: CS165
// Email: wilcox@cs.colostate.edu

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessGrades implements Interface {

    // Method to read roster file
    // Post-condition: ArrayList contains all data from roster file 
    public ArrayList<Entry> readRoster(String filename) {
        
        ArrayList<Entry> entries = new ArrayList<>();
        
        // YOUR CODE HERE
        File f = new File(filename);
        
        try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String[] field = line.split(",");
				String eid = field[2], first = field[0], last = field[1];
				Entry e = new Entry(eid, first, last);
				entries.add(e);
			}
			s.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return entries;
    }

    // Method to read grades file, returns number of students matched
    // Post-condition: All students in roster are matched with grades
    public int readGrades(String filename, ArrayList<Entry> entries) {
        
        int numberMatches = 0;

        // YOUR CODE HERE
        File f = new File(filename);
        
        try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String[] field = line.split(",");
				int Geid = Integer.parseInt(field[0]);
				for (Entry e : entries) 
					if (Geid == Integer.parseInt(e.eid)) {
						int examAverage = Integer.parseInt(field[1]);
						int assignmentAverage = Integer.parseInt(field[2]);
						int quizAverage = Integer.parseInt(field[3]);
						int labAverage = Integer.parseInt(field[4]);
						int peerAverage = Integer.parseInt(field[5]);
						e.examAverage = examAverage;
						e.assignmentAverage = assignmentAverage;
						e.quizAverage = quizAverage;
						e.labAverage = labAverage;
						e.peerAverage = peerAverage;
						numberMatches++;
					}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return numberMatches;
    }

    // Method to compute grades
    // Post-condition: All grades are in the range zero to 100
    public void computeGrades(ArrayList<Entry> entries) {

        for (Entry entry : entries) {
            
            // YOUR CODE HERE
        	entry.overallAverage = (entry.examAverage * 0.50) +
                    (entry.assignmentAverage * 0.20) +
                    (entry.quizAverage * 0.10) +
                    (entry.labAverage * 0.10) +
                    (entry.peerAverage * 0.10);
        	if (entry.overallAverage >= 90.0) {
        		entry.letterGrade = 'A';
        	} else if (entry.overallAverage >= 80.0) {
        		entry.letterGrade = 'B';
        	} else if (entry.overallAverage >= 70.0) {
        		entry.letterGrade = 'C';
        	} else if (entry.overallAverage >= 60.0) {
        		entry.letterGrade = 'D';
        	} else {
        		entry.letterGrade = 'F';
        	}
        }
    }
    
    // Method to write upload file
    // Post-condition: Upload file format and size are correct 
    public void writeUpload(String filename, ArrayList<Entry> entries) {
        
        // YOUR CODE HERE
    	File f = new File(filename);
    	
    	try {
			PrintWriter writer = new PrintWriter(f);
			writer.println("EID,First Name,Last Name,Exams,Assignments,Quizzes,Labs,Peer,Overall,Grade");
			for (Entry e : entries) 
				writer.println(e.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}

