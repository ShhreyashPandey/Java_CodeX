import java.util.Scanner;
public class ques5 {
    //Write a program in Java to take input a word and a line and print it together.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String word = scanner.next();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter a line: ");
        String line = scanner.nextLine();

        System.out.println(word + " " + line);

        scanner.close();
    }
}
