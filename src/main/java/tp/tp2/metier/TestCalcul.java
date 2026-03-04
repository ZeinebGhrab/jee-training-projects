package tp.tp2.metier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;


public class TestCalcul { 
	
	private ICreditMetier metier; 
	@Before
	public void setUp() throws Exception { 
		metier=new CreditMetierImp(); 
	 } 
	
	@Test
	public void testCalculMensu() { 
		double credit=200000; 
		int duree=240; 
		double taux=4.5; 
		double resultatAttendu =1265.2987; 
		double resultatObtenu =metier.calculeMensu(credit, taux, duree); 
		assertEquals(resultatAttendu, resultatObtenu,0.0001);
	}
}
