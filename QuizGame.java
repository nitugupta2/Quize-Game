// Quiz game
// java language 
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class QuizGame 
{

    public static void main(String[] args) 
    {
        QuizGame quizGame = new QuizGame();
        quizGame.addQuestion(new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2));
        quizGame.addQuestion(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        quizGame.addQuestion(new Question("What is the color of the sky?", new String[]{"Blue", "Green", "Red", "Yellow"}, 0));

        quizGame.start();
    }

    private ArrayList<Question> questions;
    private int score;

    public QuizGame()
    {
        questions = new ArrayList<>();
        score = 0;
    }

    public void addQuestion(Question question)
    {
        questions.add(question);
    }

    public void start() 
    {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) 
        {
            System.out.println(question.getQuestion());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++)
            {
                System.out.println((i + 1) + ": " + options[i]);
            }

            int answer = -1;
            boolean validInput = false;

            while (!validInput) 
            {
                System.out.print("Your answer: ");
                try 
                {
                    answer = scanner.nextInt() - 1;
                    validInput = true;
                } catch (InputMismatchException e) 
                {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            if (question.isCorrect(answer))
            {
                score++;
                System.out.println("Correct!\n");
            } else 
            {
                System.out.println("Wrong! The correct answer was: " + options[question.getCorrectAnswerIndex()] + "\n");
            }
        }

        System.out.println("Quiz finished! Your score is: " + score + "/" + questions.size());
        scanner.close();
    }

    static class Question 
    {
        private String question;
        private String[] options;
        private int correctAnswerIndex;

        public Question(String question, String[] options, int correctAnswerIndex) 
        {
            this.question = question;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getQuestion() 
        {
            return question;
        }

        public String[] getOptions() 
        {
            return options;
        }

        public int getCorrectAnswerIndex()
        {
            return correctAnswerIndex;
        }

        public boolean isCorrect(int answerIndex) 
        {
            return answerIndex == correctAnswerIndex;
        }
    }
}
