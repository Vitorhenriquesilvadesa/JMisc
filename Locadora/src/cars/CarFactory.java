package cars;

import enums.CarTypes;
import exceptions.InvalidCarTypeException;

public final class CarFactory {

    public static final Car createCar(int type, String model, String brand, String plate, int year,
            float locationValue, float penaltyValue, boolean haveAir, boolean haveTrunk, int tractionType,
            int fuelType, int airBagCount, float trunkSize, boolean integratedGPS) {

        if (type == CarTypes.POPULAR)
            return PopularCar.instantiate(model, brand, plate, year, locationValue, penaltyValue,
                    haveAir);

        else if (type == CarTypes.SUV) {
            return SUVCar.instantiate(model, brand, plate, year, locationValue, penaltyValue,
                    haveTrunk,
                    tractionType, fuelType);
        }

        else if (type == CarTypes.LUXE) {
            return LuxeCar.instantiate(model, brand, plate, year, locationValue, penaltyValue,
                    airBagCount,
                    trunkSize, integratedGPS);
        }

        else {
            throw new InvalidCarTypeException("Car type " + "\"" + type + "\"" + " is invalid");
        }
    }

    public static final Car createCar(int type, String model, String brand, String plate, int year, int freeRenewals,
            float locationValue, float penaltyValue, boolean haveAir) {

        if (type == CarTypes.POPULAR)
            return PopularCar.instantiate(model, brand, plate, year, locationValue, penaltyValue,
                    haveAir);

        else {
            throw new InvalidCarTypeException("Car type " + "\"" + type + "\"" + " is invalid");
        }
    }

    public static final Car createCar(int type, String model, String brand, String plate, int year, int freeRenewals,
            float locationValue, float penaltyValue, boolean haveTrunk, int tractionType,
            int fuelType) {

        if (type == CarTypes.SUV) {
            return SUVCar.instantiate(model, brand, plate, year, locationValue, penaltyValue, haveTrunk,
                    tractionType, fuelType);
        }

        else {
            throw new InvalidCarTypeException("Car type " + "\"" + type + "\"" + " is invalid");
        }
    }

    public static final Car createCar(int type, String model, String brand, String plate, int year, int freeRenewals,
            float locationValue, float penaltyValue, int airBagCount, float trunkSize, boolean integratedGPS) {

        if (type == CarTypes.LUXE) {
            return LuxeCar.instantiate(model, brand, plate, year, locationValue, penaltyValue,
                    airBagCount,
                    trunkSize, integratedGPS);
        }

        else {
            throw new InvalidCarTypeException("Car type " + "\"" + type + "\"" + " is invalid");
        }
    }
}
