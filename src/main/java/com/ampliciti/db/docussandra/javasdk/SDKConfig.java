package com.ampliciti.db.docussandra.javasdk;

import com.ampliciti.db.docussandra.javasdk.dao.rest.RestDao;
import com.ampliciti.db.docussandra.javasdk.dao.rest.impl.HttpClientRestDao;

/**
 * Configuration class that contains the information needed to make a call with this SDK.
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class SDKConfig {

  /**
   * Security token. Not required if your Docussandra doesn't have security enabled.
   */
  private final String secToken;

  /**
   * Base URL for the API service.
   */
  private final String baseUrl;

  /**
   * The specific type of HTTP client we are using for this instance of the SDK.
   */
  private final HTTPCLIENT httpLib;

  /**
   * The specific type of HTTP client we are using for this instance of the SDK.
   * 
   * @return the httpLib
   */
  public HTTPCLIENT getHttpLib() {
    return httpLib;
  }

  /**
   * Type of HTTP client to use in order to send DAO requests.
   */
  public static enum HTTPCLIENT {

    /**
     * Standard Apache HTTP client. Unless you are using this SDK with Android, you probably want
     * this.
     */
    APACHE_HTTP,
    /**
     * Android HTTP client library. Because for some insane reason, Android can't coexist with
     * Apache HTTP client.
     */
    ANDRIOD_HTTP
  };

  /**
   * Constructor.
   *
   * @param httpLib Which HTTP library to use.
   * @param baseUrl Base URL for the API service.
   * @param secToken Security token
   */
  public SDKConfig(HTTPCLIENT httpLib, String baseUrl, String secToken) {
    this.secToken = secToken;
    this.baseUrl = cleanURL(baseUrl);
    this.httpLib = httpLib;
  }

  /**
   * Constructor. No security token.
   * 
   * @param httpLib Which HTTP library to use.
   * @param baseUrl Base URL for the API service.
   *
   */
  public SDKConfig(HTTPCLIENT httpLib, String baseUrl) {
    this.secToken = null;// intentional
    this.baseUrl = cleanURL(baseUrl);
    this.httpLib = httpLib;
  }


  /**
   * Cleans a url and ensures we have a trailing slash (/).
   *
   * @param urlToClean Url to clean.
   * @return a cleaned url.
   */
  private String cleanURL(String urlToClean) {
    String toReturn = urlToClean.trim();
    if (!toReturn.endsWith("/")) {
      toReturn = toReturn + "/";
    }
    return toReturn;
  }

  /**
   * Security token. Not required if your Docussandra doesn't have security enabled.
   *
   * @return the secToken
   */
  public String getSecToken() {
    return secToken;
  }

  /**
   * Base URL for the API service.
   *
   * @return the baseUrl
   */
  public String getBaseURL() {
    return baseUrl;
  }

}
