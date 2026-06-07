package com.project.model;

import java.util.ArrayList;
import java.util.List;


/** represents a player and dealer's hand in the game
 *  has a list of cards and methods for adding cards, calculating total value, and checking for bust or blackjack.
 *  
 * @author Nick Toth-Ratazzi
**/


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

    public boolean isBust() // is bust if totalValue is greater than 21 
    {
        return totalValue > 21;
    }

    public boolean isBlackjack() // is blackjack if the first 2 cards add up to 21 
    {
        return currentHand.size() == 2 && totalValue == 21;
    }
}
