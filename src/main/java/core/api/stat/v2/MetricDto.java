package core.api.stat.v2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
// @AllArgsConstructor
@NoArgsConstructor
public class MetricDto {

  private String metric;

  public MetricDto(String metric) {
    this.metric = metric;
  }
}
