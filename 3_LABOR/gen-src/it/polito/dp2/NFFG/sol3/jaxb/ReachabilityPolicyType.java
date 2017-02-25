//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.02.20 alle 09:45:09 PM CET 
//


package it.polito.dp2.NFFG.sol3.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ReachabilityPolicyType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ReachabilityPolicyType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/nffgInfo}PolicyType">
 *       &lt;sequence>
 *         &lt;element name="SrcNode" type="{http://www.example.org/nffgInfo}nameEntityType"/>
 *         &lt;element name="DstNode" type="{http://www.example.org/nffgInfo}nameEntityType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReachabilityPolicyType", propOrder = {
    "srcNode",
    "dstNode"
})
@XmlSeeAlso({
    TraversalPolicyType.class
})
public class ReachabilityPolicyType
    extends PolicyType
{

    @XmlElement(name = "SrcNode", required = true)
    protected NameEntityType srcNode;
    @XmlElement(name = "DstNode", required = true)
    protected NameEntityType dstNode;

    /**
     * Recupera il valore della proprietà srcNode.
     * 
     * @return
     *     possible object is
     *     {@link NameEntityType }
     *     
     */
    public NameEntityType getSrcNode() {
        return srcNode;
    }

    /**
     * Imposta il valore della proprietà srcNode.
     * 
     * @param value
     *     allowed object is
     *     {@link NameEntityType }
     *     
     */
    public void setSrcNode(NameEntityType value) {
        this.srcNode = value;
    }

    /**
     * Recupera il valore della proprietà dstNode.
     * 
     * @return
     *     possible object is
     *     {@link NameEntityType }
     *     
     */
    public NameEntityType getDstNode() {
        return dstNode;
    }

    /**
     * Imposta il valore della proprietà dstNode.
     * 
     * @param value
     *     allowed object is
     *     {@link NameEntityType }
     *     
     */
    public void setDstNode(NameEntityType value) {
        this.dstNode = value;
    }

}
