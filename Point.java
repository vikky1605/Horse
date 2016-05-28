package Horse;

import java.util.HashSet;

import static Horse.GameField.disabledPoints;
import static Horse.GameField.n;


/**
 * Created by bolshakova on 21.05.2016.
 */
public class Point {

    int x; // координата поля
    int y; // координата поля
    Integer number; // порядковый номер поля
    HashSet<Point> enabledPoints = new HashSet<Point>(); // множество полей, доступных для хода конем из данной точки
    int level; // порядковый номер шага от начальной точки

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point (int number) {
        this.x = number/n;
        this.y = number - n*this.x;
        this.number = number;
    }

    public void setNumber() {
        this.number = x * n + y;
    }

    public void setEnabledPoints() {
        this.addEnabledPoint(new Point(this.x + 1, this.y + 2));
        this.addEnabledPoint(new Point(this.x + 1, this.y - 2));
        this.addEnabledPoint(new Point(this.x - 1, this.y + 2));
        this.addEnabledPoint(new Point(this.x - 1, this.y - 2));
        this.addEnabledPoint(new Point(this.x + 2, this.y + 1));
        this.addEnabledPoint(new Point(this.x + 2, this.y - 1));
        this.addEnabledPoint(new Point(this.x - 2, this.y + 1));
        this.addEnabledPoint(new Point(this.x - 2, this.y - 1));

    }

    // метод проверяет точку на допустимость (входит в размер игрового поля и не совпадает с "дырками" на поле)
    // и в случае допустимости добавляет в множество доступных точек
    public void addEnabledPoint(Point p) {
        if (!p.containsTo(disabledPoints)) enabledPoints.add(p);

    }

    // метод проверяет содержится ли точка с такими же координатами в множестве точек
    public boolean containsTo(HashSet<Point> points) {
        boolean check = false;
        for (Point p : points) {
            if (this.x == p.x && this.y == p.y) check = true;
        }
        return check;
    }

    // метод добавляет точки с уникальными координатами из одного множества в другое
    public static HashSet<Point> addTo(HashSet<Point> p1, HashSet<Point> p2) {

        for (Point p : p1) {
            if (!p.containsTo(p2)) p2.add(p);
        }
        return p2;
    }
}




