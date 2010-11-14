package com.practicaljava.onlinestore.service;

import com.practicaljava.onlinestore.model.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ItemService {

    @PersistenceContext(unitName = "online-shop")
    private EntityManager entityManager;

    public void addItem(Item item) {
        entityManager.persist(item);
    }

    public void updateItem(Item item) {
        entityManager.merge(item);
    }

    public Item itemByCode(Long productCode) {
        try {
            return (Item) entityManager.createNamedQuery("Item.byCode").
                setParameter("productCode", productCode).
                getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Item> listItems() {
        return entityManager.createNamedQuery("Item.all").getResultList();
    }
}
