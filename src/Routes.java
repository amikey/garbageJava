import java.util.Collections;
import java.util.Random;

/**
 * Created by fox on 26/09/17.
 */
public class Routes {
    private double x;
    private double y;
    private double garbage;
    private double collectTime; // use seconds
    private int collectPoint;

    public Routes(double posX, double posY, double garbageLoad, double timeToCollect, int point) {
        x = posX;
        y = posY;
        garbage = garbageLoad;
        collectTime = timeToCollect;
        collectPoint = point;
    }

    public double getX () {
        return x;
    }

    public double getY () {
        return y;
    }

    public double getGarbage () {
        return garbage;
    }

    public double getCollectTime () {
        return collectTime;
    }

    public int getCollectPoint() {
        return collectPoint;
    }

    public static Routes[] generate_random_solution () {
        int size = main.pointsNumber;
        Routes[] randomSolution = new Routes[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            if (i == (main.pointsNumber - 1) || i == 0) {
                randomSolution[i] = new Routes(0, 0, 0, 0, 0);
            } else {
                randomSolution[i] = new Routes(i*random.nextInt(30), i*random.nextInt(30), i*random.nextInt(10), i*random.nextInt(10), i);
            }
        }
        //shuffle the array
        Random rgen = new Random();  // Random number generator

        for (int i=1; i < (randomSolution.length - 1); i++) {
            int randomPosition = rgen.nextInt((randomSolution.length - 2) + 1 - 1) + 1;
            Routes temp = randomSolution[i];
            randomSolution[i] = randomSolution[randomPosition];
            randomSolution[randomPosition] = temp;
        }
        return randomSolution;
    }

    public static void initialize_garbage_trucks (int qty) {
        if (qty <= main.max_allowed_trucks) {
            main.trucks = qty;
        } else {
            System.out.println("Número máximo de caminhões excedido.");
            System.exit(1);
        }
    }

    public static void initialize_collect_points() {
        Random random = new Random();
        for (int i = 0; i < main.nodes.length; i++){
            main.nodes[i] = new Routes(i*random.nextInt(30), i*random.nextInt(30), i*random.nextInt(10), i*random.nextInt(10), i);
        }
        main.matrix = Routes.build_matrix(main.nodes);
    }

    public static double[][] build_matrix (Routes[] collectPoints) {
        double[][] matrix = new double[collectPoints.length][5];
        for (int i = 0; i < collectPoints.length; i++) {
            matrix[i][0] = collectPoints[i].x;
            matrix[i][1] = collectPoints[i].y;
            matrix[i][2] = collectPoints[i].garbage;
            matrix[i][3] = collectPoints[i].collectTime;
            matrix[i][4] = collectPoints[i].collectPoint;
        }
        return matrix;
    }

    //    public static void build_distance_matrix () {
//        double distance = 0;
//        double[][] tempDistance = new double[matrix.length][matrix.length];
//        for (int i = 0; i < matrix.length; i ++) {
//            for (int j = 0; j < matrix.length; j ++) {
//                distance = ObjetiveFunction.calculate_distance(matrix[i][0], matrix[j][0], matrix[i][1], matrix[j][1]);
//                tempDistance[i][j] = distance;
//            }
//        }
//        distanceMatrix = new double[tempDistance.length][tempDistance[0].length];
//        distanceMatrix = tempDistance;
//
//        for (int i = 0; i < distanceMatrix.length; i ++) {
//            for (int j = 0; j < distanceMatrix[0].length; j++) {
//                System.out.print(distanceMatrix[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }

    public static Routes[] get_new_neighbor(Routes[] route) {
        Random rgen = new Random();  // Random number generator

        for (int i=1; i < (route.length-1); i++) {
            int randomPosition = rgen.nextInt((route.length - 2) + 1 - 1) + 1;
            Routes temp = route[i];
            route[i] = route[randomPosition];
            route[randomPosition] = temp;
        }
        return route;
    }

}
