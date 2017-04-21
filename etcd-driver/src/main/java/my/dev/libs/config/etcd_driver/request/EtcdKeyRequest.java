package my.dev.libs.config.etcd_driver.request;

import my.dev.libs.config.etcd_driver.transport.EtcdDriverImpl;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class EtcdKeyRequest extends AbstractEtcdRequest {

    protected final String key;
    protected final Map<String, String> requestParams;


    public EtcdKeyRequest(EtcdDriverImpl clientImpl, HttpMethod method) {
        this(clientImpl, method, null);
    }


    public EtcdKeyRequest(EtcdDriverImpl clientImpl, HttpMethod method, String key) {
        super(null, clientImpl, method);

        if (key != null && key.startsWith("/")){
            key = key.substring(1);
        }

        this.key = key;
        this.requestParams = new HashMap<>();
    }

    public String getKey() {
        return key;
    }

    @Override
    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    @Override
    public String getUrl() {
        return "/v2/keys/" + ((key != null) ? key : "");
    }
}
