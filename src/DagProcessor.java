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
    Set<Vertex> entryVertices;

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
        this.entryVertices = entryVertices;
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
        Set<Vertex> procesedVertices = new HashSet<>(dag.vertices);
        Set<Vertex> currentVertices = findEntryVertex();
        Set<Vertex> bufferVertices = new HashSet<>();

        //set EST for entry vertices and remove from processedVertices
        for(Vertex v : currentVertices)
        {
            //v.EST = v.averageExecutionTime;
            procesedVertices.remove(v);
        }

        //while processedList has vertices, calculate EST
        while(procesedVertices.size() > 0)
        {
            currentVertices = findSuccessors(currentVertices);
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

    public void calculateLST()
    {
        Set<Vertex> procesedVertices = new HashSet<>(dag.vertices);
        Set<Vertex> currentVertices = findEndVertex();
        Set<Vertex> bufferVertices = new HashSet<>();

        //set LST for enf vertices and remove from processedVertices
        for(Vertex v : currentVertices)
        {
            v.LST = v.EST;
            procesedVertices.remove(v);
        }

        //while processedList has vertices, calculate EST
        while(procesedVertices.size() > 0)
        {
            currentVertices = findPredecessors(currentVertices);
            for(Vertex v : currentVertices)
            {
                if(defineLST(v))
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
                if (defineLST(v))
                {
                    iterator.remove();
                    procesedVertices.remove(v);
                }
            }
        }
    }

    public Set<Vertex> findEndVertex()
    {
        //find V entry
        Set<Vertex> endVertices = new HashSet<>();
        for(Vertex vertex : dag.vertices)
        {
            boolean hasSuccessors = false;
            for(Edge edge : dag.edges)
            {
                if(edge.startVertex == vertex.index)
                {
                    hasSuccessors = true;
                    break;
                }
            }
            if(!hasSuccessors)
            {
                endVertices.add(vertex);
            }
        }
        return endVertices;
    }

    private Set<Vertex> findPredecessors(Set<Vertex> current)
    {
        Set<Vertex> predecessors = new HashSet<>();
        for(Vertex v : current)
        {
            for(Edge e : dag.edges)
            {
                if(e.endVertex == v.index)
                {
                    predecessors.add(dag.getVertexByIndex(e.startVertex));
                }
            }
        }
        return predecessors;
    }

    private Set<Vertex> findSuccessors(Set<Vertex> current)
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
            if(v.EST == 0 & !entryVertices.contains(v))
            {
                return false;
            }
            else
            {
                if(maxEST < (v.EST + v.averageExecutionTime))
                {
                    maxEST = v.EST + v.averageExecutionTime;
                }
            }
        }
        vertex.EST = maxEST;
        return true;
    }

    private boolean defineLST(Vertex vertex)
    {
        Set<Vertex> successors = findSuccessors(vertex);
        boolean isDefined = false;
        int minLST = Integer.MAX_VALUE;
        for(Vertex v : successors)
        {
            if(v.LST == 0)
            {
                return false;
            }
            else
            {
                if(minLST > (v.LST - vertex.averageExecutionTime))
                {
                    minLST = (v.LST - vertex.averageExecutionTime);
                }
            }
        }
        vertex.LST = minLST;
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

    private Set<Vertex> findSuccessors(Vertex v)
    {
        Set<Vertex> successors = new HashSet<>();
        for(Edge e : dag.edges)
        {
            if(e.startVertex == v.index)
            {
                successors.add(dag.getVertexByIndex(e.endVertex));
            }
        }
        return successors;
    }

}
