import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class ControleurPersonnage extends KeyAdapter {

	/**
	 * Deplacement du personnage
	 * tout ce fait via fleche directionnel
	 */

	VueLab maVue;
	Personnage monPerso;
	Labyrinthe monLab;
	ZoneLab maZone;

	ControleurPersonnage(ZoneLab uneZone, VueLab uneVue){
		maZone = uneZone;
		maVue = uneVue;
		monLab = maZone.monLab;
		monPerso = maZone.monPerso;
	}

	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		switch(code){
		case KeyEvent.VK_UP :
			if (monPerso.getPersX()>0 && monLab.siOuvert(monPerso.getPos(), monPerso.getPos(monPerso.getPersX()-1,monPerso.getPersY())))
				monPerso.setPersonnage(monPerso.getPersX()-1,monPerso.getPersY());

			break;
		case KeyEvent.VK_RIGHT :
			if (monPerso.getPersY()<Labyrinthe.DIM-1 && monLab.siOuvert(monPerso.getPos(), monPerso.getPos(monPerso.getPersX(),monPerso.getPersY()+1)))
				monPerso.setPersonnage(monPerso.getPersX(), monPerso.getPersY()+1);
			else if(monLab.siPorteSortie(monPerso.getPos(), monPerso.getPos(monPerso.getPersX(),monPerso.getPersY()+1)))
				monPerso.setPersonnage(monPerso.getPersX(), monPerso.getPersY()+1);

			break;
		case KeyEvent.VK_DOWN :
			if (monPerso.getPersX()<Labyrinthe.DIM-1 && monLab.siOuvert(monPerso.getPos(), monPerso.getPos(monPerso.getPersX()+1,monPerso.getPersY())))
				monPerso.setPersonnage(monPerso.getPersX()+1,monPerso.getPersY());

			break;
		case KeyEvent.VK_LEFT :
			if (monPerso.getPersY()>0 && monLab.siOuvert(monPerso.getPos(), monPerso.getPos(monPerso.getPersX(),monPerso.getPersY()-1)))
				monPerso.setPersonnage(monPerso.getPersX(),monPerso.getPersY()-1);

			break;
		}

		//Affichage positionnement du perso et popup victory
		maZone.repaint();
		System.out.println("x = " + monPerso.persX + ", y = " + monPerso.persY);
		if (monPerso.victoire())
		{
			System.out.println("YES Victory !");
			javax.swing.JOptionPane.showMessageDialog(maZone,"You Win !Sonic got through.");
			System.exit(0);

		}

	}

}