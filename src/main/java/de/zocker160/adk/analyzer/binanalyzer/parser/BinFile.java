package de.zocker160.adk.analyzer.binanalyzer.parser;

import com.google.common.io.LittleEndianDataInputStream;
import com.google.common.io.LittleEndianDataOutputStream;
import de.zocker160.adk.analyzer.binanalyzer.ColorUtil;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinFile {
    private final File file;

    private int unknown;
    private int lightheading;
    private int daytime;

    public Color fogColor;
    public Color ambientColor;
    public Color lightColor;

    private float shadowDensity;
    public float fogStart;
    public float fogEnd;

    public List<FogZone> zonesList = new ArrayList<>();

    public static BinFile loadFile(File file) {
        return new BinFile(file);
    }

    private BinFile(File file) {
        this.file = file;
        this.parse();
    }

    private void parse() {
        try (var inputStream = new LittleEndianDataInputStream(new FileInputStream(this.file)) ){
            this.unknown = inputStream.readInt();
            this.lightheading = inputStream.readInt();
            this.daytime = inputStream.readInt();

            this.fogColor = ColorUtil.parseFromSteam(inputStream);
            this.ambientColor = ColorUtil.parseFromSteam(inputStream);
            this.lightColor = ColorUtil.parseFromSteam(inputStream);

            this.shadowDensity = inputStream.readFloat();
            this.fogStart = inputStream.readFloat();
            this.fogEnd = inputStream.readFloat();

            try {
                int locZones = inputStream.readInt();
                System.out.println("num local zones: "+locZones);

                for (int i=0; i < locZones; i++) {
                    FogZone zone = FogZone.loadFromStream(inputStream);
                    this.zonesList.add(zone);
                }
                System.out.println("loop done");
            } catch (EOFException ignore){
                System.out.println("This should not happen!!");
                //System.out.println("number of zones found: " + this.zonesList.size());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(File file) {
        try (var outputStream = new LittleEndianDataOutputStream(new FileOutputStream(file))) {
            outputStream.writeInt(this.unknown);
            outputStream.writeInt(this.lightheading);
            outputStream.writeInt(this.daytime);

            ColorUtil.writeToStream(this.fogColor, outputStream);
            ColorUtil.writeToStream(this.ambientColor, outputStream);
            ColorUtil.writeToStream(this.lightColor, outputStream);

            outputStream.writeFloat(this.shadowDensity);
            outputStream.writeFloat(this.fogStart);
            outputStream.writeFloat(this.fogEnd);

            outputStream.writeInt(this.zonesList.size());

            for (FogZone fogZone : this.zonesList) {
                fogZone.writeToStream(outputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue1() {
        return Integer.toString(this.unknown);
    }
    public void setValue1(String value) {
        this.unknown = Integer.parseInt(value);
    }

    public String getValue2() {
        return Integer.toString(this.lightheading);
    }
    public void setValue2(String value) {
        this.lightheading = Integer.parseInt(value);
    }

    public String getValue3() {
        return Integer.toString(this.daytime);
    }
    public void setValue3(String value) {
        this.daytime = Integer.parseInt(value);
    }

    public String getValue4() {
        return Float.toString(this.shadowDensity);
    }
    public void setValue4(String value) {
        this.shadowDensity = Float.parseFloat(value);
    }

    public String getFogStart() {
        return Float.toString(this.fogStart);
    }
    public void setFogStart(String value) {
        this.fogStart = Float.parseFloat(value);
    }

    public String getFogEnd() {
        return Float.toString(this.fogEnd);
    }
    public void setFogEnd(String value) {
        this.fogEnd = Float.parseFloat(value);
    }


    public FogZone getZone(int i) {
        return this.zonesList.get(i);
    }
    public int getNumberOfZones() {
        return this.zonesList.size();
    }
}
