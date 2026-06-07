package com.project.game;

import java.util.Scanner;

import com.project.model.Dealer;
import com.project.model.Deck;
import com.project.model.Player;
import com.project.state.GameState;
import com.project.state.PlayerTurnState;
import com.project.state.ResolvedState;


/** main class that handles and manages the game logic 
  * handles game states and player/dealer interactions. 
  * also contains a simple CLI for testing purposes, but this will be replaced with a UI in the future.
  * 
  * 
  * @author Nick Toth-Ratazzi
**/


public class Game 
{
    private Player player;
    private Dealer dealer;
    private Deck deck;

    private String status;
    private String outcome;
    private GameState currentState;

    public Game(double startingBalance)
    {
        this.player = new Player(startingBalance);
        this.dealer = new Dealer();
        this.deck = new Deck();
        this.deck.shuffle();
        this.status = "active"; // dead, active, resolved
        this.outcome = null;
        this.currentState = new PlayerTurnState();
    }

    public Player getPlayer()
    {
        return player;
    }

    public Dealer getDealer()
    {
        return dealer;
    }

    public Deck getDeck()
    {
        return deck;
    }

    public String getStatus()
    {
        return status;
    }

    public String getOutcome()
    {
        return outcome;
    }

    public void startGame()
    {
        dealer.dealCard(player.getHand(), deck); // first player card
        dealer.dealCard(dealer.getHand(), deck); // first dealer card
        dealer.dealCard(player.getHand(), deck); // second player card
        dealer.setHoleCard(deck.deal()); // dealer hole card
    }

    public void setState(GameState state)
    {
        this.currentState = state;
    }

    public void handleHit()
    {
        currentState.handleHit(this);
    }

    public void handleStand()
    {
        currentState.handleStand(this);
    }

    public void handleDoubleDown()
    {
        currentState.handleDoubleDown(this);
    }

    public void resolveOutcome()
    {
        int playerTotal = player.getHand().calculateTotal();  
        int dealerTotal = dealer.getHand().calculateTotal();
        boolean playerBust = player.getHand().isBust();
        boolean dealerBust = dealer.getHand().isBust();
        boolean playerBlackjack = player.getHand().isBlackjack();
        boolean dealerBlackjack = dealer.getHand().isBlackjack();

        if (playerBlackjack && !dealerBlackjack) // player gets blackjack and dealer does not, player wins
        {
            this.outcome = "player blackjack!";
            this.status = "resolved";
            player.addToBalance(player.getBet().getAmount() * 2.5); // give player winnings (3:2 winnings because of blackjack)
            setState(new ResolvedState());
        }
        
        else if (dealerBlackjack && !playerBlackjack)  // dealer gets blackjack and player does not, dealer wins 
        {
            this.outcome = "dealer blackjack";
            this.status = "resolved";
            setState(new ResolvedState());
        }
    
        else if (playerBust) // player bust, dealer wins 
        {
            this.outcome = "dealer wins";
            this.status = "resolved";
            setState(new ResolvedState());
        } 
        
        else if (dealerBust) // dealer bust, player wins 
        {
            this.outcome = "player wins";
            this.status = "resolved";
            player.addToBalance(player.getBet().getAmount() * 2); // give player winnings (original bet * 2)
            setState(new ResolvedState());
        }
    
        else if (playerTotal > dealerTotal) // player hand total is greater than dealer total 
        {
            this.outcome = "player wins";
            this.status = "resolved";
            player.addToBalance(player.getBet().getAmount() * 2); // give player winnings (original bet * 2)
            setState(new ResolvedState());
        } 
        
        else if (dealerTotal > playerTotal) // dealer hand total is greater than player total
        {
            this.outcome = "dealer wins";
            this.status = "resolved";
            setState(new ResolvedState());
        } 
        
        else // tie , a.k.a. push 
        {
            this.outcome = "push";
            this.status = "resolved";
            player.addToBalance(player.getBet().getAmount()); // give player bet back 
            setState(new ResolvedState());
        }
    }

    public static void main(String[] args) // for now, cli game
    {
        Scanner scanner = new Scanner(System.in);  
        Game game = new Game(1000.0); 
        game.startGame();
        System.out.println("Balance: " + game.getPlayer().getBalance());  // balance 
        System.out.print("Enter bet amount: ");
        double betAmount = scanner.nextDouble();
        scanner.nextLine();
        game.getPlayer().placeBet(betAmount);

        System.out.println("Player total: " + game.getPlayer().getHand().calculateTotal()); // deal player hand 
        System.out.println("Dealer showing: " + game.getDealer().getHand().calculateTotal()); // dealer hand (with hole card) 

        while (game.getStatus().equals("active"))  
        {
            System.out.print("Enter action (hit/stand/double): "); // player input
            String action = scanner.nextLine();

            switch (action) // switch function handling input
            {
                case "hit" -> game.handleHit(); 
                case "stand" -> game.handleStand();
                case "double" -> game.handleDoubleDown();
                default -> System.out.println("Invalid action");
            }

            System.out.println("Player total: " + game.getPlayer().getHand().calculateTotal()); // print total after player either stands or busts 
            System.out.println("Dealer total: " + game.getDealer().getHand().calculateTotal()); // print dealer total

           
        } 
        
        System.out.println("Outcome: " + game.getOutcome());  // outcome 
        System.out.println("Balance: " + game.getPlayer().getBalance()); // new balance
    }
}

