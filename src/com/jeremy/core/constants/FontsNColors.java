package com.jeremy.core.constants;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;

public class FontsNColors {
    public static class Fonts {
        public static final Font titleFont = new Font(Font.MONOSPACED, Font.BOLD, 24);
        public static final Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 18);
        public static final Font textFont = new Font(Font.MONOSPACED, Font.BOLD, 14);
    }
    public static class Colors {
        public static final Color blue = new Color(150, 200, 255);
        public static final Color green = new Color(150, 255, 200);
        public static final Color orange = new Color(255, 200, 150);
        public static final Color pink = new Color(255, 200, 200);
        public static final Color lightGreen = new Color(211, 233, 166);
        public static final Color purple = new Color(210, 200, 255);
        public static final Color red = new Color(255, 140, 130);
        public static Color getRandomColor() {
            try {
                Field[] colors = FontsNColors.Colors.class.getFields();
                int randIdx = (int) (Math.random() * 6) + 1;
                return (Color) colors[randIdx].get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        public static Color getRandomColor(List<Color> exceptColors){
            while (true) {
                Color color = getRandomColor();
                if(!exceptColors.contains(color)) return color;
            }
        }
    }

}