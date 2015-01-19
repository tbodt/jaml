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
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * A visitor for parsing JAML with ANTLR.
 *
 * @author Theodore Dubois
 */
public final class JamlParseVisitor extends JamlBaseVisitor<Object> {
    @Override
    public Object visitString(JamlParser.StringContext ctx) {
        String valueText = ctx.VALUE().getText();
        if (valueText.startsWith("\"") && valueText.endsWith("\""))
            valueText = valueText.substring(1, valueText.length() - 1);
        return new StringValue(valueText);
    }

    @Override
    public Object visitArray(JamlParser.ArrayContext ctx) {
        return new ArrayValue(visitEach(ctx.value()));
    }

    @Override
    public Object visitMap(JamlParser.MapContext ctx) {
        List<StringValue> keys = visitEach(ctx.VALUE());
    }

    private <T extends JamlValue> List<T> visitEach(List<? extends ParseTree> ctxs) {
        List<T> values = new ArrayList(ctxs.size());
        for (ParseTree ctx : ctxs)
            values.add((T) visit(ctx));
        return values;
    }
}
