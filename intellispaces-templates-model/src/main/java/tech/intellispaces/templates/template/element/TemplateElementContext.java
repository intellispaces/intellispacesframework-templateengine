package tech.intellispaces.templates.template.element;

import tech.intellispaces.templates.template.source.position.Position;

import java.util.List;

public interface TemplateElementContext {

  /**
   * Position of the element in template source.
   */
  Position position();

  /**
   * All elements of the template.
   */
  List<TemplateElement> templateElements();

  /**
   * Index of this element in list of all template elements.<p/>
   *
   * Actual for terminal elements only.
   */
  Integer elementIndex();
}