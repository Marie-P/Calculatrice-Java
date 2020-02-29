/**
	* Permet de calculer un factorielle
*/

public class Fact
{
/** 
	* @param n
			va calculer le factorielle de n
	*/
	public double fact (double n) 
	{
        if (n==0) 
        	return(1);
        else 
        	return(n*fact(n-1));
    }
}