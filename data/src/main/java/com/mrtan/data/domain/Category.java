package com.mrtan.data.domain;

import com.google.gson.annotations.SerializedName;
import org.immutables.value.Value;

/**
 * @author mrtan on 3/6/18.
 */

@Value.Immutable
public abstract class Category {
  @SerializedName("key") public abstract String key();

  @SerializedName("name") public abstract String name();

  @SerializedName("rank") public abstract String rank();

  @SerializedName("owner") public abstract String owner();
}
