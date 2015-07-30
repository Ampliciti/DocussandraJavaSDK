package com.docussandra.javasdk.dao.impl.parent;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.exceptions.RESTException;
import java.io.IOException;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author udeyoje
 */
public abstract class DaoParent
{

    private static final String L_DATABASES = "/databases";
    private static final String L_TABLES = "/tables";
    private static final String L_INDEXES = "/indexes";
    private static final String L_DOCUMENTS = "/documents";

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

    private JSONParser parser = new JSONParser();

    public DaoParent(Config config)
    {
        this.config = config;
        client = HttpClientBuilder.create().build();
//        if (base64Encode) {
//            this.authToken = new String(Base64.encodeBase64(authToken.getBytes()));
//        } else {
//            this.authToken = authToken;
//        }
        rc = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(3600000).setConnectionRequestTimeout(60000).build();
    }

    /**
     * Config needed to make a call.
     *
     * @return the config
     */
    public Config getConfig()
    {
        return config;
    }

    public String getBaseURL()
    {
        return config.getBaseUrl();
    }

    public String createFullURL(String route)
    {
        StringBuilder toReturn = new StringBuilder(route);
        if (toReturn.toString().startsWith("/"))
        {
            toReturn.substring(1);//remove the first slash for consistancy, will be added back later
        }
        if (config.getFormat().equals(Config.Format.LONG)) //if it's long format; we have some more work to do
        {
            int slash = toReturn.indexOf("/");
            toReturn.insert(slash - 1, L_DATABASES);
            slash = toReturn.indexOf("/", slash + L_DATABASES.length() + 1);
            toReturn.insert(slash - 1, L_TABLES);
            //TODO: finish this
        }
        toReturn.insert(0, getBaseURL());//prepend the baseurl
        return toReturn.toString();
    }

    /**
     * Does an http GET call.
     *
     * @param url to GET
     * @return JSONObject Representing the response. If the route returns no
     * body, the object will be empty.
     * @throws RESTException
     */
    public JSONObject doGetCall(String url) throws RESTException
    {
        HttpGet request = new HttpGet(url);
        request.setConfig(rc);
        // add request header
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        //add auth if specified
        if (config.getSecToken() != null)
        {
            request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getSecToken());
        }
        String responseString = "";
        try
        {
            HttpResponse response = client.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode < 200 || responseCode >= 300)
            {
                throw new RESTException("Error when doing a GET call agaist: " + url, response);
            }

            if (response.getEntity() != null)
            {
                responseString = IOUtils.toString(response.getEntity().getContent());
                return (JSONObject) parser.parse(responseString);
            } else
            {
                return new JSONObject();
            }

        } catch (ParseException pe)
        {
            throw new RESTException("Could not parse JSON: " + responseString, pe);
        } catch (IOException e)
        {
            throw new RESTException("Problem contacting REST service for GET, URL: " + url, e);
        } finally
        {
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
    public JSONObject doPostCall(String url, JSONObject toPost) throws RESTException
    {
        HttpPost request = new HttpPost(url);
        request.setConfig(rc);
        // add request header
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        //add auth if specified
        if (config.getSecToken() != null)
        {
            request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getSecToken());
        }
        String responseString = "";
        try
        {
            //add the post data
            request.setEntity(new StringEntity(toPost.toJSONString()));
            HttpResponse response = client.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode < 200 || responseCode >= 300)
            {
                throw new RESTException("Error when doing a POST call agaist: " + url + ". JSON posted: " + toPost.toJSONString(), response);
            }

            if (response.getEntity() != null)
            {
                responseString = IOUtils.toString(response.getEntity().getContent());
                return (JSONObject) parser.parse(responseString);
            } else
            {
                return new JSONObject();
            }

        } catch (ParseException pe)
        {
            throw new RESTException("Could not parse JSON: " + responseString, pe);
        } catch (IOException e)
        {
            throw new RESTException("Problem contacting REST service for POST", e);
        } finally
        {
            request.reset();
        }

    }

    /**
     * Does an HTTP PUT call.
     *
     * @param url URL to put to.
     * @param toPost JSONObject of data to PUT.
     * @return JSONObject representing the response. If the route returns no
     * body the object will be empty.
     * @throws RESTException
     */
    public JSONObject doPutCall(String url, JSONObject toPost) throws RESTException
    {
        HttpPut request = new HttpPut(url);
        request.setConfig(rc);
        // add request header
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        //add auth if specified
        if (config.getSecToken() != null)
        {
            request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getSecToken());
        }
        String responseString = "";
        try
        {
            //add the put data
            request.setEntity(new StringEntity(toPost.toJSONString()));
            HttpResponse response = client.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode < 200 || responseCode >= 300)
            {
                throw new RESTException("Error when doing a PUT call agaist: " + url + ". JSON put: " + toPost.toJSONString(), response);
            }

            if (response.getEntity() != null)
            {
                responseString = IOUtils.toString(response.getEntity().getContent());
                return (JSONObject) parser.parse(responseString);
            } else
            {
                return new JSONObject();
            }

        } catch (ParseException pe)
        {
            throw new RESTException("Could not parse JSON: " + responseString, pe);
        } catch (IOException e)
        {
            throw new RESTException("Problem contacting REST service for POST", e);
        } finally
        {
            request.reset();
        }

    }

    /**
     * Does an http DELETE call.
     *
     * @param url to DELETE
     * @throws RESTException
     */
    public void doDeleteCall(String url) throws RESTException
    {
        HttpDelete request = new HttpDelete(url);
        request.setConfig(rc);
        // add request header
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        //add auth if specified
        if (config.getSecToken() != null)
        {
            request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getSecToken());
        }
        try
        {
            HttpResponse response = client.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode < 200 || responseCode >= 300)
            {
                throw new RESTException("Error when doing a DELETE call agaist: " + url, response);
            }
        } catch (IOException e)
        {
            throw new RESTException("Problem contacting REST service for DELETE, URL: " + url, e);
        } finally
        {
            request.reset();
        }

    }

}
