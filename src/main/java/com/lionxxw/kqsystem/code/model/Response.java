package com.lionxxw.kqsystem.code.model;


import com.lionxxw.kqsystem.code.constants.DataStatus;

import java.io.Serializable;


public class Response<T> implements Serializable {

	public Response(){
	}
	
	public Response(int status, String message, T data){
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public int status = DataStatus.HTTP_SUCCESS;
	
	public String message;
	
	public T data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
