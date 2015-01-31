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

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * A test for JAML.
 *
 * @author theodore
 */
public class JamlTest {
    @Test
    public void testStrings() throws JamlSyntaxException {
        JamlFile jaml = JamlFile.parse("a = b");
        assertEquals("b", jaml.getString("a"));
    }

    @Test
    public void testMaps() throws JamlSyntaxException {
        JamlFile jaml = JamlFile.parse("map = {\n"
                                       + "key = value\n"
                                       + "}");
        assertEquals("value", jaml.getMap("map").getMap().get("key"));
        assertEquals("value", jaml.getString("map", "key"));
    }
}
