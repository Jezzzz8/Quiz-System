import java.util.Scanner;

public class QuizSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numQuestions = 0;
        String[] questions = null;
        String[] answers = null;

        while (true) {
            System.out.println("\n=== QUIZ SYSTEM ===");
            System.out.println("1. Teacher");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.println("\n=== TEACHER MENU ===");
                System.out.print("How many questions would you like to create? ");
                numQuestions = sc.nextInt();
                sc.nextLine();

                questions = new String[numQuestions];
                answers = new String[numQuestions];

                for (int i = 0; i < numQuestions; i++) {
                    System.out.print("Enter question " + (i + 1) + ": ");
                    questions[i] = sc.nextLine();
                    System.out.print("Enter correct answer for question " + (i + 1) + ": ");
                    answers[i] = sc.nextLine();
                }

                System.out.println("\nQuiz created successfully with " + numQuestions + " questions!");
                System.out.println("Returning to main menu...");
            } else if (choice == 2) {
                if (questions == null || numQuestions == 0) {
                    System.out.println("\nNo quiz available. Please wait for the teacher to create one.");
                } else {
                    System.out.println("\n=== STUDENT MENU ===");

                    int score = 0;

                    for (int i = 0; i < numQuestions; i++) {
                        System.out.println("Question " + (i + 1) + ": " + questions[i]);
                        System.out.print("Your answer: ");
                        String studentAnswer = sc.nextLine();

                        if (studentAnswer.equals(answers[i])) {
                            System.out.println("Correct!");
                            score++;
                        } else {
                            System.out.println("Wrong! The correct answer is: " + answers[i]);
                        }
                    }

                    System.out.println("\nYou scored " + score + " out of " + numQuestions + ".");
                    System.out.println("Returning to main menu...");
                }
            } else if (choice == 3) {
                System.out.println("Exiting system...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}