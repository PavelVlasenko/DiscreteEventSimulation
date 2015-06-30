import dag.Dag;
import dag.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO: Java docs
 *
 * @author Pavel Vlasenko
 */
public class JobExecutor
{
    public List<Dag> jobList = new ArrayList<>();

    ExecutorService executor = Executors.newFixedThreadPool(5);

    public void start()
    {
//        Date startIteration = new Date();
//        System.out.println("======= Start iteration time " + startIteration);
        for(Dag dag : jobList)
        {
            executor.execute(dag);
        }
        executor.shutdown();
        while (!executor.isTerminated())
        {
        }
       // Date finishIteration = new Date();

        //calculate MakeSpan and SLR



     //   System.out.println("======= Finish iteration time " + finishIteration);
    }

    public void sortingJobsByRank()
    {
        Collections.sort(jobList, new JobComparator());
    }

    public void calculateJobDeadline()
    {
        for(Dag d : jobList)
        {
            //calculate st
            double st = 0d;
            for (Vertex v : d.vertices)
            {
                st += v.computationCost;
            }
            d.st = st;
        }

        for(Dag d : jobList)
        {
            Date startJobTime =new Date();
            for(Vertex v : d.taskList)
            {
                try
                {
                    Thread.sleep(v.averageExecutionTime);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            Date endJobTime = new Date();
            long ta = endJobTime.getTime() - startJobTime.getTime();
            d.Dd =d.st + Main.sf*ta;
        }
    }

    public void showSourceJobs()
    {
        for(Dag d : jobList)
        {
            System.out.println(d);
        }
    }

    public void showOrderedJobs()
    {
        for(Dag d : jobList)
        {
            System.out.println(d);
        }
    }
}
