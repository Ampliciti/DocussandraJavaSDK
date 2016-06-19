package com.ampliciti.db.docussandra.javasdk.domain;

import com.ampliciti.db.docussandra.javasdk.domain.hal.HALObject;
import com.pearson.docussandra.domain.objects.Database;
import com.strategicgains.syntaxe.annotation.ChildValidation;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class DatabaseResponse extends Database
{
    @ChildValidation
    @JsonProperty
    private HALObject _links;

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
}
