import dag.Dag;

import java.util.Comparator;

/**
 * TODO: Java docs
 *
 * @author Pavel Vlasenko
 */
public class JobComparator implements Comparator<Dag>
{
    @Override
    public int compare(Dag o1, Dag o2)
    {
        // First by CCR.
        if (o2.CCR != o1.CCR)
        {
            return o1.CCR < o2.CCR ? -1 : 1;
        }

        // Next by Dd
        if (o2.Dd != o1.Dd)
        {
            return o1.Dd < o2.Dd ? -1 : 1;
        }

        // Finally by Jr
        if (o2.Jr != o1.Jr)
        {
            return o2.Dd < o1.Dd ? -1 : 1;
        }
        else
        {
            return 0;
        }
    }
}
