package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    
    public static int rows;
    public static int seatsInTheRow;
    public static int numberOfSold = 0;
    public static int income = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsInTheRow = scanner.nextInt();
        char[][] array = new char[rows][seatsInTheRow];
        for (char[] chars : array) {
            Arrays.fill(chars, 'S');
        }
        int menuChoice = getMenuChoice(scanner);
        boolean isRun = true;
        while (isRun) {
            switch (menuChoice) {
                case 1:
                    printCinema(array);
                    menuChoice = getMenuChoice(scanner);
                    break;
                case 2:
                    ticketDesk(scanner, rows, seatsInTheRow, array);
                    menuChoice = getMenuChoice(scanner);
                    break;
                case 3:
                    showStats();
                    menuChoice = getMenuChoice(scanner);
                    break;
                default:
                    isRun = false;
            }
        }
    }

    private static int getMenuChoice(Scanner scanner) {
        System.out.println("\n1. Show the seats\n" + "2. Buy a ticket\n" + "3. Statistics\n" + "0. Exit");
        return scanner.nextInt();
    }

    private static void ticketDesk(Scanner scanner, int rows, int seatsInTheRow, char[][] array) {
        System.out.println("\nEnter a row number:");
        int ticketRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int ticketSeat = scanner.nextInt();
        if (ticketRow < 1 || ticketRow > rows || ticketSeat < 1 || ticketSeat > seatsInTheRow) {
            System.out.println("Wrong input!");
            ticketDesk(scanner, rows, seatsInTheRow, array);
        } else if (array[ticketRow - 1][ticketSeat - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            ticketDesk(scanner, rows, seatsInTheRow, array);
        } else {
            array[ticketRow - 1][ticketSeat - 1] = 'B';
            numberOfSold++;
            if (rows * seatsInTheRow <= 60 || ticketRow <= rows / 2) {
                income += 10;
                System.out.println("\nTicket price: $" + 10);
            } else {
                income += 8;
                System.out.println("\nTicket price: $" + 8);
            }
        } 
    }

    private static void printCinema(char[][] array) {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i <= array[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    private static void showStats() {
        System.out.printf("\nNumber of purchased tickets: %d%n", numberOfSold);
        float percentage = (numberOfSold * 100 / (float) (rows * seatsInTheRow));
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.printf("Current income: $%d%n", income);
        int totalIncome = rows * seatsInTheRow <= 60 ? rows * seatsInTheRow * 10 : rows / 2 * seatsInTheRow * 10 + (rows -
            rows / 2) * seatsInTheRow * 8;
        System.out.printf("Total income: $%d%n", totalIncome);        
    }
}