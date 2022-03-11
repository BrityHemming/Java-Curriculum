import java.util.Scanner;
public class Hotel {
    /*
***Requirements
- On start up, the application prompts the administrator for the hotel's capacity. The capacity determines how many capsules are available.
- The administrator may book a guest in an unoccupied numbered capsule.
- The administrator may check out a guest from an occupied capsule.
- The administrator may view guests and their capsule numbers in groups of 10.
***Tech Requirements
-  When the program starts up, capsules and guests will be represented by a String[] of the appropriate size.
- Unoccupied capsules are represented by a null array value.
- Occupied capsules are represented by the occupant's name as a String.
- At least one method beyond main is required. A few more methods may make your life easier.
*/



        public static void main(String[] args) {
            Scanner console = new Scanner(System.in);

            //Start Up
            // Welcome Message
            System.out.println("Welcome to the Grand Capsule Hotel!");
            //On start up, the application prompts the administrator for the hotel's capacity. The capacity determines how many capsules are available.
            System.out.print("Please enter the number of vacant capsules available for booking [0 - 50]:");
            // allow use to enter number - this sets the length of the array, once you declare the length it cannot be changed
            int vacantCapsules = Integer.parseInt(console.nextLine());
            // print the number back to the user
            System.out.printf("There are %s vacant capsules available for booking!", vacantCapsules);
            // create string array to store the number of vacant capsules
            //When the program starts up, capsules and guests will be represented by a String[] of the appropriate size.
            // this initializes the capsules array and sets the size of the array to the user input stored in vacantCapsules
            String[] capsules = new String[vacantCapsules];
            //System.out.println(capsules[1]);

            //Main Menu
            boolean exit = false;
            //allow user to choose an option
            do {
                String menuOption = mainMenu(console);
                // handle each menu option
                switch(menuOption){
                    case "1": guestCheckin(console, capsules);
                        break;
                    case "2": guestCheckout(console, capsules);
                        break;
                    case "3": guestList(capsules);
                        break;
                    // update exit boolean if user confirms logout
                    case "4": exit = confirmExit(console);
                        break;
                    default: System.out.printf("%s is not a valid menu item, please try again", menuOption);
                }
            } while (!exit);
            // Display Exit Message
            System.out.println();
            System.out.println("Goodbye for now!");

        }// closes main method
        // Main Menu
        // display the menu
        // we are using static here so that the method belongs to the entire class - in otherwords we don't need to create an object to use it
        public static String mainMenu(Scanner console) {
            System.out.println();
            System.out.println("Main Menu");
            System.out.println("**************************");
            System.out.println("1. Guest Check In");
            System.out.println("2. Guest Check Out");
            System.out.println("3. View The Guest List");
            System.out.println("4. Exit");
            System.out.print("Choose on option [1-4]: ");

            String menuOption = console.nextLine();

            System.out.println();

            return menuOption;
        }

        //Check-in
        // ** put inside of method?
        public static void guestCheckin(Scanner console, String [] capsules){
            System.out.println("Guest Check In");
            System.out.println("**************************");
            // allow user to choose a capsule by number
            System.out.print("Please choose the capsule number you would like to book: ");
            int capNum = Integer.parseInt(console.nextLine());
            //check that the capsule is empty, if empty allow check in by entering guest's name
            for(int i = 0; i < capsules.length; i++){
                if(capsules[capNum-1] == null){
                    //assign guest to capsule
                    System.out.print("Please Enter The Guest's Name: ");
                    String guestName = console.nextLine();
                    capsules[capNum-1] = guestName;
                    System.out.printf("%s is successfully booked into capsule %s, we hope they enjoy their stay!", guestName, capNum);
                    break;
                }else{
                    // else occupied return message prompting user to book a different capsule
                    System.out.printf("This capsule is currently booked by %s, please choose a different capsule number.", capsules[capNum-1]);
                    //mainMenu("1");
                    break;
                }
            }
        }



        //Check-out
        public static void guestCheckout(Scanner console, String [] capsules){
            System.out.println("Guest Check Out");
            System.out.println("**************************");

            System.out.println("What is the capsule number the guest will be checking out of?");
            //allow user to choose capsule by number
            int checkOutNum = Integer.parseInt(console.nextLine());

            if(capsules[checkOutNum-1] != null){
                // display capsule information prompt user to check out if guest info is correct
                System.out.printf("The guest in capsule %s is %s is this correct? [yes/no]:", checkOutNum, capsules[checkOutNum-1]);
                String confirm = console.nextLine();
                if(confirm.equalsIgnoreCase("yes")){
                    System.out.printf("%s has been successfully checkout out of capsule %s", capsules[checkOutNum-1], checkOutNum);
                    // check out will replace guest name with null
                    capsules[checkOutNum-1] = null;
                }else{
                    //else allow user to choose a different capsule if information is incorrect
                    System.out.println("Please confirm the capsule number and try again");
                }
            }else{
                System.out.println("That capsule is currently vacant, please confirm the capsule number and try again");
            }
        }
        // ** need to re-invoke method if number is incorrect



        //View guests
        // ** Show 10 at a time
        //The administrator may view guests and their capsule numbers in groups of 10.
        public static void guestList(String [] capsules){
            System.out.println("Guest List");
            System.out.println("**************************");
            // print out all guests and capsule numbers that are not null
            for(int x = 0; x < capsules.length; x++){
                if(capsules[x] != null){
                    System.out.printf("capsule number: %s, Guest Name: %s",x+1, capsules[x]);
                }
            }
        }


        //Exit application
        public static boolean confirmExit(Scanner console) {
            System.out.println("Leaving Already?");
            System.out.println("**************************");
            System.out.println("Are you sure you want to exit?");
            System.out.println("This program does not save any data, all check in / check out details for this session will be lost");
            //confirm that guest would like to exit application
            System.out.print("Exit [yes/no]: ");

            String confirmExit = console.nextLine();

            return confirmExit.equalsIgnoreCase("yes");

        }


    }// closes class
