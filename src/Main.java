import java.io.ByteArrayInputStream;
import java.io.InputStream;


class Main {


	public static void main(String args[]){
		new VueLab();
		Sound player = new Sound("sound/greenhill.wav");
		InputStream stream = new ByteArrayInputStream(player.getSamples());
		player.play(stream);

	}

}