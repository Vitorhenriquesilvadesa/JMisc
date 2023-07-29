package entities;

import enums.EntityType;
import exceptions.InvalidEntityException;

public final class EntityFactory {

    /**
     * Method to create a new entity, to facilitate the creation, use the EntityType
     * class to get the entity type.
     * 
     * @param entityType
     * @param name
     * @param address
     * @param phone
     * @param CPF
     * @return Natural Person
     */

    public static GenericEntity createEntity(int entityType, int maxLoanCount, String name, String address,
            String phone, String CPF) {

        switch (entityType) {

            case EntityType.NATURAL_PERSON:
                return NaturalPerson.instantiate(name, address, phone, CPF, maxLoanCount);

            default:
                throw new InvalidEntityException("Entity type" + entityType + " is invalid");
        }
    }

    /**
     * Method to create a new entity, to facilitate the creation, use the EntityType
     * class to get the entity type.
     * 
     * @param entityType
     * @param name
     * @param address
     * @param phone
     * @param CNPJ
     * @param representant
     * @return Legal Entity
     */
    public static GenericEntity createEntity(int entityType, int maxLoanCount, String name, String address,
            String phone,
            String CNPJ,
            NaturalPerson representant) {

        switch (entityType) {

            case EntityType.LEGAL_ENTITY:
                return LegalEntity.instantiate(name, address, phone, CNPJ, representant, maxLoanCount);

            default:
                throw new InvalidEntityException("Entity type" + entityType + " is invalid");
        }
    }
}
