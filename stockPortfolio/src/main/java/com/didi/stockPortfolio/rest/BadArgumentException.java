package com.didi.stockPortfolio.rest;

public class BadArgumentException extends RuntimeException{
	
        public BadArgumentException(String message){
        	super(message);
        	}

		public BadArgumentException() {
			super();
			// TODO Auto-generated constructor stub
		}

		public BadArgumentException(String message, Throwable cause, boolean enableSuppression,
				boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
			// TODO Auto-generated constructor stub
		}

		public BadArgumentException(String message, Throwable cause) {
			super(message, cause);
			// TODO Auto-generated constructor stub
		}

		public BadArgumentException(Throwable cause) {
			super(cause);
			// TODO Auto-generated constructor stub
		}
}
