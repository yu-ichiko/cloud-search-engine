/* Generated By:JJTree&JavaCC: Do not edit this line. QbSSParser.java */
package location.qbss.parser;
public class QbSSParser/*@bgen(jjtree)*/implements QbSSParserTreeConstants, QbSSParserConstants {/*@bgen(jjtree)*/
  protected JJTQbSSParserState jjtree = new JJTQbSSParserState();

//�?���?定義
  final public ASTStart Start() throws ParseException {
 /*@bgen(jjtree) Start */
  ASTStart jjtn000 = new ASTStart(JJTSTART);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      Query();
                  jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
                  {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public void Query() throws ParseException {
    Word();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
      case OR:
      case NOT:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        jj_consume_token(AND);
                                ASTAnd jjtn001 = new ASTAnd(JJTAND);
                                boolean jjtc001 = true;
                                jjtree.openNodeScope(jjtn001);
        try {
          Word();
        } catch (Throwable jjte001) {
                                if (jjtc001) {
                                  jjtree.clearNodeScope(jjtn001);
                                  jjtc001 = false;
                                } else {
                                  jjtree.popNode();
                                }
                                if (jjte001 instanceof RuntimeException) {
                                  {if (true) throw (RuntimeException)jjte001;}
                                }
                                if (jjte001 instanceof ParseException) {
                                  {if (true) throw (ParseException)jjte001;}
                                }
                                {if (true) throw (Error)jjte001;}
        } finally {
                                if (jjtc001) {
                                  jjtree.closeNodeScope(jjtn001,  2);
                                }
        }
        break;
      case OR:
        jj_consume_token(OR);
                               ASTOr jjtn002 = new ASTOr(JJTOR);
                               boolean jjtc002 = true;
                               jjtree.openNodeScope(jjtn002);
        try {
          Word();
        } catch (Throwable jjte002) {
                               if (jjtc002) {
                                 jjtree.clearNodeScope(jjtn002);
                                 jjtc002 = false;
                               } else {
                                 jjtree.popNode();
                               }
                               if (jjte002 instanceof RuntimeException) {
                                 {if (true) throw (RuntimeException)jjte002;}
                               }
                               if (jjte002 instanceof ParseException) {
                                 {if (true) throw (ParseException)jjte002;}
                               }
                               {if (true) throw (Error)jjte002;}
        } finally {
                               if (jjtc002) {
                                 jjtree.closeNodeScope(jjtn002,  2);
                               }
        }
        break;
      case NOT:
        jj_consume_token(NOT);
                                ASTNot jjtn003 = new ASTNot(JJTNOT);
                                boolean jjtc003 = true;
                                jjtree.openNodeScope(jjtn003);
        try {
          Word();
        } catch (Throwable jjte003) {
                                if (jjtc003) {
                                  jjtree.clearNodeScope(jjtn003);
                                  jjtc003 = false;
                                } else {
                                  jjtree.popNode();
                                }
                                if (jjte003 instanceof RuntimeException) {
                                  {if (true) throw (RuntimeException)jjte003;}
                                }
                                if (jjte003 instanceof ParseException) {
                                  {if (true) throw (ParseException)jjte003;}
                                }
                                {if (true) throw (Error)jjte003;}
        } finally {
                                if (jjtc003) {
                                  jjtree.closeNodeScope(jjtn003,  2);
                                }
        }
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void Word() throws ParseException {
 /*@bgen(jjtree) Word */
  ASTWord jjtn000 = new ASTWord(JJTWORD);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(WORD);
                     jjtree.closeNodeScope(jjtn000, true);
                     jjtc000 = false;
                     jjtn000.nodeValue = t.image;
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  /** Generated Token Manager. */
  public QbSSParserTokenManager token_source;
  JavaCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[2];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x70,0x70,};
   }

  /** Constructor with InputStream. */
  public QbSSParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public QbSSParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new QbSSParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public QbSSParser(java.io.Reader stream) {
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new QbSSParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public QbSSParser(QbSSParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(QbSSParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[8];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 2; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 8; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
