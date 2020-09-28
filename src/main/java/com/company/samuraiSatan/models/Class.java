package com.company.samuraiSatan.models;

import com.company.samuraiSatan.IO;
import com.company.samuraiSatan.dao.ClassDao;

public class Class {
    private int classID;
    private String className;
    private final IO io = new IO();
    private final ClassDao classDao = new ClassDao();

    public Class(int classID, String className) {
        this.classID = classID;
        this.className = className;
    }

    public int getClassID() {
        return classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public void setClass_Name(String className) {
        this.className = className;
    }


}
