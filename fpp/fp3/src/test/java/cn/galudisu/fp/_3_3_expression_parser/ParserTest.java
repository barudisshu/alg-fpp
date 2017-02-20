package cn.galudisu.fp._3_3_expression_parser;

import org.junit.Test;

/**
 * @author galudisu
 */
public class ParserTest {

    @Test
    public void expr() throws Exception {
        final String exp = "(1+1)*8";
        Parser parser = new Parser(exp);
        System.out.println(exp + " = " + parser.expr());
    }

}