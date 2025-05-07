package com.example.ejb;

import jakarta.ejb.Singleton;

@Singleton
public class VisitCounterBean {

    private int count = 0;

    public int incrementAndGet() {
        return ++count;
    }

    public int getCount() {
        return count;
    }
}