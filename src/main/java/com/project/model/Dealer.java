package com.project.model;

public class Dealer 
{
    private Hand hand;
    private Card holeCard;

    public Dealer()
    {
        this.hand = new Hand();
    }

    public void hit(Deck deck)
    {
        hand.addCard(deck.deal());
    }
    
    public void flipHoleCard()
    {
        hand.addCard(holeCard);
    }

    public void playTurn(Deck deck)
    {
        while (hand.calculateTotal() < 17) 
        {
            hit(deck);
        }
    }

    public void dealCard(Hand hand, Deck deck)
    {
        hand.addCard(deck.deal());
    }
}
