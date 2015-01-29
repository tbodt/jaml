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
    private static final String testJaml = "a = b";

    @Test
    public void testJaml() throws JamlSyntaxException {
        JamlFile jaml = JamlFile.parse(testJaml);

        assertTrue(true);
    }
}
