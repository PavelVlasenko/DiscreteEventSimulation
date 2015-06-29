package dag;

import java.util.*;

/**
 * TODO: Java docs
 *
 * @author Pavel Vlasenko
 */
public class Dag implements Runnable
{
    public Set<Vertex> vertices = new HashSet<>();
    public Set<Edge> edges = new HashSet<>();

    public double CCR;
    public double Jr;

    public long TA;

    public List<Vertex> taskList;


    @Override
    public void run()
    {
        Date startTime = new Date();
        //System.out.println("===== Start job time - " + startTime);
        processDag();
        Date endTime = new Date();
        TA = endTime.getTime() - startTime.getTime();

        //System.out.println("===== End job time - " + endTime);
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
                '}';
    }
}
