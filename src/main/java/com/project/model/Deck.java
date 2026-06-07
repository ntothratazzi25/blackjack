package com.project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/** represents a standard deck of 52 playing cards
 *  has methods for shuffling and dealing cards.
 *  
 * @author Nick Toth-Ratazzi
 **/


public class Deck 
{
    private List<Card> cards;

    public Deck()
    {
        this.cards = new ArrayList<>();
        
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"}; // possible suits
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}; // possible ranks

        for (String suit : suits) 
        {
            for (String rank : ranks)
            {
                cards.add(new Card(suit, rank)); // assign a rank and suit to each card in the deck
            }
        }
    }

    public void shuffle()
    {
        Collections.shuffle(cards); // shuffle list 
    }

    public Card deal()
    {
        if (cards.isEmpty()) 
        {
            return null;
        }
        return cards.remove(0); // deal from top of list (a.k.a. deck)
    }
}
