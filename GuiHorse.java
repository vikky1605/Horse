package Horse;

import javax.swing.*;
import java.awt.*;
import static Horse.GameField.n;
import static Horse.StartHorse.icon;

/**
 * Created by bolshakova on 23.05.2016.
 */
public class GuiHorse extends JFrame {

    static JToggleButton [] jtButton = new JToggleButton[n*n];

    public void paintGameField () {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(50*n,50*n);
        this.setLocation(500,100);
        this.setTitle("Ход конем");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(n,n));
        this.getContentPane().add(panel);

        for (int i = 0; i < n*n; i++) {
            jtButton[i] = new JToggleButton();
            jtButton[i].setSize(50,50);
            jtButton[i].setEnabled(true);
            panel.add(jtButton[i]);
        }
        this.setVisible(true);
    }

    public static void paintWay(Point point) {
        point.setNumber();
        jtButton[point.number].setSelected(false);
        jtButton[point.number].setBackground(Color.YELLOW);
        jtButton[point.number].setIcon(icon);

    }
    public static void endGame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(500, 50*n +10);
        frame.setSize(50*n, 100);
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        JLabel label = new JLabel("такой ход конем невозможен");
        panel.add(label);
        frame.setVisible(true);

    }
}

