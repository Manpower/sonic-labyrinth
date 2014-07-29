
class Personnage {
	
	/**
	 * Modèle du personnage. Contient :
	 * - la zone de déplacement du personnage (grillePerso)
	 * - sa position (getPos() ou coordonnï¿½es X/Y)
	 * 
	 */
	
	Labyrinthe monLab;
	ZoneLab maZone;
	int[][] grillePerso;
	int persX,persY;
	
	Personnage(ZoneLab laZone) {
		
		maZone = laZone;
		monLab = maZone.monLab;
		persX = 0;
		persY = 0;
		
		initGrillePerso();
	}
	
	void initGrillePerso() {
		grillePerso = new int[Labyrinthe.DIM][Labyrinthe.DIM];
		for (int i=0;i<Labyrinthe.DIM;i++){
			for(int j=0;j<Labyrinthe.DIM;j++){
				grillePerso[i][j] = 0;
			}
		}
		grillePerso[persX][persY] = 1;
	}
	
	public int getPersX(){ return persX; }
	public int getPersY(){ return persY; }
	
	// Fonction qui renvoi le numero de la case dans le tableau (sans parametre, elle renvoi la position actuelle)
	public int getPos() { return persX*Labyrinthe.DIM+persY; }
	public int getPos(int x, int y) { return x*Labyrinthe.DIM+y; }
	
	public void setPersonnage(int x,int y){
		persX = x;
		persY = y;
	}
	
	public boolean victoire()
	{
		return persY >= Labyrinthe.DIM;
	}
}
