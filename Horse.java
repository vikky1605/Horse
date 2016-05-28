package Horse;

import java.util.HashSet;

import static Horse.GameField.addLevelField;
import static Horse.GameField.levelField;
import static Horse.GameField.pointsForStep;
import static Horse.GuiHorse.endGame;
import static Horse.GuiHorse.paintWay;
import static Horse.Point.addTo;


/**
 * Created by bolshakova on 12.05.2016.
 * Ход конем.
 У вас есть шахматная доска N*N клеток.
 На доске находится конь. И клетки с дырками, куда ходить конь не может.
 Определите, можно ли дойти от точки А до точки Б. Если можно, то покажите путь на самом поле.
 Входные данные.
 Размер поля. Координаты точки А и Б.
 Координаты клеток, по которым конь ходить не может.

 Выходные данные
 Отрисованное поле с обозначением пути от А до Б.
 Или слово НЕВОЗМОЖНО. Если конем нельзя добраться из А в Б
 *
 *
 */
public class Horse {

    static Point pointStart; // начальная точка на игровои поле, где стоит конь
    static Point pointFinish; // конечная точка на игромом поле, куда нужно дойти коню
    static boolean rezGame; // результат игры. true - если конем можно дойти из точки А до точки В
    static int countSteps; // количество шагов от начальной точки до конечной

    public static void main(String[] args) {

        addLevelField();
        StartHorse startHorse = new StartHorse();
        startHorse.startDialog();
    }

    public void go() {
        pointsForStep.add(pointStart);
        boolean stopGame = false;
        HashSet<Point> points = new HashSet<Point>();
        countSteps = 0;

        while (!stopGame) {
            countSteps++;

            if (pointFinish.containsTo(pointsForStep)) {
                rezGame = true;
                stopGame = true;
                pointFinish.setNumber();
                setLevelField(pointFinish.number, countSteps);
                pointFinish.setEnabledPoints();
                wayHorse();
            } else {
                addTo(pointsForStep, points);

                for (Point pt : points) {
                    pt.setNumber();
                    if (levelField[pt.number] == -1) {
                    setLevelField(pt.number, countSteps);}
                    pt.setEnabledPoints();
                    addTo(pt.enabledPoints, pointsForStep);
                }
            }
            if (points.size() == pointsForStep.size()) {
                stopGame = true;
                endGame();
            }
        }
    }

    public static void wayHorse() {
        Point p = pointFinish;
        paintWay(p);


        for (int i = 0; i < countSteps + 1; i++) {
            p.setEnabledPoints();
            for (Point pt : p.enabledPoints) {
                pt.setNumber();
                    if (levelField[pt.number] == countSteps - i) {
                    paintWay(pt);
                    p = pt;
                    break;
                }
            }
        }
    }

    public void setLevelField (int numberField, int level) {
        levelField[numberField] = level;
    }
}
