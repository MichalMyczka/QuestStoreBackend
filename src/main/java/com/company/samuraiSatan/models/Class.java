package com.company.samuraiSatan.models;

import com.company.samuraiSatan.IO;
import com.company.samuraiSatan.dao.ClassDao;

public class Class {
    private int class_ID;
    private String class_Name;
    private final IO io = new IO();
    private final ClassDao classDao = new ClassDao();

    public Class(int class_ID, String class_Name) {
        this.class_ID = class_ID;
        this.class_Name = class_Name;
    }

    public int getClass_ID() {
        return class_ID;
    }

    public String getClass_Name() {
        return class_Name;
    }

    public void setClass_ID(int class_ID) {
        this.class_ID = class_ID;
    }

    public void setClass_Name(String class_Name) {
        this.class_Name = class_Name;
    }


}
