package tech.intellispaces.templates.element;

import tech.intellispaces.templates.expression.Expression;

import java.util.List;

/**
 * The "Foreach" statement.
 */
public interface StatementForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

  List<TemplateElement> subElements();
}
