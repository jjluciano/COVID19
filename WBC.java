//CHARACTER
package covid;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class WBC extends Pic {

    private int dx;
    private int dy;
    private List<Dagger> daggers;

    public WBC(int x, int y) {
        super(x, y);

        initWbc();
    }

    private void initWbc() {
        
        daggers = new ArrayList<>();
        loadImage("src/covid/wbc.png");
        getImageDimensions();
    }

    public void move() {

        x += dx;
        y += dy;

        if (x < 1) {
            x = 5;
        }

        if (y < 1) {
            y = 5;
        }
    }

    public List<Dagger> getDagger() {
        return daggers;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -5;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 5;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -5;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 5;
        }
    }

    public void fire() {
        daggers.add(new Dagger(x + width, y + height / 2));
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

}