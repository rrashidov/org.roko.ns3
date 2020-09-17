package org.roko.ns3.fix.svc.rules;

public class ByteArrayRule {

	public byte[] mock;

	public ByteArrayRule() {
		mock = new byte[] {};
	}
	
	public void stub(byte[] stub) {
		mock = stub;
	}
}
