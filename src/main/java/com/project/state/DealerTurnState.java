package com.project.state;

import com.project.game.Game;


/** represents the state where it is the dealer's turn to play
 *  this state is entered after the player stands or doubles down, and the dealer must play their turn according to standard blackjack rules.
 *  this class is mostly a placeholder for the State pattern and to make future UI implementation easier, as all of the dealer logic is held in Dealer.java.
 *  
 * @author Nick Toth-Ratazzi
**/


public class DealerTurnState implements GameState
{

    @Override
    public void handleHit(Game game) 
    {
        // dealer logic is all held in Dealer.java, so nothing is needed here. this class is just a placeholder for the State pattern and to make future UI implementation easier.
    }

    @Override
    public void handleDoubleDown(Game game) 
    {
        // dealer logic is all held in Dealer.java, so nothing is needed here. this class is just a placeholder for the State pattern and to make future UI implementation easier.
    }

    @Override
    public void handleStand(Game game) 
    {
        // dealer logic is all held in Dealer.java, so nothing is needed here. this class is just a placeholder for the State pattern and to make future UI implementation easier.
    }
}
