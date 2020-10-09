package org.roko.ns3.org.roko.ns3.catalog.api.service;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdGeneratorServiceImpl implements IdGeneratorService {

	private MessageDigest messageDigest;

	@Autowired
	public IdGeneratorServiceImpl(MessageDigest messageDigest) {
		this.messageDigest = messageDigest;
	}

	@Override
	public String generate(String s) {
		byte[] digestedData = messageDigest.digest(s.getBytes());
		
        BigInteger no = new BigInteger(1, digestedData); 
        String hashtext = no.toString(16); 

        while (hashtext.length() < 32) { 
            hashtext = "0" + hashtext; 
        } 

		return hashtext;
	}
}
