package com.github.aoreshin.junit5.extensions;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

/** Helps to configure invocation context of the test template */
public class TestTemplateInvocationContextBuilder {
  private final List<Extension> extensions = new ArrayList<>();
  private String displayName;
  private String displayNamePrefix;
  private String displayNamePostfix;

  public TestTemplateInvocationContextBuilder withDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public TestTemplateInvocationContextBuilder withDisplayNamePrefix(String displayNamePrefix) {
    this.displayNamePrefix = displayNamePrefix;
    return this;
  }

  public TestTemplateInvocationContextBuilder withDisplayNamePostfix(String displayNamePostfix) {
    this.displayNamePostfix = displayNamePostfix;
    return this;
  }

  public <T> TestTemplateInvocationContextBuilder addParameterResolver(
      Class<T> parameterType, T value) {
    ParameterResolver resolver = new GenericParameterResolver<>(parameterType, value);
    extensions.add(resolver);
    return this;
  }

  public <T> TestTemplateInvocationContextBuilder addParameterResolver(
      Class<T> parameterType, T value, String parameterName) {
    ParameterResolver resolver =
        new GenericParameterResolver<>(parameterType, value, parameterName);
    extensions.add(resolver);
    return this;
  }

  public TestTemplateInvocationContextBuilder addExtension(Extension extension) {
    extensions.add(extension);
    return this;
  }

  /**
   * Adds extension to specified index to force order of execution
   * https://junit.org/junit5/docs/current/user-guide/#extensions-execution-order-wrapping-behavior
   */
  public TestTemplateInvocationContextBuilder addExtension(int index, Extension extension) {
    extensions.add(index, extension);
    return this;
  }

  public TestTemplateInvocationContext build() {
    StringBuilder stringBuilder = new StringBuilder();

    if (displayNamePrefix != null) {
      stringBuilder.append(displayNamePrefix).append(" ");
    }

    stringBuilder.append(displayName);

    if (displayNamePostfix != null) {
      stringBuilder.append(" ").append(displayNamePostfix);
    }

    return new TestTemplateInvocationContextImpl(stringBuilder.toString(), extensions);
  }
}
