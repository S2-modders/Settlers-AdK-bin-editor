package de.zocker160.adk.analyzer.binanalyzer.utils;

import java.io.File;
import java.net.URI;

public class ListFile extends File {

    public ListFile(File file) {
        super(file.getAbsolutePath());
    }

    public ListFile(String pathname) {
        super(pathname);
    }

    public ListFile(String parent, String child) {
        super(parent, child);
    }

    public ListFile(File parent, String child) {
        super(parent, child);
    }

    public ListFile(URI uri) {
        super(uri);
    }

    @Override
    public String toString() {
        return getName();
    }

    public String toFullString() {
        return super.toString();
    }
}
