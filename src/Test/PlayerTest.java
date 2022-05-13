package Test;

import Model.Player;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player1, player2;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        player1 = new Player(1,"Steven","Sermeus",new GregorianCalendar(1997,1,1),'M',true,1000);
        player2 = new Player(2,"Emilien","Bolle",new GregorianCalendar(1997,1,1),'F',false,200);
    }

    @org.junit.jupiter.api.Test
    void isProfessional() {
        assertTrue(player1.isProfessional());
        assertFalse(player2.isProfessional());
    }

    @org.junit.jupiter.api.Test
    void getElo() {
        assertEquals(1000, player1.getElo());
        assertEquals(200,player2.getElo());
        assertNotEquals(1000,player2.getElo());
    }
}