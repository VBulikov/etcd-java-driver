package my.dev.libs.config.etcd_driver.request;


import my.dev.libs.config.etcd_driver.transport.EtcdDriverImpl;
import org.springframework.http.HttpMethod;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class EtcdKeyDeleteRequest extends EtcdKeyRequest {

  public EtcdKeyDeleteRequest(EtcdDriverImpl clientImpl, String key) {
    super(clientImpl, HttpMethod.DELETE, key);
  }

  public EtcdKeyDeleteRequest prevValue(String value) {
    this.requestParams.put("prevValue", value);
    return this;
  }

  public EtcdKeyDeleteRequest prevIndex(long prevIndex) {
    this.requestParams.put("prevIndex", prevIndex + "");
    return this;
  }

  public EtcdKeyDeleteRequest dir() {
    this.requestParams.put("dir", "true");
    return this;
  }


  public EtcdKeyDeleteRequest recursive() {
    this.requestParams.put("recursive", "true");
    return this;
  }


}