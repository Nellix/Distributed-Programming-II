package it.polito.dp2.NFFG.sol3.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import it.polito.dp2.NFFG.sol3.jaxb.NffgType;
import it.polito.dp2.NFFG.sol3.jaxb.PolicyType;

public class NFFGSDB {


private static Map<String,NffgType> mapNffg= new ConcurrentHashMap<String,NffgType>();
private static Map<String,PolicyType> mapPolicy= new ConcurrentHashMap<String,PolicyType>();
private static Map<String,String> mapIDs= new ConcurrentHashMap<String,String>();



public static Map<String, PolicyType> getMapPolicy() {
	return mapPolicy;
}
public static void setMapPolicy(Map<String, PolicyType> mapPolicy) {
	NFFGSDB.mapPolicy = mapPolicy;
}
public static Map<String, NffgType> getMapNffg() {
	return mapNffg;
}
public static void setMapNffg(Map<String, NffgType> nffgs) {
	NFFGSDB.mapNffg = nffgs;
}


public static Map<String, String> getNode_Identity() {
	// TODO Auto-generated method stub
	return mapIDs;
}








}
