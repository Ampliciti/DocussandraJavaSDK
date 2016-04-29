package com.docussandra.javasdk.dao.impl;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.SDKUtils;
import com.docussandra.javasdk.dao.DocumentDao;
import com.docussandra.javasdk.dao.impl.parent.DaoParent;
import com.docussandra.javasdk.domain.DocumentListResponse;
import com.docussandra.javasdk.domain.DocumentResponse;
import com.docussandra.javasdk.exceptions.RESTException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.pearson.docussandra.domain.objects.Document;
import com.pearson.docussandra.domain.objects.Identifier;
import com.pearson.docussandra.domain.objects.QueryResponseWrapper;
import com.pearson.docussandra.domain.objects.Table;
import java.io.IOException;
import java.util.UUID;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Document dao that accesses Docussandra to manipulate the documents via the
 * REST API.
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class DocumentDaoImpl extends DaoParent implements DocumentDao
{

    private final ObjectReader r = SDKUtils.getObjectMapper().reader(DocumentResponse.class);
    private final ObjectReader rList = SDKUtils.getObjectMapper().reader(DocumentListResponse.class);
    private final ObjectReader rQuery = SDKUtils.getObjectMapper().reader(QueryResponseWrapper.class);

    /**
     * Constructor.
     *
     * @param config Config for this class.
     */
    public DocumentDaoImpl(Config config)
    {
        super(config);
    }

    /**
     * Creates a document.
     *
     * @param table Table in which to create the document in.
     * @param entity Document to put into the Database.
     * @return The created Document including a UUID.
     * @throws RESTException If there was a problem making the call.
     * @throws IOException If there was a problem de-serializing the JSON
     * response.
     * @throws ParseException If the passed in database couldn't be serialized.
     */
    @Override
    public Document create(Table table, Document entity) throws RESTException, ParseException, IOException
    {
        JSONParser parser = new JSONParser();
        String documentJson = SDKUtils.createJSON(entity);
        JSONObject response = (JSONObject) super.doPostCall(super.createFullURL(table) + "/documents", (JSONObject) parser.parse(documentJson));
        return r.readValue(response.toJSONString());
    }

    /**
     * Deletes a document.
     *
     * @param table Table in which to delete the document from.
     * @param id Document UUID to delete
     * @throws RESTException If there was a problem making the call.
     */
    @Override
    public void delete(Table table, UUID id) throws RESTException
    {
        super.doDeleteCall(super.createFullURL(table) + "/" + id.toString());
    }

    /**
     * Deletes a document.
     *
     * @param identifier Identifier to delete.
     * @throws RESTException If there was a problem making the call.
     */
    @Override
    public void delete(Identifier identifier) throws RESTException
    {
        if (identifier.size() < 3)
        {
            throw new IllegalArgumentException("Identifier not precise enough. Needs ID as well. " + identifier.toString());
        }
        super.doDeleteCall(super.createFullURL(identifier));
    }

    @Override
    public boolean exists(Identifier identifier) throws RESTException
    {
        if (identifier.size() < 3)
        {
            throw new IllegalArgumentException("Identifier not precise enough. Needs ID as well. " + identifier.toString());
        }
        try
        {
            super.doGetCall(super.createFullURL(identifier));
            return true;
        } catch (RESTException e)
        {
            if (e.getErrorCode() == 404)
            {
                return false;
            } else
            {
                throw e;
            }
        }
    }

    @Override
    public Document read(Identifier identifier) throws RESTException, IOException
    {
        if (identifier.size() < 3)
        {
            throw new IllegalArgumentException("Identifier not precise enough. Needs ID as well. " + identifier.toString());
        }
        JSONObject response = super.doGetCall(super.createFullURL(identifier));
        return r.readValue(response.toJSONString());
    }

    @Override
    public QueryResponseWrapper readAll(Identifier identifier, int limit, long offset) throws RESTException, IOException
    {
        if (identifier.size() < 2)
        {
            throw new IllegalArgumentException("Identifier not precise enough. Needs Database and Table. " + identifier.toString());
        }
        JSONObject response = super.doGetCall(super.createFullURL(identifier) + "/");
        return rQuery.readValue(response.toJSONString());
    }

    /**
     * Updates the document.
     *
     * @param entity Updates the Document. (Determines the document to update
     * based on the UUID.)
     * @throws RESTException If there was a problem making the call.
     * @throws ParseException If the passed in database couldn't be serialized.
     * @throws IOException If there was a problem de-serializing the JSON
     * response.
     */
    @Override
    public void update(Document entity) throws RESTException, ParseException, IOException
    {
        JSONParser parser = new JSONParser();
        String documentJson = SDKUtils.createJSON(entity);
        super.doPutCall(super.createFullURL(entity), (JSONObject) parser.parse(documentJson));
    }

}
