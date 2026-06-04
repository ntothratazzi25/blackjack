package com.project.model;

public class Card 
{
    private String suit; 
    private String rank; 
    private int value;

    public Card(String suit, String rank)
    {
        this.suit = suit;
        this.rank = rank;
        this.value = switch(rank)
        {
            case "J":
            case "Q":
            case "K":
                yield 10;
            case "A":
                yield 11;
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
}
