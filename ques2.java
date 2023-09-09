//Write a program in java to take input a number and print its table in the format "2 X 1 = 2" , "6 X 5 = 30".
import java.util.Scanner;
public class ques2{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();

        for (int i = 1; i <= 10; i++) {
            int result = num * i;
            System.out.println(num + " X " + i + " = " + result);
        }

        scanner.close();
    }
}