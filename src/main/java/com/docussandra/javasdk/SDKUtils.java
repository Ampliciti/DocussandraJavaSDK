package com.docussandra.javasdk;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.text.SimpleDateFormat;

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
                .with(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
        return ow;
    }

    public static ObjectMapper getObjectMapper()
    {
        ObjectMapper toReturn = new ObjectMapper();
        toReturn
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // Ignore additional/unknown properties in a payload.
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                // Only serialize populated properties (do no serialize nulls)
                // Use fields directly.
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
                // Ignore accessor and mutator methods (use fields per above).
                .setVisibility(PropertyAccessor.GETTER, Visibility.NONE)
                //.setVisibility(PropertyAccessor.SETTER, Visibility.NONE)
                .setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
        return toReturn;
    }
}
