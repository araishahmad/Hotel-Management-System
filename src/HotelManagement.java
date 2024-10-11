import java.util.ArrayList;

public class HotelManagement {
    private ArrayList <Customer> customers;
    private ArrayList <Room> rooms;

    public HotelManagement(){
        this.customers = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void removeCustomer(Customer customer){
        boolean hasCheckedOut = true;
        for (Room room : rooms){
            if (room.getBookedBy() != null && room.getBookedBy().equalsIgnoreCase
                    (customer.getName()) && !room.getAvailable()){
                hasCheckedOut = false;
            }
        }

        if (hasCheckedOut){
            customers.remove(customer);
            System.out.println("Customer " + customer.getName() + " has been removed.");
        }
        else
            System.out.println("Customer " + customer.getName() + "has not checked out yet");
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public void removeRoom(String roomID){
        Room removeRoom = null;
        for (Room room : rooms){
            if (room.getRoomID().equalsIgnoreCase(roomID)){
                removeRoom = room;
                break;
            }
        }

        if (removeRoom != null)
            rooms.remove(removeRoom);
        else
            System.out.println("No room found of ID" + roomID);
    }

    public Room getRoomByID(String roomID){
        for (Room room : rooms){
            if (room.getRoomID().equalsIgnoreCase(roomID))
                return room;
        }
        return null;
    }

    public void showRoomsBooked() {
        boolean anyRoomBooked = false;

        for (Room room : rooms) {
            if (!room.getAvailable()) {
                System.out.println("Room ID: " + room.getRoomID() +
                        ", Type: " + room.getRoomType() +
                        ", Booked by: " + room.getBookedBy());
                anyRoomBooked = true;
            }
        }

        if (!anyRoomBooked) {
            System.out.println("No rooms are currently booked.");
        }
    }

    public ArrayList <Customer> getCustomers(){
        return customers;
    }

    public Customer findCustomers(String name){
        for (Customer customer : customers){
            if (customer.getName().equalsIgnoreCase(name))
                return customer;
        }
        return null;
    }
}