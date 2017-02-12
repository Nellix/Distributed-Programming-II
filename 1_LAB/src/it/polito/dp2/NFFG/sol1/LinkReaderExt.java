package it.polito.dp2.NFFG.sol1;

import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.sol1.jaxb.LinkType;


public class LinkReaderExt extends NamedEntityExt implements LinkReader {
	
	private NffgReader nffg;
	private NodeReaderExt source ;
	
	public LinkReaderExt(NffgReader nffg,NodeReaderExt nodeReaderExt,LinkType l) throws NffgVerifierException {
		// TODO Auto-generated constructor stub
		super(l);
		this.nffg = nffg;
		this.source = nodeReaderExt;
	}

	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return ((LinkType)this.entity).getName();
	}

	@Override
	public NodeReader getDestinationNode() {
		// TODO Auto-generated method stub
		return nffg.getNode(((LinkType)this.entity).getDestinationNode().getName());
		
	}

	@Override
	public NodeReader getSourceNode() {
		// TODO Auto-generated method stub
		return source;
	}

}
