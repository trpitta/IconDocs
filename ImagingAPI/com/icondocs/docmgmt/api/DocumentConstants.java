package com.icondocs.docmgmt.api;

public interface DocumentConstants {

	public String FILE_NAME = "FILE_NAME";
	public String FILE_TYPE = "FILE_TYPE";
	public String FILE_CONTENT = "FILE_CONTENT";
	public String ERR_CODE_GENERIC = "DOC_EXP_001";
	public String ERR_MSG_GENERIC = "An exception occurred while processing the request. Please find more details about the exception in the application logs.";
	
	public String ERR_CODE_EMPTY_DOCID = "DOC_EXP_101";
	public String ERR_MSG_EMPTY_DOCID = "Invalid Document Id. Document Id cannot be null/empty.";

	public String ERR_CODE_ADDDOC_MISSING_REQ_PARAM = "DOC_EXP_201";
	public String ERR_MSG_ADDDOC_MISSING_REQ_PARAM = "One of the required parameters for adding the document is missing.(Required Parameters: File Name, MIME Type, Valid File Path)";
}
