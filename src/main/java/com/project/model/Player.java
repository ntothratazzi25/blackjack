package com.project.model;

public class Player 
{
    private double balance;
    private Hand hand;
    private Bet bet;

    public Player(double balance)
    {
        this.balance = balance;
        this.hand = new Hand();
        this.bet = null;
    }

    public void hit(Deck deck)
    {
        hand.addCard(deck.deal());
    }

    public void doubleDown(Deck deck)
    {
        double originalBet = bet.getAmount();
        this.bet = new Bet(originalBet * 2);
        this.balance -= originalBet;
        hand.addCard(deck.deal());
    }

    public void placeBet(double amount)
    {
        this.bet = new Bet(amount);
        this.balance -= amount;
    }
}
