package it.polito.dp2.NFFG.sol1;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.sol1.jaxb.NffgServiceType;

public class NffgVerifierFactory extends it.polito.dp2.NFFG.NffgVerifierFactory{

	@Override
	public NffgVerifier newNffgVerifier() throws NffgVerifierException {
		// TODO Auto-generated method stub
		String file = System.getProperty("it.polito.dp2.NFFG.sol1.NffgInfo.file");
		NffgVerifierExt verifier ;
		
		try {
			JAXBContext jc = JAXBContext.newInstance("it.polito.dp2.NFFG.sol1.jaxb");

			Unmarshaller u = jc.createUnmarshaller();

			SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File("." + File.separator + "xsd" + File.separator + "nffgInfo.xsd"));
			u.setSchema(schema);

			File xml = new File(file);
			JAXBElement<NffgServiceType> nv = u.unmarshal(new StreamSource(xml), NffgServiceType.class);
			NffgServiceType verifierType = nv.getValue();

			verifier = new NffgVerifierExt(verifierType);
		} catch (JAXBException | SAXException e) {
			e.printStackTrace();
			throw new NffgVerifierException(e, "Unmarshal failed");
		}
		
		
		return verifier;
	}

}
