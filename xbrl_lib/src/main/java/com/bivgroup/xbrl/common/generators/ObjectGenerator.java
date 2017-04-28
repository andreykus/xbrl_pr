package com.bivgroup.xbrl.common.generators;

/**
 * Interface Document object Generator
 * Created by andreykus on 18.03.2017.
 */
public interface ObjectGenerator<T, V extends InnerTool> {
    /**
     * Method generate object
     * @param context - in params
     * @return - document
     * @throws GeneratorException
     */
    Object generate(T context) throws GeneratorException;
}
