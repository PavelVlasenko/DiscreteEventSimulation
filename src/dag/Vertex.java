package dag;

/**
 * TODO: Java docs
 *
 * @author Pavel Vlasenko
 */
public class Vertex
{
    public int index;
    public int averageExecutionTime;
    public double computationCost;
    public int EST;
    public int LST;

    @Override
    public String toString() {
        return "Vertex{" +
                "index=" + index +
                ", averageExecutionTime=" + averageExecutionTime +
                ", computationCost=" + computationCost +
                ", EST=" + EST +
                ", LST=" + LST +
                '}';
    }
}
