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
import java.util.Map;

/**
 * A JAML map value.
 *
 * @author Theodore Dubois
 */
public final class JamlMap extends JamlObject {
    private final Map<String, JamlObject> map;

    /**
     * Constructs a new {@code JamlMap} from the given {@code map}.
     *
     * @param map the map
     */
    public JamlMap(Map<String, JamlObject> map) {
        this.map = Collections.unmodifiableMap(map);
    }

    /**
     * Return the map value of this {@code JamlMap}.
     *
     * @return the map value of this {@code JamlMap}
     */
    public Map<String, JamlObject> getMap() {
        return Collections.unmodifiableMap(map);
    }
}
