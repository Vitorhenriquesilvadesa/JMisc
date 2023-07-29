package entities;

import factoryInterface.CreateInstance;

public class NaturalPerson extends GenericEntity implements CreateInstance {

    private String cpf;

    private NaturalPerson(String name, String address, String phone, String cpf, int maxLoanCount) {
        super(name, address, phone, maxLoanCount);
        this.cpf = cpf;
    }

    protected static NaturalPerson instantiate(String name, String address, String phone, String cpf,
            int maxLoanCount) {

        return new NaturalPerson(name, address, phone, cpf, maxLoanCount);
    }

    public String getCpf() {
        return cpf;
    }

    public String toString() {
        return "Natural Person: " + super.getName() + ", CPF: " + this.cpf + ", address: " + super.getAddress()
                + ", phone: "
                + super.getPhone();
    }

    /**
     * @see #To facilitate the access of index in array, use the EntityInfo class.
     * @return String[]
     */
    public Object[] toObjectArray() {

        return new String[] { super.getName(), super.getName(), super.getName(), super.getName() };
    }
    // Fazer Getters e Setters
}
