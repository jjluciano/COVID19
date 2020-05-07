//THE COVID
package covid;

public class Virus extends Pic {

    private final int INITIAL_X = 1000;

    public Virus(int x, int y) {
        super(x, y);

        initVirus();
    }

    private void initVirus() {

        loadImage("src/covid/virus.png");
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = INITIAL_X;
        }

        x -= 4;
    }
}