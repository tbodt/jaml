/*
 * Copyright (C) 2015 Theodore Dubois
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

import java.util.Collections;
import java.util.List;

/**
 * A JAML array value. Has multiple child {@link JamlValue}s.
 *
 * @author Theodore Dubois
 */
public final class ArrayValue extends JamlValue {
    private final List<JamlValue> elements;

    /**
     * Construct an {@code ArrayValue} from the elements.
     *
     * @param elements the element
     */
    public ArrayValue(List<JamlValue> elements) {
        this.elements = Collections.unmodifiableList(elements);
    }

    /**
     * Return a list of the children.
     *
     * @return a list of the children
     */
    public List<JamlValue> getElements() {
        return Collections.unmodifiableList(elements);
    }

    @Override
    void accept(JamlVisitor visitor) {
        visitor.visitArray(this);
    }
}
