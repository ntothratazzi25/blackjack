package com.project.model;


/** represents a playing card
 *  has a suit, rank, and value
 *  
 * @author Nick Toth-Ratazzi
**/


public class Card 
{
    private String suit; 
    private String rank; 
    private int value;

    public Card(String suit, String rank)
    {
        this.suit = suit;
        this.rank = rank;
        this.value = switch(rank) // handle face card values
        {
            case "J": // Jack
            case "Q": // Queen
            case "K": // King
                yield 10; // all = value of 10
            case "A": // Ace
                 yield 11; // value of 11   *** NOTE: Need to make a case for handling soft ace (1/11) ***
            default:
                yield Integer.parseInt(rank);
        };
    }

    public String getSuit() 
    {
        return suit;
    }

    public String getRank() 
    {
        return rank;
    }

    public int getValue() 
    {
        return value;
    }

    @Override
    public String toString()
    {
        return rank + " of " + suit;
    }
}
