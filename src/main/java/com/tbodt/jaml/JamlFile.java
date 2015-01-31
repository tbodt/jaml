/*
 * Copyright (C) 2015 theodore
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

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * A convenient interface to a JAML configuration file.
 *
 * @author Theodore Dubois
 */
public final class JamlFile {
    private final JamlObject root;

    /**
     * Return the {@link JamlObject} at the given path.
     *
     * @param path the path
     * @return the {@link JamlObject} at the given path
     */
    public JamlObject getObject(String... path) {
        JamlObject obj = root;
        for (String pathComponent : path)
            if (obj instanceof JamlMap) {
                obj = ((JamlMap) obj).getMap().get(pathComponent);
                if (obj == null)
                    throw new IllegalArgumentException("nonexistent path component");
            } else
                throw new IllegalArgumentException("path component enters string");
        return obj;
    }

    /**
     * Return the {@link JamlFile} at the given path.
     *
     * @param path the path
     * @return the {@link JamlFile} at the given path
     */
    public JamlFile getFile(String... path) {
        return new JamlFile(getObject(path));
    }

    /**
     * Return the {@code String} at the given path.
     *
     * @param path the path
     * @return the {@code String} at the given path
     */
    public String getString(String... path) {
        JamlObject obj = getObject(path);
        if (obj instanceof JamlString)
            return ((JamlString) obj).getValue();
        else
            throw new IllegalArgumentException("getString called, but path leads to map");
    }

    /**
     * Return the {@link JamlMap} at the given path.
     *
     * @param path the path
     * @return the {@link JamlMap} at the given path
     */
    public JamlMap getMap(String... path) {
        JamlObject obj = getObject(path);
        if (obj instanceof JamlMap)
            return (JamlMap) obj;
        else
            throw new IllegalArgumentException("getMap called, but path leads to string");
    }

    /**
     * Return a {@code JamlFile} from the data from the file at the given path.
     *
     * @param path the file path
     * @return a {@code JamlFile} from the data from the file at the given path
     * @throws IOException if an I/O error occurs while reading from the file
     * @throws JamlSyntaxException if the JAML syntax is invalid
     */
    public static JamlFile read(String path) throws IOException, JamlSyntaxException {
        return read(new FileReader(path));
    }

    /**
     * Return a {@code JamlFile} from the data from the given {@code reader}.
     *
     * @param reader the reader
     * @return a {@code JamlFile} from the data from the given {@code reader}
     * @throws IOException if an I/O error occurs while reading from the reader
     * @throws JamlSyntaxException if the JAML syntax is invalid
     */
    public static JamlFile read(Reader reader) throws IOException, JamlSyntaxException {
        return parse(new ANTLRInputStream(reader));
    }

    /**
     * Return a new {@code JamlFile} from the JAML data in {@code jaml}.
     *
     * @param jaml the JAML
     * @return a new {@code JamlFile} from the JAML data in {@code jaml}
     * @throws JamlSyntaxException if the JAML syntax is invalid
     */
    public static JamlFile parse(String jaml) throws JamlSyntaxException {
        return parse(new ANTLRInputStream(jaml));
    }

    private static JamlFile parse(CharStream input) throws JamlSyntaxException {
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
        return new JamlFile(visitor.visit(tree));
    }

    private JamlFile(JamlObject root) {
        this.root = root;
    }
}
