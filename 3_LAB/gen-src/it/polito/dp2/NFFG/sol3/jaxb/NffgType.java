//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.02.14 alle 07:48:01 PM CET 
//


package it.polito.dp2.NFFG.sol3.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per NffgType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="NffgType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/nffgInfo}nameEntityType">
 *       &lt;sequence>
 *         &lt;element name="UpdateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Nodes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Node" type="{http://www.example.org/nffgInfo}NodeType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Policies">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ReachabilityPolicy" type="{http://www.example.org/nffgInfo}ReachabilityPolicyType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="TraversalPolicy" type="{http://www.example.org/nffgInfo}TraversalPolicyType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "NffgType", propOrder = {
    "updateTime",
    "nodes",
    "policies"
})
public class NffgType
    extends NameEntityType
{

    @XmlElement(name = "UpdateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTime;
    @XmlElement(name = "Nodes", required = true)
    protected NffgType.Nodes nodes;
    @XmlElement(name = "Policies", required = true)
    protected NffgType.Policies policies;

    /**
     * Recupera il valore della proprietà updateTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTime() {
        return updateTime;
    }

    /**
     * Imposta il valore della proprietà updateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTime(XMLGregorianCalendar value) {
        this.updateTime = value;
    }

    /**
     * Recupera il valore della proprietà nodes.
     * 
     * @return
     *     possible object is
     *     {@link NffgType.Nodes }
     *     
     */
    public NffgType.Nodes getNodes() {
        return nodes;
    }

    /**
     * Imposta il valore della proprietà nodes.
     * 
     * @param value
     *     allowed object is
     *     {@link NffgType.Nodes }
     *     
     */
    public void setNodes(NffgType.Nodes value) {
        this.nodes = value;
    }

    /**
     * Recupera il valore della proprietà policies.
     * 
     * @return
     *     possible object is
     *     {@link NffgType.Policies }
     *     
     */
    public NffgType.Policies getPolicies() {
        return policies;
    }

    /**
     * Imposta il valore della proprietà policies.
     * 
     * @param value
     *     allowed object is
     *     {@link NffgType.Policies }
     *     
     */
    public void setPolicies(NffgType.Policies value) {
        this.policies = value;
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
     *         &lt;element name="Node" type="{http://www.example.org/nffgInfo}NodeType" maxOccurs="unbounded" minOccurs="0"/>
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
        "node"
    })
    public static class Nodes {

        @XmlElement(name = "Node")
        protected List<NodeType> node;

        /**
         * Gets the value of the node property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the node property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNode().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link NodeType }
         * 
         * 
         */
        public List<NodeType> getNode() {
            if (node == null) {
                node = new ArrayList<NodeType>();
            }
            return this.node;
        }

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
     *         &lt;element name="ReachabilityPolicy" type="{http://www.example.org/nffgInfo}ReachabilityPolicyType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="TraversalPolicy" type="{http://www.example.org/nffgInfo}TraversalPolicyType" maxOccurs="unbounded" minOccurs="0"/>
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
        "reachabilityPolicy",
        "traversalPolicy"
    })
    public static class Policies {

        @XmlElement(name = "ReachabilityPolicy")
        protected List<ReachabilityPolicyType> reachabilityPolicy;
        @XmlElement(name = "TraversalPolicy")
        protected List<TraversalPolicyType> traversalPolicy;

        /**
         * Gets the value of the reachabilityPolicy property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the reachabilityPolicy property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReachabilityPolicy().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ReachabilityPolicyType }
         * 
         * 
         */
        public List<ReachabilityPolicyType> getReachabilityPolicy() {
            if (reachabilityPolicy == null) {
                reachabilityPolicy = new ArrayList<ReachabilityPolicyType>();
            }
            return this.reachabilityPolicy;
        }

        /**
         * Gets the value of the traversalPolicy property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the traversalPolicy property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTraversalPolicy().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TraversalPolicyType }
         * 
         * 
         */
        public List<TraversalPolicyType> getTraversalPolicy() {
            if (traversalPolicy == null) {
                traversalPolicy = new ArrayList<TraversalPolicyType>();
            }
            return this.traversalPolicy;
        }

    }

}
