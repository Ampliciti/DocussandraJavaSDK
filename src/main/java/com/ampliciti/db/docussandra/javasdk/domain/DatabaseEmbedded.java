package com.ampliciti.db.docussandra.javasdk.domain;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class DatabaseEmbedded
{
    @JsonProperty
    private List<DatabaseResponse> databases;

    /**
     * @return the databases
     */
    public List<DatabaseResponse> getDatabases()
    {
        return databases;
    }

    /**
     * @param databases the databases to set
     */
    public void setDatabases(List<DatabaseResponse> databases)
    {
        this.databases = databases;
    }
    
}
