package tech.intellispaces.framework.templateengine.exception;

import tech.intellispaces.framework.commons.exception.PossibleViolationException;

public class TemplateEngineException extends PossibleViolationException {

  protected TemplateEngineException(String messageTemplate, Object... arguments) {
    super(messageTemplate, arguments);
  }

  protected TemplateEngineException(Throwable cause, String messageTemplate, Object... arguments) {
    super(cause, messageTemplate, arguments);
  }
}
