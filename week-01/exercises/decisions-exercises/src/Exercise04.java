import java.util.Scanner;

public class Exercise04 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a decimal number: ");
        double value = Double.parseDouble(console.nextLine());

        // 1. Change the if condition to detect positive or negative numbers.
        // 2. Change the output messages to match.
        if (value > 1000.0) {
            System.out.println("Number is larger than 1000.");
        } else {
            System.out.println("Number is less than or equal to 1000.");
        }
    }
}
