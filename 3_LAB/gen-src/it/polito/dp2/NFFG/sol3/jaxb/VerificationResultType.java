//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.02.14 alle 07:48:01 PM CET 
//


package it.polito.dp2.NFFG.sol3.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per VerificationResultType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="VerificationResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VerificationResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="VerificationMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VerificationTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerificationResultType", propOrder = {
    "verificationResult",
    "verificationMessage",
    "verificationTime"
})
public class VerificationResultType {

    @XmlElement(name = "VerificationResult")
    protected boolean verificationResult;
    @XmlElement(name = "VerificationMessage", required = true)
    protected String verificationMessage;
    @XmlElement(name = "VerificationTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar verificationTime;

    /**
     * Recupera il valore della proprietà verificationResult.
     * 
     */
    public boolean isVerificationResult() {
        return verificationResult;
    }

    /**
     * Imposta il valore della proprietà verificationResult.
     * 
     */
    public void setVerificationResult(boolean value) {
        this.verificationResult = value;
    }

    /**
     * Recupera il valore della proprietà verificationMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerificationMessage() {
        return verificationMessage;
    }

    /**
     * Imposta il valore della proprietà verificationMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerificationMessage(String value) {
        this.verificationMessage = value;
    }

    /**
     * Recupera il valore della proprietà verificationTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVerificationTime() {
        return verificationTime;
    }

    /**
     * Imposta il valore della proprietà verificationTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVerificationTime(XMLGregorianCalendar value) {
        this.verificationTime = value;
    }

}
