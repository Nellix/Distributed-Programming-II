package it.polito.dp2.NFFG.sol1;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.sol1.jaxb.NffgType;
import it.polito.dp2.NFFG.sol1.jaxb.NodeType;

public class NffgReaderExt extends NamedEntityExt implements NffgReader{
	
	private Set<NodeReader> Nodes;

	public NffgReaderExt(NffgType n) throws NffgVerifierException {
		// TODO Auto-generated constructor stub
		super(n);
		this.Nodes = new HashSet<NodeReader>();
		
		init();

	}

	private void init() throws NffgVerifierException {
		// TODO Auto-generated method stub
		NffgType.Nodes nodes = new NffgType.Nodes();
		nodes =((NffgType)this.entity).getNodes();
		
		for(NodeType node : nodes.getNode())
		{
			Nodes.add(new NodeReaderExt(this,node));
		}
		
	}

	@Override
	public NodeReader getNode(String arg0) {
		// TODO Auto-generated method stub
		for(NodeReader n : Nodes)
			if(n.getName().compareTo(arg0)==0)
				return n;
			
		return null;

	}

	@Override
	public Set<NodeReader> getNodes() {
		// TODO Auto-generated method stub	
		return Nodes;
	}

	@Override
	public Calendar getUpdateTime() {
		// TODO Auto-generated method stub
		return ((NffgType) this.entity).getUpdateTime().toGregorianCalendar();
	}

}
