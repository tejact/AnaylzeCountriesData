import java.util.Scanner;
class Prompter {
    final Scanner scanner;

    public Prompter(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getDataFromUser(String prompt) {
        System.out.println(prompt);
        return scanner.next();
    }
}