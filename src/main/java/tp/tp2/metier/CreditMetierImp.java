package tp.tp2.metier;

public class CreditMetierImp implements ICreditMetier{ 
	
	public double calculeMensu(double c, double t, int n) { 
		 double x,y,z; 
		 x=c*((t/100)/12); 
		 y=1-Math.pow((1+((t/100)/12)),-n); 
		 
		 return(x/y); 
		 }
}