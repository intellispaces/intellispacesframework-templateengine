package tech.intellispaces.framework.templateengine.template.expression.value;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import tech.intellispaces.framework.templateengine.exception.NotApplicableOperationException;
import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.same;

/**
 * Tests for {@link RealValueBuilder}.
 */
public class RealValueTest {

  @Test
  public void testTypename() {
    assertThat(RealValueBuilder.build(123.4).typename().get()).isEqualTo(ValueTypes.Real.typename());
  }

  @Test
  public void testAsBoolean() throws Exception {
    // Given
    RealValue realValue = RealValueBuilder.build(1.0);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      boolean expectedValue = true;
      castFunctions.when(() -> ValueFunctions.castToBoolean(realValue)).thenReturn(expectedValue);

      // When
      BooleanValue booleanValue = realValue.asBoolean();

      // Then
      assertThat(booleanValue.get()).isTrue();
      castFunctions.verify(() -> ValueFunctions.castToBoolean(same(realValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsInteger() throws Exception {
    // Given
    RealValue realValue = RealValueBuilder.build(123.0);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      int expectedValue = 123;
      castFunctions.when(() -> ValueFunctions.castToInteger(realValue)).thenReturn(expectedValue);

      // When
      IntegerValue integerValue = realValue.asInteger();

      // Then
      assertThat(integerValue.get()).isEqualTo(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToInteger(same(realValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsReal() throws Exception {
    RealValue realValue = RealValueBuilder.build(123.4);
    assertThat(realValue.asReal()).isSameAs(realValue);
  }

  @Test
  public void testAString() throws Exception {
    // Given
    RealValue realValue = RealValueBuilder.build(123.4);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      String expectedValue = "123.4";
      castFunctions.when(() -> ValueFunctions.castToString(realValue)).thenReturn(expectedValue);

      // When
      StringValue stringValue = realValue.asString();

      // Then
      assertThat(stringValue.get()).isEqualTo(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToString(same(realValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsList() throws Exception {
    // Given
    RealValue realValue = RealValueBuilder.build(123.4);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      List<?> expectedValue = List.of();
      castFunctions.when(() -> ValueFunctions.castToList(realValue)).thenReturn(expectedValue);

      // When
      ListValue listValue = realValue.asList();

      // Then
      Assertions.assertThat(listValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToList(same(realValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsMap() throws Exception {
    // Given
    RealValue realValue = RealValueBuilder.build(123.4);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      var expectedValue = new LinkedHashMap<>();
      castFunctions.when(() -> ValueFunctions.castToMap(realValue)).thenReturn(expectedValue);

      // When
      MapValue mapValue = realValue.asMap();

      // Then
      assertThat(mapValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToMap(same(realValue)), Mockito.times(1));
    }
  }

  @Test
  public void testEq() throws ResolveTemplateException {
    assertThat(RealValueBuilder.build(123.4).eq(RealValueBuilder.build(123.4)).get()).isTrue();
    assertThat(RealValueBuilder.build(123.4).eq(RealValueBuilder.build(123.5)).get()).isFalse();

    assertThat(RealValueBuilder.build(123.0).eq(IntegerValueBuilder.build(123)).get()).isTrue();
    assertThat(RealValueBuilder.build(123.4).eq(IntegerValueBuilder.build(123)).get()).isFalse();

    assertThat(RealValueBuilder.build(1.0).eq(BooleanValueBuilder.build(true)).get()).isFalse();
    assertThat(RealValueBuilder.build(123.4).eq(StringValueBuilder.build("123.4")).get()).isFalse();
    assertThat(RealValueBuilder.build(123.4).eq(ListValueBuilder.build(123.4)).get()).isFalse();
    assertThat(RealValueBuilder.build(123.4).eq(MapValueBuilder.build(123.4, 123.4)).get()).isFalse();
    assertThat(RealValueBuilder.build(0.0).eq(VoidValues.get()).get()).isFalse();
  }

  @Test
  public void testIsVoid() {
    assertThat(RealValueBuilder.build(123.4).isVoid().get()).isFalse();
    assertThat(RealValueBuilder.build(0).isVoid().get()).isFalse();
  }

  @Test
  public void testIsEmpty() {
    assertThatThrownBy(() -> RealValueBuilder.build(123.4).isEmpty())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isEmpty' is not applicable for value type real. Expected string, list or map");
  }

  @Test
  public void testIsNotEmpty() {
    assertThatThrownBy(() -> RealValueBuilder.build(123.4).isNotEmpty())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotEmpty' is not applicable for value type real. Expected string, list or map");
  }

  @Test
  public void testIsBlank() {
    assertThatThrownBy(() -> RealValueBuilder.build(123.4).isBlank())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isBlank' is not applicable for value type real. Expected string");
  }

  @Test
  public void testIsNotBlank() {
    assertThatThrownBy(() -> RealValueBuilder.build(123.4).isNotBlank())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotBlank' is not applicable for value type real. Expected string");
  }

  @Test
  public void testCapitalizeFirstLetter() {
    assertThatThrownBy(() -> RealValueBuilder.build(123.4).capitalizeFirstLetter())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'capitalizeFirstLetter' is not applicable for value type real. Expected string");
  }

  @Test
  public void testInvert() throws ResolveTemplateException {
    assertThat(RealValueBuilder.build(123.4).invert().get()).isEqualTo(-123.4);
  }

  @Test
  public void testGet() {
    assertThatThrownBy(() -> RealValueBuilder.build(123.4).get(IntegerValueBuilder.build(0)))
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'get' is not applicable for value type real. Expected map, list or string");
  }

  @Test
  public void testFind() {
    assertThatThrownBy(() -> RealValueBuilder.build(123.4).find(RealValueBuilder.build(1)))
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'find' is not applicable for value type real. Expected string or list");
  }

  @Test
  public void testIndex() {
    assertThatThrownBy(() -> RealValueBuilder.build(123.4).index())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'index' is not applicable for this value");
  }

  @Test
  public void testIsFirst() {
    assertThatThrownBy(() -> RealValueBuilder.build(123.4).isFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
  }

  @Test
  public void testIsNotFirst() {
    assertThatThrownBy(() -> RealValueBuilder.build(123.4).isNotFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotFirst' is not applicable for this value");
  }

  @Test
  public void testIsLast() {
    assertThatThrownBy(() -> RealValueBuilder.build(123.4).isLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");
  }

  @Test
  public void testIsNotLast() {
    assertThatThrownBy(() -> RealValueBuilder.build(123.4).isNotLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotLast' is not applicable for this value");
  }
}
