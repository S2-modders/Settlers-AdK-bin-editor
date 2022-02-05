package de.zocker160.adk.analyzer.binanalyzer;

import de.zocker160.adk.analyzer.binanalyzer.parser.BinFile;
import de.zocker160.adk.analyzer.binanalyzer.parser.FogZone;
import de.zocker160.adk.analyzer.binanalyzer.utils.Settings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainWindow extends Application {
    private static Stage stage;
    private static MainWindowController controller;

    private static BinFile data;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MainWindow.stage = stage;
        MainWindow.controller = fxmlLoader.getController();

        stage.setTitle(Settings.APPLICATION_NAME+" "+ Settings.VERSION);
        stage.setScene(scene);
        stage.show();
    }

    public static void start() {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static MainWindowController getController() {
        return controller;
    }

    protected static String openFile() {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Select BIN file");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("AdK BIN File", "*.bin")
        );

        File file = fileChooser.showOpenDialog(stage);

        BinFile binFile = BinFile.loadFile(file);
        data = binFile;

        controller.value1.setText(binFile.getValue1());
        controller.value2.setText(binFile.getValue2());
        controller.value3.setText(binFile.getValue3());
        controller.value4.setText(binFile.getValue4());

        controller.fogStart.setText(binFile.getFogStart());
        controller.fogEnd.setText(binFile.getFogEnd());

        controller.fogColor.setValue(binFile.fogColor);
        controller.ambientColor.setValue(binFile.ambientColor);
        controller.lightColor.setValue(binFile.lightColor);

        controller.maxZone = binFile.getNumberOfZones()-1;
        controller.setZone(0);

        return file.getName();
    }

    protected static void saveToFile() {
        if (data == null) return;
        saveUiData();
        data.save();
    }

    protected static void saveToFile(File file) {
        if (data == null) return;
        saveUiData();
        data.save(file);
    }

    private static void saveUiData() {
        saveGlobal();
        saveZone(controller.getCurrentZone());
    }

    private static void saveGlobal() {
        BinFile binFile = data;

        binFile.setValue1(controller.value1.getText());
        binFile.setValue2(controller.value2.getText());
        binFile.setValue3(controller.value3.getText());
        binFile.setValue4(controller.value4.getText());

        binFile.setFogStart(controller.fogStart.getText());
        binFile.setFogEnd(controller.fogEnd.getText());

        binFile.fogColor = controller.fogColor.getValue();
        binFile.ambientColor = controller.ambientColor.getValue();
        binFile.lightColor = controller.lightColor.getValue();
    }

    protected static void loadZone(int zone) {
        if (data == null) return;

        FogZone fogZone = data.getZone(zone);

        controller.fogColorZone.setValue(fogZone.getFogColor());
        controller.ambientColorZone.setValue(fogZone.getAmbientColor());
        controller.lightColorZone.setValue(fogZone.getLightColor());

        controller.shadowDensityZone.setText(fogZone.getShadowDensity());

        controller.fogStartZone.setText(fogZone.getFogStart());
        controller.fogEndZone.setText(fogZone.getFogEnd());

        controller.posXZone.setText(fogZone.getPostition().xStr());
        controller.posYZone.setText(fogZone.getPostition().yStr());

        controller.radiusStartZone.setText(fogZone.getRadiusStart());
        controller.radiusEndZone.setText(fogZone.getRadiusEnd());
    }

    protected static void saveZone(int zone) {
        if (data == null) return;

        FogZone fogZone = data.getZone(zone);

        fogZone.setFogColor(controller.fogColorZone.getValue());
        fogZone.setAmbientColor(controller.ambientColor.getValue());
        fogZone.setLightColor(controller.lightColor.getValue());

        fogZone.setShadowDensity(controller.shadowDensityZone.getText());

        fogZone.setFogStart(controller.fogStartZone.getText());
        fogZone.setFogEnd(controller.fogEndZone.getText());

        fogZone.getPostition().setX(controller.posXZone.getText());
        fogZone.getPostition().setY(controller.posYZone.getText());

        fogZone.setRadiusStart(controller.radiusStartZone.getText());
        fogZone.setRadiusEnd(controller.radiusEndZone.getText());
    }
}