package com.bivgroup.xbrl.instance;

import com.bivgroup.xbrl.common.AbstractStructuraVisitor;
import com.bivgroup.xbrl.common.StructuraVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by bush on 15.03.2017.
 */
public class CreateFix extends AbstractStructuraVisitor {
    /**
     * logger
     */
    protected Logger logger = LogManager.getLogger(this.getClass());

    public CreateFix(StructuraVisitor next) {
        super(next);
    }

    @Override
    public void visit(Object element) {

    }
}
