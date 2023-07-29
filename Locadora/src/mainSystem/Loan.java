package mainSystem;

import entities.*;
import cars.*;

public class Loan {

    private Car car;
    private GenericEntity client;
    private int renewalsCount;
    private float penalty;

    public Loan(GenericEntity client, Car car) {
        this.client = client;
        this.car = car;
        this.renewalsCount = 0;
        this.penalty = 0;
    }

    public void renew() {
        this.renewalsCount++;
    }

    public void giveBack() {
        penalty();
        this.car.setToAvailable();
        this.client.removeLoan(this);
    }

    public void penalty() {
        this.penalty = this.car.getPenaltyValue() * exceededRenewAmount();
    }

    public int exceededRenewAmount() {
        return hasPenalty() ? this.renewalsCount - this.car.getFreeRenewals() : 0;
    }

    public boolean hasPenalty() {
        return this.renewalsCount > this.car.getFreeRenewals();
    }

    public Car getCar() {
        return car;
    }

    public GenericEntity getClient() {
        return client;
    }

    public int getRenewalsCount() {
        return renewalsCount;
    }

    public float getPenalty() {
        return penalty;
    }
}
