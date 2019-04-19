package ru.karelin.tmserver.util;

import org.apache.deltaspike.jpa.api.entitymanager.PersistenceUnitName;
import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ApplicationScoped
public class EntityManagerProducer {

    @Inject
    @PersistenceUnitName("ENTERPRISE")
    @NotNull
    private EntityManagerFactory emf;



    @NotNull
    @Produces
    @TransactionScoped
    public EntityManager create() {
        return emf.createEntityManager();
    }

    public void dispose(@NotNull @Disposes EntityManager entityManager) {
        if(entityManager.isOpen()) entityManager.close();
    }
}
