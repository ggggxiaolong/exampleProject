package com.mrtan.data.domain;

import com.google.gson.annotations.SerializedName;
import org.immutables.value.Value;

/**
 * @author mrtan on 3/6/18.
 */

@Value.Immutable
public abstract class RegisterRP {
  @SerializedName("username") public abstract String username();

  @SerializedName("name") public abstract String name();

  @SerializedName("email") public abstract String email();

  @SerializedName("description") public abstract String description();

  @SerializedName("create_at") public abstract String createTime();

  @SerializedName("authorization") public abstract Authorization authorization();
}
