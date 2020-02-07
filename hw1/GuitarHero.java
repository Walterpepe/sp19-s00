import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    private GuitarString[] strings;
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public GuitarHero() {
        strings = new GuitarString[37];
        for (int i = 0; i < strings.length; i++) {
            //character ‘q’ is 110Hz, ‘i’ is 220Hz, ‘v’ is 440Hz, and ‘ ‘ is 880Hz.
            strings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
        }
    }

    public void pluck(int index) {
        strings[index].pluck();
    }

    public double sample() {
        double sample = 0.0;
        for (GuitarString gs : strings) {
            sample += gs.sample();
        }
        return sample;
    }

    public void tic() {
        for (GuitarString gs : strings) {
            gs.tic();
        }
    }


    public static void main(String[] args) {

        GuitarHero play = new GuitarHero();

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                System.out.print(key + " ");
                int index = KEYBOARD.indexOf(key);

                if (index == -1) {
                    continue;
                }
                play.pluck(index);
            }

            double sample = play.sample();

            StdAudio.play(sample);

            play.tic();
        }
    }


}
