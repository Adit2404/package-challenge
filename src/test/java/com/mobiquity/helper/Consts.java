package com.mobiquity.helper;

import com.mobiquity.model.Item;
import com.mobiquity.model.Package;

import java.util.ArrayList;
import java.util.List;

public final class Consts {
    public static final String INPUT_FILE_PATH = "src/main/resources/Input";
    public static final String INVALID_FILE_PATH = "src/main/resources/wrong_input";
    public static final String EMPTY_INPUT_FILE_PATH = "src/main/resources/empty_input";
    public static final String EMPTY_FILE_PATH = "";
    public static final String EMPTY = "";
    public static final List<String> EMPTY_LIST = new ArrayList<>();
    public static final List<Package> EMPTY_PACKAGE = new ArrayList<>();
    public static final List<Item> EMPTY_ITEMS = new ArrayList<>();
    public static final int MAX_PRODUCTS_FOR_PACKAGE = 15;
    private Consts() {

    }
}
