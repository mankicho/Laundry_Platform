package com.coders.laundry.error;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Detail {

  private Map<String, Object> map = new HashMap<>();

  public Detail with(String key, Object value) {
    map.put(key, value);
    return this;
  }
}
