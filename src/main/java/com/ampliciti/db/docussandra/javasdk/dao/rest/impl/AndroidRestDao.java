package com.ampliciti.db.docussandra.javasdk.dao.rest.impl;

import com.ampliciti.db.docussandra.javasdk.SDKConfig;
import com.ampliciti.db.docussandra.javasdk.dao.rest.RestDao;
import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  private static final String USER_AGENT = "Mozilla/5.0";

  public AndroidRestDao(SDKConfig config) {
    this.config = config;
  }

  /**
   * Does an http GET call.
   *
   * @param url to GET
   * @return JSONObject Representing the response. If the route returns no body,
   * the object will be empty.
   * @throws RESTException
   */
  @Override
  public JSONObject doGetCall(String url) throws RESTException {
    logger.debug("Attempting to GET: " + url);
    HttpURLConnection con = null;
    try {
      URL obj = new URL(url);
      con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("User-Agent", USER_AGENT);
      con.setRequestProperty("Content-Type", "application/json");      
      if (config.getSecToken() != null) {
        con.setRequestProperty("Authorization", "Bearer " + config.getSecToken());
      }

      int responseCode = con.getResponseCode();      
      if (responseCode < 200 || responseCode >= 300) {
        throw new RESTException("Error when doing a GET call agaist: " + url, null, responseCode);
      }
      String response = parseResponse(con.getInputStream());
      if (response.length() != 0) {
        try {
          logger.debug("Result from GET call: " + response);
          JSONParser parser = new JSONParser();
          return (JSONObject) parser.parse(response);
        } catch (ParseException pe) {
          throw new RESTException("Could not parse JSON: " + response, pe);
        }
      } else {
        return new JSONObject();
      }
    } catch (IOException e) {
      throw new RESTException("Problem contacting REST service for POST", e);
    }
    finally{
      if(con != null){
        con.disconnect();
      }
    }
  }

  /**
   * Does an HTTP PUT call.
   *
   * @param url URL to put to.
   * @param toPost JSONObject of data to PUT.
   * @return JSONObject representing the response. If the route returns no body
   * the object will be empty.
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
   * @return JSONObject representing the response. If the route returns a null
   * body, the object will be empty.
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
   * @return JSONObject representing the response. If the route returns a null
   * body, the object will be empty.
   * @throws RESTException
   */
  @Override
  public JSONAware doPostCall(String url, JSONObject toPost, HashMap<String, String> headers)
          throws RESTException {
    logger.debug("Attempting to POST: " + url + ", payload: " + toPost.toJSONString());
    throw new UnsupportedOperationException("Not done yet!");

  }

  /**
   * Parses our response InputStream into a String.
   *
   * @param is InputString to parse.
   * @return String based on that InputStream.
   */
  private String parseResponse(InputStream is) throws IOException{
    BufferedReader in = null;
    try {
      in = new BufferedReader(
              new InputStreamReader(is));
      String inputLine;
      StringBuilder response = new StringBuilder();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      return response.toString();
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          logger.trace("Could not close input stream.", e);
        }
      }
    }
  }

}
