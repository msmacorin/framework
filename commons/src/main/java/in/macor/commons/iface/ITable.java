/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.macor.commons.iface;

import org.joda.time.LocalDate;

/**
 *
 * @author macorin
 */
public interface ITable {
 
    public IEnums getSituation();
    public void setSituation(IEnums enums);
    public LocalDate getCreation();
    public LocalDate getChanged();
}
