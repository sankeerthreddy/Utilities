package com.awesome.pro.utilities;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * This allows JavaScripts to be executed using javax script engine.
 * The script is not executed in the browser context. 
 * @author siddharth.s
 * @review pending
 */
public class JavaScriptEngine {
	
	/**
	 * Script engine which is used to execute JavaScripts.
	 */
	private ScriptEngine engine;

	/**
	 * Java Script Engine constructor.
	 */
	public JavaScriptEngine() {
		this.engine = new ScriptEngineManager().getEngineByName("JavaScript");
	}
	
	/**
	 * @param script String JavaScript to be executed.
	 * @return Return value of the specified script.
	 */
	private Object executeJS(String script) {
		Object object = null;
		try {
			object = this.engine.eval(script);
		}
		catch (ScriptException e) {
			throw new Error("Error in the input script:\n" + script + "\n");
		}
		return object;
	}
	
	/**
	 * @param script String JavaScript to be executed.
	 * @return String formatted return value of the script.
	 */
	public String getStringOutput(String script) {
		return (String) executeJS(script);
	}
	
	/**
	 * @param script String JavaScript to be executed.
	 * @return Double formatted return value of the script.
	 */
	public double getDoubleOutput(String script) {
		return Double.parseDouble(getStringOutput(script));
	}
	
	/**
	 * @param script String JavaScript to be executed.
	 * @return Integer formatted return value of the script.
	 */
	public int getIntegerOutput(String script) {
		return Integer.parseInt(getStringOutput(script));
	}
	
	/**
	 * @param script String JavaScript to be executed.
	 * @return Boolean formatted return value of the script.
	 */
	public boolean getBooleanOutput(String script) {
		String out = getStringOutput(script).toLowerCase();
		if(out.equals("true"))
			return true;
		else if(out.equals("false"))
			return false;
		else
			throw new Error("Expected boolean value, found: " + out);
	}

}
