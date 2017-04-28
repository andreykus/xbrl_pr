package com.bivgroup.xbrl.instance;

import com.bivgroup.xbrl.common.generators.GeneratorException;

import java.util.List;

/**
 * Interface Create xbrl report
 * Created by bush on 15.03.2017.
 */
public interface Creator {
    /**
     * get instance report xbrl
     * @param params - in params
     * @return - retort xbrl
     * @throws InstanceXbrlException - exception generate xbrl
     */
    Object getInstance(Object params) throws InstanceXbrlException;

    List getContexts(Object params) throws InstanceXbrlException;
}
