package kr.or.ddit.web.calculate;

public enum Mime {
	
	/*
	 	//enum 적용하기
		if(accept.contains("plain")) {
			mime = "text/plain;charset=UTF-8";
		} else if(accept.contains("json")) {
			mime = "application/json;charset=UTF-8";
			result = "{\"result\":\""+result+"\"}";
		} else  {
			mime = "text/html;charset=UTF-8";
			result = "<p>"+result+"</p>";
		} 
	 *
	 */
	
	TEXT("text/plain;charset=UTF-8", (result)->{
		return result;
	}),
	JSON("application/json;charset=UTF-8", (result)->{
		return "{\"result\":\""+result+"\"}";
	}),
	HTML("text/html;charset=UTF-8",  (result)->{
		return "<p>"+result+"</p>";
	}),
	XML("application/xml;charset=UTF-8",(result)->{
		return result;
	});
	
	private String contentType;
    private RealMimeType realMimeType;

    Mime(String contentType, RealMimeType realMimeType) { 
    	this.contentType = contentType;
    	this.realMimeType = realMimeType;
    }

    public String getContentType() {
    	return this.contentType;
    }
    
    public String getRealMimeType(String result) {
    	return realMimeType.settingRes(result);
    }
    
}
