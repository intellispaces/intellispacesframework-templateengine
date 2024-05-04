package tech.intellispacesframework.templateengine.template.element;

/**
 * Template marker type.
 */
public interface MarkerType {

  /**
   * Marker name.
   */
  String name();

  /**
   * Marker text.
   */
  String text();

  /**
   * Marker value.
   */
  String value();
}