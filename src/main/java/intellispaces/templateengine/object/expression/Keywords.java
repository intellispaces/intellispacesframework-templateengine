package intellispaces.templateengine.object.expression;

import intellispaces.templateengine.model.expression.Keyword;

/**
 * Expression keywords.
 */
public enum Keywords implements Keyword {

  True("true"),

  False("false"),

  Void("void");

  private final String word;

  Keywords(String word) {
    this.word = word;
  }

  @Override
  public String word() {
    return word;
  }
}
