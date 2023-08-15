package core.api.stat.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
 @AllArgsConstructor
//@NoArgsConstructor
public class MetricDto {

  private String metric;

//  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String value = "";

  public MetricDto(String metric) {
    this.metric = metric;
  }
}
