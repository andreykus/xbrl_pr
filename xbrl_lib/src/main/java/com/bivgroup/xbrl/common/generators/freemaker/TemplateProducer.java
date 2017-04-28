package com.bivgroup.xbrl.common.generators.freemaker;


import com.bivgroup.xbrl.common.generators.GeneratorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Producer document by template
 * Created by bush on 15.09.2016.
 */
public class TemplateProducer {
	/**
	 * logger
	 */
	protected Logger logger = LogManager.getLogger(this.getClass());
	/**
	 * engine generator document
	 */
	private final TemplateHelper th;

	/**
	 * Constructor for producer
	 * @param th - engine generator document
	 */
	public TemplateProducer(TemplateHelper th) {
		this.th = th;
	}

	/**
	 * Prodouse document
	 * @param additionalContext - additional in param
	 * @param templateName -  start template
	 * @param destination - document as StringBuffer
	 * @param rootContext - in param
	 * @throws GeneratorException - exception generator
	 */
	public void produce(Map<String,Object> additionalContext, String templateName, StringBuffer destination, String rootContext) throws  GeneratorException{
		String tempResult = produceToString( additionalContext, templateName, rootContext );
		if(tempResult.trim().length()==0) {
			logger.warn("Generated output is empty. Skipped creation for file " + destination);
			return;
		}
		try {
			destination.append(tempResult);
		} 
		catch (Exception e) {
			throw new  GeneratorException("Error while writing result to file", e);
		}
	}

	/**
	 * get document boby
	 * @param additionalContext - additional in param
	 * @param templateName -  start template
	 * @param rootContext - in param
	 * @return -  document as string
	 * @throws GeneratorException - exception generator
	 */
	private String produceToString(Map<String,Object> additionalContext, String templateName, String rootContext) throws  GeneratorException{
		Map<String,Object> contextForFirstPass = additionalContext;
		putInContext( th, contextForFirstPass );		
		StringWriter tempWriter = new StringWriter();
		BufferedWriter bw = new BufferedWriter(tempWriter);
		// execute engine generator -- this create document
		th.processTemplate(templateName, bw, rootContext);
		removeFromContext( th, contextForFirstPass );
		try {
			bw.flush();
		}
		catch (IOException e) {
			throw new RuntimeException("Error while flushing to string",e);
		}
		return tempWriter.toString();
	}

	/**
	 * clean context from engine generator document
	 * @param templateHelper - engine generator document
	 * @param context - in parms
	 * @throws GeneratorException - exception generator
	 */
	private void removeFromContext(TemplateHelper templateHelper, Map<String,Object> context) throws  GeneratorException{
		Iterator<Entry<String,Object>> iterator = context.entrySet().iterator();
		while ( iterator.hasNext() ) {
			Entry<String,Object> element = iterator.next();
			templateHelper.removeFromContext((String) element.getKey(), element.getValue());
		}
	}

	/**
	 * put param in engine generator document
	 * @param templateHelper - engine generator document
	 * @param context - in parms
	 * @throws GeneratorException - exception generator
	 */
	private void putInContext(TemplateHelper templateHelper, Map<String,Object> context) throws GeneratorException {
		Iterator<Entry<String,Object>> iterator = context.entrySet().iterator();
		while ( iterator.hasNext() ) {
			Entry<String,Object> element = iterator.next();
			templateHelper.putInContext((String) element.getKey(), element.getValue());
		}
	}

}
