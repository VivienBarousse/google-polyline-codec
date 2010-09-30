/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aperigeek.google.polyline;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vivien Barousse
 */
public class PolylineDecoder {

    public List<Point> decodePath(String polyline) {
        List<Point> points = new ArrayList<Point>();
        List<Double> coordinates = decode(polyline);
        double oldX = 0,
                oldY = 0;
        for (int i = 0; i < coordinates.size(); i += 2) {
            double diffX = coordinates.get(i);
            double diffY = coordinates.get(i + 1);
            double newX = oldX + diffX;
            double newY = oldY + diffY;

            points.add(new Point(newX, newY));

            oldX = newX;
            oldY = newY;
        }
        return points;
    }

    public List<Double> decode(String polyline) {
        char[] chars = polyline.toCharArray();
        byte[] data = new byte[chars.length];

        for (int i = 0; i < chars.length; i++) {
            data[i] = (byte) (chars[i] - 63);
        }

        int start = 0;
        List<Double> points = new ArrayList<Double>();
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];
            if ((b & 0x20) == 0) {
                points.add(decodePoint(data, start, (i - start) + 1));
                start = 1 + i;
            }
        }

        return points;
    }

    public double decodePoint(byte[] data, int start, int length) {
        int intValue = data[start + length - 1] & 31;
        for (int i = (start + length) - 2; i >= start; i--) {
            intValue = intValue << 5;
            intValue += data[i] & 31;
        }
        if ((intValue & 1) != 0) {
            intValue = ~intValue;
        }
        intValue = intValue >> 1;
        return (intValue / 1e5);
    }

}
