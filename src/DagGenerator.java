import dag.Dag;
import dag.Edge;
import dag.Vertex;

import java.util.Random;

/**
 * TODO: Java docs
 *
 * @author Pavel Vlasenko
 */
public class DagGenerator
{
    public Dag createDag(int vertexNumb)
    {
        Random random = new Random();
        Dag dag = new Dag();

        //create triangle matrix
        int [][] matrix = new int[vertexNumb][vertexNumb];
        for(int i = 0; i < vertexNumb; i++) {

            boolean edgeExist = false;
            for (int j = i+1; j < vertexNumb; j++) {
                //maybePutAnEdgeBetween(i, j);
                int value = random.nextInt(2);
                matrix[i][j] = value;
                if (value == 1)
                {
                    edgeExist = true;
                }
            }
            if (!edgeExist & i!= vertexNumb -1)
            {
                int jRandom = random.nextInt(vertexNumb - i - 1) + i + 1;
                matrix[i][jRandom] = 1;
            }
        }

        //it's needed for latest vertex
        int iRandom = random.nextInt(vertexNumb - 2);
        matrix[iRandom][vertexNumb -1] = 1;

        for(int i = 0; i < vertexNumb; i++)
        {
            for(int j = 0; j < vertexNumb; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.print("\r\n");
        }

        //create vertices
        for(int i = 0; i < vertexNumb; i++)
        {
            Vertex vertex = new Vertex();
            vertex.index = i;
            vertex.averageExecutionTime = 25 + random.nextInt(75);
            vertex.computationCost = (2 + random.nextInt(5))/10.0;
            dag.vertices.add(vertex);
        }

        //create edges
        for(int i = 0; i < vertexNumb; i++)
        {
            for (int j = i+1; j < vertexNumb; j++)
            {
                if(matrix[i][j] == 1)
                {
                    Edge edge = new Edge();
                    edge.startVertex = i;
                    edge.endVertex = j;
                    edge.communicationCost = (3 + random.nextInt(2))/10.0;
                    dag.edges.add(edge);
                }
            }
        }

        return dag;
    }

}
