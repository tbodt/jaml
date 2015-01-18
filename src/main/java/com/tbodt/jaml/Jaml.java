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

import com.tbodt.jaml.parse.JamlLexer;
import com.tbodt.jaml.parse.JamlParser;
import java.io.IOException;
import java.io.Reader;
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
     */
    public JamlValue parse(String jaml) {
        return parse(new ANTLRInputStream(jaml));
    }

    /**
     * Parse the JAML from the reader and return a {@link JamlObject}.
     *
     * @param jaml a reader for JAML
     * @return a {@link JamlObject} with the appropriate contents
     */
    public JamlValue parse(Reader jaml) throws IOException {
        return parse(new ANTLRInputStream(jaml));
    }

    private JamlValue parse(CharStream input) {
        JamlLexer lexer = new JamlLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        JamlParser parser = new JamlParser(tokens);
        ParseTree tree = parser.file();
        JamlParseVisitor visitor = new JamlParseVisitor();
        return (JamlValue) visitor.visit(tree);
    }

    private Jaml() {
    }
}
