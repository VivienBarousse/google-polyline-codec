/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
