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

import com.tbodt.jaml.parse.JamlBaseVisitor;
import com.tbodt.jaml.parse.JamlParser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * A visitor for parsing JAML with ANTLR.
 *
 * @author Theodore Dubois
 */
public final class JamlParseVisitor extends JamlBaseVisitor<JamlObject> {
    @Override
    public JamlObject visitString(JamlParser.StringContext ctx) {
        String valueText = ctx.VALUE().getText();
        if (valueText.startsWith("\"") && valueText.endsWith("\""))
            valueText = valueText.substring(1, valueText.length() - 1);
        return new JamlString(valueText);
    }

    @Override
    public JamlObject visitMap(JamlParser.MapContext ctx) {
        List<TerminalNode> keys = ctx.VALUE();
        List<JamlParser.ValueContext> values = ctx.value();
        Map<String, JamlObject> map = new HashMap<String, JamlObject>();
        for (int i = 0; i < keys.size(); i++) {
            String key = removeQuotes(keys.get(i).getText());
            JamlObject value = visit(values.get(i));
            map.put(key, value);
        }
        return new JamlMap(map);
    }
    
    private static String removeQuotes(String string) {
        if (string.startsWith("\"") && string.endsWith("\""))
            return string.substring(1, string.length() - 1);
        else
            return string;
    }
}
