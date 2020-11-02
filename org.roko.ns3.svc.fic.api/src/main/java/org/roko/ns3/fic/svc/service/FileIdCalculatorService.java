package org.roko.ns3.fic.svc.service;

public interface FileIdCalculatorService {

	/**
	 * Calculates hash based on the provided input data in the form if byte array
	 * @param data Input data to calculate hash on
	 * @return Hash calculated based on the provided byte array
	 */
	public String calculate(byte[] data);
}
