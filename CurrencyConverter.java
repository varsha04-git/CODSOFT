import java.util.Scanner;

//A simple program to convert INR to different currencies using mock exchange rates
public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // simulated exchange rates (as of now, not real-time)
        double usdRate = 83.12;  // US Dollar
        double eurRate = 90.85;   //Euro
        double gbpRate = 105.50; //British Pound
        double jpyRate = 0.56;      //Japanese Yen

	//Greeting message 
        System.out.println("Welcome to Currency Converter!");
        System.out.print("Enter amount in INR: ₹");
        double inr = scanner.nextDouble();

	//show conversion options
        System.out.println("\nSelect the currency to convert to:");
        System.out.println("1. USD (US Dollar)");
        System.out.println("2. EUR (Euro)");
        System.out.println("3. GBP (British Pound)");
        System.out.println("4. JPY (Japanese Yen)");
        System.out.print("Enter your choice (1-4): ");
        int choice = scanner.nextInt();

	//Variables to hold results
        double converted = 0;
        String currency = "";

  	//Perform conversion based on user choice
        switch (choice) {
            case 1:
                converted = inr / usdRate;
                currency = "USD";
                break;
            case 2:
                converted = inr / eurRate;
                currency = "EUR";
                break;
            case 3:
                converted = inr / gbpRate;
                currency = "GBP";
                break;
            case 4:
                converted = inr / jpyRate;
                currency = "JPY";
                break;
            default:
                System.out.println("Invalid choice.");
                scanner.close();
                return;
        }

	//Display conversion result
        System.out.printf("₹%.2f INR = %.2f %s%n", inr, converted, currency);
        scanner.close();
    }
}
