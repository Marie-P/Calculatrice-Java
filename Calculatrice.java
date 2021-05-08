/**
	* Calculatrice est la classe permettant de faire les principaux calcul de l'utilisateur.
	* Elle utilise : 
	* La classe Pile pour gérer d'opérations
	* La classe Fact permettant de calculer un factorielle
	*/

public class Calculatrice{
	private double resultat;

	// Constructeur
	Calculatrice(){
		this.resultat = 0;
	}

/** getter de la variable resultat
	* @return le resultat de l'objet courant
	*/
	public double getResultat(){
		return resultat;
	}

/** Va permettre de faire les calculs entrés par l'utilisateur
	* @param infix
			Opération entrée par l'utilisateur
	*/
	public void miniCalculatrice(String infix)
	{
		String postfix = infix2postfix(infix);
		double res = 0;
		double args[] = new double[256]; // Va nous permettre de construire le chiffre qu'on veut (on essaye d'abord avec res)
		Pile pile = new Pile(); // On va mettre ici ce qu'on va calculer
		int chiffreApresLaVirgule = 0, argsCount = 0;

		for (int i = 0; postfix.charAt(i)!='\0'; i++) 
		{
			// L'espace sépare 2 nombres, donc si il y en a un on transforme le nombre en réel avant de continuer
			for (; postfix.charAt(i) != ' ' && i < postfix.length(); i++)
			{
				if (postfix.charAt(i) == '.')
				{
					chiffreApresLaVirgule++;
				}
				
				// Si on est sur un chiffre 
				if (postfix.charAt(i) >= '0' && postfix.charAt(i) <= '9')
				{
					// Pour les chiffres à virgule
					if (chiffreApresLaVirgule > 0)
					{
						for (int j = 0; !pile.empty(); j++) 
						{
							res += pile.pop() * Math.pow(10, j); 	
						}

						for (int j = chiffreApresLaVirgule; postfix.charAt(i) != ' '; j++,i++) {
							res += (postfix.charAt(i) - '0') * Math.pow(10, -j);
						}
						i--; // On a besoin de l'espace pour ne pas avoir d'erreur
					}

					else
					{
						pile.push(postfix.charAt(i) - '0');
					}
				}

				// Si on est sur un opérateur
				else if (postfix.charAt(i) == '+' || postfix.charAt(i) == '-' || postfix.charAt(i) == '*' || postfix.charAt(i) == '/' 
					|| postfix.charAt(i) == '^' || postfix.charAt(i) == 'V' || postfix.charAt(i) == '!' || postfix.charAt(i) == '%')
				{
					if (postfix.charAt(i) == 'V' || postfix.charAt(i) == '!') 
					{
						res = calcul(postfix.charAt(i), args[--argsCount]);
					}

					else
					{
						double droit = args[--argsCount];
						double gauche = args[--argsCount];
						res = calcul(postfix.charAt(i), gauche, droit);
					}
					
					args[argsCount++] = res;
					res = 0;
				}
			}

			// On range notre nombre!
			if (chiffreApresLaVirgule > 0) // Si on vient de rentrer un chiffre à virgule
			{
				args[argsCount++] = res; // On range le nombre qu'on vient de récupérer dans notre tableau de nombre
				chiffreApresLaVirgule = 0;
				res = 0;	
			}

			else if(!pile.empty())
			{
				for (int j = 0; !pile.empty(); j++) 
				{
					res += pile.pop() * Math.pow(10, j); 	
				}
				args[argsCount++] = res; // On range le nombre qu'on vient de récupérer dans notre tableau de nombre
				res = 0;
			}
		}
		if (argsCount == 0) 
			System.out.println("Erreur Syntaxe !");
		else
			this.resultat = args[--argsCount];
	}

/** Fonction qui effectue un calcul en fonction de l'opérateur
	* @param operateur
			L'opérateur qui nous permet de savoir quelle opération à appliquer
	* @param args
			Tout ce qui se trouve après l'opérateur
	* @return le résultat du calcul
	*/
	public double calcul(char operateur, double...args){
		double res = 0;
		switch(operateur)
		{
			case '+':
				res = args[0];
				res +=args[1];
				break;
			case '-':
				res = args[0];
				res -=args[1];
				break;
			case '/':
				if (args[1] == 0)
				{
					System.out.println("Nous ne pouvons pas diviser par zéro");
					res = 0;
				}
				
				else
				{
					res = args[0];
					res /=args[1];
				}
				break;
			case '*':
				res = args[0];
				res *= args[1];
				break;
			case '^':
				res = args[0];
				res = Math.pow(res,args[1]);
				break;
			case 'V':
				res = Math.sqrt(args[0]);
				break;
			case '!':
				Fact fact = new Fact();
				res = fact.fact(args[0]);
				break;
			case '%':
				res = args[0];
				res %=args[1];
				break;
		}
		return res;
	}

/** Fonction qui va mettre une opération infixé en postfixé
	* @param infix
			opération en notation infixé (ou autre mais en présume que l'utilisateur entrera principalement ses opérations dans cette notation)
	* @return l'opération en notation postfixé
	*/
	public String infix2postfix(String infix){
		infix += ' '; // evite une erreur pour le cas où le dernier character est un chiffre
		char postfix[] = new char[256];
		int postfixCount = 0;
		boolean point = true;
		Pile pile = new Pile();

		for (int i = 0; i < infix.length(); i++) 
		{
			if ((infix.charAt(i) >= '0' && infix.charAt(i) <= '9') || (infix.charAt(i) == '.' && point))
			{
				do
				{
					if (infix.charAt(i) == '.')
						point = false;
					postfix[postfixCount++] = infix.charAt(i++);
				}while((infix.charAt(i) >= '0' && infix.charAt(i) <= '9') || (infix.charAt(i) == '.' && point));
				postfix[postfixCount++] = ' ';

			}
			if((infix.charAt(i) == ')') && !pile.empty()) 
			{ 
				postfix[postfixCount++] = (char) pile.pop();
				postfix[postfixCount++] = ' '; 
			}

			else if((infix.charAt(i) == '+') || (infix.charAt(i)  == '-') || (infix.charAt(i)  == '*') || (infix.charAt(i)  == '/')
				|| (infix.charAt(i)  == '^') || (infix.charAt(i)  == 'V') || (infix.charAt(i)  == '!') || (infix.charAt(i)  == '%'))
			{
				pile.push(infix.charAt(i));
				point = true;
			}
		}
		while(!pile.empty())
		{
			postfix[postfixCount++] = (char) pile.pop();
			postfix[postfixCount++] = ' ';
		}
		postfix[postfixCount] = '\0';
		String postfixResult = new String(postfix);
		return postfixResult;
	} 
	
}
