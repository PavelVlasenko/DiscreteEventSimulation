import dag.Dag;

public class Main {

    public static double sf = 1d;

    public static void main(String[] args) {

        int dagNumb = 3;
        int taskNumb = 3;
        int iteration = 1;



        for(int i = 0; i < iteration; i++)
        {
            JobExecutor jobExecutor = new JobExecutor();
            DagGenerator dagGenerator = new DagGenerator();
            System.out.println("========== Start iteration №" + i + " ===========");
            System.out.println("======= Generate dags ========");
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
            jobExecutor.showSourceJobs();

            System.out.println("======= Ordered jobs ========");
            jobExecutor.sortingJobsByRank();
            jobExecutor.showSourceJobs();
        }


//        DagGenerator generator = new DagGenerator();
//        Dag dag1 = generator.createDag(30);
//        dag1.showDag();
//
//        System.out.println("Calculate EST");
//
//        DagProcessor processor = new DagProcessor(dag1);
//        processor.calculateEST();
//
//        System.out.println("Calculate LST");
//        processor.calculateLST();
//
//        dag1.showDag();
//
//        System.out.println("Create taskList");
//        processor.calculateTaskList();
//        dag1.showTaskList();
//
//        System.out.println("Calculate CCR");
//        processor.calculateCCR();
//
//        System.out.println("Calculate JobRank");
//        processor.calculateJobRank();
//        dag1.showDag();
//        System.out.println(dag1);
//
//        System.out.println("Start Job Executor");
//        JobExecutor jobExecutor = new JobExecutor();
//        jobExecutor.jobList.add(dag1);
//        jobExecutor.start();
//
//        System.out.println("Hello World!");
    }
}
