package com.ampliciti.db.docussandra.javasdk.domain;

import com.ampliciti.db.docussandra.javasdk.domain.hal.HALObject;
import com.strategicgains.syntaxe.annotation.ChildValidation;

/**
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class DatabaseListResponse
{

    @ChildValidation
    private HALObject _links;
    
    @ChildValidation
    private DatabaseEmbedded _embedded;

    /**
     * @return the _links
     */
    public HALObject getLinks()
    {
        return _links;
    }

    /**
     * @param _links the _links to set
     */
    public void setLinks(HALObject _links)
    {
        this._links = _links;
    }

    /**
     * @return the _embedded
     */
    public DatabaseEmbedded getEmbedded()
    {
        return _embedded;
    }

    /**
     * @param embedded the _embedded to set
     */
    public void setEmbedded(DatabaseEmbedded embedded)
    {
        this._embedded = embedded;
    }
}
