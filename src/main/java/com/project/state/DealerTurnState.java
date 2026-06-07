package com.project.state;

import com.project.game.Game;

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
