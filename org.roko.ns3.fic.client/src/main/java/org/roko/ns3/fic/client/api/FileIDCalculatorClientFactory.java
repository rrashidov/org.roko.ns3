package org.roko.ns3.fic.client.api;

import org.roko.ns3.fic.client.impl.FileIDCalculatorClientImpl;

public class FileIDCalculatorClientFactory {

	public static FileIDCalculatorClient get(String serviceURL) {
		return new FileIDCalculatorClientImpl(serviceURL);
	}
}
