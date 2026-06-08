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

    public Card getCard(int index)
    {
        return currentHand.get(index);
    }

    public String getSoftTotal()
    {
        int hardTotal = 0;
        int softTotal = 0;
        boolean hasAce = false;

        for (Card card : currentHand) // loop through each card in hand  
        {
            if (card.getRank().equals("A")) // if one of the cards in the hand is an ace
            {
                hardTotal += 1; // hardTotal = hardTotal + 1
                softTotal += 11; // softTotal = softTotal + 11
                hasAce = true; // set to true
            }
            else
            {
                hardTotal += card.getValue();
                softTotal += card.getValue();
            }
        }

        if (isBlackjack()) // check to see if hand is blackjack before executing ace logic
        {
            return "21";
        }
        
        if (hasAce && softTotal <= 21) // ace exists in hand the ace (11) and the other card is less than 21
        {
            return hardTotal + "/" + softTotal;
        }
        else
        {
            return String.valueOf(hardTotal); // if not return normal value of cards
        }
    }

    public int calculateTotal() // calculates the current hand total
    {
        totalValue = 0;
        int aces = 0;

        for (Card card : currentHand) 
        {
            totalValue += card.getValue();
            
            if (card.getRank().equals("A"))
            {
                aces++;
            }
        }
        
        while (totalValue > 21 && aces > 0)
        {
            totalValue -= 10;
            aces--;
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
