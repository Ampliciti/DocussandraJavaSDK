package com.docussandra.javasdk;

/**
 * Configuration class that contains the information needed to make a call.
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class Config
{

    /**
     * URL format. Long or short. Will default to LONG.
     *
     * @return the format
     */
    public Format getFormat()
    {
        return format;
    }

    /**
     * Security token. Not required if your Docussandra doesn't have security
     * enabled.
     *
     * @return the secToken
     */
    public String getSecToken()
    {
        return secToken;
    }

    /**
     * Base URL for the API service.
     *
     * @return the baseUrl
     */
    public String getBaseUrl()
    {
        return baseUrl;
    }

    /**
     * Route format enum.
     */
    public enum Format
    {

        SHORT,
        LONG;
    }

    /**
     * URL format. Long or short. Will default to LONG.
     */
    private final Format format;

    /**
     * Security token. Not required if your Docussandra doesn't have security
     * enabled.
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
     * @param format URL format to use.
     * @param secToken Security token
     */
    public Config(String baseUrl, Format format, String secToken)
    {
        this.format = format;
        this.secToken = secToken;
        this.baseUrl = cleanURL(baseUrl);
    }

    /**
     * Constructor. No security token.
     *
     * @param baseUrl Base URL for the API service.
     * @param format URL format to use.
     *
     */
    public Config(String baseUrl, Format format)
    {
        this.format = format;
        this.secToken = null;//intentional
        this.baseUrl = cleanURL(baseUrl);
    }

    /**
     * Constructor. Format will default to long.
     *
     * @param baseUrl Base URL for the API service.
     * @param secToken Security token
     */
    public Config(String baseUrl, String secToken)
    {
        this.format = Format.LONG;//default
        this.secToken = secToken;
        this.baseUrl = cleanURL(baseUrl);
    }

    /**
     * Constructor. Format will default to short. No security token.
     *
     * @param baseUrl Base URL for the API service.
     */
    public Config(String baseUrl)
    {
        this.format = Format.LONG;//default
        this.secToken = null;//intentional
        this.baseUrl = cleanURL(baseUrl);
    }

    /**
     * Cleans a url and ensures we have a trailing slash (/).
     *
     * @param urlToClean Url to clean.
     * @return a cleaned url.
     */
    private String cleanURL(String urlToClean)
    {
        String toReturn = urlToClean.trim();
        if (!toReturn.endsWith("/"))
        {
            toReturn = toReturn + "/";
        }
        return toReturn;
    }

}
