//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.02.23 alle 05:13:46 PM CET 
//


package it.polito.dp2.NFFG.sol3.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per LinkType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="LinkType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/nffgInfo}nameEntityType">
 *       &lt;sequence>
 *         &lt;element name="destinationNode" type="{http://www.example.org/nffgInfo}nameEntityType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LinkType", propOrder = {
    "destinationNode"
})
public class LinkType
    extends NameEntityType
{

    @XmlElement(required = true)
    protected NameEntityType destinationNode;

    /**
     * Recupera il valore della proprietà destinationNode.
     * 
     * @return
     *     possible object is
     *     {@link NameEntityType }
     *     
     */
    public NameEntityType getDestinationNode() {
        return destinationNode;
    }

    /**
     * Imposta il valore della proprietà destinationNode.
     * 
     * @param value
     *     allowed object is
     *     {@link NameEntityType }
     *     
     */
    public void setDestinationNode(NameEntityType value) {
        this.destinationNode = value;
    }

}
