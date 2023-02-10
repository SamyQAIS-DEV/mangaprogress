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
    BULBASAUR(1, "bulbasaur", 0, 0, 32),
    YUGIOH_CARD(2, "yugioh-card", 0, 0, 32),
    GOGETTA(3, "GogettaKameameaReversed", 0, 0, 32);

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