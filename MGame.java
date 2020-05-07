//BOARD
package covid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MGame extends JPanel implements ActionListener {

    private Timer timer;
    private WBC wbc;
    private List<Virus> viruses;
    private boolean ingame;
    private final int ICRAFT_X = 80;
    private final int ICRAFT_Y = 120;
    private final int B_WIDTH = 1080;
    private final int B_HEIGHT = 720;
    private final int DELAY = 15;

    private final int[][] pos = {
        {1250, 100}, {935, 200}, {544, 300},
        {1111, 400}, {1080, 500}, {728, 600},
        {1222, 300}, {720, 300}, {555, 300},
        {1356, 700}, {690, 800}, {1230, 900},
        {900, 150}, {700, 250}, {1150, 350},
        {800, 450}, {1075, 550}, {1170, 650},
        {850, 750}, {1020, 850}, {1190, 950},
        {960, 400}, {1090, 320}, {999, 440},
        {790, 600}, {1056, 330}, {555, 910},
        {1102, 270}, {1352, 190}, {777, 220},
    };

    public MGame() {

        initMGame();
    }

    private void initMGame() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.DARK_GRAY);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        wbc = new WBC(ICRAFT_X, ICRAFT_Y);

        initViruses();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initViruses() {
        
        viruses = new ArrayList<>();

        for (int[] p : pos) {
            viruses.add(new Virus(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        if (wbc.isVisible()) {
            g.drawImage(wbc.getImage(), wbc.getX(), wbc.getY(),
                    this);
        }

        List<Dagger> ms = wbc.getDagger();

        ms.stream().filter((dagger) -> (dagger.isVisible())).forEachOrdered((dagger) -> {
            g.drawImage(dagger.getImage(), dagger.getX(),
                    dagger.getY(), this);
        });

        viruses.stream().filter((virus) -> (virus.isVisible())).forEachOrdered((virus) -> {
            g.drawImage(virus.getImage(), virus.getX(), virus.getY(), this);
        });

        g.setColor(Color.WHITE);
        g.drawString("Virus Left: " + viruses.size(), 5, 15);
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("New Times Roman", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updateWbc();
        updateDaggers();
        updateViruses();

        checkCollisions();

        repaint();
    }

    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }

    private void updateWbc() {

        if (wbc.isVisible()) {
            
            wbc.move();
        }
    }

    private void updateDaggers() {

        List<Dagger> ms = wbc.getDagger();

        for (int i = 0; i < ms.size(); i++) {

            Dagger m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }

    private void updateViruses() {

        if (viruses.isEmpty()) {

            ingame = false;
            return;
        }

        for (int i = 0; i < viruses.size(); i++) {

            Virus a = viruses.get(i);
            
            if (a.isVisible()) {
                a.move();
            } else {
                viruses.remove(i);
            }
        }
    }

    public void checkCollisions() {

        Rectangle r3 = wbc.getBounds();

        viruses.forEach((virus) -> {
            Rectangle r2 = virus.getBounds();
            if (r3.intersects(r2)) {
                wbc.setVisible(false);
                virus.setVisible(false);
                ingame = false;
            }
        });

        List<Dagger> ms = wbc.getDagger();

        ms.forEach((Dagger m) -> {
            Rectangle r1 = m.getBounds();
            
            viruses.forEach((virus) -> {
                Rectangle r2 = virus.getBounds();
                if (r1.intersects(r2)) {
                    m.setVisible(false);
                    virus.setVisible(false);
                }
            });
        });
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            wbc.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            wbc.keyPressed(e);
        }
    }
}