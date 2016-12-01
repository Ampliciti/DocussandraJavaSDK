package com.ampliciti.db.docussandra.javasdk.dao.impl.parent;

import com.ampliciti.db.docussandra.javasdk.SDKConfig;
import com.ampliciti.db.docussandra.javasdk.dao.rest.RestDao;
import com.ampliciti.db.docussandra.javasdk.dao.rest.impl.HttpClientRestDao;

/**
 * Parent class for all DAOs.
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class DaoParent {

  /**
   * Config for our SDK.
   */
  private SDKConfig config;

  /**
   * Our RestDao for all calls.
   */
  protected final RestDao restDao;

  /**
   * Constructor.
   *
   * @param config Configuration object to base this dao on.
   */
  public DaoParent(SDKConfig config) {
    this.config = config;
    this.restDao = buildRestDao();
  }

  /**
   * Gets the base URL associated with this class.
   *
   * @return
   */
  public String getBaseURL() {
    return getConfig().getBaseURL();
  }

  /**
   * Builds our RestDao based on the type of configuration we have requested.
   *
   * @return A ready to use RestDao.
   */
  private RestDao buildRestDao() {
    if (SDKConfig.HTTPCLIENT.APACHE_HTTP.equals(getConfig().getHttpLib())) {
      return new HttpClientRestDao(getConfig());
    } else {
      throw new UnsupportedOperationException("Not done yet!");
    }
  }

  /**
   * Config for our SDK.
   * 
   * @return the config
   */
  public SDKConfig getConfig() {
    return config;
  }

}
