package api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import  org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import model.Viewer;

public class ApiTester {
	

    private static final String SERVICE_URL
      = "http://localhost:8080/PortfolioBackend/resources/v2";
    int newViewerId;

    
    
    @Test
    @Order(1)
    public void test_getViewerAll() 
      throws ClientProtocolException, IOException {
 
        HttpUriRequest request = new HttpGet(SERVICE_URL+"/getallviewer");

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_OK, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
  
    @Test
    @Order(2)
    public void test_getViewerById() 
      throws ClientProtocolException, IOException {
 
        HttpUriRequest request = new HttpGet(SERVICE_URL+"/validateuser?id=66");

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_OK, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
 
    @Test 
    @Order(3)
    public void test_addNewAccount() throws ClientProtocolException, IOException {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    	Viewer viewer = new Viewer("Sebastian", "Mueller", LocalDate.parse("1997-01-01", dtf), "Male","Switzerland", "david.mueller@ubs.com","1234","1236", 1);
    
        //Converting the Object to JSONString
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.registerModule(new JavaTimeModule());
        String jsonString = mapper.writeValueAsString(viewer);
    	 
        CloseableHttpClient httpclient  = HttpClients.createDefault();
       
         
         HttpPost httpPost = new HttpPost(SERVICE_URL+"/createnewaccount");
         httpPost.setHeader("Accept", "application/json");
         httpPost.setHeader("Content-type", "application/json");
         
         StringEntity stringEntity = new StringEntity(jsonString);
         httpPost.setEntity(stringEntity);
         
             

         CloseableHttpResponse httpResponse = HttpClientBuilder
           .create()
           .build()
           .execute(httpPost);

         assertEquals(HttpStatus.SC_CREATED, httpResponse
           .getStatusLine()
           .getStatusCode());
         
        //Json To String Converter
         String viewerAsResponseString = EntityUtils.toString(httpResponse.getEntity());
         Viewer viewerResponse = mapper.readValue(viewerAsResponseString, Viewer.class);

         newViewerId =   viewerResponse.getViewerId();
    }
   
   @Test
   @Order(4)
    public void test_updateViewerById() throws ClientProtocolException, IOException, URISyntaxException {
    	
    	URIBuilder builder = new URIBuilder(SERVICE_URL+"/updateexistingviewer?email=nasrin.jafari@powercoders.org&id=");
    	builder.setParameter("id",String.valueOf(newViewerId));
 
    	 
        HttpUriRequest request = new HttpPut(builder.build());

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(request);

        assertEquals(HttpStatus.SC_ACCEPTED, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
  
    @Test
    @Order(5)
    public void test_deleteViewerById() throws ClientProtocolException, IOException, URISyntaxException {
    	URIBuilder builder = new URIBuilder(SERVICE_URL+"/deleteviewer?");
    	builder.setParameter("id",String.valueOf(newViewerId));
 
        HttpDelete httpDelete = new HttpDelete(builder.build());
     
        

        CloseableHttpResponse httpResponse = HttpClientBuilder
          .create()
          .build()
          .execute(httpDelete);

        assertEquals(HttpStatus.SC_OK, httpResponse
          .getStatusLine()
          .getStatusCode());
    }
    

//    
    
}