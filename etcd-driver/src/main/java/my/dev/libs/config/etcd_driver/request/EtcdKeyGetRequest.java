package my.dev.libs.config.etcd_driver.request;

import my.dev.libs.config.etcd_driver.transport.EtcdDriverImpl;
import org.springframework.http.HttpMethod;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class EtcdKeyGetRequest extends EtcdKeyRequest {
  private boolean wait = false;

  public EtcdKeyGetRequest(EtcdDriverImpl clientImpl) {
    super(clientImpl, HttpMethod.GET);
  }

  public EtcdKeyGetRequest(EtcdDriverImpl clientImpl, String key) {
    super(clientImpl, HttpMethod.GET, key);
  }

  public EtcdKeyGetRequest waitForChange() {
    this.wait = true;
    this.requestParams.put("wait", "true");
    return this;
  }

  public EtcdKeyGetRequest waitForChange(long waitIndex) {
    this.waitForChange();
    this.requestParams.put("waitIndex", waitIndex + "");
    return this;
  }

  public EtcdKeyGetRequest sorted() {
    this.requestParams.put("sorted", "true");
    return this;
  }

  public EtcdKeyGetRequest dir() {
    this.requestParams.put("dir", "true");
    return this;
  }

  public EtcdKeyGetRequest recursive() {
    this.requestParams.put("recursive", "true");
    return this;
  }

  public EtcdKeyGetRequest consistent() {
    this.requestParams.put("consistent", "true");
    return this;
  }

  public boolean shouldBeWaiting() {
    return this.wait;
  }


}