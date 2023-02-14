package com.samyqais.intellij.plugins.mangaprogress.model;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.intellij.openapi.util.text.StringUtil;

public enum MangaPicture {
    // Gen I
    CELL(1, "cell", 0, 0, 32),
    CROCODILE(2, "crocodile", 0, 0, 32),
    DEIDARA(3, "deidara", 0, 0, 32),
    GOHAN(4, "gohan", 0, 0, 32),
    GOKU(5, "goku", 0, 0, 32),
    GOKUSUPER(6, "goku_super", 0, 0, 32),
    ICHIGOHOLLOW(7, "ichigo_hollow", 0, 0, 32),
    ITACHI(8, "itachi", 0, 0, 32),
    KAKASHI(9, "kakashi", 0, 0, 32),
    LUFFY(10, "luffy_run_render", 0, 0, 32),
    NARUTO(11, "naruto", 0, 0, 32),
    PICOLO(12, "picolo", 0, 0, 32),
    ROCKLEE(13, "rock_lee", 0, 0, 32),
    SASUKE(14, "sasuke", 0, 0, 32),
    SATAN(12, "satan", 0, 0, 32),
    TRUNK(12, "trunk", 0, 0, 32);

    public static final Map<String, MangaPicture> DEFAULT_MANGA_PICTURE = Arrays.stream(values())
            .collect(ImmutableMap.toImmutableMap(MangaPicture::getId, Function.identity(), (u, v) -> {
                throw new IllegalStateException(String.format("Duplicate MangaPicture ID %s", u));
            }));

    private final String id;
    private final int number;
    private final String name;

    private final int xShift;
    private final int yShift;
    private final int height;

    public static MangaPicture getById(final String id) {
        return DEFAULT_MANGA_PICTURE.get(id);
    }

    MangaPicture(final int number, final String name, final int xShift, final int yShift, final int height) {

        this.xShift = xShift;
        this.yShift = yShift;
        this.height = height;
        this.name = name;
        this.number = number;
        this.id = getNumberString();

    }

    public int getXShift() {
        return xShift;
    }

    public int getYShift() {
        return yShift;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getNumberString() {
        return number > 0 ? String.format("%03d", number) : "???";
    }

    public String getId() {
        return id;
    }

    public String getNameWithNumber() {
        return StringUtil.capitalizeWords(name, true) + " (#" + getNumberString() + ")";
    }

    @Override
    public String toString() {
        return getNameWithNumber();
    }

}