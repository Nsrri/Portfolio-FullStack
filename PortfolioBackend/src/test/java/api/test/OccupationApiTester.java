package api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.core.HttpHeaders;
import model.Occupation;
import service.ViewerService;

public class OccupationApiTester {
    private static final String SERVICE_URL
    = "http://localhost:8080/PortfolioBackend/resources/occupation";
    
    int newOccupationId = 0;
    
    
    
    @Test
    public void test_getOccupationAll() 
      throws ClientProtocolException, IOException {
    	
        HttpUriRequest request = new HttpGet(SERVICE_URL+ "/getalloccupation");
        authenticate(request);


        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_OK, httpResponse
          .getStatusLine()
          .getStatusCode());
    

}
    
    @Test
    public void test_getOccuptionById() 
      throws ClientProtocolException, IOException {
 
        HttpUriRequest request = new HttpGet(SERVICE_URL+"/getbyid?id=2");
        authenticate(request);

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_OK, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    
    @Test
    public void negativeTest_getOccuptionById() 
      throws ClientProtocolException, IOException {
 
        HttpUriRequest request = new HttpGet(SERVICE_URL+"/validateuser?id=-1");
        authenticate(request);

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    
    @Test 
    public void test_addNewOccupation() throws ClientProtocolException, IOException {
    	
    	ViewerService viewer = new ViewerService();
    	Occupation occupation = new Occupation("Civil Engineer",viewer.extractViewerByOccupation(2) );
    
        //Converting the Object to JSONString
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.registerModule(new JavaTimeModule());
        String jsonString = mapper.writeValueAsString(occupation);
    	 
       
         
         HttpPost httpPost = new HttpPost(SERVICE_URL+"/createnewoccupation");
         httpPost.setHeader("Accept", "application/json");
         httpPost.setHeader("Content-type", "application/json");
         
         StringEntity stringEntity = new StringEntity(jsonString);
         httpPost.setEntity(stringEntity);
         authenticate(httpPost);
         
             

         CloseableHttpResponse httpResponse = HttpClientBuilder
           .create()
           .build()
           .execute(httpPost);

         assertEquals(HttpStatus.SC_CREATED, httpResponse
           .getStatusLine()
           .getStatusCode());
         
        //Json To String Converter
         String viewerAsResponseString = EntityUtils.toString(httpResponse.getEntity());
         Occupation occResponse = mapper.readValue(viewerAsResponseString,  Occupation.class);

         newOccupationId =   occResponse.getOccupation_id();
    }
    
    
    @Test 
    public void negativeTest_addNewOccupation() throws ClientProtocolException, IOException {
    	ViewerService viewer = new ViewerService();
    
    	Occupation occupation = new Occupation( "cvs", viewer.extractViewerByOccupation(2) );
    
        //Converting the Object to JSONString
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.registerModule(new JavaTimeModule());
        String jsonString = mapper.writeValueAsString(occupation);
    	 
       
         
         HttpPost httpPost = new HttpPost(SERVICE_URL+"/createnewoccupation");
         httpPost.setHeader("Accept", "application/json");
         httpPost.setHeader("Content-type", "application/json");
         
         StringEntity stringEntity = new StringEntity(jsonString);
         httpPost.setEntity(stringEntity);
         authenticate(httpPost);
         
             

         CloseableHttpResponse httpResponse = HttpClientBuilder
           .create()
           .build()
           .execute(httpPost);

         assertEquals(HttpStatus.SC_BAD_REQUEST, httpResponse
           .getStatusLine()
           .getStatusCode());
         
        //Json To String Converter
         String viewerAsResponseString = EntityUtils.toString(httpResponse.getEntity());
         Occupation occResponse = mapper.readValue(viewerAsResponseString,  Occupation.class);

         newOccupationId =   occResponse.getOccupation_id();
    }
    
    @Test
    public void test_updateOccupationById() throws ClientProtocolException, IOException, URISyntaxException {
    	
    	URIBuilder builder = new URIBuilder(SERVICE_URL+"/updateexistingoccupation?name=c&id=");
    	builder.setParameter("id",String.valueOf(newOccupationId));
 
    	 
        HttpUriRequest request = new HttpPut(builder.build());
        authenticate(request);

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_ACCEPTED, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    
    @Test
    public void negativeTest_updateOccupationById() throws ClientProtocolException, IOException, URISyntaxException {
    	
    	URIBuilder builder = new URIBuilder(SERVICE_URL+"/updateexistingoccupation?name=c&id=-1");
    	builder.setParameter("id",String.valueOf(-1));
 
    	 
        HttpUriRequest request = new HttpPut(builder.build());
        authenticate(request);

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    
//    @Test
//    public void test_deleteOccupationById() throws ClientProtocolException, IOException, URISyntaxException {
//    	URIBuilder builder = new URIBuilder(SERVICE_URL+"/deleteoccupation?");
//    	builder.setParameter("id",String.valueOf(newOccupationId));
// 
//        HttpDelete httpDelete = new HttpDelete(builder.build());
//        authenticate(httpDelete);
//
//        CloseableHttpResponse httpResponse = HttpClientBuilder
//          .create()
//          .build()
//          .execute(httpDelete);
//
//        assertEquals(HttpStatus.SC_OK, httpResponse
//          .getStatusLine()
//          .getStatusCode());
//    }
    
    @Test
    public void negativeTest_deleteOccupationById() throws ClientProtocolException, IOException, URISyntaxException {
    	URIBuilder builder = new URIBuilder(SERVICE_URL+"/deleteoccupation?");
    	builder.setParameter("id",String.valueOf(-1));
 
        HttpDelete httpDelete = new HttpDelete(builder.build());
        authenticate(httpDelete);

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(httpDelete);

        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    
    public void authenticate(HttpUriRequest request) {
    	String auth = "admin" + ":" + "Jafari9090!";
    	byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.ISO_8859_1));
    	String authHeader = "Basic " + new String(encodedAuth);
    	request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
    }
}
