package cars;

import enums.CarInfo;

public class LuxeCar extends Car {

    int airBagCount;
    float trunkSize;
    boolean integratedGPS;

    private LuxeCar(String model, String brand, String plate, int years,
            float locationValue, float penaltyValue, int airBagCount, float trunkSize, boolean integratedGPS) {

        super(model, brand, plate, years, locationValue, penaltyValue);

        this.airBagCount = airBagCount;
        this.trunkSize = trunkSize;
        this.integratedGPS = integratedGPS;
        super.setFreeRenewals(CarInfo.Luxe.FREE_RENEWALS_COUNT);
    }

    protected static LuxeCar instantiate(String model, String brand, String plate, int years,
            float locationValue, float penaltyValue, int airBagCount, float trunkSize, boolean integratedGPS) {

        return new LuxeCar(model, brand, plate, years, locationValue, penaltyValue, airBagCount,
                trunkSize, integratedGPS);
    }

    public String toString() {

        return super.getModel() + " SUV car of " + this.getBrand() + ", plate " + this.getPlate() + ", year "
                + this.getYear() + ", air bag count: " + this.airBagCount + ", trunk size: " + this.trunkSize
                + ", integrated GPS:" + this.integratedGPS;
    }

    public Object[] toObjectArray() {

        return new Object[] { this.getModel(), this.getBrand(), this.getPlate(), this.getYear(),
                this.getFreeRenewals(), this.getLocationValue(), this.getPenaltyValue(),
                this.airBagCount, this.trunkSize, this.integratedGPS };
    }
}
