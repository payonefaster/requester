package pay.one.faster.requester.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "requesters")
public class Requester {

  @Id
  private String id;

  private String name;

  private Boolean enabled;

  public Requester() {}

  public Requester(String id, String name, Boolean enabled) {
    this.id = id;
    this.name = name;
    this.enabled = enabled;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }
}
