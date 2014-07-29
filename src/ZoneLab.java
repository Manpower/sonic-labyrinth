import java.awt.*;

import javax.swing.*;

class ZoneLab extends JPanel {

	/*
	 * 
	 * 
	 */

	private static final long serialVersionUID = 1L;
	final static int NORD = 0;
	final static int SUD = 1;
	final static int EST = 2;
	final static int OUEST = 3;

	Labyrinthe monLab;
	int dimCase;
	Personnage monPerso;
	Image imagePerso;
	int placeXY;


	ZoneLab(Labyrinthe unLab){
		// Dimension du labyrinthe 
		monLab = unLab;
		setSize(800,800);
		dimCase = 800/Labyrinthe.DIM;

		// creation du perso
		monPerso = new Personnage(this);
		ImageIcon perso = new ImageIcon("img/Sonic.gif");
		imagePerso = perso.getImage();

		// centrage du perso 
		placeXY = (dimCase-50)/2;
		//musique 

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		dessineLab(g);
		dessinePerso(g);
	}

	void dessineLab(Graphics g){
		int[][] tab = monLab.defLab;
		int x = -1;
		int y = -1;
		for (int i=0;i<tab.length;i++){
			if (tab[i][NORD]==-1){
				x = (i%Labyrinthe.DIM)*dimCase;
				y = (i/Labyrinthe.DIM)*dimCase;
				g.drawLine(x,y,x+dimCase,y);
			}
			if (tab[i][SUD]==-1){
				x = (i%Labyrinthe.DIM)*dimCase;
				y = (i/Labyrinthe.DIM+1)*dimCase;
				g.drawLine(x,y,x+dimCase,y);
			}
			if (tab[i][OUEST]==-1){
				x = (i%Labyrinthe.DIM)*dimCase;
				y = (i/Labyrinthe.DIM)*dimCase;
				g.drawLine(x,y,x,y+dimCase);
			}
			if (tab[i][EST]==-1){
				x = (i%Labyrinthe.DIM+1)*dimCase;
				y = (i/Labyrinthe.DIM)*dimCase;
				g.drawLine(x,y,x,y+dimCase);
			}
		}
	}

	void dessinePerso(Graphics g) {
		g.drawImage(imagePerso, dimCase*monPerso.getPersY()+placeXY, dimCase*monPerso.getPersX()+placeXY, null);
	}

}