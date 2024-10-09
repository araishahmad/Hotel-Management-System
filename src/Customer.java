import java.util.ArrayList;

public class Customer extends User{
    private ArrayList <Room> bookedRoom;
    private Room room;

    public Customer(String name, String CNIC, int age){
        super(name, CNIC, age);
        this.bookedRoom = new ArrayList<>();
    }

    public void bookRoom(Room room){
        if (room.getAvailable()) {
            room.setAvailable(false);
            room.setBookedBy(getName());
            bookedRoom.add(room);
            System.out.println("Room " + room.getRoomID() + " booked by " + room.getBookedBy());
        }
        else
            System.out.println("Room " + room.getRoomID() + " is already booked by " + room.getBookedBy());
    }

    public void checkOut(Room room){
        if (bookedRoom.contains(room)){
            System.out.println("Room " +room.getRoomID() + " checked out by " + room.getBookedBy());
            bookedRoom.remove(room);
            room.setAvailable(true);
        }
        else
            System.out.println("No booking found for the room " + room.getRoomType() + " by " + room.getBookedBy());
    }

    public void showRooms(){
        if (bookedRoom.isEmpty())
            System.out.println("No rooms booked by " + getName());
        else{
            for (Room room : bookedRoom) {
                System.out.println("Room ID: " + room.getRoomID() +
                        ", Type: " + room.getRoomType() +
                        ", Booked by: " + room.getBookedBy());
            }
        }
    }
}
