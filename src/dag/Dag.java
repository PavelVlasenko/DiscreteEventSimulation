package dag;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Java docs
 *
 * @author Pavel Vlasenko
 */
public class Dag
{
    public List<Vertex> vertices = new ArrayList<>();
    public List<Edge> edges = new ArrayList<>();

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
}
