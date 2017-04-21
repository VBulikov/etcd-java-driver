package my.dev.libs.config.etcd_driver.request;

import my.dev.libs.config.etcd_driver.transport.EtcdDriverImpl;
import org.springframework.http.HttpMethod;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class EtcdKeyPostRequest extends EtcdKeyRequest {


  public EtcdKeyPostRequest(EtcdDriverImpl clientImpl, String key) {
    super(clientImpl, HttpMethod.POST, key);
  }

  public EtcdKeyPostRequest value(String value) {
    this.requestParams.put("value", value);
    return this;
  }

  public EtcdKeyPostRequest ttl(Integer ttl) {
    this.requestParams.put("ttl", (ttl == null) ? "" : ttl + "");
    return this;
  }

}