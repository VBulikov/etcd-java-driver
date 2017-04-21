package my.dev.libs.config.etcd_driver.transport;

import my.dev.libs.config.etcd_driver.request.EtcdRequest;
import my.dev.libs.config.etcd_driver.response.EtcdKeysResponse;

import java.io.Closeable;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public interface EtcdDriverImpl extends Closeable {

    EtcdKeysResponse send(EtcdRequest etcdRequest) throws IOException, URISyntaxException;

    @Override
    void close() throws IOException;
}
