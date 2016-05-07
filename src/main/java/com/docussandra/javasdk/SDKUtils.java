package com.docussandra.javasdk;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mongodb.util.JSON;
import com.pearson.docussandra.domain.seralization.UuidDeserializer;
import com.pearson.docussandra.domain.seralization.UuidSerializer;
import com.strategicgains.hyperexpress.domain.hal.HalResource;
import com.strategicgains.hyperexpress.serialization.jackson.HalResourceDeserializer;
import com.strategicgains.hyperexpress.serialization.jackson.HalResourceSerializer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import org.bson.BSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//TODO: Javadoc!
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
                .registerModule(new SimpleModule()
                        .addDeserializer(UUID.class, new UuidDeserializer())
                        .addSerializer(UUID.class, new UuidSerializer())
                        .addDeserializer(HalResource.class, new HalResourceDeserializer())
                        .addSerializer(HalResource.class, new HalResourceSerializer())
                        .addDeserializer(BSONObject.class, new BSONObjectDeserializer()))
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
        return toReturn;
    }

    /**
     * Deserailzer for BSON Objects; use only to parse the actual Docussandra
     * document object. Kind of ugly right now, and could almost certainly be
     * made more efficient.
     */
    private static class BSONObjectDeserializer extends JsonDeserializer<BSONObject>
    {

        @Override
        public BSONObject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException
        {
            //JSONParser parser = new JSONParser();
            //fixed? //there is probably almost certianly a better way to do this, but it will work for now
            String jsonString = p.readValueAsTree().toString();//the object response
            return (BSONObject) JSON.parse(jsonString);

//            String jsonString = p.readValueAsTree().toString();//the whole response, including the document and the metadata
//            try
//            {
//                JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
//                JSONObject bsonObejct = (JSONObject) jsonObject.get("object");
//                return (BSONObject) JSON.parse(bsonObejct.toJSONString());
//            } catch (ParseException e)
//            {
//                throw new IOException(e);
//            }
        }

    }
}
