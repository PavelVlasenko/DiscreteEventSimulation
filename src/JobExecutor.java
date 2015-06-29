import dag.Dag;

import java.util.ArrayList;
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
        Date startIteration = new Date();
        System.out.println("======= Start iteration time " + startIteration);
        for(Dag dag : jobList)
        {
            executor.execute(dag);
        }
        executor.shutdown();
        while (!executor.isTerminated())
        {
        }
        Date finishIteration = new Date();

        //calculate MakeSpan and SLR



        System.out.println("======= Finish iteration time " + finishIteration);
    }
}
