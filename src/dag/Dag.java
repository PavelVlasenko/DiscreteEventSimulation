package dag;

import java.util.*;

public class Dag implements Runnable
{
    public Set<Vertex> vertices = new HashSet<>();
    public Set<Edge> edges = new HashSet<>();

    public double CCR;
    public double Jr;
    public double Dd;

    public long makespan;
    public double SLR;

    public double st;

    public List<Vertex> taskList;


    @Override
    public void run()
    {
        Date startTime = new Date();
        processDag();
        Date endTime = new Date();
        makespan = endTime.getTime() - startTime.getTime();
    }

    private void processDag()
    {
        try {
            for(Vertex v : taskList)
            {
                Thread.sleep(v.averageExecutionTime);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void showDag() {
        System.out.println("==================== Vertices   ================");
        for(Vertex vertex : vertices)
       {
           System.out.println(vertex);
       }

       System.out.println("==================== Edges ================");

       for(Edge edge : edges)
       {
           System.out.println(edge);
       }
    }

    public Vertex getVertexByIndex(int index)
    {
        for(Vertex v : vertices)
        {
            if(v.index == index)
            {
                return v;
            }
        }
        return null;
    }

    public void showTaskList()
    {
        System.out.println("==================== TaskList   ================");
        for(Vertex vertex : taskList)
        {
            System.out.println(vertex);
        }

    }

    @Override
    public String toString()
    {
        return "Dag{" +
                "CCR=" + CCR +
                ", Jr=" + Jr +
                ", Dd=" + Dd +
                ", SLR=" + SLR +
                ", makespan=" + makespan +
                '}';
    }
}
