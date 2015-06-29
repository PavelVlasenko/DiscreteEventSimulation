package dag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO: Java docs
 *
 * @author Pavel Vlasenko
 */
public class Dag
{
    public Set<Vertex> vertices = new HashSet<>();
    public Set<Edge> edges = new HashSet<>();

    public double CCR;

    public List<Vertex> taskList;

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
}
