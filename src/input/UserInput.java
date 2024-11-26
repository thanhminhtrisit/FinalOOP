package input;

import java.util.Scanner;

import validation.Validator;

public class UserInput {
    public static int getIntegerInput() {
        Scanner scanner = new Scanner(System.in);
        Integer parsedValue = null;

        while (parsedValue == null) {
            System.out.print("Please enter an integer: ");
            String userInput = scanner.nextLine();

            if (Validator.isInteger(userInput)) {
                parsedValue = Integer.parseInt(userInput);
                System.out.println("You entered a valid integer: " + parsedValue);
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        return parsedValue;
    }

    public static double getDoubleInput() {
        Scanner scanner = new Scanner(System.in);
        Double parsedValue = null;

        while (parsedValue == null) {
            System.out.print("Please enter a double: ");
            String userInput = scanner.nextLine();

            if (Validator.isDouble(userInput)) {
                parsedValue = Double.parseDouble(userInput);
                System.out.println("You entered a valid double: " + parsedValue);
            } else {
                System.out.println("Invalid input. Please enter a valid double.");
            }
        }

        return parsedValue;
    }
}
