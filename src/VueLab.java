import java.awt.*;
import javax.swing.*;




class VueLab extends JFrame {

	/**
	 * vue principale
	 * dimension de la fenetre 
	 */ 

	private static final long serialVersionUID = 1L;

	Labyrinthe monLab;
	ZoneLab maZone;
	Personnage monPerso;
	ControleurPersonnage ctrlPerso;

	VueLab(){

		setSize(850,850);

		setTitle("Sonic dans le labyrinthe");
		monLab = new Labyrinthe();
		maZone = new ZoneLab(monLab);
		setContentPane(maZone);
		getContentPane().setBackground(Color.decode("#A4CDFD"));

		ctrlPerso = new ControleurPersonnage(maZone, this);
		addKeyListener(ctrlPerso);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);


	}


}