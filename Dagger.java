//WEAPON
package covid;

public class Dagger extends Pic {

    private final int MGAME_WIDTH = 1080;
    private final int DAGGER_SPEED = 10;

    public Dagger(int x, int y) {
        super(x, y);

        initDagger();
    }
    
    private void initDagger() {
        
        loadImage("src/covid/dagger.png");
        getImageDimensions();        
    }

    public void move() {
        
        x += DAGGER_SPEED;
        
        if (x > MGAME_WIDTH)
            visible = false;
    }
}