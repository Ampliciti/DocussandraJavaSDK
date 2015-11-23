package com.docussandra.javasdk.domain;

import com.docussandra.javasdk.SDKUtils;
import com.docussandra.javasdk.domain.hal.HALObject;
import com.strategicgains.syntaxe.annotation.ChildValidation;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectReader;

/**
 *
 * @author udeyoje
 */
public class DatabaseListResponse
{
    
    private final ObjectReader r = SDKUtils.getObjectMapper().reader(DatabaseResponse.class);

    @ChildValidation
    @JsonProperty
    private HALObject _links;
    
    @ChildValidation
    @JsonProperty("_embedded")
    private DatabaseEmbedded embedded;

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
     * @return the embedded
     */
    public DatabaseEmbedded getEmbedded()
    {
        return embedded;
    }

    /**
     * @param embedded the embedded to set
     */
    public void setEmbedded(DatabaseEmbedded embedded)
    {
        this.embedded = embedded;
    }
}
