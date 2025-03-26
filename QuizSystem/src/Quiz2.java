import java.util.Scanner;

public class Quiz2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Maximum nga number sa quizzes nga mahimo
        final int MAX_QUIZZES = 5;
        int quizCount = 0;

        // Arrays para sa pag-store sa quiz data
        String[][] quizQuestions = new String[MAX_QUIZZES][]; // Nag-store sa mga pangutana sa matag quiz
        String[][] quizAnswers = new String[MAX_QUIZZES][]; // Nag-store sa mga correct answers
        String[][][] quizChoices = new String[MAX_QUIZZES][][]; // Nag-store sa mga choices para sa multiple-choice
        String[] quizTypes = new String[MAX_QUIZZES]; // Nag-store kung Multiple Choice ba o Direct Answer ang type sa quiz
        String[] quizNames = new String[MAX_QUIZZES]; // Nag-store sa ngalan sa quiz
        String[] teacherNames = new String[MAX_QUIZZES]; // Nag-store sa pangalan sa teacher nga naghimo sa quiz

        // Arrays para sa student data ug scores
        String[][] studentNames = new String[MAX_QUIZZES][10];  // Nag-store sa mga pangalan sa students nga ni take sa quiz
        int[][] studentScores = new int[MAX_QUIZZES][10]; // Nag-store sa score sa kada estudyante
        int[] studentCount = new int[MAX_QUIZZES]; // Nag-track sa pila ka estudyante ang nag take sa kada quiz

        while (true) {
            // Main Menu para sa quiz system
            System.out.println("\n=== QUIZ SYSTEM ===");
            System.out.println("1. Teacher - Create Quiz");
            System.out.println("2. Student - Take Quiz");
            System.out.println("3. View Scores");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) { // Kung teacher gusto maghimo og quiz
                if (quizCount >= MAX_QUIZZES) {
                    System.out.println("\nQuiz storage is full! No more quizzes can be created.");
                    continue;
                }
                // Enter una ang teacher ug quiz details
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

                System.out.print("How many questions will this quiz have? ");
                int numQuestions = sc.nextInt();
                sc.nextLine();

                // Mag initialize dayun sa mga arrays para sa pangutana, tubag, ug choices
                quizQuestions[quizCount] = new String[numQuestions];
                quizAnswers[quizCount] = new String[numQuestions];
                quizChoices[quizCount] = (quizTypeChoice == 1) ? new String[numQuestions][4] : null;

                for (int i = 0; i < numQuestions; i++) {
                    System.out.print("\nEnter question " + (i + 1) + ": ");
                    quizQuestions[quizCount][i] = sc.nextLine();

                    if (quizTypeChoice == 1) { // Kung Multiple Choice
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

                System.out.println("\nQuiz '" + quizNames[quizCount] + "' (" + quizTypes[quizCount] + ") by " + teacherNames[quizCount] + " created successfully!");
                quizCount++;
            }
            else if (choice == 2) { // Kung student gusto mo-take sa quiz
                if (quizCount == 0) {
                    System.out.println("\nNo quizzes available. Ask a teacher to create one.");
                    continue;
                }

                // Ipakita ang available nga quizzes
                System.out.println("\nAvailable Quizzes:");
                for (int i = 0; i < quizCount; i++) {
                    System.out.println((i + 1) + ". " + quizNames[i] + " (By: " + teacherNames[i] + ") - " + quizTypes[i]);
                }
                System.out.print("Select a quiz by number: ");
                int selectedQuiz = sc.nextInt() - 1;
                sc.nextLine();

                if (selectedQuiz < 0 || selectedQuiz >= quizCount) {
                    System.out.println("Invalid selection.");
                    continue;
                }

                // Student mag take sa quiz
                System.out.print("\nEnter your name: ");
                String studentName = sc.nextLine();
                System.out.println("\nStarting Quiz: " + quizNames[selectedQuiz] + " (" + quizTypes[selectedQuiz] + ")");
                int score = 0;
                int numQuestions = quizQuestions[selectedQuiz].length;

                for (int i = 0; i < numQuestions; i++) {
                    System.out.println("\nQuestion " + (i + 1) + ": " + quizQuestions[selectedQuiz][i]);
                    if (quizTypes[selectedQuiz].equals("Multiple Choice")) {
                        for (int j = 0; j < 4; j++) {
                            System.out.println((char) ('A' + j) + ". " + quizChoices[selectedQuiz][i][j]);
                        }
                    }

                    System.out.print("Your answer: ");
                    String studentAnswer = sc.nextLine().trim().toUpperCase();

                    if (studentAnswer.equals(quizAnswers[selectedQuiz][i])) {
                        System.out.println("Correct!");
                        score++;
                    } else {
                        System.out.println("Wrong! Correct answer: " + quizAnswers[selectedQuiz][i]);
                    }
                }

                System.out.println("\nYour final score: " + score + " out of " + numQuestions);
                if (studentCount[selectedQuiz] < 10) {
                    studentNames[selectedQuiz][studentCount[selectedQuiz]] = studentName;
                    studentScores[selectedQuiz][studentCount[selectedQuiz]] = score;
                    studentCount[selectedQuiz]++;
                }
            } 
            else if (choice == 3) { // Show tanan scores sa kada student
                if (quizCount == 0) {
                    System.out.println("\nNo quizzes available.");
                    continue;
                }
                System.out.println("\nQuiz Scores:");
                for (int i = 0; i < quizCount; i++) {
                    System.out.println("\n" + quizNames[i] + " (By: " + teacherNames[i] + ") - " + quizTypes[i]);
                    if (studentCount[i] == 0) {
                        System.out.println("  No scores recorded yet.");
                    } else {
                        for (int j = 0; j < studentCount[i]; j++) {
                            System.out.println("  " + studentNames[i][j] + " - Score: " + studentScores[i][j] + "/" + quizQuestions[i].length);
                        }
                    }
                }
            }
            else if (choice == 4) {
                System.out.println("Exiting system...");
                break;
            }
        }
    }
}