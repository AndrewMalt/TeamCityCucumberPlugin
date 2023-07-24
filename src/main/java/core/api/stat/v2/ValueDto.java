package core.api.stat.v2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
// @AllArgsConstructor
@NoArgsConstructor
public class ValueDto extends MetricDto {

  private String value;

  public ValueDto(String metric, String value) {
    this.value = value;
    super.setMetric(metric);
  }
}
