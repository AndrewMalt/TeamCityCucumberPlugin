package core.api.stat.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDto {

  @JsonProperty("report_date")
  private String reportDate;

  @JsonProperty("report_type")
  private String reportType;

  @JsonProperty("app_name")
  private String appName;

  @JsonProperty("app_version")
  private String appVersion;

  @JsonProperty("metrics")
  private List<MetricDto> metricDto;
}
