package Horse;

import java.util.HashSet;

/**
 * Created by bolshakova on 21.05.2016.
 */
public class GameField {
     static int n; // размер игрового поля N x N
     static HashSet<Point> disabledPoints = new HashSet<Point>(); // множество недоступных полей на игровом поле (дырок)
     static HashSet<Point> pointsForStep = new HashSet<Point>(); // множество полей на игровом поле, куда может попасть конь
     static int[] levelField = new int [200];

     public static void addLevelField () {
          for (int i = 0; i < 200; i++) {
               levelField[i] = -1;
          }
     }

     public static void addDisabledPoints () {
          for (int i = -2; i < n+2; i++) {
               disabledPoints.add(new Point(-2, i));
               disabledPoints.add(new Point(-1, i));
               disabledPoints.add(new Point(n, i));
               disabledPoints.add(new Point(n+1, i));
               disabledPoints.add(new Point(i, -2));
               disabledPoints.add(new Point(i, -1));
               disabledPoints.add(new Point(i, n));
               disabledPoints.add(new Point(i, n+1));
          }
     }

}


