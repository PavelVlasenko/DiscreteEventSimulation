import dag.Dag;
import dag.Edge;
import dag.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavel on 25.06.15.
 */
public class DagProcessor
{
    public void calculateEstLst(Dag dag)
    {
        //find V entry
        List<Vertex> entryVertices = new ArrayList<>();
        for(Vertex vertex : dag.vertices)
        {
            boolean hasPredecessors = false;
            for(Edge edge : dag.edges)
            {
                if(edge.endVertex == vertex.index)
                {
                    hasPredecessors = true;
                    break;
                }
            }
            if(!hasPredecessors)
            {
                entryVertices.add(vertex);
            }
        }


    }
}
