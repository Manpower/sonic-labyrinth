import java.util.*;

class Labyrinthe {

	/**
	 * voila comment le lab est genenr�
	 * 
	 * - dimension =DIM
	 * - cases =sommets
	 * - ouvertures 
	 * -  murs =aretes 
	 */

	public final static int DIM = 15;
	final static int NORD = 0;
	final static int SUD = 1;
	final static int EST = 2;
	final static int OUEST = 3;

	ArrayList<Arete> aretes;
	ArrayList<Sommet> sommets;
	ArrayList<Arete> ouvertures;
	ArrayList<Arete> sorties;
	Sommet[] initSommets;
	int[][] defLab;

	int sortie; 

	Labyrinthe(){

		aretes = new ArrayList<Arete>();
		ouvertures = new ArrayList<Arete>();
		sommets = new ArrayList<Sommet>();
		sorties = new ArrayList<Arete>();
		initSommets = new Sommet[DIM*DIM];
		initSomm();
		initAretes();
		tri(aretes);
		initLabyrinthe();
		constructDefLab();

	}

	void initSomm(){
		for (int i=0;i<initSommets.length;i++){
			initSommets[i] = new Sommet(i);
		}
	}
	void initAretes(){
		for (int i=0;i<DIM*DIM;i++){
			if ((i%DIM!=DIM-1))
				aretes.add(new Arete(initSommets[i],initSommets[i+1],(int)(Math.random()*DIM*DIM*10)));
		}
		for (int i=0;i<DIM*(DIM-1);i++){
			aretes.add(new Arete(initSommets[i],initSommets[i+DIM],(int)(Math.random()*DIM*DIM*10)));
		}
	}

	void afficheListe(ArrayList<Arete> liste){
		for (int i=0;i<liste.size();i++){
			System.out.println(liste.get(i).s1.num+","+liste.get(i).s2.num+","+liste.get(i).poids);
		}
	}

	ArrayList<Arete> selectAretes(){
		ArrayList<Arete> selection = new ArrayList<Arete>();
		for (int j=0;j<aretes.size();j++){
			if (sommets.contains(aretes.get(j).s1)||sommets.contains(aretes.get(j).s2))
				selection.add(aretes.get(j));
		}
		return selection;
	}

	void tri(ArrayList<Arete> liste){
		int i;
		Arete temp;
		int j = liste.size()-1;
		while(j>0){
			i=0;
			while(i<j){
				if (liste.get(i).poids>liste.get(i+1).poids){
					temp = liste.get(i);
					liste.set(i,liste.get(i+1));
					liste.set(i+1,temp);
				}
				else i++;
			}
			j--;		
		}
	}

	void initLabyrinthe(){
		ArrayList<Arete> liste;
		int j;
		boolean flag;
		sommets.add(aretes.get(0).s1);
		for(int i=1;i<DIM*DIM;i++){
			liste = selectAretes();
			j=0;
			flag = false;
			while (!flag){
				if (!sommets.contains(liste.get(j).s1)){
					sommets.add(liste.get(j).s1);
					flag = true;
				}
				else if (!sommets.contains(liste.get(j).s2)){
					sommets.add(liste.get(j).s2);
					flag = true;
				}
				else j++;
			}
			ouvertures.add(liste.get(j));
			aretes.remove(liste.get(j));
		}

		sortieAleatoire();
	}
	//Cr�ation de la sortie du labyrinthe
	void sortieAleatoire() {
		Random rand = new Random();
		this.sortie = (rand.nextInt(DIM) + 1)* DIM;

		System.out.println("Sortie : " + this.sortie);
	}


	void constructDefLab(){
		defLab = new int[DIM*DIM][4];

		for (int i=0;i<DIM*DIM;i++){
			defLab[i][NORD] = -1;
			defLab[i][SUD] = -1;
			defLab[i][EST] = -1;
			defLab[i][OUEST] = -1;
		}

		for (int i=0;i<ouvertures.size();i++){
			if (ouvertures.get(i).s2.num-ouvertures.get(i).s1.num==1){
				defLab[ouvertures.get(i).s1.num][EST] = ouvertures.get(i).s2.num;
				defLab[ouvertures.get(i).s2.num][OUEST] = ouvertures.get(i).s1.num;
			}

			else if (ouvertures.get(i).s2.num-ouvertures.get(i).s1.num==DIM){
				defLab[ouvertures.get(i).s1.num][SUD] = ouvertures.get(i).s2.num;
				defLab[ouvertures.get(i).s2.num][NORD] = ouvertures.get(i).s1.num;
			}
		}
		// Sortie random mais toujours cote droit du labyrinthe
		defLab[this.sortie - 1][EST] = -2;


	}


	/*
	 * 
	 * FONCTION 
	 * 
	 * Effectue un test sur la variable tableau qui contient les murs : "aretes"
	 * deux cases 
	 * case 1 : ou est le personnage
	 * case 2 : ou il compte aller
	 * -> renvoi false s'il y a un mur (donc s'il y a le couple case1 / case 2 contenu dans le tableau "aretes")
	 * -> renvoi true (par defaut) si c'est ouvert (donc absence du couple case 1 / case 2)
	 * 
	 */

	boolean siOuvert(int case1, int case2) {
		boolean ouvert = true;

		for (int i=0; i<aretes.size(); i++) {
			if (aretes.get(i).s1.num == case1) {
				if (aretes.get(i).s2.num == case2)
					ouvert = false;
			}
			else if (aretes.get(i).s1.num == case2) {
				if (aretes.get(i).s2.num == case1)
					ouvert = false;
			}
		}

		return ouvert;
	}

	boolean siPorteSortie(int case1, int case2)
	{
		return (case1 == (this.sortie - 1) && case2 == this.sortie);
	}
}