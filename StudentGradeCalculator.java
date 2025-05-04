import java.util.Scanner;
public class StudentGradeCalculator {
	public static void main(String[] args) {
        	Scanner scanner = new Scanner(System.in);
			//Ask for number of subjects
        	System.out.print("Enter number of subjects: ");
        	int numSubjects = scanner.nextInt();
			//Array to score marks for each subject
        	int[] marks = new int[numSubjects];
        	int totalMarks = 0;
        	// Get marks from user
        	for (int i = 0; i < numSubjects; i++) {
            		System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            		marks[i] = scanner.nextInt();
            		// Validate marks are within 0-100
           	 	while (marks[i] < 0 || marks[i] > 100) {
               			 System.out.print("Invalid marks! Enter again for subject " + (i + 1) + ": ");
                		  marks[i] = scanner.nextInt();
            		}
            		totalMarks += marks[i];
        	}
			//calculate average percentage
        	double average = (double) totalMarks / numSubjects;
			//determine grade based on percentage
        	String grade;
       		 if (average >= 90) {
            		grade = "A+";
        	} else if (average >= 80) {
            		grade = "A";
        	} else if (average >= 70) {
            		grade = "B";
        	} else if (average >= 60) {
            		grade = "C";
        	} else if (average >= 50) {
           		 grade = "D";
        	} else {
            		grade = "F";
        	}
        	// Display the result
        	System.out.println("\n--- Result ---");
        	System.out.println("Total Marks: " + totalMarks + " out of " + (numSubjects * 100));
        	System.out.println("Average Percentage: " + average + "%");
        	System.out.println("Grade: " + grade);
       		scanner.close();
    }
}
