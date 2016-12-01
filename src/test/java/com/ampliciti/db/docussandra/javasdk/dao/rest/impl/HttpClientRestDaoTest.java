package com.ampliciti.db.docussandra.javasdk.dao.rest.impl;

import com.ampliciti.db.docussandra.javasdk.RestUtils;
import com.ampliciti.db.docussandra.javasdk.SDKConfig;
import com.ampliciti.db.docussandra.javasdk.dao.impl.DatabaseDaoImpl;
import com.ampliciti.db.docussandra.javasdk.dao.rest.RestDao;
import com.ampliciti.docussandra.javasdk.testhelper.TestUtils;
import java.util.HashMap;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Jeffrey DeYoung
 */
public class HttpClientRestDaoTest {

  private SDKConfig config;
  private RestDao instance;

  public HttpClientRestDaoTest() {
    config = TestUtils.establishTestServer();
    //instance = new HttpClientRestDao(config);
    instance = new AndroidRestDao(config);
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    TestUtils.cleanupTestDb(config);
  }

  @After
  public void tearDown() {
    TestUtils.cleanupTestDb(config);
  }

  /**
   * Test of doGetCall method, of class HttpClientRestDao.
   */
  @Test
  public void testDoGetCall() throws Exception {
    System.out.println("doGetCall");
    DatabaseDaoImpl dbDao = new DatabaseDaoImpl(config);
    dbDao.create(TestUtils.getTestDb());
    //JSONObject expResult = null;
    JSONObject result = instance.doGetCall(RestUtils.createFullURL(config.getBaseURL(), TestUtils.getTestDb().getId()));
    assertNotNull(result);
    
  }

  /**
   * Test of doPutCall method, of class HttpClientRestDao.
   */
  @Test
  @Ignore
  public void testDoPutCall() throws Exception {
    System.out.println("doPutCall");
    String url = "";
    JSONObject toPost = null;
    HttpClientRestDao instance = null;
    JSONObject expResult = null;
    JSONObject result = instance.doPutCall(url, toPost);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of doDeleteCall method, of class HttpClientRestDao.
   */
  @Test
  @Ignore
  public void testDoDeleteCall() throws Exception {
    System.out.println("doDeleteCall");
    String url = "";
    HttpClientRestDao instance = null;
    instance.doDeleteCall(url);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of doPostCall method, of class HttpClientRestDao.
   */
  @Test
  @Ignore
  public void testDoPostCall_String_JSONObject() throws Exception {
    System.out.println("doPostCall");
    String url = "";
    JSONObject toPost = null;
    HttpClientRestDao instance = null;
    JSONAware expResult = null;
    JSONAware result = instance.doPostCall(url, toPost);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of doPostCall method, of class HttpClientRestDao.
   */
  @Test
  @Ignore
  public void testDoPostCall_3args() throws Exception {
    System.out.println("doPostCall");
    String url = "";
    JSONObject toPost = null;
    HashMap<String, String> headers = null;
    HttpClientRestDao instance = null;
    JSONAware expResult = null;
    JSONAware result = instance.doPostCall(url, toPost, headers);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
