//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.02.10 alle 04:49:37 PM CET 
//


package it.polito.dp2.NFFG.sol1.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.polito.dp2.NFFG.sol1.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _NffgService_QNAME = new QName("http://www.example.org/nffgInfo", "NffgService");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.polito.dp2.NFFG.sol1.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TraversalPolicyType }
     * 
     */
    public TraversalPolicyType createTraversalPolicyType() {
        return new TraversalPolicyType();
    }

    /**
     * Create an instance of {@link NffgType }
     * 
     */
    public NffgType createNffgType() {
        return new NffgType();
    }

    /**
     * Create an instance of {@link NodeType }
     * 
     */
    public NodeType createNodeType() {
        return new NodeType();
    }

    /**
     * Create an instance of {@link NffgServiceType }
     * 
     */
    public NffgServiceType createNffgServiceType() {
        return new NffgServiceType();
    }

    /**
     * Create an instance of {@link PolicyType }
     * 
     */
    public PolicyType createPolicyType() {
        return new PolicyType();
    }

    /**
     * Create an instance of {@link VerificationResultType }
     * 
     */
    public VerificationResultType createVerificationResultType() {
        return new VerificationResultType();
    }

    /**
     * Create an instance of {@link NameEntityType }
     * 
     */
    public NameEntityType createNameEntityType() {
        return new NameEntityType();
    }

    /**
     * Create an instance of {@link LinkType }
     * 
     */
    public LinkType createLinkType() {
        return new LinkType();
    }

    /**
     * Create an instance of {@link ReachabilityPolicyType }
     * 
     */
    public ReachabilityPolicyType createReachabilityPolicyType() {
        return new ReachabilityPolicyType();
    }

    /**
     * Create an instance of {@link TraversalPolicyType.TraversedFTypes }
     * 
     */
    public TraversalPolicyType.TraversedFTypes createTraversalPolicyTypeTraversedFTypes() {
        return new TraversalPolicyType.TraversedFTypes();
    }

    /**
     * Create an instance of {@link NffgType.Nodes }
     * 
     */
    public NffgType.Nodes createNffgTypeNodes() {
        return new NffgType.Nodes();
    }

    /**
     * Create an instance of {@link NffgType.Policies }
     * 
     */
    public NffgType.Policies createNffgTypePolicies() {
        return new NffgType.Policies();
    }

    /**
     * Create an instance of {@link NodeType.Links }
     * 
     */
    public NodeType.Links createNodeTypeLinks() {
        return new NodeType.Links();
    }

    /**
     * Create an instance of {@link NffgServiceType.Nffgs }
     * 
     */
    public NffgServiceType.Nffgs createNffgServiceTypeNffgs() {
        return new NffgServiceType.Nffgs();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NffgServiceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/nffgInfo", name = "NffgService")
    public JAXBElement<NffgServiceType> createNffgService(NffgServiceType value) {
        return new JAXBElement<NffgServiceType>(_NffgService_QNAME, NffgServiceType.class, null, value);
    }

}
