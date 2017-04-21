package my.dev.libs.config.etcd_driver.request;

import my.dev.libs.config.etcd_driver.transport.EtcdDriverImpl;
import org.springframework.http.HttpMethod;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class EtcdKeyPutRequest extends EtcdKeyRequest {


  public EtcdKeyPutRequest(EtcdDriverImpl clientImpl, String key) {
    super(clientImpl, HttpMethod.PUT, key);
  }

  public EtcdKeyPutRequest value(String value) {
    this.requestParams.put("value", value);
    return this;
  }

  public EtcdKeyPutRequest ttl(Integer ttl) {
    this.requestParams.put("ttl", (ttl == null) ? "" : ttl + "");
    return this;
  }

  public EtcdKeyPutRequest refresh(Integer ttl) {
    this.requestParams.put("refresh", "true");
    this.prevExist(true);
    return ttl(ttl);
  }

  public EtcdKeyPutRequest isDir() {
    this.requestParams.put("dir", "true");
    return this;
  }

  @Deprecated
  public EtcdKeyPutRequest prevExist() {
    this.requestParams.put("prevExist", "true");
    return this;
  }

  public EtcdKeyPutRequest prevExist(boolean prevExists) {
    this.requestParams.put("prevExist", String.valueOf(prevExists));
    return this;
  }

  public EtcdKeyPutRequest prevIndex(long prevIndex) {
    this.requestParams.put("prevIndex", prevIndex + "");
    return this;
  }


  public EtcdKeyPutRequest prevValue(String value) {
    this.requestParams.put("prevValue", value);
    return this;
  }

}