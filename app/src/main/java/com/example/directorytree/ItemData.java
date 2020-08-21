package com.example.directorytree;

import java.io.File;

public class ItemData {

    private String name;
    private File file;
    private boolean isDirectory;
    private int imgRes;
    private boolean isExpanded;

    public ItemData(String name, File file, boolean isDirectory, int imgRes, boolean isExpanded) {
        this.name = name;
        this.file = file;
        this.isDirectory = isDirectory;
        this.imgRes = imgRes;
        this.isExpanded = isExpanded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
