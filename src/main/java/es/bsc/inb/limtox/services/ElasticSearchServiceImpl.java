package es.bsc.inb.limtox.services;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.springframework.stereotype.Service;

import es.bsc.inb.limtox.model.Document;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

	public void index(Document document) {
		Node node = nodeBuilder().clusterName("yourclustername").node();
		Client client = node.client();
	}
	
}
