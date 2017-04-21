package my.dev.libs.config.etcd_driver.transport;

import my.dev.libs.config.etcd_driver.request.EtcdRequest;
import my.dev.libs.config.etcd_driver.response.EtcdKeysResponse;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class EtcdDriverRestTemplate implements EtcdDriverImpl{
    private final static Logger logger = LoggerFactory.getLogger(EtcdDriverRestTemplate.class);
    private final RestTemplate restTemplate;
    private String url;
    private UrlValidator validator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);


    public EtcdDriverRestTemplate(String url){
        this.restTemplate = new RestTemplate();
        if(this.validator.isValid(url)) {
            try {
                this.url = (new URL(url)).toString();

            } catch (Exception e) {
                logger.error("Etcd server url : " + url + " - is not correct.");
                System.exit(-1);
            }
        }else {
            logger.error("Etcd server url : " + url + " - is not correct.");
            System.exit(-1);
        }

    }

    @Override
    public EtcdKeysResponse send(EtcdRequest etcdRequest) throws IOException, URISyntaxException {


        EtcdKeysResponse s = connect(etcdRequest);
        return s;
    }

    protected EtcdKeysResponse connect(final EtcdRequest etcdRequest) throws IOException, URISyntaxException {
        final URI uri;
        // when we are called from a redirect, the url in the com.ssc.etcd_driver.request may also
        // contain host and port!
        URI requestUri = new URI(this.url + etcdRequest.getUrl());
        if (requestUri.getHost() != null && requestUri.getPort() > -1) {
            uri = requestUri;
        } else if (System.getenv("CONFIG_URL") != null) {
            String endpoint_uri = System.getenv("CONFIG_URL");
            if(logger.isDebugEnabled()) {
                logger.debug("Will use environment variable {} as uri with value {}", "CONFIG_URL", endpoint_uri);
            }
            uri = new URI(endpoint_uri);
        } else {
            uri = new URI("http://127.0.0.1:2379");
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(new HttpHeaders());

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(requestUri.toString());
        if(etcdRequest.getMethod()!= HttpMethod.POST) {

            for (Iterator<Map.Entry<String, String>> mapIterator = etcdRequest.getRequestParams().entrySet().iterator(); mapIterator.hasNext(); ) {
                Map.Entry<String, String> entry = mapIterator.next();
                uriComponentsBuilder.queryParam(entry.getKey(),entry.getValue());
            }
        }
        ResponseEntity<EtcdKeysResponse> responseEntity = restTemplate.exchange(uriComponentsBuilder.toUriString(), etcdRequest.getMethod(), requestEntity, EtcdKeysResponse.class);
        return responseEntity.getBody();

    }





    @Override
    public void close() {
        logger.info("Shutting down Etcd4j Netty client");
    }
}
