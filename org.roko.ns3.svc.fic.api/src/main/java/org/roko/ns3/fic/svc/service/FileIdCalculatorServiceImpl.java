package org.roko.ns3.fic.svc.service;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileIdCalculatorServiceImpl implements FileIdCalculatorService {

	private MessageDigest messageDigest;

	@Autowired
	public FileIdCalculatorServiceImpl(MessageDigest messageDigest) {
		this.messageDigest = messageDigest;
	}

	@Override
	public String calculate(byte[] data) {
		byte[] digestedData = messageDigest.digest(data);
	
        BigInteger no = new BigInteger(1, digestedData); 
        String hashtext = no.toString(16); 

        while (hashtext.length() < 32) { 
            hashtext = "0" + hashtext; 
        } 

		return hashtext;
	}

}
