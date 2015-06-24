import dag.Dag;

import java.util.Random;

/**
 * TODO: Java docs
 *
 * @author Pavel Vlasenko
 */
public class DagGenerator
{
    public Dag createDag(int vertex)
    {
        Dag dag = new Dag();

        //create triangle matrix
        int [][] matrix = new int[vertex][vertex];
        for(int i = 0; i < vertex; i++) {
            for (int j = i+1; j < vertex; j++) {
                //maybePutAnEdgeBetween(i, j);
                Random random = new Random();
                matrix[i][j] = random.nextInt(1);
            }
        }

        return null;
    }

}
