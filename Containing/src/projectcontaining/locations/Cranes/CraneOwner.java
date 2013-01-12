/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.locations.Cranes;

import projectcontaining.xmlparser.ContainerData;

/**
 * Crane feedback interface
 * @author Hielke Hielkema
 */
public interface CraneOwner {
    void OnContainerPickUp(Crane crane, ContainerData container);
    void OnContainerPutDown(Crane crane, ContainerData container);
    void OnCraneDone(Crane crane);
}
