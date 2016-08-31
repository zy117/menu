package com.haiziguo.recipe.util;

public class Logger {

	private  Integer logLevel=Define.LOG_INFO;
	private  Boolean logOn=true;
	public  Integer getLogLevel() {
		return logLevel;
	}
	public  void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}
	public  Boolean getLogOn() {
		return logOn;
	}
	public  void setLogOn(Boolean logOn) {
		this.logOn = logOn;
	}
	
	public void debug(String str){
		if(logOn){
			if(logLevel<=Define.LOG_DEBUG){
				System.out.println("D:"+str);
			}
		}
	}
	public void info(String str){
		if(logOn){
			if(logLevel<=Define.LOG_INFO){
				System.out.println("I:"+str);
			}
		}
	}
	public void warn(String str){
		if(logOn){
			if(logLevel<=Define.LOG_WARN){
				System.out.println("W:"+str);
			}
		}
	}
	public void error(String str){
		if(logOn){
			if(logLevel<=Define.LOG_ERROR){
				System.err.println(str);
			}
		}
	}
	
}
