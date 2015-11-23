package com.docussandra.javasdk.dao.impl;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.SDKUtils;
import com.docussandra.javasdk.dao.DocumentDao;
import com.docussandra.javasdk.dao.impl.parent.DaoParent;
import com.docussandra.javasdk.domain.DocumentListResponse;
import com.docussandra.javasdk.domain.DocumentResponse;
import com.fasterxml.jackson.databind.ObjectReader;
import com.strategicgains.docussandra.domain.objects.Document;
import com.strategicgains.docussandra.domain.objects.Identifier;
import com.strategicgains.docussandra.domain.objects.QueryResponseWrapper;
import org.json.simple.parser.JSONParser;

/**
 * Document dao that accesses Docussandra to manipulate the documents via the
 * REST API.
 *
 * @author udeyoje
 */
public class DocumentDaoImpl extends DaoParent implements DocumentDao
{

    private final JSONParser parser = new JSONParser();
    private final ObjectReader r = SDKUtils.getObjectMapper().reader(DocumentResponse.class);
    private final ObjectReader rList = SDKUtils.getObjectMapper().reader(DocumentListResponse.class);

    public DocumentDaoImpl(Config config)
    {
        super(config);
    }

    @Override
    public Document create(Document entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Document entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Identifier id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(Identifier identifier)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Document read(Identifier identifier)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public QueryResponseWrapper readAll(String database, String tableString, int limit, long offset)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Document update(Document entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
