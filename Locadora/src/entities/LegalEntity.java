package entities;

import factoryInterface.CreateInstance;

public class LegalEntity extends GenericEntity implements CreateInstance {

    private NaturalPerson representant;
    private String cnpj;

    private LegalEntity(String name, String address, String phone, String cnpj, NaturalPerson representant,
            int maxLoanCount) {
        super(name, address, phone, maxLoanCount);
        this.cnpj = cnpj;
        this.representant = representant;
    }

    protected static LegalEntity instantiate(String name, String address, String phone, String cnpj,
            NaturalPerson representant,
            int maxLoanCount) {

        return new LegalEntity(name, address, phone, cnpj, representant, maxLoanCount);
    }

    public NaturalPerson getRepresentant() {
        return representant;
    }

    public void setRepresentant(NaturalPerson representant) {
        this.representant = representant;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String toString() {
        return "Legal Entity: " + super.getName() + ", CNPJ: " + this.cnpj + ", address: " + super.getAddress()
                + ", phone: "
                + super.getPhone();
    }

    /**
     * @see #To facilitate the access of index in array, use the EntityInfo class.
     * @return String[]
     */
    public Object[] toObjectArray() {

        return new String[] { super.getName(), this.cnpj, this.getAddress(), super.getPhone(),
                this.representant.toString() };
    }
}
