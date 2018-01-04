package com.mrtan.data.domain;

import android.support.annotation.Nullable;
import org.immutables.value.Value;

@Value.Immutable public abstract class Response<Data> {
  /*状态码*/
  public abstract int code();

  /*结果信息*/
  public abstract String message();

  @Nullable public abstract Data data();

  @Value.Derived public boolean isSuccess() {
    return code() == 0;
  }
}