import dag.Dag;

public class Main {

    public static double sf = 1d;

    public static void main(String[] args) {

        int dagNumb = 3;
        int taskNumb = 3;
        int iteration = 2;

        System.out.println("======================================================\r\n                  Discrete Event Simulation\r\n======================================================");
        for(int i = 0; i < iteration; i++)
        {
            JobExecutor jobExecutor = new JobExecutor();
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

            jobExecutor.calculateJobDeadline();
            jobExecutor.sortingJobsByRank();
            System.out.println("...Start Jobs processing");
            jobExecutor.start();
            jobExecutor.calculateSLR();
            System.out.println();
            jobExecutor.showJobs();
            System.out.println();

            System.out.println("...Save iteration in file: Iteration" + (i+1) + ".txt");
            jobExecutor.writeOnFile(i);
            System.out.println("___________________________________________________________________\r\n");
        }
    }
}
