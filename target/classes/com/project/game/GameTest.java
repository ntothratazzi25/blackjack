package com.project.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GameTest 
{
    private Game game;

    @Before
    public void setUp()
    {
        game = new Game(1000.0);
        game.startGame();
        game.getPlayer().placeBet(100.0);
    }

    @Test
    public void testStartingBalance()
    {
        assertEquals(900.0, game.getPlayer().getBalance(), 0.01);
    }

    @Test
    public void testInitialStatusIsActive()
    {
        assertEquals("active", game.getStatus());
    }

    @Test
    public void testPlayerTotalIsValid()
    {
        int total = game.getPlayer().getHand().calculateTotal();
        assertTrue(total >= 2 && total <= 21);
    }

    @Test
    public void testDealerShowingIsValid()
    {
        int showing = game.getDealer().getHand().getCard(0).getValue();
        assertTrue(showing >= 1 && showing <= 11);
    }

    @Test
    public void testStandResolvesGame()
    {
        game.handleStand();
        assertEquals("resolved", game.getStatus());
    }

    @Test
    public void testOutcomeSetAfterStand()
    {
        game.handleStand();
        assertNotNull(game.getOutcome());
    }

    @Test
    public void testHitIncreasesTotal()
    {
        int before = game.getPlayer().getHand().calculateTotal();
        game.handleHit();
        int after = game.getPlayer().getHand().calculateTotal();
        assertTrue(after >= before);
    }

    @Test
    public void testInsuranceBetStartsAtZero()
    {
        assertEquals(0.0, game.getInsuranceBet(), 0.01);
    }
}