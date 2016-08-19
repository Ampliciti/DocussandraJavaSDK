package com.ampliciti.db.docussandra.javasdk.dao.rest.impl;

import com.ampliciti.db.docussandra.javasdk.SDKConfig;
import com.ampliciti.db.docussandra.javasdk.dao.rest.RestDao;
import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RestDao that uses Apache HTTP client to make the calls.
 * @author Jeffrey DeYoung
 */
public class HttpClientRestDao implements RestDao {

  /**
   * Configurations for requests
   */
  public RequestConfig rc;
  /**
   * Config needed to make a call.
   */
  public SDKConfig config;
  /**
   * Logger for this class.
   */
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * Our http client.
   */
  public HttpClient client;
  
  public HttpClientRestDao(SDKConfig config){
        client = HttpClientBuilder.create().build();
    // if (base64Encode) {
    // this.authToken = new String(Base64.encodeBase64(authToken.getBytes()));
    // } else {
    // this.authToken = authToken;
    // }
    rc = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(3600000)
        .setConnectionRequestTimeout(60000).build();
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
    HttpGet request = new HttpGet(url);
    request.setConfig(rc);
    request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    if (config.getSecToken() != null) {
      request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getSecToken());
    }
    String responseString = "";
    try {
      HttpResponse response = client.execute(request);
      int responseCode = response.getStatusLine().getStatusCode();
      if (responseCode < 200 || responseCode >= 300) {
        throw new RESTException("Error when doing a GET call agaist: " + url, response);
      }
      if (response.getEntity() != null) {
        responseString = IOUtils.toString(response.getEntity().getContent());
        logger.debug("Result from GET call: " + responseString);
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(responseString);
      } else {
        return new JSONObject();
      }
    } catch (ParseException pe) {
      throw new RESTException("Could not parse JSON: " + responseString, pe);
    } catch (IOException e) {
      throw new RESTException("Problem contacting REST service for GET, URL: " + url, e);
    } finally {
      request.reset();
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
    HttpPut request = new HttpPut(url);
    request.setConfig(rc);
    request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    if (config.getSecToken() != null) {
      request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getSecToken());
    }
    String responseString = "";
    try {
      request.setEntity(new StringEntity(toPost.toJSONString()));
      HttpResponse response = client.execute(request);
      int responseCode = response.getStatusLine().getStatusCode();
      if (responseCode < 200 || responseCode >= 300) {
        throw new RESTException("Error when doing a PUT call against: " + url + ". JSON put: " + toPost.toJSONString(), response);
      }
      if (response.getEntity() != null) {
        responseString = IOUtils.toString(response.getEntity().getContent());
        logger.debug("Result from PUT call: " + responseString);
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(responseString);
      } else {
        return new JSONObject();
      }
    } catch (ParseException pe) {
      throw new RESTException("Could not parse JSON: " + responseString, pe);
    } catch (IOException e) {
      throw new RESTException("Problem contacting REST service for POST", e);
    } finally {
      request.reset();
    }
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
    HttpDelete request = new HttpDelete(url);
    request.setConfig(rc);
    request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    if (config.getSecToken() != null) {
      request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getSecToken());
    }
    try {
      HttpResponse response = client.execute(request);
      int responseCode = response.getStatusLine().getStatusCode();
      if (responseCode < 200 || responseCode >= 300) {
        throw new RESTException("Error when doing a DELETE call agaist: " + url, response);
      }
    } catch (IOException e) {
      throw new RESTException("Problem contacting REST service for DELETE, URL: " + url, e);
    } finally {
      request.reset();
    }
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
    HttpPost request = new HttpPost(url);
    request.setConfig(rc);
    request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    if (config.getSecToken() != null) {
      request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getSecToken());
    }
    String responseString = "";
    try {
      request.setEntity(new StringEntity(toPost.toJSONString()));
      HttpResponse response = client.execute(request);
      int responseCode = response.getStatusLine().getStatusCode();
      if (responseCode < 200 || responseCode >= 300) {
        throw new RESTException("Error when doing a POST call agaist: " + url + ". JSON posted: " + toPost.toJSONString(), response);
      }
      if (response.getEntity() != null) {
        responseString = IOUtils.toString(response.getEntity().getContent());
        logger.debug("Result from POST call: " + responseString);
        JSONParser parser = new JSONParser();
        try {
          return (JSONObject) parser.parse(responseString);
        } catch (ClassCastException e) {
          return (JSONArray) parser.parse(responseString);
        }
      } else {
        return new JSONObject();
      }
    } catch (ParseException pe) {
      throw new RESTException("Could not parse JSON: " + responseString, pe);
    } catch (IOException e) {
      throw new RESTException("Problem contacting REST service for POST", e);
    } finally {
      request.reset();
    }
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
  public JSONAware doPostCall(String url, JSONObject toPost, HashMap<String, String> headers) throws RESTException {
    logger.debug("Attempting to POST: " + url + ", payload: " + toPost.toJSONString());
    HttpPost request = new HttpPost(url);
    request.setConfig(rc);
    request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    if (config.getSecToken() != null) {
      request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getSecToken());
    }
    if (!headers.isEmpty()) {
      headers.entrySet();
      for (Map.Entry<String, String> entry : headers.entrySet()) {
        request.addHeader(entry.getKey(), entry.getValue());
      }
    }
    String responseString = "";
    try {
      request.setEntity(new StringEntity(toPost.toJSONString()));
      HttpResponse response = client.execute(request);
      int responseCode = response.getStatusLine().getStatusCode();
      if (responseCode < 200 || responseCode >= 300) {
        throw new RESTException("Error when doing a POST call against: " + url + ". JSON posted: " + toPost.toJSONString(), response);
      }
      if (response.getEntity() != null) {
        responseString = IOUtils.toString(response.getEntity().getContent());
        logger.debug("Result from POST call: " + responseString);
        JSONParser parser = new JSONParser();
        try {
          return (JSONObject) parser.parse(responseString);
        } catch (ClassCastException e) {
          return (JSONArray) parser.parse(responseString);
        }
      } else {
        return new JSONObject();
      }
    } catch (ParseException pe) {
      throw new RESTException("Could not parse JSON: " + responseString, pe);
    } catch (IOException e) {
      throw new RESTException("Problem contacting REST service for POST", e);
    } finally {
      request.reset();
    }
  }

}
