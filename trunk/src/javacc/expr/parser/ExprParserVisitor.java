/* Generated By:JavaCC: Do not edit this line. ExprParserVisitor.java Version 5.0 */
package javacc.expr.parser;

public interface ExprParserVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTStart node, Object data);
  public Object visit(ASTAdd node, Object data);
  public Object visit(ASTSub node, Object data);
  public Object visit(ASTMulti node, Object data);
  public Object visit(ASTDivision node, Object data);
  public Object visit(ASTInteger node, Object data);
}
/* JavaCC - OriginalChecksum=1fa4ece1616f88c4b56892a6f7a276bc (do not edit this line) */
