package Data;

public class ParkingLot {
    public int parkingLotID;
    public int parkingSpaceNum;
    public boolean status;

    public ParkingLot(int parkingLotID, int parkingSpaceNum, boolean status) {
        this.parkingLotID = parkingLotID;
        this.parkingSpaceNum = parkingSpaceNum;
        this.status = status;
    }
}
