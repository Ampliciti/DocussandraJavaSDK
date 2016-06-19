package com.ampliciti.db.docussandra.javasdk.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.json.simple.JSONObject;

/**
 * Exception for problems with REST calls.
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class RESTException extends Exception {

    /**
     * Error code for the request, if applicable.
     */
    private int errorCode;

    /**
     * Default constructor.
     */
    public RESTException() {
    }

    /**
     * Constructor that takes a string of an error message.
     *
     * @param message Message for this exception.
     */
    public RESTException(String message) {
        super(message);
    }

    /**
     * Constructor that takes a string of an error message and a HttpResponse.
     *
     * @param message Message for this exception.
     * @param response response that caused the error.
     */
    public RESTException(String message, HttpResponse response) {
        super(message + generateErrorMessage(response));
        this.errorCode = response.getStatusLine().getStatusCode();
    }

    private static String generateErrorMessage(HttpResponse response) {
        StringBuilder message = new StringBuilder(21);
        message.append(" HTTP Error code: ");
        message.append(response.getStatusLine().getStatusCode());
        //below here probably doesn't actually do anything most of the time -- you can't read from the same stream twice
        BufferedReader rd = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(response.getEntity().getContent());
            rd = new BufferedReader(isr);
            String responseBody = IOUtils.toString(rd);
            if(responseBody != null){
                message.append(" The body of the response was: ");
                message.append(responseBody);
            }
        } catch (Exception e) {
            //this is just extra error information; do nothing
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {;
                }
            }
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {;
                }
            }
        }
        return message.toString();
    }

    /**
     * Constructor that takes a Throwable.
     *
     * @param e Throwable that caused this exception.
     */
    public RESTException(Throwable e) {
        super(e);
    }

    /**
     * Constructor that takes a string of an error message and a Throwable.
     *
     * @param message Message for this exception.
     * @param e Throwable that caused this exception.
     */
    public RESTException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * Constructor that indicates a problem with the format of the JSON response
     * from a REST call.
     *
     * @param responseObject JSON response from the server.S
     */
    public RESTException(JSONObject responseObject) {
        super("Response was not in the expected format: " + responseObject.toJSONString());
    }

    /**
     * Constructor that indicates a problem with the format of the JSON response
     * from a REST call with included throwable information.
     *
     * @param responseObject JSON response from the server.S
     * @param e Throwable that caused this exception.
     */
    public RESTException(JSONObject responseObject, Throwable e) {
        super("Response was not in the expected format: " + responseObject.toJSONString(), e);
    }

    /**
     * Error code for the request, if applicable.
     *
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

}
