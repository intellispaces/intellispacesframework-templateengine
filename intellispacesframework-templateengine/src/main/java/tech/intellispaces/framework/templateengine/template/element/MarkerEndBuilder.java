package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.source.position.Position;

import java.util.Objects;

public final class MarkerEndBuilder {
  private Position position;

  public static MarkerEndBuilder get() {
    return new MarkerEndBuilder();
  }

  public MarkerEndBuilder position(Position position) {
    this.position = position;
    return this;
  }

  public MarkerEnd build() {
    validate();
    return new MarkerEndImpl(position);
  }

  private void validate() {
    Objects.requireNonNull(position);
  }

  private MarkerEndBuilder() {}
}
