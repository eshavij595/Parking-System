package Data;

public class ParkingSpace {
    public int parkingSpaceID;
    public int parkingLotID;
    public int location;
    public boolean status;
    public String vehicle;
    
    public ParkingSpace(int parkingSpaceID, int parkingLotID, int location, boolean status, String vehicle) {
        this.parkingSpaceID = parkingSpaceID;
        this.parkingLotID = parkingLotID;
        this.location = location;
        this.status = status;
        this.vehicle = vehicle;
    }
}
