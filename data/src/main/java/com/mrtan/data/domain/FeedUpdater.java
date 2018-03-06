package com.mrtan.data.domain;

import com.google.gson.annotations.SerializedName;

/**
 * @author mrtan on 3/6/18.
 */
public abstract class FeedUpdater {
  public abstract String id();

  @SerializedName("title") public abstract String title();

  @SerializedName("content") public abstract String content();

  @SerializedName("category") public abstract String category();

  @SerializedName("url") public abstract String url();

  @SerializedName("cover_url") public abstract String convertUrl();

  @SerializedName("owner") public abstract String owner();
}
