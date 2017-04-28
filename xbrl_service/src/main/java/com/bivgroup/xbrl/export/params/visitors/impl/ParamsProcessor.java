package com.bivgroup.xbrl.export.params.visitors.impl;

import com.bivgroup.xbrl.entity.PrepareProcessor;
import com.bivgroup.xbrl.export.params.visitors.AbstractStructuraProcessor;

/**
 * Created by andreykus on 12.03.2017.
 */
public class ParamsProcessor extends AbstractStructuraProcessor<PrepareProcessor,ParamProcessVisitor>{
    public ParamsProcessor(){
        super();
        setVisitor(new ParamProcessVisitor(null));
    }

}
