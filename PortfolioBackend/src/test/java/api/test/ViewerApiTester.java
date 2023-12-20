package api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import exceptionManager.InvalidCredentialException;
import jakarta.ws.rs.core.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import  org.junit.jupiter.api.Test;

import model.Occupation;
import model.Viewer;
import service.OccupationService;

public class ViewerApiTester {
	

    private static final String SERVICE_URL
      = "http://localhost:8080/PortfolioBackend/resources/viewer";
    int newViewerId;

    
    
    @Test
    public void test_getViewerAll() 
      throws ClientProtocolException, IOException {
    	
        HttpUriRequest request = new HttpGet(SERVICE_URL+"/allviewer");
        authenticate(request, "admin" + ":" + "Jafari9090!");


        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_OK, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    
    
  
    @Test
    public void test_getViewerById() 
      throws ClientProtocolException, IOException {
 
        HttpUriRequest request = new HttpGet(SERVICE_URL+"/validateuser?id=74&email=nasrin.jafari@ubs.com");
        authenticate(request, "admin" + ":" + "Jafari9090!");

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_OK, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    
    @Test
    public void negativeTest_getViewerBId() 
      throws ClientProtocolException, IOException {
 
        HttpUriRequest request = new HttpGet(SERVICE_URL+"/validateuser?id=0&email=nasrin.jafari@ubs.com");
        authenticate(request, "admin" + ":" + "Jafari9090!");

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    
    
 
    @Test 
    public void test_addNewAccount() throws ClientProtocolException, IOException, InvalidCredentialException {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	OccupationService occupationServ = new OccupationService();
    
    	Viewer viewer = new Viewer("Sebastian", "Mueller", LocalDate.parse("1997-01-01", dtf), "Male","Switzerland", "david.mueller@ubs.com","Dela9090!","Dela9090!", occupationServ.getById(2));
    
        //Converting the Object to JSONString
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.registerModule(new JavaTimeModule());
        String jsonString = mapper.writeValueAsString(viewer);
    	 
       
         
         HttpPost httpPost = new HttpPost(SERVICE_URL+"/createnewaccount");
         httpPost.setHeader("Accept", "application/json");
         httpPost.setHeader("Content-type", "application/json");
         
         StringEntity stringEntity = new StringEntity(jsonString);
         httpPost.setEntity(stringEntity);
         authenticate(httpPost, "viewer" + ":" + "Dela9090!");
         
             

         CloseableHttpResponse httpResponse = HttpClientBuilder
           .create()
           .build()
           .execute(httpPost);

         assertEquals(HttpStatus.SC_OK, httpResponse
           .getStatusLine()
           .getStatusCode());
         
        //Json To String Converter
         String viewerAsResponseString = EntityUtils.toString(httpResponse.getEntity());
         Viewer viewerResponse = mapper.readValue(viewerAsResponseString, Viewer.class);

         newViewerId =   viewerResponse.getViewerId();
    }
    
    @Test 
    public void negativeTest_addNewAccount() throws ClientProtocolException, IOException, InvalidCredentialException {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	OccupationService occupationServ = new OccupationService();
    
    	Viewer viewer = new Viewer("Sebastian", "Mueller", LocalDate.parse("1997-01-01", dtf), "Male","Switzerland", "david.mueller@ubs.com","Dela9090!","Dela!9090", occupationServ.getById(900));
    
        //Converting the Object to JSONString
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.registerModule(new JavaTimeModule());
        String jsonString = mapper.writeValueAsString(viewer);
    	 
       
         
         HttpPost httpPost = new HttpPost(SERVICE_URL+"/createnewaccount");
         httpPost.setHeader("Accept", "application/json");
         httpPost.setHeader("Content-type", "application/json");
         
         StringEntity stringEntity = new StringEntity(jsonString);
         httpPost.setEntity(stringEntity);
         authenticate(httpPost, "viewer" + ":" + "Dela9090!");
         
             

         CloseableHttpResponse httpResponse = HttpClientBuilder
           .create()
           .build()
           .execute(httpPost);

         assertEquals(HttpStatus.SC_BAD_REQUEST, httpResponse
           .getStatusLine()
           .getStatusCode());
         
        //Json To String Converter
         String viewerAsResponseString = EntityUtils.toString(httpResponse.getEntity());
         Viewer viewerResponse = mapper.readValue(viewerAsResponseString, Viewer.class);

         newViewerId =   viewerResponse.getViewerId();
    }
   
   @Test
    public void test_updateViewerById() throws ClientProtocolException, IOException, URISyntaxException {
    	
    	URIBuilder builder = new URIBuilder(SERVICE_URL+"/updateexistingviewer?email=nasrin.jafari@powercoders.org&id=");
    	builder.setParameter("id",String.valueOf(newViewerId));
 
    	 
        HttpUriRequest request = new HttpPut(builder.build());
        authenticate(request, "admin" + ":" + "Jafari9090!");

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_OK, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
   
   @Test
   public void negativeTest_updateViewerById() throws ClientProtocolException, IOException, URISyntaxException {
   	
   	URIBuilder builder = new URIBuilder(SERVICE_URL+"/updateexistingviewer?email=nasrin.jafari@powercoders.org&id=");
   	builder.setParameter("id",String.valueOf(0));

   	 
       HttpUriRequest request = new HttpPut(builder.build());
       authenticate(request, "admin" + ":" + "Jafari9090!");

       CloseableHttpResponse httpResponse = HttpClientBuilder
         .create()
         .build()
         .execute(request);

       assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse
         .getStatusLine()
         .getStatusCode());
   }
  
    @Test
    public void test_deleteViewerById() throws ClientProtocolException, IOException, URISyntaxException {
    	URIBuilder builder = new URIBuilder(SERVICE_URL+"/deleteviewer?");
    	builder.setParameter("id",String.valueOf(newViewerId));
 
        HttpDelete httpDelete = new HttpDelete(builder.build());
        authenticate(httpDelete, "admin" + ":" + "Jafari9090!");

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(httpDelete);

        assertEquals(HttpStatus.SC_OK, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    
    @Test
    public void negativeTest_deleteViewerById() throws ClientProtocolException, IOException, URISyntaxException {
    	URIBuilder builder = new URIBuilder(SERVICE_URL+"/deleteviewer?");
    	builder.setParameter("id",String.valueOf(0));
 
        HttpDelete httpDelete = new HttpDelete(builder.build());
        authenticate(httpDelete, "admin" + ":" + "Jafari9090!");

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(httpDelete);

        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    
    
    
    @Test
    public void test_getOccupation() 
      throws ClientProtocolException, IOException {
 
        HttpUriRequest request = new HttpGet(SERVICE_URL+"/occupation?id=2");
        authenticate(request, "admin" + ":" + "Jafari9090!");

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_OK, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    
    @Test
    public void negativeTest_getOccupation() 
      throws ClientProtocolException, IOException {
 
        HttpUriRequest request = new HttpGet(SERVICE_URL+"/occupation?id=1000");
        authenticate(request, "admin" + ":" + "Jafari9090!");

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    
    
    public void authenticate(HttpUriRequest request, String auth) {
    	byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.ISO_8859_1));
    	String authHeader = "Basic " + new String(encodedAuth);
    	request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
    }
    

//    
    
}