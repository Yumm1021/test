package com.koreait.apart.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="response") // class 위에는 무조건 RootElement
public class ResponseDomain { 
	
	// response의 body 다 담은 객체
	@JacksonXmlProperty(localName="body")
	private BodyDomain body;

	public BodyDomain getBody() {
		return body;
	}

	public void setBody(BodyDomain body) {
		this.body = body;
	}
	
}
