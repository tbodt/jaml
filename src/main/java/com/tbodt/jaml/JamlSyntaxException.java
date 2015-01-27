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

import java.util.List;

/**
 * An exception thrown when the syntax of a JAML file is incorrect.
 *
 * @author Theodore Dubois
 */
public class JamlSyntaxException extends Exception {
    private final List<String> errors;

    JamlSyntaxException(List<String> errors) {
        super(errors.toString());
        this.errors = errors;
    }

    /**
     * Return a list of the error messages.
     * @return a list of the error messages
     */
    public List<String> getErrors() {
        return errors;
    }
}
