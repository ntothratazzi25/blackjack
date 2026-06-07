package com.project.model;


/** represents the player in the game
 *  has a balance, hand, and bet, and methods for hitting, doubling down, placing bets, and adding to balance.
 *  
 * @author Nick Toth-Ratazzi
**/


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

    public Hand getHand()
    {
        return hand;
    }

    public double getBalance()
    {
        return balance;
    }

    public Bet getBet()
    {
        return bet;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public void hit(Deck deck)
    {
        hand.addCard(deck.deal());
    }

    public void doubleDown(Deck deck) // player doubles bet and gets one card 
    {
        double originalBet = bet.getAmount(); // og bet 
        this.bet = new Bet(originalBet * 2); // create new bet that's double the og bet 
        this.balance -= originalBet; 
        hand.addCard(deck.deal()); // add one card to hand from dceck
    }

    public void placeBet(double amount)
    {
        this.bet = new Bet(amount); // create bet
        this.balance -= amount; // subtract bet amount from balance
    }

    public void addToBalance(double amount)
    {
        this.balance += amount; // add amount to balance
    }
}
