/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aperigeek.google.polyline;

import java.util.List;

/**
 *
 * @author Vivien Barousse
 */
public class PolylineEncoder {

    public String encodePath(List<Point> points) {
        String encoded = "";
        
        Point previousLocation = null;
        for (Point location : points) {
            if (previousLocation == null) {
                encoded += encode(location.getLatitude());
                encoded += encode(location.getLongitude());
            } else {
                encoded += encode(location.getLatitude() - previousLocation.getLatitude());
                encoded += encode(location.getLongitude() - previousLocation.getLongitude());
            }
            previousLocation = location;
        }

        return encoded;
    }

    public String encode(double number) {
        int base = (int) Math.round(number * 1e5);
        base = base << 1;
        if (number < 0) {
            base = ~base;
        }

        char[] chars = new char[10];
        int i = 0;
        do {
            chars[i] = (char) ((base & 31) + 63);
            if (i > 0) {
                chars[i - 1] += 32;
            }
            
            base = base >>> 5;
            i++;
        } while (base != 0);
        
        return new String(chars, 0, i);
    }

}
