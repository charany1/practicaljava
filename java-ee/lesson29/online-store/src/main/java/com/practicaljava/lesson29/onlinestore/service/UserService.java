package com.practicaljava.lesson29.onlinestore.service;

import com.practicaljava.lesson29.onlinestore.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "online-shop")
    private EntityManager entityManager;

    public void addUser(User user) {
        entityManager.persist(user);
    }
    
    public User userById(String id) {
        try {
            return (User) entityManager.createNamedQuery("User.byId").
                setParameter("id", id).
                getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }
}