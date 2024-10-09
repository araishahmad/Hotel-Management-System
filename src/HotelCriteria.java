public enum HotelCriteria {
    ECONOMY(120),
    LUXURY(170);

    private int billPerNight;

    private HotelCriteria(int billPerNight){
        this.billPerNight = billPerNight;
    }

    public int getBillPerNight(){
        return billPerNight;
    }
}