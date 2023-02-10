package com.samyqais.intellij.plugins.mangaprogress.configuration;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
//import com.samyqais.intellij.plugins.mangaprogress.MangaIconLoaderIconReplacer;
import com.samyqais.intellij.plugins.mangaprogress.model.MangaPicture;

@State(
        name = "com.samyqais.intellij.plugins.mangaprogress.configuration.MangaProgressState",
        storages = {@Storage("MangaProgress.xml")}
)
public class MangaProgressState implements PersistentStateComponent<MangaProgressState> {
    public String version;

    public float initialVelocity = 1.0f;
    public float acceleration = 0.4f;

    // Named poorly; this is actually Manga IDs enabled
    public Map<String, Boolean> mangaPictureNumbersEnabled = MangaPicture.DEFAULT_MANGA_PICTURE.keySet().stream()
            .collect(Collectors.toMap(Function.identity(), p -> true));
    public String theme;
    public boolean drawSprites = true;
    public boolean addToolTips = true;
    public boolean transparencyOnIndeterminate = true;
    public boolean transparencyOnDeterminate = false;
    public String colorScheme;
    private boolean replaceLoaderIcon = true;
    public boolean showUpdateNotification = true;

    public boolean restrictMaximumHeight = false;
    public int maximumHeight = 20;
    public boolean restrictMinimumHeight = false;
    public int minimumHeight = 20;

    public void setReplaceLoaderIcon(final boolean updated) {
        //this.replaceLoaderIcon = updated;
        //MangaIconLoaderIconReplacer.updateSpinner(updated);
    }

    public boolean isReplaceLoaderIcon() {
        return replaceLoaderIcon;
    }

    public void setHeightLimits(final int newMaxHeight, final int newMinHeight) {
        if (newMinHeight > newMaxHeight) {
            minimumHeight = newMaxHeight;
            maximumHeight = newMaxHeight;
            return;
        }

        minimumHeight = newMinHeight;
        maximumHeight = newMaxHeight;
    }

    public static MangaProgressState getInstance() {
        return ApplicationManager.getApplication().getService(MangaProgressState.class);
    }

    @Override
    public MangaProgressState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull final MangaProgressState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
