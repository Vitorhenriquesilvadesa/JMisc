package mainSystem;

import entities.*;
import cars.Car;

import java.util.ArrayList;

public class CarLoanAgency {

    private ArrayList<Loan> loans;
    private ArrayList<Car> cars;
    private ArrayList<GenericEntity> clients;

    // A classe GenericEntity, nesse codigo são os usuários clientes da locadora
    // verifica se o cliente pode pegar um carro, independente do tipo do cliente
    // (Natural person, Legal entity ou qualquer outro que precise ser adicionado
    // futuramente).

    public CarLoanAgency() {
        loans = new ArrayList<>();
        clients = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void makeLoan(GenericEntity client, Car car) {
        if (client.canToBorrow()) {
            car.setToNotAvailable();
        }

        Loan newLoan = new Loan(client, car);
        client.addLoan(newLoan);

        loans.add(new Loan(client, car));
    }

    public void addClient(GenericEntity client) {
        this.clients.add(client);
    }

    public void removeClient(GenericEntity client) {
        this.clients.remove(client);
    }

    public ArrayList<GenericEntity> getClients() {
        return this.clients;
    }

    public ArrayList<Loan> getLoans() {
        return this.loans;
    }

    public ArrayList<Car> getCars() {
        return this.cars;
    }
}
