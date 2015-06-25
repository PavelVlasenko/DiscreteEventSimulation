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

    @Override
    public String toString() {
        return "Vertex{" +
                "index=" + index +
                ", averageExecutionTime=" + averageExecutionTime +
                ", computationCost=" + computationCost +
                '}';
    }
}
