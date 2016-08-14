package com.ksn.core.rpc.ice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import Ice.Communicator;
import Ice.Util;

public class CommunicatorFactory {
	private Communicator communicator;

	@PostConstruct
	public void init() {
		communicator = Util.initialize();
		
	}
	
	@PreDestroy
	public void destroy() {
		if (communicator != null) {
			communicator.destroy();
		}
	}

	public Communicator getCommunicator() {
		return communicator;
	}

	public void setCommunicator(Communicator communicator) {
		this.communicator = communicator;
	}

}
