/**
	* Permet gérer les opérations de la Calculatrice
*/

public class Pile{
	private int hautPile;
	private double pile[] = new double[256];

	Pile(){
		hautPile = -1;
	}

/** fonction qui empile les éléments en haut de la pile
	* @param element
			element à stocker
	*/
	public void push(double element){
		pile[++hautPile] = element;
	}

/** fonction permettant de dépiler en enlevant le dernier élément ajouté
	* @return l'element en tête de pile
	*/
	public double pop(){
		return pile[hautPile--];
	}

/** fonction qui vérifie si la pile est vide
	* @return true si la pile est vide false sinon
	*/
	public boolean empty(){
		if (hautPile < 0)
			return true;
		else
			return false;
	}
}