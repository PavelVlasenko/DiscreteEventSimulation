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

    private int experiment;
    public double avgMakespan;
    public double avgSLR;

    public JobExecutor(int experiment)
    {
        this.experiment = experiment;
    }

    public void start()
    {
        if(experiment == 0)
        {
            for(Dag dag : jobList)
            {
                executor.execute(dag);
            }
            executor.shutdown();
            while(!executor.isTerminated())
            {
            }
        }

        //Experiment N1(Loose situation). Arrival time 30unit
        else if(experiment == 1)
        {
            for(Dag dag : jobList)
            {
                try
                {
                    Thread.sleep(5);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                executor.execute(dag);
            }
            executor.shutdown();
            while(!executor.isTerminated())
            {
            }
        }

        //Experiment N2(Tight situation). Arrival time 5unit
        else if(experiment == 2)
        for(Dag dag : jobList)
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
        }
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

    public void calculateAvgMakespan()
    {
        long sumMakespan = 0;
        for(Dag d : jobList)
        {
            sumMakespan += d.makespan;
        }
        avgMakespan = (double)sumMakespan/jobList.size();
    }

    public void calculateAvgSLR()
    {
        double sumSLR = 0;
        for(Dag d : jobList)
        {
            sumSLR += d.SLR;
        }
        avgSLR = sumSLR/(double)jobList.size();
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
        File f = new File("Iteration " + (++iteration) + ".txt");
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
        String s = "=================================================================================\r\n               Iteration N" + iteration + "\r\n=================================================================================\r\n\r\n";
        s+="DAG LIST\r\n\r\n";
        for(Dag d : jobList)
        {
            s += d.toString() + "\r\n";
        }

        s+="\r\navg Makespan = " + avgMakespan + ", avg SLR = " + avgSLR + "\r\n";

        for(Dag d : jobList)
        {
            s += "_________________________________________________________________________________\r\nDAG N" + jobList.indexOf(d);

            s += "\r\nDAG VERTICES\r\n";
            for(Vertex v : d.taskList)
            {
                s+=v.toString() + "\r\n";
            }

            s += "\r\nDAG EDGES\r\n";
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
