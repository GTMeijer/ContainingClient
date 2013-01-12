package projectcontaining;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import projectcontaining.xmlparser.ContainerData;
import projectcontaining.xmlparser.Point3D;

/**
 * Class that takes care of container sorting.
 * @author Kuchinawa
 */
public class ContainerOrderSorter 
{
     /**
     * Sort a list of ContainerData based on their position, the Z position is reversed.
     * @param containers List of ContainerData to sort
     */
    public static void sortContainers(List<ContainerData> containers) 
    {
        // Sort the containers based on position
        Collections.sort(containers, new Comparator<ContainerData>() 
        {
            @Override
            public int compare(ContainerData contData1, ContainerData contData2)
            {
                int e = contData1.getEnterDateTimeFrom().compareTo(contData2.getEnterDateTimeFrom());
                
                if(e == 0)
                    return positionSort(contData1.getLocation(), contData2.getLocation());
                else
                    return e;
            }
        });        
        
        for(ContainerData cd : containers)
            System.out.println(cd.getLocation().getX() + " " + cd.getLocation().getY() + " " + cd.getLocation().getZ() + " - " + cd.getEnterDateTimeFrom().toString());
    }
    
    /**
     * Compare a Point3D to another Point3D, the Z position is reversed
     * @param pos1 first position
     * @param pos2 position to compare to
     * @return -1 = lower, 0 = equal, 1 = higher
     */
    private static int positionSort(Point3D pos1, Point3D pos2)
    {
        if(pos1.getX() == pos2.getX())
        {
            if(pos1.getY() == pos2.getY())
            {
                if(pos1.getZ() == pos2.getZ())
                    return 0; //Same
                else if(pos1.getZ() > pos2.getZ())
                    return -1; //Lower
                else
                    return 1; //Higher
            }
            else if(pos1.getY() > pos2.getY())
                return 1;  //Higher
            else
                return -1; //Lower
        }
        else if(pos1.getX() > pos2.getX())
            return 1; //Higher
        else
            return -1; //Lower
    }
}