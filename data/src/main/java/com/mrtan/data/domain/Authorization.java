package com.mrtan.data.domain;

import com.google.gson.annotations.SerializedName;
import org.immutables.value.Value;

/**
 * @author mrtan on 3/6/18.
 */

@Value.Immutable
public abstract class Authorization {
  @SerializedName("access_token") public abstract String token();

  @SerializedName("update_at") public abstract String updateTime();
}
