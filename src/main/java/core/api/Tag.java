package core.api;

import static core.api.TagCollectorPlugin.*;

public enum Tag {
  UI("@ui") {

    private int count = 0;

    public void incTag() {
      count++;
      uiNum++;
      total++;
    }
  },
  API("@api") {

    //        public int count = 0;
    public void incTag() {
      //            count++;
      apiNum++;
      total++;
    }
  },
  MIX("@mix") {
    public void incTag() {
      mixNum++;
      total++;
    }
  };

  private final String name;
  //    private final int count;

  Tag(String name) {
    this.name = name;
    //        this.count = count;
  }

  public String getName() {
    return this.name;
  }

  public String getCount() {
    return Tag.UI.getCount();
  }

  public abstract void incTag();
}
