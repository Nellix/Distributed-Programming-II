//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.02.10 alle 04:49:37 PM CET 
//


package it.polito.dp2.NFFG.sol1.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per PolicyType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="PolicyType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/nffgInfo}nameEntityType">
 *       &lt;sequence>
 *         &lt;element name="VerificationResult" type="{http://www.example.org/nffgInfo}VerificationResultType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="PolicyResult" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolicyType", propOrder = {
    "verificationResult"
})
@XmlSeeAlso({
    ReachabilityPolicyType.class
})
public class PolicyType
    extends NameEntityType
{

    @XmlElement(name = "VerificationResult")
    protected VerificationResultType verificationResult;
    @XmlAttribute(name = "PolicyResult", required = true)
    protected boolean policyResult;

    /**
     * Recupera il valore della proprietà verificationResult.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getVerificationResult() {
        return verificationResult;
    }

    /**
     * Imposta il valore della proprietà verificationResult.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setVerificationResult(VerificationResultType value) {
        this.verificationResult = value;
    }

    /**
     * Recupera il valore della proprietà policyResult.
     * 
     */
    public boolean isPolicyResult() {
        return policyResult;
    }

    /**
     * Imposta il valore della proprietà policyResult.
     * 
     */
    public void setPolicyResult(boolean value) {
        this.policyResult = value;
    }

}
