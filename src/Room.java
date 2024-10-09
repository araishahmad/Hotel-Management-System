public class Room {
    private String roomID;
    private String roomType;
    private String bookedBy;
    private double billPerNight;
    private boolean isAvailable;

    public Room(String roomID, String roomType){
        setRoomID(roomID);
        setRoomType(roomType);
        this.isAvailable = true;

        if (roomType.equalsIgnoreCase(HotelCriteria.ECONOMY.name()))
            this.billPerNight = HotelCriteria.ECONOMY.getBillPerNight();
        else if (roomType.equalsIgnoreCase(HotelCriteria.LUXURY.name()))
            this.billPerNight = HotelCriteria.LUXURY.getBillPerNight();
    }

    public String getRoomID() {
        return this.roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getBookedBy() {
        return this.bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public double getBillPerNight() {
        return this.billPerNight;
    }

    public void setBillPerNight(double billPerNight) {
        this.billPerNight = billPerNight;
    }

    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public boolean getAvailable(){
        return this.isAvailable;
    }

    public double calculateBill (int nights){
        return nights * billPerNight;
    }
}
