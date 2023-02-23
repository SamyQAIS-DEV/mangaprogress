package com.samyqais.intellij.plugins.mangaprogress.model;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import com.google.common.collect.ImmutableMap;
import com.intellij.openapi.util.text.StringUtil;

public enum MangaPicture {
    // Naruto
    NARUTO(1, "naruto", -20, 0, 32),
    ROCKLEE(2, "rock_lee", -20, 0, 32),
    SASUKE(3, "sasuke", -20, 0, 32),
    KYUBI(4, "kyubi", -20, 0, 32),
    KAKASHI(5, "kakashi", -20, 0, 32),
    ITACHI(6, "itachi", -20, 0, 32),
    DEIDARA(7, "deidara", -20, 0, 32),

    //DB
    GOKU(8, "goku", -20, 0, 32),
    GOKUSUPER(9, "goku_super", -20, 0, 32),
    GOHAN(10, "gohan", -20, 0, 32),
    PICOLO(11, "picolo", -20, 0, 32),
    TRUNK(12, "trunk", -20, 0, 32),
    SATAN(13, "satan", -20, 0, 32),
    CELL(14, "cell", -20, 0, 32),

    // Demon Slayer
    TANJIRO(15, "tanjiro", -20, 0, 32),
    NEZUKO(16, "nezuko", -20, 0, 32),
    INOSUKE(17, "inosuke", -20, 0, 32),

    // One Piece
    LUFFY(18, "luffy", -20, 0, 32),
    CROCODILE(19, "crocodile", -20, 0, 32),

    // Bleach
    ICHIGO(20, "ichigo", -20, 0, 32),
    ICHIGOHOLLOW(21, "ichigo_h", -20, 0, 32),

    //Autre
    FMA(22, "fma", -20, 0, 32),
    SAO(23, "kirito", -20, 0, 32),
    SAITAMA(24, "saitama", -20, 0, 32),
    TEMPEST(25, "tempest", -10, 0, 32),
    YUGIOH(26, "yugioh", -20, 0, 32),
    GON(27, "gon", -20, 0, 32),
    KILLUA(28, "killua", -20, 0, 32),
    ALLEN(29, "allen", -20, 0, 32),

    YUGIOH_CARD(99, "yugioh-card", -20, 0, 32);

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