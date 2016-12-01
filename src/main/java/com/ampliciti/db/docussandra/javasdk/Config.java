package com.ampliciti.db.docussandra.javasdk;

/**
 * Configuration class that contains the information needed to make a call.
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class Config {


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
  public String getBaseUrl() {
    return baseUrl;
  }

  /**
   * Security token. Not required if your Docussandra doesn't have security enabled.
   */
  private final String secToken;

  /**
   * Base URL for the API service.
   */
  private final String baseUrl;

  /**
   * Constructor.
   *
   * @param baseUrl Base URL for the API service.
   * @param secToken Security token
   */
  public Config(String baseUrl, String secToken) {
    this.secToken = secToken;
    this.baseUrl = cleanURL(baseUrl);
  }

  /**
   * Constructor. No security token.
   *
   * @param baseUrl Base URL for the API service.
   *
   */
  public Config(String baseUrl) {
    this.secToken = null;// intentional
    this.baseUrl = cleanURL(baseUrl);
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

}
