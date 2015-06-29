import dag.Vertex;

import java.util.Comparator;

/**
 * Created by pavel on 29.06.15.
 */
public class TaskComparator implements Comparator<Vertex>
{
    @Override
    public int compare(Vertex o1, Vertex o2)
    {
        if(o1.LST == o2.LST)
            return 0;
        return o1.LST < o2.LST ? -1 : 1;
    }
}
