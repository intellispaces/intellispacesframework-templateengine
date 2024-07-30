package tech.intellispaces.templates.template;

import tech.intellispaces.templates.element.TemplateElement;
import tech.intellispaces.templates.exception.ResolveTemplateException;

import java.util.List;
import java.util.Map;

/**
 * Text template.
 */
public interface Template {

  /**
   * Template elements.
   */
  List<TemplateElement> elements();

  /**
   * Resolve template.
   *
   * @param variables template variables.
   * @return resolved template.
   * @throws ResolveTemplateException throws if template could not be resolved.
   */
  String resolve(Map<String, Object> variables) throws ResolveTemplateException;
}
