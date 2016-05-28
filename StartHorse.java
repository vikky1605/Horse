package Horse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.net.URL;

import static Horse.GameField.addDisabledPoints;
import static Horse.GameField.disabledPoints;
import static Horse.GameField.n;
import static Horse.GuiHorse.jtButton;
import static Horse.Horse.pointFinish;
import static Horse.Horse.pointStart;


/**
 * Created by bolshakova on 26.05.2016.
 */
public class StartHorse extends JFrame {

    static String path = "1.jpg"; // иконка коня
    static URL imgUrl = StartHorse.class.getResource(path);
    static ImageIcon icon = new ImageIcon(imgUrl);

    public void startDialog () {
        final JFrame startFrame = new JFrame("Ход конем");
        startFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startFrame.setSize(300,150);
        startFrame.setLocation(500,100);
        JPanel panel = new JPanel();
        startFrame.getContentPane().add(panel);
        JLabel label = new JLabel("Задай размер игрового поля");
        panel.add(label);
        final Integer[] choose = new Integer[18];
        for (int i = 0; i < 12; i++) {
            choose[i] = 3 + i;
        }

        final JComboBox cb = new JComboBox(choose);
        panel.add(cb);
        JLabel label1 = new JLabel("Подтверди свой выбор!");
        panel.add(label1);
        JButton button = new JButton("нажми меня!");
        panel.add(button);
        startFrame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int s = cb.getSelectedIndex();
                n = choose[s];
                startFrame.dispose();
                StartHorse startHorse = new StartHorse();
                startHorse.nextDialog();
            }
        }) ;

    }
    public void nextDialog() {
        final JFrame nextFrame = new JFrame("Ход конем");
        nextFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        nextFrame.setSize(300, 200);
        nextFrame.setLocation(500, 100);
        JPanel panel = new JPanel();
        nextFrame.getContentPane().add(panel);
        JLabel label = new JLabel("выбери начальную точку");
        panel.add(label);
        Integer[] choose = new Integer[n * n];
        for (int i = 0; i < n * n; i++) {
            choose[i] = i;
        }
        final JComboBox cb = new JComboBox(choose);
        panel.add(cb);
        JLabel label1 = new JLabel("выбери конечную точку");
        panel.add(label1);
        final JComboBox cb1 = new JComboBox(choose);
        panel.add(cb1);
        JLabel label2 = new JLabel("подтверди свой выбор");
        panel.add(label2);
        JButton button = new JButton("нажми меня");
        panel.add(button);
        nextFrame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pointStart = new Point(cb.getSelectedIndex());
                pointFinish = new Point(cb1.getSelectedIndex());
                nextFrame.dispose();
                StartHorse startHorse = new StartHorse();
                startHorse.lastDialog();
            }
        });
    }

        public void lastDialog() {

            final JFrame lastFrame = new JFrame("Ход конем");
            lastFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            lastFrame.setSize(50*n,50*n+100);
            lastFrame.setLocation(500,100);
            lastFrame.setTitle("Ход конем");
            Box mainbox = Box.createVerticalBox();
            lastFrame.getContentPane().add(mainbox);
            JPanel panel = new JPanel();
            mainbox.add(panel);
            panel.setLayout(new GridLayout(n,n));

            for (int i = 0; i < n*n; i++) {
                jtButton[i] = new JToggleButton();
                jtButton[i].setSize(50,50);
                jtButton[i].setEnabled(true);
                panel.add(jtButton[i]);
            }

            jtButton[pointStart.number].setEnabled(false);
            jtButton[pointFinish.number].setEnabled(false);
            jtButton[pointStart.number].setIcon(icon);
            jtButton[pointFinish.number].setIcon(icon);

            JPanel panel1 = new JPanel();
            mainbox.add(panel1);
            JLabel label = new JLabel("выбери недоступные для ходов поля и нажми кнопку");
            panel1.add(label);
            JButton button = new JButton("нажми меня");
            panel1.add(button);
            lastFrame.setVisible(true);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < jtButton.length; i++) {
                        if (jtButton[i].isSelected()) disabledPoints.add(new Point(i));
                    }
                    lastFrame.dispose();
                    addDisabledPoints();
                    GuiHorse guiHorse = new GuiHorse();
                    guiHorse.paintGameField();
                    Horse horse = new Horse();
                    horse.go();

                }
            });
    }
}



