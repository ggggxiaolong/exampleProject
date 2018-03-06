package com.mrtan.data.domain;

import com.google.gson.annotations.SerializedName;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

/**
 * @author mrtan on 3/6/18.
 */

@Value.Immutable
public abstract class Feed {
  @SerializedName("_id") public abstract String id();

  @SerializedName("title") public abstract String title();

  @SerializedName("content") public abstract String content();

  @SerializedName("url") public abstract String url();

  @SerializedName("category") public abstract String category();

  @SerializedName("owner") public abstract String owner();

  @SerializedName("cover_url") public abstract String coverUrl();

  @SerializedName("created_time") public abstract String createTime();

  @Nullable @SerializedName("updated_time") public abstract String updateTime();

  @SerializedName("published_time") public abstract String publishTime();
}
