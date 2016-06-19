package com.ampliciti.db.docussandra.javasdk.domain;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class DocumentEmbedded
{
    @JsonProperty
    private List<DocumentResponse> documents;

    /**
     * @return the documents
     */
    public List<DocumentResponse> getDocuments()
    {
        return documents;
    }

    /**
     * @param tables the documents to set
     */
    public void setDocuments(List<DocumentResponse> tables)
    {
        this.documents = tables;
    }
    
}
