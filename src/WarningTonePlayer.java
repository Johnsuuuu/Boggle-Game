import java.io.InputStream;
import java.net.URL;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class WarningTonePlayer {
    private URL url;
    private AudioStream audioStream;

    /**
     * Constructor for the WarningTonePlayer class
     */
    public WarningTonePlayer() {
        try {
            url = WarningTonePlayer.class.getResource("beep-2.wav");
            InputStream inputStream = url.openStream();
            audioStream = new AudioStream(inputStream);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * play the warning tone
     */
    public void play() {
        AudioPlayer.player.start(audioStream);
    }

}
