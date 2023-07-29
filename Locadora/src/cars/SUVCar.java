package cars;

import enums.CarInfo;
import enums.FuelType;
import enums.TractionType;
import factoryInterface.CreateInstance;

public class SUVCar extends Car implements CreateInstance {

    boolean haveTrunk;
    int tractionType;
    int fuelType;

    private SUVCar(String model, String brand, String plate, int years,
            float locationValue, float penaltyValue, boolean haveTrunk, int tractionType, int fuelType) {

        super(model, brand, plate, years, locationValue, penaltyValue);
        this.haveTrunk = haveTrunk;
        this.fuelType = fuelType;
        super.setFreeRenewals(CarInfo.SUV.FREE_RENEWALS_COUNT);
    }

    protected static SUVCar instantiate(String model, String brand, String plate, int years,
            float locationValue, float penaltyValue, boolean haveTrunk, int tractionType, int fuelType) {

        return new SUVCar(model, brand, plate, years, locationValue, penaltyValue, haveTrunk,
                tractionType, fuelType);
    }

    public String toString() {
        String haveTrunkString;
        String tractionTypeString;

        if (haveTrunk)
            haveTrunkString = "yes";
        else
            haveTrunkString = "no";

        switch (tractionType) {
            case TractionType.FRONT_WHEEL_DRIVE:
                tractionTypeString = "front wheel drive";
                break;

            case TractionType.REAR_WHEEL_DRIVE:
                tractionTypeString = "rear wheel drive";
                break;

            case TractionType.ALL_WHEEL_DRIVE:
                tractionTypeString = "all wheel drive";
                break;

            default:
                tractionTypeString = null;
        }

        return super.getModel() + " SUV car of " + this.getBrand() + ", plate " + this.getPlate() + ", year "
                + this.getYear() + ", haveTrunk: " + haveTrunkString + ", traction type: " + tractionTypeString
                + ", fuel type:" + FuelType.FuelTypeStrings[this.getFuelType()];
    }

    public Object[] toObjectArray() {

        return new Object[] { this.getModel(), this.getBrand(), this.getPlate(), this.getYear(),
                this.getFreeRenewals(), this.getLocationValue(), this.getPenaltyValue(),
                FuelType.FuelTypeStrings[this.getFuelType()] };
    }

    public boolean isHaveTrunk() {
        return haveTrunk;
    }

    public int getTractionType() {
        return tractionType;
    }

    public int getFuelType() {
        return fuelType;
    }
}
