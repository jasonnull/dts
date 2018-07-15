package io.github.jasonnull.dts.client.enums;


public class RegistryConfig {

    public static final int BEAT_TIMEOUT = 1;
    public static final int DEAD_TIMEOUT = BEAT_TIMEOUT * 3;

    public enum RegistType {EXECUTOR, ADMIN}

}
