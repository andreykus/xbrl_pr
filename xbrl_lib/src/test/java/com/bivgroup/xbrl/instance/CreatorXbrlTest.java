package com.bivgroup.xbrl.instance;

import com.bivgroup.xbrl.excelexport.EventExcel;
import com.bivgroup.xbrl.excelexport.ProcessExcelFileModel;
import com.bivgroup.xbrl.instance.elements.InstanceXbrl;
import com.bivgroup.xbrl.instance.impl.freemaker.CreatorXbrlFreeMarker;
import com.bivgroup.xbrl.instance.impl.xwand.CreatorXbrlXWand;


import java.util.Collections;


/**
 * Created by bush on 15.03.2017.
 */
public class CreatorXbrlTest {

    String file1="c:\\1222\\property1.xlsx";
    String file2="c:\\1222\\property2.xlsx";
    String sheet="rId1";

    @org.testng.annotations.Test(enabled = false)
    public void testGetInstanceFreeMarker() throws Exception {
        CreatorXbrlFreeMarker xbrl = new CreatorXbrlFreeMarker();
        xbrl.getInstance(new InstanceXbrl());
    }

    @org.testng.annotations.Test(enabled = true)
    public void testGetInstanceXwand() throws Exception {
        CreatorXbrlXWand xbrl = new CreatorXbrlXWand();
        xbrl.getInstance(new InstanceXbrl());
    }

    @org.testng.annotations.Test(enabled = false)
    public void testloadExcel() throws Exception {
        ProcessExcelFileModel xml = new ProcessExcelFileModel();
        xml.processOneSheet(file1,sheet );
    }


    @org.testng.annotations.Test(enabled = false)
    public void testloadExcelAll() throws Exception {
        ProcessExcelFileModel xml = new ProcessExcelFileModel();
        xml.processAllSheets(file2);
    }

    @org.testng.annotations.Test(enabled = false)
    public void testloadExcelDom() throws Exception {
        EventExcel xml = new  EventExcel();
        xml.load(file1,sheet);
    }
}