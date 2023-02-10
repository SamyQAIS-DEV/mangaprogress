package com.samyqais.intellij.plugins.mangaprogress;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import com.samyqais.intellij.plugins.mangaprogress.configuration.MangaProgressState;
import com.samyqais.intellij.plugins.mangaprogress.model.MangaPicture;

public class MangaPicturePicker {
    private static final String TARGET_ENV_VAR = "MANGA_PICTURE_PROGRESS_TARGET";

    private static final Random RANDOM = new Random();

    public static MangaPicture get() {
        final String target = System.getenv().get(TARGET_ENV_VAR);
        if (target != null) {
            return MangaPicture.getById(target);
        }
        final List<String> enabledMangaPictureIds = Optional.ofNullable(MangaProgressState.getInstance())
                .map(MangaPicturePicker::getEnabledMangaPictureNumbers)
                .orElse(null);
        if (enabledMangaPictureIds == null || enabledMangaPictureIds.isEmpty()) {
            return MangaPicture.YUGIOH_CARD;
        }
        return MangaPicture.getById(enabledMangaPictureIds.get(RANDOM.nextInt(enabledMangaPictureIds.size())));
    }

    private static List<String> getEnabledMangaPictureNumbers(final MangaProgressState state) {
        return state.mangaPictureNumbersEnabled.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).collect(Collectors.toList());
    }

}
