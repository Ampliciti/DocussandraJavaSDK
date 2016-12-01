package com.ampliciti.db.docussandra.javasdk;

import com.pearson.docussandra.domain.objects.Document;
import com.pearson.docussandra.domain.objects.Identifier;
import com.pearson.docussandra.domain.objects.Table;

/**
 * Utility class for working with REST endpoints and URLs.
 * 
 * @author Jeffrey DeYoung
 */
public class RestUtils {
  public static final String L_INDEXES = "/indexes";
  public static final String SLASH = "/";
  public static final String L_DATABASES = "/databases";
  public static final String L_TABLES = "/tables";
  public static final String L_DOCUMENTS = "/documents";

  /**
   * Creates a full usable REST URL based on the passed in parameters.
   *
   * @param id Identifier for what we are looking to create a URL for.
   * @return
   */
  public static String createFullURL(String baseUrl, Identifier id) {
    int size = id.components().size();
    if (size == 1) {
      return createFullURL(baseUrl, id.getDatabaseName(), null, null);
    } else if (size == 2) {
      return createFullURL(baseUrl, id.getDatabaseName(), id.getTableName(), null);
    } else {
      return createFullURL(baseUrl, id.getDatabaseName(), id.getTableName(),
          id.components().get(2).toString());
    }
  }

  /**
   * Creates a full usable REST URL based on the passed in parameters.
   *
   * @param tb Table to create the URL for.
   * @return A full REST url.
   */
  public static String createFullURL(String baseUrl, Table tb) {
    return createFullURL(baseUrl, tb.getDatabaseName(), tb.getName());
  }

  /**
   * Creates a full usable REST URL based on the passed in parameters.
   *
   * @param doc Document to create the URL for.
   * @return A full REST url.
   */
  public static String createFullURL(String baseUrl, Document doc) {
    return createFullURL(baseUrl, doc.getDatabaseName(), doc.getTableName(),
        doc.getUuid().toString());
  }

  /**
   * Creates a full usable REST URL based on the passed in parameters.
   *
   * @param db Database name to create the URL for.
   * @param tb Table name to create the URL for.
   * @return A full REST url.
   */
  public static String createFullURL(String baseUrl, String db, String tb) {
    return createFullURL(baseUrl, db, tb, null);
  }

  /**
   * Creates a full usable REST URL based on the passed in parameters.
   *
   * @param db Database name to use in the URL.
   * @param tb Table name to use in the URL.
   * @param docUUID Document UUID (as a String).
   * @return A REST URL.
   */
  public static String createFullURL(String baseUrl, String db, String tb, String docUUID) {
    StringBuilder toReturn = new StringBuilder();
    if (!toReturn.toString().startsWith("/")) {
      toReturn.insert(0, "/");
    }
    int slash = toReturn.indexOf("/");
    if (slash == -1) {
      slash = 0;
    }
    toReturn.insert(slash, L_DATABASES);
    if (db != null) {
      toReturn.append(db);
    }
    if (tb != null) {
      toReturn.append(L_TABLES);
      toReturn.append(SLASH);
      toReturn.append(tb);
    }
    if (docUUID != null) {
      toReturn.append(L_DOCUMENTS);
      toReturn.append(SLASH);
      toReturn.append(docUUID);
    }
    if (toReturn.toString().startsWith("/")) {
      toReturn = new StringBuilder(toReturn.substring(1));
    }
    toReturn.insert(0, baseUrl);
    return toReturn.toString();
  }

}
