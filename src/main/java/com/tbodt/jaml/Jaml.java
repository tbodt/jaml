/*
 * Copyright (C) 2014 Theodore Dubois
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.tbodt.jaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * A class with static helper methods that parse and generate JAML.
 *
 * @author Theodore Dubois
 */
public final class Jaml {
    /**
     * Parse the JAML in the given string and return a {@link JamlObject}.
     *
     * @param jaml the JAML string
     * @return a {@link JamlObject} with the appropriate contents
     * @throws JamlSyntaxException if the JAML syntax is invalid
     */
    public static JamlObject parse(String jaml) throws JamlSyntaxException {
        return parse(new ANTLRInputStream(jaml));
    }

    /**
     * Parse the JAML from the reader and return a {@link JamlObject}.
     *
     * @param reader a reader for JAML
     * @return a {@link JamlObject} with the appropriate contents
     * @throws java.io.IOException if an I/O error occurs while reading from the
     * {@code reader}
     * @throws JamlSyntaxException if the JAML syntax is invalid
     */
    public static JamlObject parse(Reader reader) throws IOException, JamlSyntaxException {
        return parse(new ANTLRInputStream(reader));
    }

    private static JamlObject parse(CharStream input) throws JamlSyntaxException {
        JamlLexer lexer = new JamlLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        JamlParser parser = new JamlParser(tokens);

        final List<String> errors = new ArrayList<String>();
        ANTLRErrorListener listener = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                errors.add(msg);
            }
        };
        parser.addErrorListener(listener);
        lexer.addErrorListener(listener);

        ParseTree tree = parser.file();
        if (!errors.isEmpty())
            throw new JamlSyntaxException(errors);

        JamlParseVisitor visitor = new JamlParseVisitor();
        return visitor.visit(tree);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null)
            try {
                System.out.println(parse(line));
            } catch (JamlSyntaxException ex) {
                ex.printStackTrace();
            }
    }

    private Jaml() {
    }
}
