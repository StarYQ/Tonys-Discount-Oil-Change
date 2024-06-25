import java.util.Scanner;
public class OilChangeManager {
    private static CarList joe;
    private static CarList donny;
    private static CarList finished;
    private static Car car;
    public static void main(String[] args) {
        joe = new CarList();
        donny = new CarList();
        finished = new CarList();
        car = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Tony's Discount Oil Change Computer Management System! It's way better than a rolodex, cork board, post-its, and pre-chewed bubblegum!");
        while (true){
            printMenu();
            String option = sc.nextLine().toLowerCase();
            switch (option){
                case "l":
                    selectList(sc, car);
                    break;
                case "m":
                    selectDestination(sc);
                    break;
                case "p":
                    System.out.println("\nJoe's List:\nMake   Owner\n----------------------");
                    joe.display();
                    System.out.println();
                    System.out.println("Donny's List:\nMake   Owner\n----------------------");
                    donny.display();
                    System.out.println(" ");
                    System.out.println("Finished List:\nMake   Owner\n----------------------");
                    finished.display();
                    System.out.println(" ");
                    break;
                case "f":
                    finished.appendToTail(car);
                    if (car!=null) {
                        System.out.println("Car pasted in finished list");
                        car=new Car();
                    }
                    else{
                        System.out.println("Nothing to paste");
                    }
                    break;
                case "s":
                    //TODO:sort the list (EC)
                    break;
                case "q":
                    System.out.println("Enjoy your retirement!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }
    public static void printMenu(){
        System.out.println("\nMenu:\n\tL) Edit Job Lists for Joe and Donny\n\tM) Merge Job Lists");
        System.out.println("\tP) Print Job Lists\n\tF) Paste car to end of finished car list");
        System.out.println("\tS) Sort Job Lists\n\tQ) Quit.\n");
        System.out.println("Please select an option: ");

    }
    public static void selectList(Scanner sc, Car car){
        System.out.println("Please select a list - Joe (J) or Donny (D): ");
        String list = sc.nextLine().toLowerCase();
        switch (list){
            case "j":
                joeOptions(sc, car);
                break;
            case "d":
                donnyOptions(sc, car);
                break;
            default:
                System.out.println("Invalid list");
        }
    }
    public static void joeOptions(Scanner sc, Car car){
            System.out.println("\nOptions\n\tA) Add a car to the end of the list");
            System.out.println("\tF) Cursor Forward\n\tH) Cursor to Head");
            System.out.println("\tT) Cursor to Tail\n\tB) Cursor Backward");
            System.out.println("\tI) Insert car before cursor\n\tX) Cut car at cursor");
            System.out.println("\tV) Paste before cursor\n\tR) Remove cursor\n");
            System.out.println("Please select an option: ");
            String option = sc.nextLine().toLowerCase();
            try {
                switch (option) {
                    case "a":
                        System.out.println("Please enter vehicle make (Ford, GMC, Chevy, Jeep, Dodge, Chrysler, and Lincoln): ");
                        String make = sc.nextLine();
                        Make ownerMake = findMake(make.toUpperCase());
                        if (ownerMake == null) {
                            System.out.println("We don't service " + make + "!");
                            printMenu();
                        } else {
                            System.out.println("Please enter owner's name: ");
                            String ownerName = sc.nextLine();
                            joe.appendToTail(new Car(ownerMake, ownerName));
                            System.out.println(ownerName + "'s " + ownerMake + " has been scheduled for an oil change with Joe and has been added to his list.");
                        }
                        break;
                    case "f":
                        joe.cursorForward();
                        System.out.println("Cursor Moved Forward in Joe's List.");
                        break;
                    case "h":
                        joe.resetCursorToHead();
                        System.out.println("Cursor Moved To Head in Joe's List.");
                        break;
                    case "t":
                        joe.cursorToTail();
                        System.out.println("Cursor Moved To Tail in Joe's List.");
                        break;
                    case "b":
                        joe.cursorBackward();
                        System.out.println("Cursor Moved Backward in Joe's List.");
                        break;
                    case "i":
                        System.out.println("Please enter vehicle make (Ford, GMC, Chevy, Jeep, Dodge, Chrysler, and Lincoln): ");
                        String joeMake = sc.nextLine();
                        Make joeOwnerMake = findMake(joeMake.toUpperCase());
                        if (joeOwnerMake == null) {
                            System.out.println("We don't service " + joeMake);
                        } else {
                            System.out.println("Please enter owner's name: ");
                            String ownerName = sc.nextLine();
                            joe.insertBeforeCursor(new Car(joeOwnerMake, ownerName));
                            System.out.println(ownerName + "'s " + joeOwnerMake + " has been scheduled for an oil change with Joe and has been added to his list before the cursor.");
                        }
                        break;
                    case "x":
                        car = joe.cutCarAtCursor();
                        if (car == null) {
                            System.out.println("Empty List");
                        }
                        System.out.println("Cursor cut in Joe's List");
                        break;
                    case "v":
                        if (car!=null) {
                            joe.pasteCarBeforeCursor(car);
                            System.out.println("Cursor pasted in Joe's List");
                            car=new Car();
                        }
                        else{
                            System.out.println("Nothing to paste");
                        }
                        break;
                    case "r":
                        joe.removeCarAtCursor();
                        System.out.println("Cursor removed in Joe's List");
                        break;
                    default:
                        System.out.println("Invalid list");
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
        }
    }
    public static void donnyOptions(Scanner sc, Car car){
        try {
            System.out.println("\nOptions:\n\tA) Add a car to the end of the list");
            System.out.println("\tF) Cursor Forward\n\tH) Cursor to Head\n\tT) Cursor to Tail");
            System.out.println("\tB) Cursor Backward\n\tI) Insert car before cursor\n\tX) Cut car at cursor");
            System.out.println("\tV) Paste before cursor\n\tR) Remove cursor\n");
            System.out.println("Please select an option: ");
            String option = sc.nextLine().toLowerCase();
            switch (option) {
                case "a":
                    System.out.println("Please enter vehicle make (Ford, GMC, Chevy, Jeep, Dodge, Chrysler, and Lincoln): ");
                    String make = sc.nextLine();
                    Make ownerMake = findMake(make.toUpperCase());
                    if (ownerMake == null) {
                        System.out.println("We don't service " + make);
                    }
                    else {
                        System.out.println("Please enter owner's name: ");
                        String ownerName = sc.nextLine();
                        donny.appendToTail(new Car(ownerMake, ownerName));
                        System.out.println(ownerName + "'s " + ownerMake + " has been scheduled for an oil change with Donny and has been added to his list.");
                    }
                    break;
                case "f":
                    donny.cursorForward();
                    System.out.println("Cursor Moved Forward in Donny's List.");
                    break;
                case "h":
                    donny.resetCursorToHead();
                    System.out.println("Cursor Moved To Head in Donny's List.");
                    break;
                case "t":
                    donny.cursorToTail();
                    System.out.println("Cursor Moved To Tail in Donny's List.");
                    break;
                case "b":
                    donny.cursorBackward();
                    System.out.println("Cursor Moved Backward in Donny's List.");
                    break;
                case "i":
                    System.out.println("Please enter vehicle make (Ford, GMC, Chevy, Jeep, Dodge, Chrysler, and Lincoln): ");
                    String donnyMake = sc.nextLine();
                    Make donnyOwnerMake = findMake(donnyMake.toUpperCase());
                    if (donnyOwnerMake == null) {
                        System.out.println("We don't service " + donnyMake + "!");
                    } else {
                        System.out.println("Please enter owner's name: ");
                        String ownerName = sc.nextLine();
                        donny.insertBeforeCursor(new Car(donnyOwnerMake, ownerName));
                        System.out.println(ownerName + "'s " + donnyOwnerMake + " has been scheduled for an oil change with Donny and has been added to his list before the cursor.");
                    }
                    break;
                case "x":
                    car = donny.cutCarAtCursor();
                    if (car == null) {
                        System.out.println("Empty List");
                    }
                    System.out.println("Cursor cut in Donny's List");
                    break;
                case "v":
                    if (car!=null) {
                        donny.pasteCarBeforeCursor(car);
                        System.out.println("Cursor pasted in Donny's List");
                        car=new Car();
                    }
                    else{
                        System.out.println("Nothing to paste");
                    }
                    break;
                case "r":
                    donny.removeCarAtCursor();
                    System.out.println("Cursor removed in Donny's List");
                    break;
                default:
                    System.out.println("Invalid list");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static void selectDestination(Scanner sc) {
        System.out.println("Please select a destination list - Joe (J) or Donny (D): ");
        String destination = sc.nextLine().toLowerCase();
        switch (destination){
            case "j":
                mergeDonnyListToJoe();
                if (joe.numCars()==1){
                    joe.resetCursorToHead();
                }
                break;
            case "d":
                mergeJoeListToDonny();
                if (donny.numCars()==1){
                    donny.resetCursorToHead();
                }
                break;
            default:
                System.out.println("Invalid list");
        }
    }
    private static void mergeDonnyListToJoe() {
        if (donny != null && joe != null){
            boolean isMerged = joe.merge(donny);
            if (isMerged){
                System.out.println("Donny's list merged into Joe's");
            }
        }
    }
    private static void mergeJoeListToDonny() {
        if (joe != null && donny != null){
            boolean isMerged = donny.merge(joe);
            if (isMerged){
                System.out.println("Joe's list merged into Donny's");
            }
        }
    }
    private static Make findMake(String make) {
        Make ownerMake = null;
        for (Make theMake : Make.values()) {
            if (make.equals(theMake.toString())){
                ownerMake = theMake;
                break;
            }
        }
        return ownerMake;
    }
}