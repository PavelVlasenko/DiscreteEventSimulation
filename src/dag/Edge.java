package dag;

/**
 * TODO: Java docs
 *
 * @author Pavel Vlasenko
 */
public class Edge
{
    public int startVertex;
    public int endVertex;
    public double communicationCost;

    public int LST;
    public int EST;

    @Override
    public String toString() {
        return "Edge{" +
                "startVertex=" + startVertex +
                ", endVertex=" + endVertex +
                ", communicationCost=" + communicationCost +
                '}';
    }
}
