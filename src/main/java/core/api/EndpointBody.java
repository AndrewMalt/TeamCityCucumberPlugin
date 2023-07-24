package core.api;

import java.util.TreeSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EndpointBody {
  //    private LocalDate date = LocalDate.now();
  private String date;
  //  @JsonNaming("total_")
  private String totalTestCases;
  private String ui;
  private String api;
  private String mix;
  private TreeSet<String> endpoints;

  private Tags tags;
}
