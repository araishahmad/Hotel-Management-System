public enum HotelCriteria {
    ECONOMY(40),
    LUXURY(110);

    private int billPerNight;

    private HotelCriteria(int billPerNight){
        this.billPerNight = billPerNight;
    }

    public int getBillPerNight(){
        return billPerNight;
    }
}