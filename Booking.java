package Data;

public class Booking {
    public int bookingID;
    public int startTime;
    public int endTime;
    public int userID;
    public int parkingSpaceID;
    public boolean status;

    public Booking(int startTime, int endTime, int userID, int parkingSpaceID, boolean status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.userID = userID;
        this.parkingSpaceID = parkingSpaceID;
        this.status = status;
    }
}
