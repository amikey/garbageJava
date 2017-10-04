/**
 * Created by fox on 26/09/17.
 */

public class main {
    public static Timer timer = new Timer();

    public static int iterations = 1000;

    public static int workHours = 6;

    public static int max_allowed_trucks = 10;
    public static int trucks = 0;
    public static int truckLoad = 300;

    public static int pointsNumber = 10;
    public static Routes[] nodes = new Routes[pointsNumber];
    public static double[][] matrix;

    public static Routes[] finalSolution = new Routes[pointsNumber];
    public static double finalFitness;

    public static void main (String[] args) {
        timer.start();
        Routes.initialize_garbage_trucks(1);
        Routes.initialize_collect_points();

        SolveLocal solve = new SolveLocal();
        solve.grasp();

        timer.stop();
        print_results();
    }

    private static void print_results() {
        System.out.print("Tempo para Execução (Segundos): " + timer.getTime());
        System.out.println();
        for (int index = 0; index < finalSolution.length; index++) {
            if (index == 0) {
                System.out.print("Solução Final: ");
            }
            System.out.print(finalSolution[index].getCollectPoint() + " ");
        }
        System.out.println();
        System.out.print("Função Objetiva = " + finalFitness);
    }
}
