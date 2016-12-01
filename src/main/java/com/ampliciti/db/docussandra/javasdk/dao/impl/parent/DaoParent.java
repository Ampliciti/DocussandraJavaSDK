package com.ampliciti.db.docussandra.javasdk.dao.impl.parent;

import com.ampliciti.db.docussandra.javasdk.Config;
import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import com.pearson.docussandra.domain.objects.Document;
import com.pearson.docussandra.domain.objects.Identifier;
import com.pearson.docussandra.domain.objects.Table;
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
 * Parent class for all DAOs.
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class DaoParent implements RESTDao {

  private static final String L_DATABASES = "/databases";
  private static final String L_TABLES = "/tables";
  private static final String L_INDEXES = "/indexes";
  private static final String L_DOCUMENTS = "/documents";
  private static final String SLASH = "/";

  /**
   * Logger for this class.
   */
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * Config needed to make a call.
   */
  private Config config;

  /**
   * Our http client.
   */
  private HttpClient client;

  /**
   * Configurations for requests
   */
  private RequestConfig rc;

  /**
   * Constructor.
   * 
   * @param config Configuration object to base this dao on.
   */
  public DaoParent(Config config) {
    this.config = config;
    client = HttpClientBuilder.create().build();
    // if (base64Encode) {
    // this.authToken = new String(Base64.encodeBase64(authToken.getBytes()));
    // } else {
    // this.authToken = authToken;
    // }
    rc = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(3600000)
        .setConnectionRequestTimeout(60000).build();
  }

  /**
   * Config needed to make a call.
   *
   * @return the config
   */
  public Config getConfig() {
    return config;
  }

  /**
   * Gets the base URL associated with this class.
   * @return
   */
  public String getBaseURL() {
    return config.getBaseUrl();
  }

  /**
   * Creates a full usable REST URL based on the passed in parameters.
   * @param id Identifier for what we are looking to create a URL for.
   * @return
   */
  public String createFullURL(Identifier id) {
    int size = id.components().size();
    if (size == 1) {
      return createFullURL(id.getDatabaseName(), null, null);
    } else if (size == 2) {
      return createFullURL(id.getDatabaseName(), id.getTableName(), null);
    } else { // size should == 3
      return createFullURL(id.getDatabaseName(), id.getTableName(),
          id.components().get(2).toString());
    }
  }

  /**
   * Creates a full usable REST URL based on the passed in parameters.
   * @param tb Table to create the URL for.
   * @return A full REST url.
   */
  public String createFullURL(Table tb) {
    return createFullURL(tb.getDatabaseName(), tb.getName());
  }

  /**
   * Creates a full usable REST URL based on the passed in parameters.
   * 
   * @param doc Document to create the URL for.
   * @return A full REST url.
   */
  public String createFullURL(Document doc) {
    return createFullURL(doc.getDatabaseName(), doc.getTableName(), doc.getUuid().toString());
  }

  /**
   * Creates a full usable REST URL based on the passed in parameters.
   * @param db Database name to create the URL for.
   * @param tb Table name to create the URL for.
   * @return A full REST url.
   */
  public String createFullURL(String db, String tb) {
    return createFullURL(db, tb, null);
  }

  /**
   * Creates a full usable REST URL based on the passed in parameters.
   * @param db Database name to use in the URL.
   * @param tb Table name to use in the URL.
   * @param docUUID Document UUID (as a String).
   * @return A REST URL.
   */
  public String createFullURL(String db, String tb, String docUUID) {
    StringBuilder toReturn = new StringBuilder();
    // toReturn.append("/");
    if (!toReturn.toString().startsWith("/")) {
      // toReturn = new StringBuilder(toReturn.substring(1));//remove the first slash for
      // consistancy, will be added back later
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
      toReturn = new StringBuilder(toReturn.substring(1));// remove the first slash for consistancy,
                                                          // will be added back later
    }
    toReturn.insert(0, getBaseURL());// prepend the baseurl
    return toReturn.toString();
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
    HttpGet request = new HttpGet(url);
    request.setConfig(rc);
    // add request header
    request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    // add auth if specified
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
    HttpPost request = new HttpPost(url);
    request.setConfig(rc);
    // add request header
    request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    // add auth if specified
    if (config.getSecToken() != null) {
      request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getSecToken());
    }
    String responseString = "";
    try {
      // add the post data
      request.setEntity(new StringEntity(toPost.toJSONString()));
      HttpResponse response = client.execute(request);
      int responseCode = response.getStatusLine().getStatusCode();
      if (responseCode < 200 || responseCode >= 300) {
        throw new RESTException("Error when doing a POST call agaist: " + url + ". JSON posted: "
            + toPost.toJSONString(), response);
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
   * @return JSONObject representing the response. If the route returns a null body, the object will
   *         be empty.
   * @throws RESTException
   */
  @Override
  public JSONAware doPostCall(String url, JSONObject toPost, HashMap<String, String> headers)
      throws RESTException {
    logger.debug("Attempting to POST: " + url + ", payload: " + toPost.toJSONString());
    HttpPost request = new HttpPost(url);
    request.setConfig(rc);
    // add request header
    request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    // add auth if specified
    if (config.getSecToken() != null) {
      request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getSecToken());
    }
    // check for headers if present add them to the request
    if (!headers.isEmpty()) {
      headers.entrySet();
      for (Map.Entry<String, String> entry : headers.entrySet()) {
        request.addHeader(entry.getKey(), entry.getValue());
      }
    }
    String responseString = "";
    try {
      // add the post data
      request.setEntity(new StringEntity(toPost.toJSONString()));
      HttpResponse response = client.execute(request);
      int responseCode = response.getStatusLine().getStatusCode();
      if (responseCode < 200 || responseCode >= 300) {
        throw new RESTException("Error when doing a POST call against: " + url + ". JSON posted: "
            + toPost.toJSONString(), response);
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
    HttpPut request = new HttpPut(url);
    request.setConfig(rc);
    // add request header
    request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    // add auth if specified
    if (config.getSecToken() != null) {
      request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getSecToken());
    }
    String responseString = "";
    try {
      // add the put data
      request.setEntity(new StringEntity(toPost.toJSONString()));
      HttpResponse response = client.execute(request);
      int responseCode = response.getStatusLine().getStatusCode();
      if (responseCode < 200 || responseCode >= 300) {
        throw new RESTException(
            "Error when doing a PUT call against: " + url + ". JSON put: " + toPost.toJSONString(),
            response);
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
    // add request header
    request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    // add auth if specified
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

}
