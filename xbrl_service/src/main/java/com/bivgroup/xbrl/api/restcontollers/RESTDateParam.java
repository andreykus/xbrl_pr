package com.bivgroup.xbrl.api.restcontollers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ws.rs.WebApplicationException;

/**
 * Date Type for rest service
 * Created by andreykus on 12.03.2017.
 */
public class RESTDateParam {

    private SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
    private java.sql.Date date;

    public RESTDateParam( String dateStr ) throws WebApplicationException {
        try {
            date = new java.sql.Date( df.parse( dateStr ).getTime() );
        } catch ( final ParseException ex ) {
            throw new WebApplicationException( ex );
        }
    }

    public java.sql.Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        if ( date != null ) {
            return date.toString();
        } else {
            return "";
        }
    }
}