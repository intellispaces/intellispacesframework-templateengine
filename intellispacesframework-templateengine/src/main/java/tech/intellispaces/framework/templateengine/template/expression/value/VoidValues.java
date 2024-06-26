package tech.intellispaces.framework.templateengine.template.expression.value;

public enum VoidValues {

  Void(new VoidValueImpl());

  public static VoidValue get() {
    return Void.instance;
  }

  private final VoidValue instance;

  VoidValues(VoidValue instance) {
    this.instance = instance;
  }
}
