package com.example.coffeeshoprestapi.services;

import com.example.coffeeshoprestapi.models.Coffee;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;

@Stateless
public class CoffeeService {

    @PersistenceContext(unitName = "CoffeePU")
    private EntityManager em;

    public List<Coffee> getAllCoffees() {
        return em.createQuery("SELECT c FROM Coffee c", Coffee.class).getResultList();
    }

    public Coffee update(int id, Coffee updatedCoffee) {
        Coffee existing = em.find(Coffee.class, id);
        if (existing == null) {
            return null;
        }
        existing.setName(updatedCoffee.getName());
        existing.setPrice(updatedCoffee.getPrice());    // Update other fields if any
        return existing;
        }

        public Coffee add(Coffee coffee) {
        em.persist(coffee);
        return coffee;}

    public boolean delete(int id) {
        Coffee c = em.find(Coffee.class, id);
        if (c != null)
        {
            em.remove(c);
            return true;
        }
        return false;
    }
    public Coffee getById(int id) {
        return em.find(Coffee.class, id);
    }
}