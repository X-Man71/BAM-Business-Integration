
package de.hfu.vschufa.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.5.0
 * Sat Dec 17 15:52:38 CET 2011
 * Generated source version: 2.5.0
 */

@XmlRootElement(name = "getScoreValueResponse", namespace = "http://vschufa.hfu.de/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getScoreValueResponse", namespace = "http://vschufa.hfu.de/")

public class GetScoreValueResponse {

    @XmlElement(name = "return")
    private int _return;

    public int getReturn() {
        return this._return;
    }

    public void setReturn(int new_return)  {
        this._return = new_return;
    }

}
