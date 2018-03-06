package com.mrtan.data.domain;

import com.google.gson.annotations.SerializedName;
import org.immutables.value.Value;

/**
 * 创建 feed
 *
 * @author mrtan on 3/6/18.
 */
public abstract class FeedCreate {
  @SerializedName("title") public abstract String title();

  @SerializedName("content") @Value.Default public String content() {
    return "";
  }

  @SerializedName("url") @Value.Default public String url() {
    return "";
  }

  @SerializedName("cover_url") @Value.Default public String coverUrl() {
    return "";
  }

  @SerializedName("category") public abstract String category();
}
