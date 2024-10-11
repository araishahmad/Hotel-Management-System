import java.util.Scanner;

public class UserInputs {
    static Scanner input = new Scanner(System.in);
    static HotelManagement hotelManagement = new HotelManagement();  // Hotel management instance

    public static boolean isValidCNIC(String cnic) {
        String regex = "\\d{5}-\\d{7}-\\d{1}";
        return cnic.matches(regex);
    }

    public static Customer getCustomerDetails() {
        System.out.println("Enter your name: ");
        String name = input.nextLine();

        System.out.println("Enter your CNIC: ");
        String CNIC = input.nextLine();
        while (!isValidCNIC(CNIC)) {
            System.out.println("Invalid CNIC, Enter a valid CNIC: ");
            CNIC = input.nextLine();
        }

        System.out.println("Enter your age: ");
        int age = input.nextInt();
        input.nextLine(); // Consume newline

        return new Customer(name, CNIC, age);
    }

    public static Room getRoomDetails() {
        boolean validType = false;
        String roomType = "";
        while (!validType) {
            for (HotelCriteria hotelCriteria : HotelCriteria.values())
                System.out.println(" - " + hotelCriteria);

            System.out.println("Enter room type: ");
            roomType = input.nextLine();

            try {
                HotelCriteria.valueOf(roomType.toUpperCase());
                validType = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid room type. Please enter a valid room type.");
            }
        }

        System.out.println("Enter room ID: ");
        String roomID = input.nextLine();
        return new Room(roomID, roomType);
    }

    public static void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Book Room");
        System.out.println("2. Check Out");
        System.out.println("3. Calculate total bill");
        System.out.println("4. Display All Booked Rooms");
        System.out.println("5. Check Specific Customer's Rooms");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

   public static void userOperations(int option){
        switch (option){
            case 1:
                Customer bookCustomer = getCustomerDetails();
                Room bookRoom = getRoomDetails();
                if (hotelManagement.findCustomers(bookCustomer.getName()) == null)
                    hotelManagement.addCustomer(bookCustomer);

                bookCustomer.bookRoom(bookRoom);
                hotelManagement.addRoom(bookRoom);
                break;

            case 2:
                System.out.println("Enter name: ");
                String name = input.nextLine();
                Customer checkOutCustomer = hotelManagement.findCustomers(name);
                if (checkOutCustomer == null){
                    System.out.println("No customer of name: " + name);
                    break;
                }

                System.out.println("Enter room ID: ");
                String roomID = input.nextLine();

                Room checkOutRoom = hotelManagement.getRoomByID(roomID);
                if (checkOutRoom != null && checkOutCustomer.getBookedRoom().contains(checkOutRoom)){
                    System.out.println("Enter nights spent: ");
                    int nights = input.nextInt();
                    double totalBill = checkOutRoom.calculateBill(nights);
                    System.out.println("Total bill of customer: $" + totalBill);

                    checkOutCustomer.checkOut(checkOutRoom);
                    hotelManagement.removeRoom(roomID);
                }
                else
                    System.out.println("Room with ID" + roomID + " not found");
                break;

            case 3:
                System.out.println("Enter nights spent: ");
                int nights = input.nextInt();
                input.nextLine();

                System.out.println("Enter room ID: ");
                String roomId = input.nextLine();
                Room room = new Room(roomId);

                if(room == hotelManagement.getRoomByID(roomId)){
                    double totalBill = room.calculateBill(nights);
                    System.out.println("Total bill till now: $" + totalBill);
                }
                else
                    System.out.println("No room found with ID" + roomId);
                break;

            case 4:
                hotelManagement.showRoomsBooked();
                break;

            case 5:
                if(!hotelManagement.getCustomers().isEmpty()){
                    System.out.println("Enter name: ");
                    String findName = input.nextLine();

                    Customer getCustomer = hotelManagement.findCustomers(findName);
                    if (getCustomer != null)
                        getCustomer.showRooms();
                    else
                        System.out.println("No customer found with name " + findName);
                } else
                    System.out.println("No customer in the hotel.");
                break;

            case 6:
                System.out.println("Terminated successfully");
                System.exit(0);

            default:
                System.out.println("Invalid option entered");
        }
   }
}
