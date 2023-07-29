package cars;

import enums.CarInfo;
import factoryInterface.CreateInstance;

public class PopularCar extends Car implements CreateInstance {

    private boolean haveAir;

    private PopularCar(String model, String brand, String plate, int years, int freeRenewals,
            float locationValue, float penaltyValue, boolean haveAir) {

        super(model, brand, plate, years, locationValue, penaltyValue);
        this.haveAir = haveAir;
        super.setFreeRenewals(CarInfo.Popular.FREE_RENEWALS_COUNT);
    }

    protected static PopularCar instantiate(String model, String brand, String plate, int years,
            float locationValue, float penaltyValue, boolean haveAir) {

        return new PopularCar(model, brand, plate, years, 1, locationValue, penaltyValue, haveAir);
    }

    public String toString() {

        return super.getModel() + " popular car of " + this.getBrand() + ", plate " + this.getPlate() + ", year "
                + this.getYear();
    }

    public Object[] toObjectArray() {

        return new Object[] { this.getModel(), this.getBrand(), this.getPlate(), this.getYear(),
                this.getFreeRenewals(), this.getLocationValue(), this.getPenaltyValue(), this.isHaveAir() };
    }

    public boolean isHaveAir() {
        return haveAir;
    }

    public void setHaveAir(boolean haveAir) {
        this.haveAir = haveAir;
    }
}
