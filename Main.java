/**
	* Main est la classe contenant la fonction principale
*/

import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		Calculatrice calculatrice = new Calculatrice();
		boolean calcul = true;

		// Menu
		System.out.println("\nBienvenue dans votre Calculatrice !");
		System.out.println("\nPour sortir tapez \"quit\" ou \"stop\" !");

		Scanner sc = new Scanner(System.in);
		String choix = new String();

		while(calcul)
		{
			System.out.println("\nEntrez votre opération : ");
			choix = sc.nextLine();

			if (choix.equals("quit") || choix.equals("stop")) 
			{
				calcul = false;
				System.out.println("Au revoir !");
			}
			else
			{
				calculatrice.miniCalculatrice(choix);
				System.out.println(calculatrice.getResultat());
			}
		}

	}
}

/*
javac Main.java
java Main
Bienvenue dans votre Calculatrice !

Pour sortir tapez "quit" ou "stop" !

Entrez votre opération :
1+2
3.0

Entrez votre opération :
(((6 + 6) - 2) * 2) * 3)/2)
30.0

Entrez votre opération :
(6.33 + 5 - 2.22)
9.11

Entrez votre opération :
(2 + 2 ^ 2)
6.0

Entrez votre opération :
(V4 +5)
3.0

Entrez votre opération :
(V4) +5)
7.0

Entrez votre opération :
(!4)
24.0

Entrez votre opération :
1%3)
1.0

Entrez votre opération :
6 + 6) - 2) * 2) * 3) oops)
60.0

Entrez votre opération :
45.500*42.000*23.000*19.000*29.000*3.000/10.500
6919458.0

Entrez votre opération :
52.500+8.000+84.000+71.000*81.000
5895.5

Entrez votre opération :
55.000+12.500+2.750+12.000*95.000
1210.25

Entrez votre opération :
quit
Au revoir !
*/