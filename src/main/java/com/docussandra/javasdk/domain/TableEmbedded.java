package com.docussandra.javasdk.domain;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author udeyoje
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