import dag.Dag;
import dag.Edge;
import dag.Vertex;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JobExecutor
{
    public List<Dag> jobList = new ArrayList<>();

    //Pool of threads with 4 processors.
    ExecutorService executor = Executors.newFixedThreadPool(4);

    public void start()
    {
        for(Dag dag : jobList)
        {
            executor.execute(dag);
        }
        executor.shutdown();
        while (!executor.isTerminated())
        {
        }

        //Uncomment one of experiment if needed.

        //Experiment N1(Loose situation). Arrival time 30unit
        /*for(Dag dag : jobList)
        {
             try
             {
                Thread.sleep(5);
             }
             catch (InterruptedException e)
             {
                e.printStackTrace();
             }
            executor.execute(dag);
        }
        executor.shutdown();
        while (!executor.isTerminated())
        {
        }*/

        //Experiment N2(Tight situation). Arrival time 5unit
        /*for(Dag dag : jobList)
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            executor.execute(dag);
        }
        executor.shutdown();
        while (!executor.isTerminated())
        {
        }*/
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

        Date startIterationTime = new Date();
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
        Date endIterationTime = new Date();
        long exe = endIterationTime.getTime() - startIterationTime.getTime();

        for(Dag d : jobList)
        {
            if(d.Dd > exe)
            {
                d.Dd = exe;
            }
        }
    }

    public void calculateSLR()
    {
        long minMakespan = Long.MAX_VALUE;
        for(Dag d : jobList)
        {
            if(d.makespan < minMakespan)
            {
                minMakespan = d.makespan;
            }
        }

        for(Dag d : jobList)
        {
            d.SLR = d.makespan/(double)minMakespan;
        }
    }

    public void showJobs()
    {
        for(Dag d : jobList)
        {
            System.out.println(d);
        }
    }

    public void writeOnFile(int iteration)
    {
        File f = new File("Iteration " + iteration + ".txt");
        try
        {
            if(!f.exists())
            {
                f.createNewFile();
            }
        }
        catch(IOException e)
        {
             System.out.println("Error creating file iteration " + iteration);
        }
        //print Dag with CCR, JR, Dd, Makespan and SLR
        String s = "";
        for(Dag d : jobList)
        {
            s += d.toString() + "\r\n";
        }


        for(Dag d : jobList)
        {
            s += "\r\n\r\n =====================================================================\r\nDAG N" + jobList.indexOf(d) +"\r\n======================================================\r\n";

            s += "\r\n\r\n =====================================================================\r\nDAG VERTICES\r\n======================================================\r\n";
            for(Vertex v : d.taskList)
            {
                s+=v.toString() + "\r\n";
            }

            s += "\r\n\r\n =====================================================================\r\nDAG EDGES\r\n======================================================\r\n";
            for(Edge e : d.edges)
            {
                s+=e.toString() + "\r\n";
            }
        }

        //write to file
        try
        {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
            writer.write(s);
            writer.close();
        }
        catch(IOException e)
        {
             System.out.println("Error while writing to file");
        }

    }
}
