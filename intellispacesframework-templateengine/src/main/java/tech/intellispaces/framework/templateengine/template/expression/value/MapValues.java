package tech.intellispaces.framework.templateengine.template.expression.value;

import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;

import java.util.Map;
import java.util.Objects;

public final class MapValues {

  public static MapValue get(Map<Value, Value> map) {
    Objects.requireNonNull(map);
    return new MapValueImpl(map);
  }

  public static MapValue get(Value key, Value target) {
    return get(Map.of(key, target));
  }

  public static MapValue get(
      Object key, Object target
  ) throws ResolveTemplateException {
    return get(Map.of(ValueFunctions.objectToValue(key), ValueFunctions.objectToValue(target)));
  }

  public static MapValue get(
      Object key1, Object target1,
      Object key2, Object target2
  ) throws ResolveTemplateException {
    return get(Map.of(
        ValueFunctions.objectToValue(key1), ValueFunctions.objectToValue(target1),
        ValueFunctions.objectToValue(key2), ValueFunctions.objectToValue(target2)));
  }

  public static MapValue get(
      Object key1, Object target1,
      Object key2, Object target2,
      Object key3, Object target3
  ) throws ResolveTemplateException {
    return get(Map.of(
        ValueFunctions.objectToValue(key1), ValueFunctions.objectToValue(target1),
        ValueFunctions.objectToValue(key2), ValueFunctions.objectToValue(target2),
        ValueFunctions.objectToValue(key3), ValueFunctions.objectToValue(target3)));
  }

  public static MapValue empty() {
    return EMPTY;
  }

  public static MapValueBuilder get() {
    return new MapValueBuilder();
  }

  private static final MapValue EMPTY = get(Map.of());

  private MapValues() {}
}