package my.dev.libs.config.etcd_driver;

import my.dev.libs.config.etcd_driver.request.EtcdKeyGetRequest;
import my.dev.libs.config.etcd_driver.request.EtcdKeyPutRequest;
import my.dev.libs.config.etcd_driver.transport.EtcdDriverImpl;
import my.dev.libs.config.etcd_driver.request.EtcdKeyDeleteRequest;
import my.dev.libs.config.etcd_driver.request.EtcdKeyPostRequest;
import my.dev.libs.config.etcd_driver.transport.EtcdDriverRestTemplate;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class EtcdDriver implements Closeable {

    private final EtcdDriverImpl client;

    public EtcdDriver(String url){
        this(new EtcdDriverRestTemplate(url));
    }

    public EtcdDriver(EtcdDriverImpl etcdDriver){
        this.client = etcdDriver;
    }

    public EtcdDriverImpl getClient() {
        return client;
    }

    @Override
    public void close() throws IOException {

    }

    public EtcdKeyPutRequest put(String key, String value) {
        return new EtcdKeyPutRequest(client, key).value(value);
    }


    public EtcdKeyPutRequest putDir(String dir) {
        return new EtcdKeyPutRequest(client, dir).isDir();
    }

    public EtcdKeyPostRequest post(String key, String value) {
        return new EtcdKeyPostRequest(client, key).value(value);
    }

    public EtcdKeyDeleteRequest delete(String key) {
        return new EtcdKeyDeleteRequest(client, key);
    }

    public EtcdKeyDeleteRequest deleteDir(String dir) {
        return new EtcdKeyDeleteRequest(client, dir).dir();
    }

    public EtcdKeyGetRequest get(String key) {
        return new EtcdKeyGetRequest(client, key);
    }


    public EtcdKeyGetRequest getDir(String dir) {
        return new EtcdKeyGetRequest(client, dir).dir();
    }

    public EtcdKeyGetRequest getAll() {
        return new EtcdKeyGetRequest(client);
    }
}
