package Data;

import java.util.List;

import Data.User.UserType;

public class DataFactory {
    public static <T> T make(Class<T> returnType, List<String> args) {
        if (returnType.equals(Booking.class)) {
            return returnType.cast(new Booking(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)), Integer.parseInt(args.get(2)), Integer.parseInt(args.get(3)), Boolean.parseBoolean(args.get(4))));
        }
        if (returnType.equals(ParkingLot.class)) {
            return returnType.cast(new ParkingLot(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)), Boolean.parseBoolean(args.get(2))));
        }
        if (returnType.equals(ParkingSpace.class)) {
            return returnType.cast(new ParkingSpace(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)), Integer.parseInt(args.get(2)), Boolean.parseBoolean(args.get(3)), args.get(4)));
        }
        if (returnType.equals(User.class)) {
            return returnType.cast(new User(Integer.parseInt(args.get(0)), args.get(1), args.get(2), args.get(3), args.get(4), UserType.valueOf(args.get(5))));
        }
        return null;
    }
}
