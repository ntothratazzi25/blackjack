package com.project.model;

import java.util.ArrayList;
import java.util.List;

public class Hand 
{
    private int totalValue;
    private List<Card> currentHand;

    public Hand() 
    { 
        this.currentHand = new ArrayList<>();
        this.totalValue = 0;
    }

    public void addCard(Card card)
    {
        currentHand.add(card);
        totalValue = calculateTotal();
    }

    public int calculateTotal() // calculates the current hand total
    {
        totalValue = 0;

        for (Card card : currentHand) 
        {
            totalValue += card.getValue();
        }
        return totalValue;
    }

    public boolean isBust()
    {
        return totalValue > 21;
    }

    public boolean isBlackjack()
    {
        return currentHand.size() == 2 && totalValue == 21;
    }
}
