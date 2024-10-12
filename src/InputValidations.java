import java.util.Scanner;

public class InputValidations {
    public static boolean isValidCNIC(String cnic) {
        String regex = "\\d{5}-\\d{7}-\\d{1}";
        return cnic.matches(regex);
    }

    public static int validAge(Scanner input) {
        int age = 0;
        boolean correct = false;

        while (!correct) {
            try{
                System.out.println("Enter age: ");
                age = input.nextInt();
                input.nextLine();

                if (age > 0 && age <= 130)
                    correct = true;
                else
                    System.out.println("Enter a valid age: ");
            } catch (IllegalArgumentException e){
                System.out.println("Invalid age entered, Enter a valid age");
                input.nextLine();
            }
        }
        return age;
    }

    public static String validString(Scanner input, String message) {
        String str = "";
        boolean correct = false;

        while (!correct) {
            System.out.println(message);
            str = input.nextLine();
            if (str.matches("[a-zA-z ]+"))
                correct = true;
            else
                System.out.println("Invalid string entered, Please enter a valid string");
        }
        return str;
    }
}