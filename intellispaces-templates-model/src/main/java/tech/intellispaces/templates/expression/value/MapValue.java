package tech.intellispaces.templates.expression.value;

import java.util.Map;

/**
 * Map value.
 */
public interface MapValue extends Value {

  Map<Value, Value> get();
}
