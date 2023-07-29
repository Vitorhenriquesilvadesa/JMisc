package entities;

import java.util.ArrayList;
import mainSystem.*;

import exceptions.PermissionToLoanDeniedException;

public abstract class GenericEntity {
    private String name;
    private String address;
    private String phone;
    private int loanCount;
    private int maxLoanCount;
    private ArrayList<Loan> loans;

    public GenericEntity(String name, String address, String phone, int maxLoanCount) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.loanCount = 0;
        this.maxLoanCount = maxLoanCount;
        this.loans = new ArrayList<>();
    }

    public boolean canToBorrow() {
        if (this.maxLoanCount > 0) {
            if (this.loanCount < this.maxLoanCount) {
                return true;
            } else {
                throw new PermissionToLoanDeniedException("You have exceeded the maximum amount of loan.");
            }

        } else {

            return true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLoanCount() {
        return loanCount;
    }

    // O metodo addLoanCount permite
    // adicionar ou remover apenas 1 emprestimo por vez
    // public void setLoanCount(int loanCount) {
    // this.loanCount = loanCount;
    // }

    private void addLoanCount(int value) {
        if (Math.abs(value) != 1) {
            throw new IllegalArgumentException("Value should incremented or decremented by 1");
        } else {
            this.loanCount += value;
        }
    }

    public int getMaxLoanCount() {
        return maxLoanCount;
    }

    public void setMaxLoanCount(int maxLoanCount) {
        this.maxLoanCount = maxLoanCount;
    }

    public ArrayList<Loan> getLoans() {
        return this.loans;
    }

    public void addLoan(Loan loan) {
        this.loans.add(loan);
        addLoanCount(1);
    }

    public void removeLoan(Loan loan) {
        this.loans.remove(loan);
        addLoanCount(-1);
    }

    @Override
    public abstract String toString();

    public abstract Object[] toObjectArray();
}
