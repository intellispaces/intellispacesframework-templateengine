package tech.intellispaces.framework.templateengine.template.expression.value;

import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;

import java.util.Map;
import java.util.Objects;

class MapValueImpl extends AbstractValue implements MapValue {
  private final Map<Value, Value> map;

  MapValueImpl(Map<Value, Value> map) {
    this.map = map;
  }

  @Override
  public ValueType type() {
    return ValueTypes.Map;
  }

  @Override
  public Map<Value, Value> get() {
    return map;
  }

  @Override
  public Value origin() {
    return this;
  }

  @Override
  public MapValue asMap() {
    return this;
  }

  @Override
  public BooleanValue eq(Value other) {
    if (other.type() == ValueTypes.Map) {
      return BooleanValueBuilder.build(Objects.equals(get(), ((MapValue) other).get()));
    }
    return BooleanValueBuilder.build(false);
  }

  @Override
  public BooleanValue isEmpty() {
    return BooleanValueBuilder.build(get().isEmpty());
  }

  @Override
  public BooleanValue isNotEmpty() {
    return BooleanValueBuilder.build(!get().isEmpty());
  }

  @Override
  public Value get(Value key) throws ResolveTemplateException {
    Value value = get().get(key);
    if (value == null) {
      return VoidValues.get();
    }
    return get().get(key);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!MapValue.class.isInstance(o)) {
      return false;
    }
    MapValue other = (MapValue) o;
    return Objects.equals(get(), other.get());
  }

  @Override
  public int hashCode() {
    return Objects.hash(get());
  }
}
