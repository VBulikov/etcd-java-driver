package my.dev.libs.config.etcd_driver.request;

import my.dev.libs.config.etcd_driver.transport.EtcdDriverImpl;
import my.dev.libs.config.etcd_driver.response.EtcdKeysResponse;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class AbstractEtcdRequest extends EtcdRequest {
    private final String url;

    public AbstractEtcdRequest(String url, EtcdDriverImpl clientImpl, HttpMethod method) {
        super(clientImpl, method);
        this.url = url;
    }

    @Override
    public EtcdKeysResponse send() throws IOException, URISyntaxException {
        return clientImpl.send(this);
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }
}
