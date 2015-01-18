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

/**
 * A JAML string value.
 *
 * @author Theodore Dubois
 */
public final class StringValue extends JamlValue {
    private final String value;

    /**
     * Construct a {@code StringValue} with the given value..
     *
     * @param value the value
     */
    public StringValue(String value) {
        this.value = value;
    }

    /**
     * Return the value.
     * @return the value
     */
    public String getValue() {
        return value;
    }
    
    @Override
    void accept(JamlVisitor visitor) {
        visitor.visitString(this);
    }
}
