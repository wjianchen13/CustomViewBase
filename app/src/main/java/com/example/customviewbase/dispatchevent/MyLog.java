package com.example.customviewbase.dispatchevent;

/**
 * @name MpaLog
 * @description 日志打印
 * @author wj
 * @date 2016.05.05 11:00
 * @copyright
 */
public class MyLog {

	/**
	 * @name log(String msg) 
	 * @description 日志输出
	 * @param 
	 * @return void
	 */
    public static void log(String msg)
    {
        StackTraceElement[] stes = new Throwable().getStackTrace();   
        System.out.println("---> " + stes[1].getFileName() + " Line " + stes[1].getLineNumber() + ": " + msg);
    }
	
}
