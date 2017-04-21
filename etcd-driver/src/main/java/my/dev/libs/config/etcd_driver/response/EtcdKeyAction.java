package my.dev.libs.config.etcd_driver.response;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

/**
 * The etcd key response actions
 */

public enum EtcdKeyAction {
  set,
  get,
  create,
  update,
  delete,
  expire,
  compareAndSwap,
  compareAndDelete
}