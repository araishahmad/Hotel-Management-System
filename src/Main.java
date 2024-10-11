import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true){
            UserInputs.showMenu();
            int option = input.nextInt();
            input.nextLine();

            UserInputs.userOperations(option);
        }
    }
}
