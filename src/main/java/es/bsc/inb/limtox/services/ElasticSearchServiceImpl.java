package es.bsc.inb.limtox.services;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import es.bsc.inb.limtox.model.Document;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

	private RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200, "http")));
	
	public void index(Document document) {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200, "http")));
		String json = "{" +
		        "\"user\":\"kimchy\"," +
		        "\"postDate\":\"2013-01-30\"," +
		        "\"message\":\"trying out Elasticsearch\"" +
		    "}";
    	try {
			IndexRequest request = new IndexRequest("twitter", "doc","12345"); 
			request.source(json, XContentType.JSON);
			IndexResponse response = client.index(request);
			response.toString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
