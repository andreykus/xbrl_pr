package com.bivgroup.xbrl.common.generators.freemaker;

import com.bivgroup.xbrl.common.generators.GeneratorException;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Engine generator document
 * Created by bush on 15.09.2016.
 */
public class TemplateHelper {
    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());
    /**
     * output document
     */
    private StringBuffer outputStream;
    /**
     * config framework FreeMarker
     */
    protected Configuration freeMarkerEngine;
    /**
     * inner params for template
     */
    protected SimpleHash context;

    /**
     * init FreeMarker farmework
     *
     * @param outputStream  - output document
     * @param templatePaths -  paths to templates
     * @throws GeneratorException - exception generator
     */
    public void init(StringBuffer outputStream, String[] templatePaths) throws GeneratorException {
        this.outputStream = outputStream;
        context = new SimpleHash(ObjectWrapper.BEANS_WRAPPER);
        freeMarkerEngine = new Configuration();
        List<TemplateLoader> loaders = new ArrayList<TemplateLoader>();
        for (int i = 0; i < templatePaths.length; i++) {
            File file = new File(templatePaths[i]);
            if (file.exists() && file.isDirectory()) {
                try {
                    loaders.add(new FileTemplateLoader(file));
                } catch (IOException e) {
                    throw new GeneratorException("Problems with templatepath " + file, e);
                }
            } else {
                logger.warn("template path" + file + " either does not exist or is not a directory");
            }
        }
        loaders.add(new ClassTemplateLoader(this.getClass(), "/")); // the template names are like pojo/Somewhere so have to be a rooted classpathloader
        freeMarkerEngine.setTemplateLoader(new MultiTemplateLoader((TemplateLoader[]) loaders.toArray(new TemplateLoader[loaders.size()])));
    }


    public class Templates {
    }

    /**
     * put param in inner params
     *
     * @param key   - key
     * @param value - value
     * @throws GeneratorException - exception generator
     */
    public void putInContext(String key, Object value) throws GeneratorException {
        logger.trace("putInContext " + key + "=" + value);
        if (value == null) throw new IllegalStateException("value must not be null for " + key);
        Object replaced = internalPutInContext(key, value);
        if (replaced != null) {
            logger.warn("Overwriting " + replaced + " when setting " + key + " to " + value + ".");
        }
    }

    /**
     * remove param from inner params
     *
     * @param key   - key
     * @param expected - expected value
     * @throws GeneratorException - exception generator
     */
    public void removeFromContext(String key, Object expected) throws GeneratorException {
        logger.trace("removeFromContext " + key + "=" + expected);
        Object replaced = internalRemoveFromContext(key);
        if (replaced == null) throw new IllegalStateException(key + " did not exist in template context.");
    }

    /**
     * get inner params
     * @return - inner params
     */
    public SimpleHash getContext() {
        return context;
    }

    /**
     * init default params for inner params
     */
    public void setupContext() {
        //TODO get this from METAINF
        //set serion module
        getContext().put(Constants.VERSION, "1");
        //set name module;
        getContext().put(Constants.MODULE, "1");
        getContext().put(Constants.CONTEXT, getContext()); //TODO: I would like to remove this, but don't know another way to actually get the list possible "root" keys for debugging.
        getContext().put(Constants.TEMPLATES, new Templates());
        getContext().put(Constants.DATE, new SimpleDate(new Date(), TemplateDateModel.DATETIME));

    }

    /**
     * put param in inner params
     *
     * @param key   - key
     * @param value - value
     * @return - model params
     * @throws GeneratorException - exception generator
     */
    protected Object internalPutInContext(String key, Object value) throws GeneratorException {
        TemplateModel model = null;
        try {
            model = getContext().get(key);
        } catch (TemplateModelException e) {
            throw new GeneratorException("Could not get key " + key, e);
        }
        getContext().put(key, value);
        return model;
    }

    /**
     * remove param from inner params
     * @param key   - key
     * @return - model params
     * @throws GeneratorException - exception generator
     */
    protected Object internalRemoveFromContext(String key) throws GeneratorException {
        TemplateModel model = null;
        try {
            model = getContext().get(key);
        } catch (TemplateModelException e) {
            throw new GeneratorException("Could not get key " + key, e);
        }
        getContext().remove(key);
        return model;
    }

    /**
     * main method process generator
     * look up the template named templateName via the paths and print the content to the output
     */
    public void processTemplate(String templateName, Writer output, String rootContext) throws GeneratorException {
        if (rootContext == null) {
            rootContext = "Unknown context";
        }
        try {
            Template template = freeMarkerEngine.getTemplate(templateName);
            template.process(getContext(), output);
        } catch (IOException e) {
            throw new GeneratorException("Error while processing " + rootContext + " with template " + templateName, e);
        } catch (TemplateException te) {
            throw new GeneratorException("Error while processing " + rootContext + " with template " + templateName, te);
        } catch (Exception e) {
            throw new GeneratorException("Error while processing " + rootContext + " with template " + templateName, e);
        }
    }

}
