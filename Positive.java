//Collision
package covid;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Positive extends JFrame {

    public Positive() {

        initUI();
    }

    private void initUI() {

        add(new MGame());

        setResizable(false);
        pack();

        setTitle("WBC vs COVID-19");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Positive ex = new Positive();
            ex.setVisible(true);
        });
    }
}
