import java.util.Scanner;

public class UserInputs {
    static Scanner input = new Scanner(System.in);
    static HotelManagement hotelManagement = new HotelManagement();  // Hotel management instance


    public static Customer getCustomerDetails() {
        String name = InputValidations.validString(input, "Enter your name: ");

        System.out.println("Enter your CNIC: ");
        String CNIC = input.nextLine();
        while (!InputValidations.isValidCNIC(CNIC)) {
            System.out.println("Invalid CNIC, Enter a valid CNIC: ");
            CNIC = input.nextLine();
        }

        int age = InputValidations.validInt(input, "Enter your age: ");
        return new Customer(name, CNIC, age);
    }

    public static Room getRoomDetails() {
        boolean validType = false;
        String roomType = "";
        while (!validType) {
            for (HotelCriteria hotelCriteria : HotelCriteria.values())
                System.out.println(" - " + hotelCriteria);

            roomType = InputValidations.validString(input, "Enter room type: ");

            try {
                HotelCriteria.valueOf(roomType.toUpperCase());
                validType = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid room type. Please enter a valid room type.");
            }
        }

        String roomID = InputValidations.validString(input, "Enter room ID: ");
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
                String name = InputValidations.validString(input, "Enter name: ");
                Customer checkOutCustomer = hotelManagement.findCustomers(name);
                if (checkOutCustomer == null){
                    System.out.println("No customer of name: " + name);
                    break;
                }

                String roomID = InputValidations.validString(input, "Enter Room ID: ");

                Room checkOutRoom = hotelManagement.getRoomByID(roomID);
                if (checkOutRoom != null && checkOutCustomer.getBookedRoom().contains(checkOutRoom)){
                    int nights = InputValidations.validInt(input, "Enter nights spent: ");
                    double totalBill = checkOutRoom.calculateBill(nights);
                    System.out.println("Total bill of customer: $" + totalBill);

                    checkOutCustomer.checkOut(checkOutRoom);
                    hotelManagement.removeRoom(roomID);
                }
                else
                    System.out.println("Room with ID" + roomID + " not found");
                break;

            case 3:
                int nights = InputValidations.validInt(input, "Enter nights spent: ");
                input.nextLine();

                String roomId = InputValidations.validString(input, "Enter room ID: ");
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
                    String findName = InputValidations.validString(input, "Enter name: ");

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
