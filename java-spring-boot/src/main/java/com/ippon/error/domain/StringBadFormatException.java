package com.ippon.error.domain;

public class StringBadFormatException extends AssertionException {

  private StringBadFormatException(StringBadFormatExceptionBuilder builder) {
    super(builder.message());
  }

  public static StringBadFormatExceptionBuilder builder() {
    return new StringBadFormatExceptionBuilder();
  }

  public static class StringBadFormatExceptionBuilder {

    private String value;
    private String acceptedValues;
    private String field;

    private StringBadFormatExceptionBuilder() {}

    public StringBadFormatExceptionBuilder field(String field) {
      this.field = field;

      return this;
    }

    public StringBadFormatExceptionBuilder value(String value) {
      this.value = value;

      return this;
    }

    public StringBadFormatExceptionBuilder acceptedValues(String acceptedValues) {
      this.acceptedValues = acceptedValues;

      return this;
    }

    private String message() {
      return new StringBuilder()
        .append("The value in field \"")
        .append(field)
        .append("\" has a bad format.")
        .append("The accepted values are (")
        .append(acceptedValues)
        .append(") but was ")
        .append(value)
        .toString();
    }

    public StringBadFormatException build() {
      return new StringBadFormatException(this);
    }
  }
}
