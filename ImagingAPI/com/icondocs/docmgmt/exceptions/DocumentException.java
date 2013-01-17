package com.icondocs.docmgmt.exceptions;

public class DocumentException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;

	public DocumentException(Exception ex) {
		super(ex);
	}

	public DocumentException(String ec, String emsg) {
		this.errorCode = ec;
		this.errorMessage = emsg;
	}

	public DocumentException(Exception ex, String ec, String emsg) {
		super(emsg, ex);
		this.errorCode = ec;
		this.errorMessage = emsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
