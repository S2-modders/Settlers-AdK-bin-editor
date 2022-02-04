package de.zocker160.adk.analyzer.binanalyzer.utils;

import com.google.common.io.LittleEndianDataInputStream;
import com.google.common.io.LittleEndianDataOutputStream;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ColorUtil {
    public static Color parseFromSteam(LittleEndianDataInputStream inputStream) throws IOException {
        return Color.color(inputStream.readFloat(), inputStream.readFloat(), inputStream.readFloat());
    }

    public static void writeToStream(Color color, LittleEndianDataOutputStream outputStream) throws IOException {
        outputStream.writeFloat((float) color.getRed());
        outputStream.writeFloat((float) color.getGreen());
        outputStream.writeFloat((float) color.getBlue());
    }
}
