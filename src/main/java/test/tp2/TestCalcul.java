package test.tp2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCalcul {

    private ICreditMetier metier;

    @BeforeEach
    public void setUp() {
        metier = new CreditMetierImp();
    }

    @Test
    public void testCalculMensu() {
        double credit = 200000;
        int duree = 240;
        double taux = 4.5;
        double resultatAttendu = 1265.2987;

        double resultatObtenu = metier.calculeMensu(credit, taux, duree);

        assertEquals(resultatAttendu, resultatObtenu, 0.0001);
    }
}

