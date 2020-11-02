package org.roko.ns3.fic.client.api;

public interface FileIDCalculatorClient {

	/**
	 * Calculates file id by calling FIC (File Id Calculator) service
	 * @param data Data to be used to calculate file id
	 * @return Id of the file that will be calculateed
	 */
	public String calculate(byte[] data);

}
