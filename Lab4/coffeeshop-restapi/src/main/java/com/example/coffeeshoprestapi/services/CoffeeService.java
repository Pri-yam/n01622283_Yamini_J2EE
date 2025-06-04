package com.example.coffeeshoprestapi.services;

import com.example.coffeeshoprestapi.models.Coffee;
import jakarta.ejb.Stateless;
import java.util.*;

@Stateless
public class CoffeeService {

    private static Map<Integer, Coffee> coffeeMap = new HashMap<>();
    private static int currentId = 1;

    public List<Coffee> getAll() {
        return new ArrayList<>(coffeeMap.values());
    }

    public Coffee getById(int id) {
        return coffeeMap.get(id);
    }

    public Coffee create(Coffee coffee) {
        coffee.setId(currentId++);
        coffeeMap.put(coffee.getId(), coffee);
        return coffee;
    }

    public Coffee update(int id, Coffee coffee) {
        if (!coffeeMap.containsKey(id)) return null;
        coffee.setId(id);
        coffeeMap.put(id, coffee);
        return coffee;
    }

    public boolean delete(int id) {
        return coffeeMap.remove(id) != null;
    }
}

