
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.*;

public class WestminsterShoppingManager implements ShoppingManager {

    public String output;
    // Store the products in a list
    ArrayList<Product> Product_Items = new ArrayList<>();

    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        
        // Create an instance of WestminsterShoppingManager
        WestminsterShoppingManager NEW = new WestminsterShoppingManager();
        
        // Load data from a file into the WestminsterShoppingManager instance
        NEW.Load_from_file();

        // Print the list of products in WestminsterShoppingManager
        NEW.Print_the_list_of_the_products();
        
        // Display the menu options
        Display_menu();  // calling for the display menu//

        // Declare and initialize a variable for user choice
        int choice;

        // Start a do-while loop for menu interaction
        do {
            // Prompt the user to enter an option
            System.out.print("\nEnter the option you want to enter: ");
            choice = scanner.nextInt();

            // Use a switch statement to perform actions based on user choice
            switch (choice) {
                case 1:
                    // Add a new product to WestminsterShoppingManager
                    NEW.Add_a_new_product();
                    break;
                case 2:
                    // Delete a product from WestminsterShoppingManager
                    NEW.Delete_a_product();
                    break;
                case 3:
                    // Print the list of products in WestminsterShoppingManager
                    NEW.Print_the_list_of_the_products();
                    break;
                case 4:
                    // Save the data in WestminsterShoppingManager to a file
                    NEW.Save_in_a_file();
                    break;
                case 5:
                    // Open the graphical user interface (GUI)
                    NEW.openGUI();
                    break;
                case 6:
                    // Exit the program
                    System.out.println("Exit");
                    break;
                default:
                    // Inform the user if the entered option is not recognized
                    System.out.println("Menu not identified. Enter a valid option");
                    break;
            }

        } while (choice != 6);  // Continue the loop until the user chooses to exit

        // Close the Scanner object to avoid resource leaks
        scanner.close();
    }


    // Dislpaya the menu for the user \\
    public static String Display_menu() {
        System.out.println(
                "\n                                            ****************** welcome to the Westminster Shopping Manager ******************");
        System.out.println(
                "\n                                                                         1. Add a new product");
        System.out.println(
                "\n                                                                         2. Delete a product");
        System.out.println(
                "\n                                                                         3. Print the list of the products ");
        System.out.println(
                "\n                                                                         4. Save in a file");
        System.out.println(
                "\n                                                                         5. Open GUI ");
        System.out.println(
                "\n                                                                         6. Exit from the programme ");

        return null;
    }

// Add a product to the system
public void Add_a_new_product() {
    // Create a Scanner object for user input
    Scanner input = new Scanner(System.in);

    // Display options for the user to choose the type of item to add
    System.out.println("\n                      ******   Enter 1 if you want to enter  electronics item   *****");
    System.out.println("                      ******   Enter 2 if you want to to enter  clothing item   *****");

    // Prompt the user to enter the item number they want to add (1 for electronics, 2 for clothing)
    System.out.print("\nEnter the Item no you want to enter (1 / 2): ");
    int ITEM = input.nextInt();

    // Use a switch statement to perform actions based on the user's choice
    switch (ITEM) {
        case 1:
            // If the user chose 1, call the method to add an electronics item
            electronics_item();
            break;
        case 2:
            // If the user chose 2, call the method to add a clothing item
            clothing_item();
            break;
        default:
            // If the user entered an invalid number, inform them
            System.out.println("Invalid number. Enter 1 or 2");
            break;
    }
}


// Add an electronic item to the product
private void electronics_item() {
    // Create a Scanner object for user input
    Scanner input = new Scanner(System.in);

    // Get the current count of electronic products in the system
    int productCount = Product_Items.size();

    // Check if the maximum number of items is reached
    if (productCount > 50) {
        System.out.println("Error! The maximum number of items has been reached.");
    } else {
        // Use a while loop to repeatedly prompt the user until valid input is provided
        while (true) {
            try {
                // Create a new Scanner for each input to avoid issues with newline characters
                Scanner A = new Scanner(System.in);

                // Prompt the user to enter product details
                System.out.print("\nEnter product id: ");
                String productId = input.nextLine();

                System.out.print("Enter product name: ");
                String productName = input.nextLine();

                System.out.print("Enter number of available items: ");
                int numberOfAvailableItems = input.nextInt();

                System.out.print("Enter the price: $");
                double price = input.nextDouble();

                // Consume the newline character left in the buffer
                input.nextLine();

                System.out.print("Enter the brand: ");
                String brand = input.nextLine();

                System.out.print("Enter the warranty period: ");
                int warrantyPeriod = input.nextInt();

                // Create a new Electronics object with the entered details
                Electronics newItem = new Electronics(productId, productName, numberOfAvailableItems, price, brand, warrantyPeriod);

                // Add the new electronic item to the list of products
                Product_Items.add(newItem);

                // Inform the user about the successful addition
                System.out.println("\n*** Successfully added the item. ***");

                // Exit the while loop after successful addition
                break;
            } catch (Exception e) {
                // Handle any exception by informing the user and allowing them to retry
                System.out.println("Error! Please enter valid input.");
            }
        }
    }
}

