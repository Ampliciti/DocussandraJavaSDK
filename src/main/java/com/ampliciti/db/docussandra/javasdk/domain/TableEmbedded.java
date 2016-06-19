package com.ampliciti.db.docussandra.javasdk.domain;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class TableEmbedded
{
    @JsonProperty
    private List<TableResponse> tables;

    /**
     * @return the tables
     */
    public List<TableResponse> getTables()
    {
        return tables;
    }

    /**
     * @param tables the tables to set
     */
    public void setTables(List<TableResponse> tables)
    {
        this.tables = tables;
    }
    
}
