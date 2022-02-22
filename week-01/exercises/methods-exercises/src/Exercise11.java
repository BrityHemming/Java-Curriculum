import java.util.Scanner;

public class Exercise11 {

    public static void main(String[] args) {
        String name = null;
        int pillowCount = 0;

        name = readString("What's your name?: ");
        // 2. Uncomment the line below and confirm readInt works.
        // pillowCount = readInt("How many pillows do you sleep with?: ");

        System.out.printf("%s sleeps with %s pillows.%n", name, pillowCount);
    }

    // 1. Create a method.
    // Name: readInt
    // Inputs: String
    // Output: int
    // Description: prompts a user to enter a whole number and returns their input as an int.
    // The parameter is the message displayed to the user.
    //
    // Requirements:
    // readInt must use the readString method. Don't create a Scanner inside readInt.
    // Pass the prompt along to readString.
    // Parse the output from readString into an int.

    public static String readString(String prompt) {
        Scanner console = new Scanner(System.in);
        System.out.print(prompt);
        return console.nextLine();
    }
}