// Add a clothing item to the product
private void clothing_item() {
    // Create a Scanner object for user input
    Scanner input = new Scanner(System.in);

    // Get the current count of clothing products in the system
    int productCount = Product_Items.size();

    // Check if the maximum number of items is reached
    if (productCount > 50) {
        System.out.println("Error! The maximum number of items has been reached.");
    } else {
        // Use a while loop to repeatedly prompt the user until valid input is provided
        while (true) {
            try {
                // Create a new Scanner for each input to avoid issues with newline characters
                Scanner A = new Scanner(System.in);

                // Prompt the user to enter clothing details
                System.out.print("\nEnter product id: ");
                String productId = input.nextLine();

                System.out.print("Enter product name: ");
                String productName = input.nextLine();

                System.out.print("Enter number of available items: ");
                int numberOfAvailableItems = input.nextInt();

                System.out.print("Enter the price: $");
                double price = input.nextDouble();

                // Consume the newline character left in the buffer
                input.nextLine();

                System.out.print("Enter the size: ");
                String size = input.nextLine();

                System.out.print("Enter the color: ");
                String color = input.nextLine();

                // Create a new Clothing object with the entered details
                Clothing newItem = new Clothing(productId, productName, numberOfAvailableItems, price, size, color);

                // Add the new clothing item to the list of products
                Product_Items.add(newItem);

                // Inform the user about the successful addition
                System.out.println("\n*** Successfully added the item. ***");

                // Exit the while loop after successful addition
                break;
            } catch (Exception e) {
                // Handle any exception by informing the user and allowing them to retry
                System.out.println("Error! Please enter valid input.");
            }
        }
    }
}


 // Delete a product from the system
public void Delete_a_product() {
    // Create a Scanner object for user input
    Scanner input = new Scanner(System.in);

    // Display a welcome message for the delete product operation
    System.out.println("\n \n                                       ****Welcome to Delete a product ****");

    // Prompt the user to enter the product ID to delete
    System.out.print("\nEnter the product ID to delete: ");
    String productIdToDelete = input.next();

    // Initialize a boolean variable to track if the product was found and deleted
    boolean found = false;

    // Iterate through the list of products to find and delete the specified product
    for (Product product : Product_Items) {
        if (product.getProductId().equals(productIdToDelete)) {
            // Display details of the product before deletion
            System.out.println("\nProduct ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getproductName());
            System.out.println("Product Type: " + (product instanceof Electronics ? "Electronics" : "Clothing"));

            // Remove the product from the list
            Product_Items.remove(product);

            // Set the found flag to true and exit the loop
            found = true;
            break;
        }
    }

    // Display a message if the product was not found
    if (!found) {
        System.out.println("Product not found.");
    }

    // Display the total number of products left in the system
    System.out.println("Total number of products left: " + Product_Items.size());

    // Display a confirmation message about the successful removal of the product
    System.out.println("\n ----- The above product successfully removed from the system -----");
}


// Print the list of products in the system
public void Print_the_list_of_the_products() {
    try {
        // Check if the list of products is empty
        if (Product_Items.size() == 0) {
            System.out.println("No products in the system.");
        } else {
            // Sort the list of products based on product IDs
            Collections.sort(Product_Items, Comparator.comparing(Product::getProductId));

            // Display a header for the information about products in the system
            System.out.println("\n\n                       ***** INFORMATION ABOUT THE PRODUCTS IN THE SYSTEM ***** ");

            // Iterate through the list of products and print their details
            for (Product product : Product_Items) {
                System.out.println("\nProduct ID: " + product.getProductId());
                System.out.println("Product Name: " + product.getproductName());
                System.out.println("Number of available items: " + product.getnumberofawalabailitems());
                System.out.println("Product Price: " + product.getprice());
                System.out.println("----------------------------------");
            }
        }
    } catch (Exception e) {
        // Handle any exception by informing the user about an error
        System.out.println("Error!");
    }
}


// Save the list of products to a file
public void Save_in_a_file() {
    try (
        // Create a FileWriter to write to the specified file path
        FileWriter writer = new FileWriter("C:\\Users\\thisu\\OneDrive\\Desktop\\OPP_TUT_IIT\\OPP_TUT_IIT\\products.txt");
        
        // Create a BufferedWriter to efficiently write characters to the FileWriter
        BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

        // Iterate through the list of products and write their details to the file
        for (Product product : Product_Items) {
            if (product != null) {
                // Write product details to the file, each detail on a new line
                bufferedWriter.write("\nproduct ID: " + product.getProductId());
                bufferedWriter.write("\nproduct Name: " + product.getproductName());
                bufferedWriter.write("\nNumber of available items: " + product.getnumberofawalabailitems());
                bufferedWriter.write("\nProduct price: " + product.getprice());
                bufferedWriter.write("\n----------------------------------");
                
                // Move to the next line for the next product
                bufferedWriter.newLine();
            }
        }
        // Inform the user that the product list has been saved successfully
        System.out.println("Product list saved successfully.");
    } catch (IOException e) {
        // Handle exceptions related to file I/O, informing the user about the error
        System.out.println("Error saving product list to file: " + e.getMessage());
        e.printStackTrace();
    }
}


// Load the list of products from a file
public void Load_from_file() {
    try {
        // Create a BufferedReader to read from the specified file path
        BufferedReader reader = new BufferedReader(
                new FileReader("C:\\Users\\thisu\\OneDrive\\Desktop\\OPP_TUT_IIT\\OPP_TUT_IIT\\products.txt"));

        // Initialize a variable to store each line read from the file
        String line = "";

        // Create an ArrayList to store the lines read from the file
        ArrayList<String> list = new ArrayList<>();

        // Read each line from the file and add it to the list
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }

        // Print the list of lines (for testing or debugging purposes)
        System.out.println(list);

    } catch (FileNotFoundException e) {
        // Handle the case where the file is not found
        System.out.println("No saved data found.");
    } catch (IOException e) {
        // Handle exceptions related to file I/O, informing the user about the error
        System.out.println("Error loading product list from file: " + e.getMessage());
        e.printStackTrace();
    }
}


   
    
    private void openGUI() {
        new My(this);
        
    }

    
}
