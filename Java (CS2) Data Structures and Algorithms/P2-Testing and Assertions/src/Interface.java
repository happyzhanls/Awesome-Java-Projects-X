// Interface.java - Interface for grades assignment
// Author: Chris Wilcox
// Date: 1/25/2017
// Class: CS165
// Email: wilcox@cs.colostate.edu

import java.util.ArrayList;

public interface Interface {
    
    // Student entry
    public class Entry {
        public String eid;
        public String first;
        public String last;
        public int examAverage;
        public int assignmentAverage;
        public int quizAverage;
        public int labAverage;
        public int peerAverage;
        public double overallAverage;
        public char letterGrade;
        
        public Entry(String eid, String first, String last) {
            this.eid = eid;
            this.first = first;
            this.last = last;
        }

        public String toString() {
            String s = "";
            s += this.eid + ",";
            s += this.first + ",";
            s += this.last + ",";
            s += this.examAverage + ",";
            s += this.assignmentAverage + ",";
            s += this.quizAverage + ",";
            s += this.labAverage + ",";
            s += this.peerAverage + ",";
            s += String.format("%.2f", this.overallAverage) + ",";
            s += this.letterGrade;
            return s;
        }
    };
    
    // Method to read roster file, returns ArrayList of entries
    // Post-condition: ArrayList contains all data from roster file
    public abstract ArrayList<Entry> readRoster(String filename); 

    // Method to read grades file, returns number of students matched
    // Post-condition: All students in roster are matched with grades
    public abstract int readGrades(String filename, ArrayList<Entry> entries); 
    
    // Method to compute grades, no return value
    // Post-condition: All grades are in the range zero to 100
    public abstract void computeGrades(ArrayList<Entry> entries);
    
    // Method to write upload file, no return value
    // Post-condition: Upload file format and size are correct 
    public abstract void writeUpload(String filename, ArrayList<Entry> entries);
    
}
