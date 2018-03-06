package com.mrtan.data.domain;

import org.immutables.value.Value;

/**
 * @author mrtan on 3/6/18.
 */
@Value.Immutable
public abstract class Register {
  public abstract String username();

  public abstract String password();

  public abstract String name();

  public abstract String email();

  public abstract String description();
}
