import dag.Dag;

import java.util.Scanner;

public class Main {

    public static double sf = 1d;

    public static void main(String[] args) {

        //Enter parameters
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of jobs");
        int dagNumb = Integer.valueOf(scanner.nextLine());

        System.out.println("Enter the number of tasks");
        int taskNumb = Integer.valueOf(scanner.nextLine());

        System.out.println("Enter the number iteration");
        int iteration = Integer.valueOf(scanner.nextLine());

        System.out.println("Enter experiment\r\n1 - Experiment N1 (Arrive time - 30unit)\r\n2 - Experiment N2(Arrive time - 5unit)\r\n0 - Arrive time - 0 unit");
        int experiment = Integer.valueOf(scanner.nextLine());

        System.out.println("======================================================\r\n                  Discrete Event Simulation\r\n======================================================");
        for(int i = 0; i < iteration; i++)
        {
            JobExecutor jobExecutor = new JobExecutor(experiment);
            DagGenerator dagGenerator = new DagGenerator();
            System.out.println("         START ITERATION N" + (i+1));
            System.out.println("...Generate dags");
            for (int j = 0; j < dagNumb; j++)
            {
                Dag dag = dagGenerator.createDag(taskNumb);
                DagProcessor dagProcessor = new DagProcessor(dag);
                dagProcessor.calculateEST();
                dagProcessor.calculateLST();
                dagProcessor.calculateTaskList();
                dagProcessor.calculateCCR();
                dagProcessor.calculateJobRank();
                jobExecutor.jobList.add(dag);
            }
            System.out.println("...Calculate all parameters");

            jobExecutor.calculateJobDeadline();
            jobExecutor.sortingJobsByRank();
            System.out.println("...Start Jobs processing");
            jobExecutor.start();
            jobExecutor.calculateSLR();
            System.out.println("...Calculate avg Makespan and avg SLR");
            jobExecutor.calculateAvgMakespan();
            jobExecutor.calculateAvgSLR();
            System.out.println();
            jobExecutor.showJobs();
            System.out.println();
            System.out.println("avg Makespan = " + jobExecutor.avgMakespan + ", avg SLR = " + jobExecutor.avgSLR);
            System.out.println();

            System.out.println("...Save iteration in file: Iteration" + (i+1) + ".txt");
            jobExecutor.writeOnFile(i);
            System.out.println("___________________________________________________________________\r\n");
        }
    }
}
