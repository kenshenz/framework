package com.ksn.core.aspect;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAspect {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	protected class ReqLog implements Serializable {
		private static final long serialVersionUID = -6475949107388092834L;
		private String uuid;
		private String typeName;
		private String methodName;
		private List<Object> args;

		public ReqLog() {
		}

		public ReqLog(String uuid, String typeName, String methodName, List<Object> args) {
			this.uuid = uuid;
			this.typeName = typeName;
			this.methodName = methodName;
			this.args = args;
		}
	}
	
	protected class RespLog implements Serializable {
		private static final long serialVersionUID = -8755085858250442011L;
		private String uuid;
		private Object rtnValue;
		
		public RespLog() {}
		public RespLog(String uuid, Object rtnValue) {
			this.uuid = uuid;
			this.rtnValue = rtnValue;
		}
	}

}
