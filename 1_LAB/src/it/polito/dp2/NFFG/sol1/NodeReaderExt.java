package it.polito.dp2.NFFG.sol1;

import java.util.HashSet;
import java.util.Set;

import it.polito.dp2.NFFG.FunctionalType;
import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.sol1.jaxb.LinkType;
import it.polito.dp2.NFFG.sol1.jaxb.NodeType;

public class NodeReaderExt extends NamedEntityExt implements NodeReader {
	
	private Set<LinkReader> Links;
	private NffgReader nffg;

	public NodeReaderExt(NffgReader nffg,NodeType n) throws NffgVerifierException {
		// TODO Auto-generated constructor stub
		super(n);
		this.Links = new HashSet<LinkReader>();
		this.nffg = nffg;
		init();
		
	}

	private void init() throws NffgVerifierException {
		// TODO Auto-generated method stub

		NodeType.Links links = new NodeType.Links();
		links = ( (NodeType) this.entity).getLinks();
				
		for(LinkType l : links.getLink())
		{
			Links.add(new LinkReaderExt(nffg,this,l));
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return ((NodeType)this.entity).getName();
	}

	@Override
	public FunctionalType getFuncType() {
		// TODO Auto-generated method stub
		return FunctionalType.valueOf(((NodeType)this.entity).getType().name());
	}

	@Override
	public Set<LinkReader> getLinks() {
		// TODO Auto-generated method stub
		return Links;
		
		
	}

}
