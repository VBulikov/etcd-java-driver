package my.dev.libs.config.etcd_driver.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.DatatypeConverter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

@JsonIgnoreProperties( ignoreUnknown = true )
public final class EtcdKeysResponse  {

  public final EtcdKeyAction action;
  public final EtcdNode node;
  public final EtcdNode prevNode;

  public String etcdClusterId;
  public Long etcdIndex;
  public Long raftIndex;
  public Long raftTerm;

  /**
   * Protected constructor
   *
   * @param action
   * @param node
   * @param prevNode
   */
  EtcdKeysResponse(
      @JsonProperty("action") String action,
      @JsonProperty("node") EtcdNode node,
      @JsonProperty("prevNode") EtcdNode prevNode) {

    this.action = EtcdKeyAction.valueOf(action);
    this.node = node;
    this.prevNode = prevNode;

    this.etcdClusterId = null;
    this.etcdIndex = null;
    this.raftIndex = null;
    this.raftTerm = null;
  }

  public EtcdKeyAction getAction() {
    return action;
  }

  public EtcdNode getNode() {
    return node;
  }

  public EtcdNode getPrevNode() {
    return prevNode;
  }

  /**
   * An Etcd node
   */
  @JsonIgnoreProperties( ignoreUnknown = true )
  public static final class EtcdNode {
    public final String key;
    public final boolean dir;
    public final Long createdIndex;
    public final Long modifiedIndex;
    public final String value;
    public final Date expiration;
    public final Long ttl;
    public final List<EtcdNode> nodes;

    /**
     * Etcd Node
     *
     * @param dir
     * @param key
     * @param value
     * @param createdIndex
     * @param modifiedIndex
     * @param expiration
     * @param ttl
     * @param nodes
     */
    EtcdNode(
        @JsonProperty("dir") final Boolean dir,
        @JsonProperty("key") final String key,
        @JsonProperty("value") final String value,
        @JsonProperty("createdIndex") final long createdIndex,
        @JsonProperty("modifiedIndex") final long modifiedIndex,
        @JsonProperty("expiration") final String expiration,
        @JsonProperty("ttl") final long ttl,
        @JsonProperty("nodes") final List<EtcdNode> nodes) {

      this.dir = dir != null ? dir : false;
      this.key = key;
      this.value = value;
      this.createdIndex = createdIndex;
      this.modifiedIndex = modifiedIndex;
      this.ttl = ttl;
      this.nodes = nodes != null
          ? Collections.unmodifiableList(nodes)
          : Collections.unmodifiableList(Collections.<EtcdNode>emptyList());
      this.expiration = expiration != null
          ? DatatypeConverter.parseDateTime(expiration).getTime()
          : null;

   }
   public String getKey() {
     return key;
   }

   public boolean isDir() {
     return dir;
   }

   public Long getCreatedIndex() {
     return createdIndex;
   }

   public Long getModifiedIndex() {
     return modifiedIndex;
   }
   
   public String getValue() {
     return value;
   }

   public Date getExpiration() {
     return expiration;
   }

   public Long getTTL() {
     return ttl;
   }

   public List<EtcdNode> getNodes() {
     return nodes;
   }

      @Override
      public String toString() {
          return "EtcdNode{" +
                  "key='" + key + '\'' +
                  ", dir=" + dir +
                  ", createdIndex=" + createdIndex +
                  ", modifiedIndex=" + modifiedIndex +
                  ", value='" + value + '\'' +
                  ", expiration=" + expiration +
                  ", ttl=" + ttl +
                  ", nodes=" + nodes +
                  '}';
      }
  }

    @Override
    public String toString() {
        return "EtcdKeysResponse{" +
                "action=" + action +
                ", node=" + node +
                ", prevNode=" + prevNode +
                ", etcdClusterId='" + etcdClusterId + '\'' +
                ", etcdIndex=" + etcdIndex +
                ", raftIndex=" + raftIndex +
                ", raftTerm=" + raftTerm +
                '}';
    }
}
