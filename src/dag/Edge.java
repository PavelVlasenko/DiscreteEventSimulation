package dag;

public class Edge
{
    public int startVertex;
    public int endVertex;
    public double communicationCost;

    @Override
    public String toString() {
        return "Edge{" +
                "startVertex=" + startVertex +
                ", endVertex=" + endVertex +
                ", communicationCost=" + communicationCost +
                '}';
    }
}
