package de.zocker160.adk.analyzer.binanalyzer.parser;

import com.google.common.io.LittleEndianDataInputStream;
import com.google.common.io.LittleEndianDataOutputStream;
import de.zocker160.adk.analyzer.binanalyzer.utils.ColorUtil;
import javafx.scene.paint.Color;

import java.io.IOException;

public class FogZone {
    private Color fogColor;
    private Color ambientColor;
    private Color lightColor;

    private float shadowDensity;
    private float fogStart;
    private float fogEnd;

    private AdkCoord postition;

    private float radiusStart;
    private float radiusEnd;

    public static FogZone loadFromStream(LittleEndianDataInputStream inputStream) throws IOException {
        return new FogZone(inputStream);
    }

    private FogZone(LittleEndianDataInputStream inputStream) throws IOException {
        this.fogColor = ColorUtil.parseFromSteam(inputStream);
        this.ambientColor = ColorUtil.parseFromSteam(inputStream);
        this.lightColor = ColorUtil.parseFromSteam(inputStream);

        this.shadowDensity = inputStream.readFloat();
        this.fogStart = inputStream.readFloat();
        this.fogEnd = inputStream.readFloat();

        this.postition = AdkCoord.fromInputStream(inputStream);

        this.radiusStart = inputStream.readFloat();
        this.radiusEnd = inputStream.readFloat();
    }

    public void writeToStream(LittleEndianDataOutputStream outputStream) throws IOException {
        ColorUtil.writeToStream(this.fogColor, outputStream);
        ColorUtil.writeToStream(this.ambientColor, outputStream);
        ColorUtil.writeToStream(this.lightColor, outputStream);

        outputStream.writeFloat(this.shadowDensity);
        outputStream.writeFloat(this.fogStart);
        outputStream.writeFloat(this.fogEnd);

        this.postition.toOutputStream(outputStream);

        outputStream.writeFloat(this.radiusStart);
        outputStream.writeFloat(this.radiusEnd);
    }


    public Color getFogColor() {
        return fogColor;
    }

    public Color getAmbientColor() {
        return ambientColor;
    }

    public Color getLightColor() {
        return lightColor;
    }

    public void setFogColor(Color fogColor) {
        this.fogColor = fogColor;
    }

    public void setAmbientColor(Color ambientColor) {
        this.ambientColor = ambientColor;
    }

    public void setLightColor(Color lightColor) {
        this.lightColor = lightColor;
    }


    public String getShadowDensity() {
        return Float.toString(shadowDensity);
    }
    public void setShadowDensity(String value) {
        this.shadowDensity = Float.parseFloat(value);
    }

    public String getFogStart() {
        return Float.toString(fogStart);
    }
    public void setFogStart(String value) {
        this.fogStart = Float.parseFloat(value);
    }

    public String getFogEnd() {
        return Float.toString(fogEnd);
    }
    public void setFogEnd(String value) {
        this.fogEnd = Float.parseFloat(value);
    }

    public AdkCoord getPostition() {
        return postition;
    }

    public String getRadiusStart() {
        return Float.toString(radiusStart);
    }
    public void setRadiusStart(String value) {
        this.radiusStart = Float.parseFloat(value);
    }

    public String getRadiusEnd() {
        return Float.toString(radiusEnd);
    }
    public void setRadiusEnd(String value) {
        this.radiusEnd = Float.parseFloat(value);
    }
}
