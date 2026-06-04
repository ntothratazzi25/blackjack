package com.project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck 
{
    private List<Card> cards;

    public Deck()
    {
        this.cards = new ArrayList<>();
        
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits)
        {
            for (String rank : ranks)
            {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public Card deal()
    {
        if (cards.isEmpty()) 
        {
            return null;
        }
        return cards.remove(0);
    }
}
