package my.dev.libs.config.etcd_driver.request;


import my.dev.libs.config.etcd_driver.transport.EtcdDriverImpl;
import org.springframework.http.HttpMethod;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class EtcdGetVersionRequest extends AbstractEtcdRequest {

    public EtcdGetVersionRequest(EtcdDriverImpl etcdDriver){
        super("/version", etcdDriver, HttpMethod.GET);
    }



}
