import dag.Dag;
import dag.Edge;
import dag.Vertex;

import java.util.*;

/**
 * Created by pavel on 25.06.15.
 */
public class DagProcessor
{
    Dag dag;

    public DagProcessor(Dag dag) {
        this.dag = dag;
    }

    public Set<Vertex> findEntryVertex()
    {
        //find V entry
        Set<Vertex> entryVertices = new HashSet<>();
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
                //vertex.EST = vertex.averageExecutionTime;
            }
        }
        return entryVertices;
    }

   /* private List<Vertex> findPredecessors(List<Vertex> dag, Vertex vertex)
    {
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
                vertex.EST = vertex.averageExecutionTime;
            }
        }
        return entryVertices;
    }*/

    public void calculateEST()
    {
        Set<Vertex> allVertices = new HashSet<>(dag.vertices);
        Set<Vertex> procesedVertices = new HashSet<>(dag.vertices);
        Set<Vertex> currentVertices = findEntryVertex();
        Set<Vertex> bufferVertices = new HashSet<>();

        //set EST for entry vertices and remove from processedVertices
        for(Vertex v : currentVertices)
        {
            v.EST = v.averageExecutionTime;
            procesedVertices.remove(v);
        }

        //while processedList has vertices, calculate EST
        while(procesedVertices.size() > 0)
        {
            currentVertices = findSuccessors(currentVertices, dag);
            for(Vertex v : currentVertices)
            {
                if(defineEST(v))
                {
                    procesedVertices.remove(v);
                }
                else
                {
                    bufferVertices.add(v);
                }
            }

            for (Iterator<Vertex> iterator = bufferVertices.iterator(); iterator.hasNext(); )
            {
                Vertex v = iterator.next();
                if (defineEST(v))
                {
                    iterator.remove();
                    procesedVertices.remove(v);
                }
            }
        }
    }

    private Set<Vertex> findSuccessors(Set<Vertex> current, Dag dag)
    {
        Set<Vertex> successors = new HashSet<>();
        for(Vertex v : current)
        {
            for(Edge e : dag.edges)
            {
                if(e.startVertex == v.index)
                {
                    successors.add(dag.getVertexByIndex(e.endVertex));
                }
            }
        }
        return successors;
    }

    private boolean defineEST(Vertex vertex)
    {
        Set<Vertex> predecessors = findPredecessors(vertex);
        boolean isDefined = false;
        int maxEST = 0;
        for(Vertex v : predecessors)
        {
            if(v.EST == 0)
            {
                return false;
            }
            else
            {
                if(maxEST < v.EST)
                {
                    maxEST = v.EST;
                }
            }
        }
        vertex.EST = maxEST + vertex.averageExecutionTime;
        return true;
    }

    private Set<Vertex> findPredecessors(Vertex v)
    {
        Set<Vertex> predecessors = new HashSet<>();
        for(Edge e : dag.edges)
        {
            if(e.endVertex == v.index)
            {
                predecessors.add(dag.getVertexByIndex(e.startVertex));
            }
        }
        return predecessors;
    }

}
