package my.dev.libs.config.etcd_driver.request;

import my.dev.libs.config.etcd_driver.transport.EtcdDriverImpl;
import my.dev.libs.config.etcd_driver.response.EtcdKeysResponse;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public abstract class EtcdRequest {

    protected final EtcdDriverImpl clientImpl;

    protected final HttpMethod method;

    protected Map<String, String> requestParams;

    private String url;

    public abstract EtcdKeysResponse send() throws IOException, URISyntaxException;

    public EtcdRequest(EtcdDriverImpl clientImpl, HttpMethod method) {
        this.clientImpl = clientImpl;
        this.method = method;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getRequestParams() {
        return requestParams;
    }
}
