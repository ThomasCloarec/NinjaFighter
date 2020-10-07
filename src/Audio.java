import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Audio {
    private Clip clip;

    public Audio(String fileName) {

        try {
            InputStream audioSrc = this.getClass().getResourceAsStream(fileName);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
            DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
            this.clip = (Clip)AudioSystem.getLine(info);
            this.clip.open(inputStream);
        } catch (IllegalArgumentException ignored) {
            assert this.clip != null;
            this.clip.close();
            this.clip = null;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }

    public void play() {

        if (this.clip != null && !this.clip.isRunning()) {
            this.clip.setFramePosition(0);
            this.clip.start();
        }
    }

    @SuppressWarnings("unused")
    public void loop() {
        if (this.clip != null) {
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    @SuppressWarnings("unused")
    public void stop() {
        if (this.clip != null) {
            this.clip.stop();
        }
    }
}
