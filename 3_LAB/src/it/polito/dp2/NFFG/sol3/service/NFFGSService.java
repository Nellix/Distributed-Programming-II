package it.polito.dp2.NFFG.sol3.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.lab2.ServiceException;
import it.polito.dp2.NFFG.lab2.UnknownNameException;
import it.polito.dp2.NFFG.sol3.jaxb.NffgType;
import it.polito.dp2.NFFG.sol3.jaxb.PolicyType;


public class NFFGSService {

	Map <String,NffgType> mapNffg = NFFGSDB.getMapNffg();
	Map <String,PolicyType> mapPolicy = NFFGSDB.getMapPolicy();

	
	
	
}

