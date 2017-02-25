//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.02.20 alle 09:45:09 PM CET 
//


package it.polito.dp2.NFFG.sol3.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TraversalPolicyType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TraversalPolicyType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/nffgInfo}ReachabilityPolicyType">
 *       &lt;sequence>
 *         &lt;element name="TraversedFTypes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="FType" type="{http://www.example.org/nffgInfo}FunctionalType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TraversalPolicyType", propOrder = {
    "traversedFTypes"
})
public class TraversalPolicyType
    extends ReachabilityPolicyType
{

    @XmlElement(name = "TraversedFTypes", required = true)
    protected TraversalPolicyType.TraversedFTypes traversedFTypes;

    /**
     * Recupera il valore della proprietà traversedFTypes.
     * 
     * @return
     *     possible object is
     *     {@link TraversalPolicyType.TraversedFTypes }
     *     
     */
    public TraversalPolicyType.TraversedFTypes getTraversedFTypes() {
        return traversedFTypes;
    }

    /**
     * Imposta il valore della proprietà traversedFTypes.
     * 
     * @param value
     *     allowed object is
     *     {@link TraversalPolicyType.TraversedFTypes }
     *     
     */
    public void setTraversedFTypes(TraversalPolicyType.TraversedFTypes value) {
        this.traversedFTypes = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="FType" type="{http://www.example.org/nffgInfo}FunctionalType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "fType"
    })
    public static class TraversedFTypes {

        @XmlElement(name = "FType")
        @XmlSchemaType(name = "string")
        protected List<FunctionalType> fType;

        /**
         * Gets the value of the fType property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the fType property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFType().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FunctionalType }
         * 
         * 
         */
        public List<FunctionalType> getFType() {
            if (fType == null) {
                fType = new ArrayList<FunctionalType>();
            }
            return this.fType;
        }

    }

}
