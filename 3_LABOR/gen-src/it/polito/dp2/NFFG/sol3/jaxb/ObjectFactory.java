//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.02.20 alle 09:45:09 PM CET 
//


package it.polito.dp2.NFFG.sol3.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.polito.dp2.NFFG.sol3.jaxb package. 
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
    private final static QName _Nffg_QNAME = new QName("http://www.example.org/nffgVerifier", "Nffg");
    private final static QName _TraversalPolicy_QNAME = new QName("http://www.example.org/nffgVerifier", "TraversalPolicy");
    private final static QName _ReachabilityPolicy_QNAME = new QName("http://www.example.org/nffgVerifier", "ReachabilityPolicy");
    private final static QName _Link_QNAME = new QName("http://www.example.org/nffgVerifier", "Link");
    private final static QName _Policy_QNAME = new QName("http://www.example.org/nffgVerifier", "Policy");
    private final static QName _Policies_QNAME = new QName("http://www.example.org/nffgVerifier", "Policies");
    private final static QName _VerificationResult_QNAME = new QName("http://www.example.org/nffgVerifier", "VerificationResult");
    private final static QName _Node_QNAME = new QName("http://www.example.org/nffgVerifier", "Node");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.polito.dp2.NFFG.sol3.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NffgServiceType }
     * 
     */
    public NffgServiceType createNffgServiceType() {
        return new NffgServiceType();
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
     * Create an instance of {@link PolicyType }
     * 
     */
    public PolicyType createPolicyType() {
        return new PolicyType();
    }

    /**
     * Create an instance of {@link PoliciesType }
     * 
     */
    public PoliciesType createPoliciesType() {
        return new PoliciesType();
    }

    /**
     * Create an instance of {@link VerificationResultType }
     * 
     */
    public VerificationResultType createVerificationResultType() {
        return new VerificationResultType();
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
     * Create an instance of {@link NameEntityType }
     * 
     */
    public NameEntityType createNameEntityType() {
        return new NameEntityType();
    }

    /**
     * Create an instance of {@link NffgServiceType.Nffgs }
     * 
     */
    public NffgServiceType.Nffgs createNffgServiceTypeNffgs() {
        return new NffgServiceType.Nffgs();
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
     * Create an instance of {@link NodeType.Links }
     * 
     */
    public NodeType.Links createNodeTypeLinks() {
        return new NodeType.Links();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NffgServiceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/nffgInfo", name = "NffgService")
    public JAXBElement<NffgServiceType> createNffgService(NffgServiceType value) {
        return new JAXBElement<NffgServiceType>(_NffgService_QNAME, NffgServiceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NffgType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/nffgVerifier", name = "Nffg")
    public JAXBElement<NffgType> createNffg(NffgType value) {
        return new JAXBElement<NffgType>(_Nffg_QNAME, NffgType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TraversalPolicyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/nffgVerifier", name = "TraversalPolicy")
    public JAXBElement<TraversalPolicyType> createTraversalPolicy(TraversalPolicyType value) {
        return new JAXBElement<TraversalPolicyType>(_TraversalPolicy_QNAME, TraversalPolicyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReachabilityPolicyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/nffgVerifier", name = "ReachabilityPolicy")
    public JAXBElement<ReachabilityPolicyType> createReachabilityPolicy(ReachabilityPolicyType value) {
        return new JAXBElement<ReachabilityPolicyType>(_ReachabilityPolicy_QNAME, ReachabilityPolicyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/nffgVerifier", name = "Link")
    public JAXBElement<LinkType> createLink(LinkType value) {
        return new JAXBElement<LinkType>(_Link_QNAME, LinkType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolicyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/nffgVerifier", name = "Policy")
    public JAXBElement<PolicyType> createPolicy(PolicyType value) {
        return new JAXBElement<PolicyType>(_Policy_QNAME, PolicyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PoliciesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/nffgVerifier", name = "Policies")
    public JAXBElement<PoliciesType> createPolicies(PoliciesType value) {
        return new JAXBElement<PoliciesType>(_Policies_QNAME, PoliciesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificationResultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/nffgVerifier", name = "VerificationResult")
    public JAXBElement<VerificationResultType> createVerificationResult(VerificationResultType value) {
        return new JAXBElement<VerificationResultType>(_VerificationResult_QNAME, VerificationResultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/nffgVerifier", name = "Node")
    public JAXBElement<NodeType> createNode(NodeType value) {
        return new JAXBElement<NodeType>(_Node_QNAME, NodeType.class, null, value);
    }

}
