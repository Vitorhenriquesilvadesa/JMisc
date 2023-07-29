package cars;

import exceptions.UnavailableToLoanCarException;

public abstract class Car {

    private String brand;
    private String model;
    private String plate;
    private int year;
    private float locationValue;
    private float penaltyValue;
    private int freeRenewals;
    private boolean isAvailable;

    public Car(String model, String brand, String plate, int years,
            float locationValue, float penaltyValue) {

        this.model = model;
        this.brand = brand;
        this.plate = plate;
        this.year = years;
        this.locationValue = locationValue;
        this.penaltyValue = penaltyValue;

        // For default, the disponibility of car is true.
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setToNotAvailable() throws UnavailableToLoanCarException {
        if (this.isAvailable) {
            this.isAvailable = false;
        } else {
            // Exception lançada caso a classe Loan tente emprestar um carro já emprestado.
            throw new UnavailableToLoanCarException("This car is not available.");
        }
    }

    public void setToAvailable() {
        this.isAvailable = true;
    }

    public String getBrand() {
        return brand;
    }

    // public void setBrand(String brand) { Não é possivel mudar a marca do carro
    // this.brand = brand;
    // }

    public String getModel() {
        return model;
    }

    // public void setModel(String model) { Não é possivel mudar o modelo do carro
    // this.model = model;
    // }

    public String getPlate() {
        return plate;
    }

    // public void setPlate(String plate){ Não é possivel mudar a placa do carro
    // this.plate = plate;
    // }

    public int getYear() {
        return year;
    }

    // public void setYear(int year) { *Não deve ser possivel alterar o ano do
    // carro*
    // this.year = year;
    // }

    public float getLocationValue() {
        return locationValue;
    }

    public void setLocationValue(float locationValue) {
        this.locationValue = locationValue;
    }

    public float getPenaltyValue() {
        return penaltyValue;
    }

    public void setPenaltyValue(float penaltyValue) {
        this.penaltyValue = penaltyValue;
    }

    public int getFreeRenewals() {
        return freeRenewals;
    }

    public void setFreeRenewals(int freeRenewals) {
        this.freeRenewals = freeRenewals;
    }

    /**
     * @return Returns a Object array containing the values of the specific
     *         parameters
     *         of the car type.
     */

    // To facilitate access for array index, use the CarInfo enum, for example:
    // CarInfo.BRAND or CarInfo.PLATE that the method will return the corresponding
    // value.
    public abstract Object[] toObjectArray();

    @Override
    public abstract String toString();
}