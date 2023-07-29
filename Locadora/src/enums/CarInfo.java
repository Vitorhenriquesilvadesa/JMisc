package enums;

public abstract class CarInfo {
    public static final int MODEL = 0;
    public static final int BRAND = 1;
    public static final int PLATE = 2;
    public static final int YEAR = 3;
    public static final int FREE_RENEWALS = 4;
    public static final int LOCATION_VALUE = 5;
    public static final int PENALTY_VALUE = 6;

    public final class Popular extends CarInfo {
        public static final int HAVE_AIR = 7;
        public static final int FREE_RENEWALS_COUNT = 1;
    }

    public final class SUV extends CarInfo {
        public static final int HAVE_TRUNK = 7;
        public static final int TRACTION_TYPE = 8;
        public static final int FUEL_TYPE = 9;
        public static final int FREE_RENEWALS_COUNT = 3;
    }

    public final class Luxe extends CarInfo {
        public static final int AIRBAG_COUNT = 7;
        public static final int TRUNK_SIZE = 8;
        public static final int INTEGRATED_GPS = 9;
        public static final int FREE_RENEWALS_COUNT = 5;
    }
}