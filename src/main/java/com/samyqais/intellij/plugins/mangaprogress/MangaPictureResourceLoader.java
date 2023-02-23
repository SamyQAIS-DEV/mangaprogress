package com.samyqais.intellij.plugins.mangaprogress;

import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.samyqais.intellij.plugins.mangaprogress.model.MangaPicture;

public final class MangaPictureResourceLoader {
    private static final String SPRITE_RESOURCE_PATH = "sprites/";

    private static final Cache<String, ImageIcon> cache = CacheBuilder
            .newBuilder()
            .maximumSize(100L)
            .build();

    private MangaPictureResourceLoader() {
    }

    public static ImageIcon getIcon(final MangaPicture mangaPicture) {
        return getIconInternal(getIconPath(mangaPicture));
    }

    public static ImageIcon getReversedIcon(final MangaPicture mangaPicture) {
        return getIconInternal(getReversedIconPath(mangaPicture));
    }

    public static String getIconPath(final MangaPicture mangaPicture) {
        return SPRITE_RESOURCE_PATH + mangaPicture.getName().replace(' ', '_') + ".gif";
    }

    public static String getReversedIconPath(final MangaPicture mangaPicture) {
        return SPRITE_RESOURCE_PATH + mangaPicture.getName().replace(' ', '_') + "_r.gif";
    }

    public static Optional<URL> getResource(final String resourceName) {
        return Optional
                .ofNullable(MangaPictureResourceLoader.class.getClassLoader()
                        .getResource(resourceName))
                .or(() -> Optional.ofNullable(MangaPictureResourceLoader.class.getClassLoader()
                        .getResource(resourceName.startsWith("/") ? resourceName.replaceFirst("/", "") : "/" + resourceName)));
    }

    public static Optional<InputStream> getResourceAsStream(final String resourceName) {
        return Optional
                .ofNullable(MangaPictureResourceLoader.class.getClassLoader()
                        .getResourceAsStream(resourceName))
                .or(() -> Optional.ofNullable(MangaPictureResourceLoader.class.getClassLoader()
                        .getResourceAsStream(
                                resourceName.startsWith("/") ? resourceName.replaceFirst("/", "") : "/" + resourceName)));
    }

    private static ImageIcon getIconInternal(final String resourceName) {
        try {
            return cache.get(resourceName,
                    () -> getResource(resourceName)
                            .map(ImageIcon::new)
                            .orElseGet(ImageIcon::new));
        } catch (final ExecutionException e) {
            return new ImageIcon();
        }
    }
}