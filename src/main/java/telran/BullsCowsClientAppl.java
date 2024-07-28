package telran;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BullsCowsClientAppl {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345; 

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to server.");

            boolean running = true;
            while (running) {
                System.out.println("1. Start New Game");
                System.out.println("2. Show Results");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                out.println(choice);

                switch (choice) {
                    case 1:
                        startNewGame(scanner, out, in);
                        break;
                    case 2:
                        showResults(in);
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startNewGame(Scanner scanner, PrintWriter out, BufferedReader in) throws Exception {
        System.out.print("Enter your move (4 non-repeated digits): ");
        String move = scanner.nextLine();
        out.println(move);

        String response = in.readLine();
        System.out.println("Server response: " + response);
    }

    private static void showResults(BufferedReader in) throws Exception {
     
        String response = in.readLine();
        System.out.println("Results: " + response);
    }
}