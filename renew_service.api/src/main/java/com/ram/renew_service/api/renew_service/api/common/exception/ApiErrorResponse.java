package com.ram.renew_service.api.renew_service.api.common.exception;

public class ApiErrorResponse {
    private final String guid;
    private final String errorCode;
    private final String message;
    private final Integer statusCode;
    private final String statusName;
    private final String path;
    private final String method;
    private final String timestamp;
	public ApiErrorResponse(String guid, String errorCode, String message, Integer statusCode, String statusName,
			String path, String method,String timestamp) {
		super();
		this.guid = guid;
		this.errorCode = errorCode;
		this.message = message;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.path = path;
		this.method = method;
		this.timestamp = timestamp;
	}
	public String getGuid() {
		return guid;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public String getMessage() {
		return message;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public String getPath() {
		return path;
	}
	public String getMethod() {
		return method;
	}
	public String getTimestamp() {
		return timestamp;
	}
    
    
    
}