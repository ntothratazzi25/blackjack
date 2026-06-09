package com.project.game;

import java.util.Scanner;

import com.project.model.Card;
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

    private double insuranceBet;

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

    public boolean isInsuranceAvailable()
    {
        Card upCard = dealer.getHand().getCard(0); // dealer's first card 
        return upCard.getValue() == 11; // return true if dealer's first card is an ace (11)
    }

    public void takeInsurance()
    {
        insuranceBet = player.getBet().getAmount() / 2;  // called if player accepts insurance, which is half their og bet
        player.addToBalance(-insuranceBet); // subtract insurance from balance 
    }

    public double getInsuranceBet()
    {
        return insuranceBet;
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
            if (insuranceBet > 0)
            {
                player.addToBalance(insuranceBet * 2);
            }
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
        double balance = 1000.00;
        
        while (true) 
        { 
            Game game = new Game(balance); 
            game.startGame();
            System.out.println("\nBalance: $" + balance);  // balance 
            System.out.print("Enter bet amount: $");
            double betAmount = scanner.nextDouble();
            scanner.nextLine();
            game.getPlayer().placeBet(betAmount);

            System.out.println("\nPlayer's first card: " + game.getPlayer().getHand().getCard(0));
            System.out.println("Dealer's first card: " + game.getDealer().getHand().getCard(0));
            System.out.println("Player's second card: " + game.getPlayer().getHand().getCard(1));
            System.out.println("Dealer's Hole Card is dealt.\n");
            System.out.println("Player total: " + game.getPlayer().getHand().getSoftTotal()); // deal player hand 
            System.out.println("Dealer showing: " + game.getDealer().getHand().getCard(0).getValue()); // dealer hand (with hole card) 

            if (game.getPlayer().getHand().isBlackjack()) // check if blackjack before going into gameplay logic
            {
                game.resolveOutcome();
            }

            if (game.isInsuranceAvailable()) // check if dealer's face up card is an ace
            {
                System.out.print("Take Insurance? (y/n) "); 
                if (scanner.nextLine().equals("y"))
                {
                    game.takeInsurance();
                }
                else
                {
                    System.out.println("Insurance declined.");
                }   
            }

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

                if (game.getStatus().equals("active"))
                {
                    System.out.println("Player total: " + game.getPlayer().getHand().getSoftTotal()); // print total after player either stands or busts if ace involved 
                    System.out.println("Dealer total: " + game.getDealer().getHand().calculateTotal()); // print dealer total
                }
                else 
                {
                    System.out.println("Player total: " + game.getPlayer().getHand().calculateTotal()); // print total after player either stands or busts
                    System.out.println("Dealer total: " + game.getDealer().getHand().calculateTotal()); // print dealer total
                }
            }

            balance = game.getPlayer().getBalance();  // set new balance 
            
            System.out.println("Outcome: " + game.getOutcome());  // outcome 
            System.out.println("Balance: $" + game.getPlayer().getBalance()); // print new balance

            if (game.getInsuranceBet() > 0 && game.getOutcome().equals("dealer blackjack")) // player took insurance and dealer got blackjack 
            {
                System.out.println("Insurance paid out: $" + game.getInsuranceBet() * 2); // pay insurance back to 
            }
            else if (game.getInsuranceBet() > 0) // player took insurance and dealer did not get blackjack
            {
                System.out.println("Insurance lost: $" + game.getInsuranceBet());
            }

            System.out.print("\nPlay again? (y/n) ");
            if (scanner.nextLine().equals("n"))
            {
                break;
            }
        }
        scanner.close();
    }
}