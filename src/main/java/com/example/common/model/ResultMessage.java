package com.example.common.model;

import lombok.Getter;
import lombok.Setter;

/**
 * ResultMessage
 */
@Getter
@Setter
public class ResultMessage {

    private String successYn;
	private String statusCode;
	private String code;	 
	private String message;
	private String devMessage;
	
	
	public ResultMessage() {		
	}

	public ResultMessage(String successYn) {
		this.successYn = successYn;
		this.statusCode = null;
		this.message = null;
		this.devMessage = null;
	}
	public ResultMessage(String successYn, String message) {
		this.successYn = successYn;
		this.statusCode = null;
		this.message = message;
		this.devMessage = null;
	}
	
	public ResultMessage(String successYn,String statusCode, String message, String devMessage) {
		this.successYn = successYn;
		this.statusCode = null;
		this.message = message;
		this.devMessage = null;
	}

}