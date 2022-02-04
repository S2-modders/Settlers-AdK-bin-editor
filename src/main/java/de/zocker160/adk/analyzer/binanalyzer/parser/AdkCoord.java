package de.zocker160.adk.analyzer.binanalyzer.parser;

import com.google.common.io.LittleEndianDataInputStream;
import com.google.common.io.LittleEndianDataOutputStream;
import de.zocker160.adk.analyzer.binanalyzer.Main;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;

public class AdkCoord {
    //private static final DecimalFormat format = new DecimalFormat("####.00");

    private float x;
    private float y;

    public static AdkCoord fromInputStream(LittleEndianDataInputStream inputStream) throws IOException {
        return new AdkCoord(inputStream.readFloat(), inputStream.readFloat());
    }

    public AdkCoord(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public AdkCoord(String x, String y) {
        this.setX(x);
        this.setY(y);
    }

    public void toOutputStream(LittleEndianDataOutputStream outputStream) throws IOException {
        outputStream.writeFloat(this.x);
        outputStream.writeFloat(this.y);
    }


    public String xStr() {
        //return format.format(this.x);
        return Float.toString(Math.round(this.x * 100f) / 100f);
    }
    public String yStr() {
        //return format.format(this.y);
        return Float.toString(Math.round(this.y * 100f) / 100f);
    }

    public void setX(String value) {
        this.x = Float.parseFloat(value);
    }
    public void setY(String value) {
        this.y = Float.parseFloat(value);
    }
}
