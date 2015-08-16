import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TributeClicker extends Stage {
	final double MARGIN = 100;
	int clickCount = 0;
	int xBound;
	int yBound;
	ImageView view;
	MediaPlayer songPlayer;
	MediaPlayer soundPlayer;
	Random random = new Random();
	

	TributeClicker(String imageLocation, String songLocation, String soundLocation) {	
		soundPlayer = new MediaPlayer(new Media(getClass().getResource(soundLocation).toString()));
		soundPlayer.setOnEndOfMedia(() -> soundPlayer.stop());

		songPlayer = new MediaPlayer(new Media(getClass().getResource(songLocation).toString()));
		songPlayer.setAutoPlay(true);
		songPlayer.setCycleCount(MediaPlayer.INDEFINITE);

		Image image = new Image(imageLocation);
		xBound = (int)(Screen.getPrimary().getVisualBounds().getWidth() - MARGIN - image.getWidth());
		yBound = (int)(Screen.getPrimary().getVisualBounds().getHeight() - MARGIN - image.getHeight());

		view = new ImageView(image);
		view.setOnMouseClicked(e -> {
			setX(random.nextInt(xBound));
			setY(random.nextInt(yBound));
			soundPlayer.stop();
			soundPlayer.play();
			clickCount++;
		});

		Scene scene = new Scene(new Group(view), Color.TRANSPARENT);
		scene.setCursor(Cursor.HAND);
		setScene(scene);
		initStyle(StageStyle.TRANSPARENT);
	}
}