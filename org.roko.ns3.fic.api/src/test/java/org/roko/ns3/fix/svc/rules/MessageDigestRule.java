package org.roko.ns3.fix.svc.rules;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.security.MessageDigest;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MessageDigestRule {

	public MessageDigest mock;

	public MessageDigestRule() {
		mock = mock(MessageDigest.class);

		when(mock.digest(any(byte[].class))).then(new Answer<byte[]>() {
			@Override
			public byte[] answer(InvocationOnMock invocation) throws Throwable {
				return (byte[]) invocation.getArgument(0);
			}
		});
	}
}
