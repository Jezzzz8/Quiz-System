import java.util.Scanner;

public class QuizSystem {
    static String questions = ""; // Store questions as a single string
    static String answers = "";   // Store answers as a single string

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== QUIZ SYSTEM ===");
            System.out.println("1. Teacher");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (choice == 1) {
                teacherMenu(sc);
            } else if (choice == 2) {
                studentMenu(sc);
            } else if (choice == 3) {
                System.out.println("Exiting system...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }

    // Teacher menu
    static void teacherMenu(Scanner sc) {
        System.out.println("\n=== TEACHER MENU ===");
        System.out.print("How many questions would you like to create? ");
        int numQuestions = sc.nextInt();
        sc.nextLine(); // Consume newline

        questions = ""; // Reset questions and answers each time teacher sets new quiz
        answers = "";

        for (int i = 1; i <= numQuestions; i++) {
            System.out.print("Enter question " + i + ": ");
            String question = sc.nextLine();
            System.out.print("Enter correct answer for question " + i + ": ");
            String answer = sc.nextLine();

            // Store questions and answers separated by ";"
            questions += question + ";";
            answers += answer + ";";
        }

        System.out.println("\nQuiz created successfully with " + numQuestions + " questions!");
        System.out.println("Returning to main menu...");
    }

    // Student menu
    static void studentMenu(Scanner sc) {
        if (questions.isEmpty()) {
            System.out.println("\nNo quiz available. Please wait for the teacher to create one.");
            return;
        }

        System.out.println("\n=== STUDENT MENU ===");

        // Split questions and answers into arrays using ";" as a separator
        String[] questionList = questions.split(";");
        String[] answerList = answers.split(";");

        int score = 0;

        for (int i = 0; i < questionList.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questionList[i]);
            System.out.print("Your answer: ");
            String studentAnswer = sc.nextLine();

            if (studentAnswer.equalsIgnoreCase(answerList[i])) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer is: " + answerList[i]);
            }
        }

        System.out.println("\nYou scored " + score + " out of " + questionList.length + ".");
        System.out.println("Returning to main menu...");
    }
}