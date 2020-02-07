package es.datastructur.synthesizer;

/* Imports the required audio library from the
 * edu.princeton.cs.introcs package. */
import edu.princeton.cs.introcs.StdAudio;

import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the GuitarString class.
 *  @author liyu
 */

public class TestHarp {
    @Test
    public void testPluckTheAString() {
        double CONCERT_A = 440.0;
        Harp aString = new Harp(CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }
}
