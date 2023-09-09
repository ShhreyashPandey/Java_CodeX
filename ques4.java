import java.util.Scanner;
public class ques4 {
    //Write a program in java to take input a number and print whether the number is spy number or not.
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a number: ");
            int num = scanner.nextInt();

            int product = 1;
            int sum = 0;

            int originalNum = num;

            while (num > 0) {
                int digit = num % 10;
                product *= digit;
                sum += digit;
                num /= 10;
            }

            if (product == sum) {
                System.out.println(originalNum + " is a Spy number.");
            } else {
                System.out.println(originalNum + " is not a Spy number.");
            }

            scanner.close();
        }

}
