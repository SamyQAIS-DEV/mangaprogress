package com.samyqais.intellij.plugins.mangaprogress;

import java.util.Objects;
import java.util.Optional;

import javax.swing.UIManager;

import org.jetbrains.annotations.NotNull;

import com.intellij.ide.plugins.DynamicPluginListener;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.openapi.extensions.PluginId;
import com.samyqais.intellij.plugins.mangaprogress.configuration.MangaProgressState;

public class MangaProgressListener implements LafManagerListener, DynamicPluginListener {
    private static final String PROGRESS_BAR_UI_KEY = "ProgressBarUI";
    private static final String MANGA_PROGRESS_BAR_UI_IMPLEMENTATION_NAME = MangaProgressBarUi.class.getName();
    private volatile static Object previousProgressBar = null;
    private volatile static PluginId pluginId = null;

    public MangaProgressListener() {
        updateProgressBarUi();
        pluginId = PluginId.getId("com.samyqais.intellij.plugins.mangaprogress");
    }

    @Override
    public void lookAndFeelChanged(@NotNull final LafManager lafManager) {
        updateProgressBarUi();
    }

    @Override
    public void pluginLoaded(@NotNull final IdeaPluginDescriptor pluginDescriptor) {
        if (Objects.equals(pluginId, pluginDescriptor.getPluginId())) {
            updateProgressBarUi();
        }
    }

    @Override
    public void beforePluginUnload(@NotNull final IdeaPluginDescriptor pluginDescriptor, final boolean isUpdate) {
        if (Objects.equals(pluginId, pluginDescriptor.getPluginId())) {
            resetProgressBarUi();
        }
    }

    static void updateProgressBarUi() {
        final Object prev = UIManager.get(PROGRESS_BAR_UI_KEY);
        if (!Objects.equals(MANGA_PROGRESS_BAR_UI_IMPLEMENTATION_NAME, prev)) {
            previousProgressBar = prev;
        }
        //Optional.ofNullable(MangaProgressState.getInstance())
                //.ifPresent(s -> MangaIconLoaderIconReplacer.updateSpinner(s.isReplaceLoaderIcon()));
        UIManager.put(PROGRESS_BAR_UI_KEY, MANGA_PROGRESS_BAR_UI_IMPLEMENTATION_NAME);
        UIManager.getDefaults().put(MANGA_PROGRESS_BAR_UI_IMPLEMENTATION_NAME, MangaProgressBarUi.class);
    }

    static void resetProgressBarUi() {
        /*UIManager.put(PROGRESS_BAR_UI_KEY, previousProgressBar);
        MangaIconLoaderIconReplacer.updateSpinner(false);*/
    }
}