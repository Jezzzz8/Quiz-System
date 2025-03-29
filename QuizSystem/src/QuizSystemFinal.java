import java.util.Scanner;

public class QuizSystemFinal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int quizCount = 0;
        String[][] quizQuestions = new String[100][];
        String[][] quizAnswers = new String[100][];
        String[][][] quizChoices = new String[100][][];
        String[] quizTypes = new String[100];
        String[] quizNames = new String[100];
        String[] teacherNames = new String[100];
        
        String[][] studentNames = new String[100][10];
        int[][] studentScores = new int[100][10];
        int[] studentCount = new int[100];
        
        while (true) {
            System.out.println("\n=== QUIZ SYSTEM ===");
            System.out.println("1. Teacher Options");
            System.out.println("2. Student Options");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("\n=== TEACHER MENU ===");
                        System.out.println("1. Create Quiz");
                        System.out.println("2. Delete Quiz");
                        System.out.println("3. View Student Scores");
                        System.out.println("4. Delete Student Record");
                        System.out.println("5. Back to Main Menu");
                        System.out.print("Enter choice: ");
                        int teacherChoice = sc.nextInt();
                        sc.nextLine();
                        
                        if (teacherChoice == 5) break;
                        
                        switch (teacherChoice) {
                            case 1:
                                System.out.print("\nEnter your name (Teacher): ");
                                teacherNames[quizCount] = sc.nextLine();
                                
                                System.out.print("Enter quiz name: ");
                                quizNames[quizCount] = sc.nextLine();
                                
                                System.out.println("Select quiz type:");
                                System.out.println("1. Multiple Choice");
                                System.out.println("2. Direct Answer");
                                System.out.print("Enter choice: ");
                                int quizTypeChoice = sc.nextInt();
                                sc.nextLine();
                                
                                if (quizTypeChoice == 1) {
                                    quizTypes[quizCount] = "Multiple Choice";
                                } else if (quizTypeChoice == 2) {
                                    quizTypes[quizCount] = "Direct Answer";
                                } else {
                                    System.out.println("Invalid selection. Defaulting to Direct Answer.");
                                    quizTypes[quizCount] = "Direct Answer";
                                }
                                
                                System.out.print("How many questions? ");
                                int numQuestions = sc.nextInt();
                                sc.nextLine();
                                
                                quizQuestions[quizCount] = new String[numQuestions];
                                quizAnswers[quizCount] = new String[numQuestions];
                                quizChoices[quizCount] = (quizTypeChoice == 1) ? new String[numQuestions][4] : null;
                                
                                for (int i = 0; i < numQuestions; i++) {
                                    System.out.print("\nEnter question " + (i + 1) + ": ");
                                    quizQuestions[quizCount][i] = sc.nextLine();
                                    
                                    if (quizTypeChoice == 1) {
                                        System.out.println("Enter 4 choices:");
                                        for (int j = 0; j < 4; j++) {
                                            System.out.print("Choice " + (char) ('A' + j) + ": ");
                                            quizChoices[quizCount][i][j] = sc.nextLine();
                                        }
                                        System.out.print("Enter correct answer (A, B, C, or D): ");
                                    } else {
                                        System.out.print("Enter correct answer: ");
                                    }
                                    quizAnswers[quizCount][i] = sc.nextLine().trim().toUpperCase();
                                }
                                System.out.println("Quiz created successfully!");
                                quizCount++;
                                break;
                            
                            case 2:
                                if (quizCount == 0) {
                                    System.out.println("No quizzes available.");
                                    break;
                                }
                                
                                System.out.println("\nAvailable Quizzes:");
                                for (int i = 0; i < quizCount; i++) {
                                    System.out.println((i + 1) + ". " + quizNames[i] + " by " + teacherNames[i]);
                                }
                                
                                System.out.print("Enter quiz number to delete: ");
                                int delIndex = sc.nextInt() - 1;
                                
                                if (delIndex >= 0 && delIndex < quizCount) {
                                    System.out.println("Deleting Quiz: " + quizNames[delIndex]);
                                    for (int i = delIndex; i < quizCount - 1; i++) {
                                        quizNames[i] = quizNames[i + 1];
                                    }
                                    quizCount--;
                                    System.out.println("Quiz deleted.");
                                    System.out.println("Remaining Quizzes:");
                                    for (int i = 0; i < quizCount; i++) {
                                        System.out.println((i + 1) + ". " + quizNames[i]);
                                    }
                                }
                                break;
                            
                            case 3:
                                for (int i = 0; i < quizCount; i++) {
                                    System.out.println("Quiz: " + quizNames[i] + " (Total Items: " + quizQuestions[i].length + ")");
                                    for (int j = 0; j < studentCount[i]; j++) {
                                        System.out.println(studentNames[i][j] + " - " + studentScores[i][j] + " / " + quizQuestions[i].length);
                                    }
                                }
                                break;
                            
                            case 4:
                                if (quizCount == 0) {
                                    System.out.println("No quizzes available.");
                                    break;
                                }
                                System.out.println("\nAvailable Student Record:");
                                for (int i = 0; i < quizCount; i++) {
                                    System.out.println((i + 1) + ". " + quizNames[i] + " by " + teacherNames[i]);
                                    for (int j = 0; j < studentCount[i]; j++) {
                                        System.out.println(studentNames[i][j]);
                                    }
                                    System.out.println();
                                }
                                System.out.print("Enter quiz number to delete student record: ");
                                int qIndex = sc.nextInt() - 1;
                                sc.nextLine();
                                
                                if (qIndex < 0 || qIndex >= quizCount) break;
                                
                                System.out.print("Enter student name: ");
                                String delStudent = sc.nextLine();
                                
                                for (int j = 0; j < studentCount[qIndex]; j++) {
                                    if (studentNames[qIndex][j].equalsIgnoreCase(delStudent)) {
                                        System.out.println("Deleting student record: " + studentNames[qIndex][j]);
                                        for (int k = j; k < studentCount[qIndex] - 1; k++) {
                                            studentNames[qIndex][k] = studentNames[qIndex][k + 1];
                                            studentScores[qIndex][k] = studentScores[qIndex][k + 1];
                                        }
                                        studentCount[qIndex]--;
                                        System.out.println("Student record deleted.");
                                        System.out.println("Remaining Students:");
                                        for (int j2 = 0; j2 < studentCount[qIndex]; j2++) {
                                            System.out.println(studentNames[qIndex][j2] + " - " + studentScores[qIndex][j2]);
                                        }
                                        break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                
                case 2:
                    System.out.println("\n=== STUDENT MENU ===");
                    System.out.println("1. Take Quiz");
                    System.out.println("2. View Scores");
                    System.out.println("3. Back to Main Menu");
                    System.out.print("Enter choice: ");
                    int studentChoice = sc.nextInt();
                    sc.nextLine();
                    
                    if (studentChoice == 3) break;
                    
                    switch (studentChoice) {
                        case 1:
                            if (quizCount == 0) {
                                System.out.println("No quizzes available.");
                                break;
                            }
                            
                            System.out.println("\nAvailable Quizzes:");
                            for (int i = 0; i < quizCount; i++) {
                                System.out.println((i + 1) + ". " + quizNames[i] + " by " + teacherNames[i]);
                            }
                            
                            System.out.print("Enter quiz number to take: ");
                            int quizIndex = sc.nextInt() - 1;
                            sc.nextLine();
                            
                            if (quizIndex < 0 || quizIndex >= quizCount) break;
                            
                            System.out.print("Enter your name: ");
                            String studentName = sc.nextLine();
                            
                            int score = 0;
                            int totalQuestions = quizQuestions[quizIndex].length;
                            for (int i = 0; i < totalQuestions; i++) {
                                System.out.println("\nQuestion " + (i + 1) + ": " + quizQuestions[quizIndex][i]);
                                if (quizTypes[quizIndex].equals("Multiple Choice")) {
                                    for (int j = 0; j < 4; j++) {
                                        System.out.println((char) ('A' + j) + ". " + quizChoices[quizIndex][i][j]);
                                    }
                                }
                                System.out.print("Your answer: ");
                                String answer = sc.nextLine().trim().toUpperCase();
                                
                                if (answer.equals(quizAnswers[quizIndex][i])) {
                                    score++;
                                }
                            }
                            
                            System.out.println("Quiz completed! Your score: " + score + " / " + totalQuestions);
                            studentNames[quizIndex][studentCount[quizIndex]] = studentName;
                            studentScores[quizIndex][studentCount[quizIndex]] = score;
                            studentCount[quizIndex]++;
                            break;
                        
                        case 2:
                            System.out.println("\nYour Scores:");
                            for (int i = 0; i < quizCount; i++) {
                                System.out.println("Quiz: " + quizNames[i]);
                                for (int j = 0; j < studentCount[i]; j++) {
                                    System.out.println(studentNames[i][j] + " - " + studentScores[i][j] + " / " + quizQuestions[i].length);
                                }
                            }
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}