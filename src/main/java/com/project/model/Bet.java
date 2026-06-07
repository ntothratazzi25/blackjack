package com.project.model;

public class Bet 
{
    private double amount;
    private String status;

    public Bet(double amount)
    {
        this.amount = amount;
        this.status = null;
    }

    public double getAmount()
    {
        return amount;
    }

    public String setStatus(String status) // setter for bet status 
    {
        this.status = status;
        return status;
    }
}
