package com.ampliciti.db.docussandra.javasdk.dao.rest.impl;

import com.ampliciti.db.docussandra.javasdk.SDKConfig;
import com.ampliciti.db.docussandra.javasdk.dao.rest.RestDao;
import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import java.io.IOException;
import java.util.HashMap;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestDao that uses Apache HTTP client to make the calls.
 *
 * @author Jeffrey DeYoung
 */
public class AndroidRestDao implements RestDao {

  /**
   * Config needed to make a call.
   */
  public SDKConfig config;

  /**
   * Logger for this class.
   */
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public AndroidRestDao(SDKConfig config) {
    this.config = config;
  }

  /**
   * Does an http GET call.
   *
   * @param url to GET
   * @return JSONObject Representing the response. If the route returns no body, the object will be
   *         empty.
   * @throws RESTException
   */
  @Override
  public JSONObject doGetCall(String url) throws RESTException {
    logger.debug("Attempting to GET: " + url);
    String responseString = "";
    RestTemplate restTemplate = new RestTemplate();
    try {
      responseString = restTemplate.getForObject(url, String.class);
      if (responseString != null) {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(responseString);
      }
    } catch (ParseException pe) {
      throw new RESTException("Could not parse JSON: " + responseString, pe);
    } catch (Exception e) {
      throw new RESTException("Problem contacting REST service for GET, URL: " + url, e);
    }
    throw new UnsupportedOperationException("Not done yet!");
  }

  /**
   * Does an HTTP PUT call.
   *
   * @param url URL to put to.
   * @param toPost JSONObject of data to PUT.
   * @return JSONObject representing the response. If the route returns no body the object will be
   *         empty.
   * @throws RESTException
   */
  @Override
  public JSONObject doPutCall(String url, JSONObject toPost) throws RESTException {
    logger.debug("Attempting to PUT: " + url + ", payload: " + toPost.toJSONString());
    throw new UnsupportedOperationException("Not done yet!");
  }

  /**
   * Does an http DELETE call.
   *
   * @param url to DELETE
   * @throws RESTException
   */
  @Override
  public void doDeleteCall(String url) throws RESTException {
    logger.debug("Attempting to DELETE: " + url);
    throw new UnsupportedOperationException("Not done yet!");
  }

  /**
   * Does an HTTP POST call.
   *
   * @param url URL to post to.
   * @param toPost JSONObject of data to POST.
   * @return JSONObject representing the response. If the route returns a null body, the object will
   *         be empty.
   * @throws RESTException
   */
  @Override
  public JSONAware doPostCall(String url, JSONObject toPost) throws RESTException {
    logger.debug("Attempting to POST: " + url + ", payload: " + toPost.toJSONString());
    throw new UnsupportedOperationException("Not done yet!");

  }

  /**
   * Does an HTTP POST call with headers
   *
   * @param url URL to post to.
   * @param toPost JSONObject of data to POST.
   * @return JSONObject representing the response. If the route returns a null body, the object will
   *         be empty.
   * @throws RESTException
   */
  @Override
  public JSONAware doPostCall(String url, JSONObject toPost, HashMap<String, String> headers)
      throws RESTException {
    logger.debug("Attempting to POST: " + url + ", payload: " + toPost.toJSONString());
    throw new UnsupportedOperationException("Not done yet!");

  }

}
