package com.docussandra.javasdk;

import java.text.SimpleDateFormat;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class SDKUtils
{

    public static String createJSON(Object obj/*, boolean prettyPrint*/)
    {

//        if (prettyPrint)
//        {
//            ow = ow.withDefaultPrettyPrinter();
//        }
        try
        {
            return getObjectWriter().writeValueAsString(obj);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static ObjectWriter getObjectWriter()
    {
        ObjectWriter ow = getObjectMapper().writer()
                .withDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
        return ow;
    }

    public static ObjectMapper getObjectMapper()
    {
        return new ObjectMapper();
    }
}
