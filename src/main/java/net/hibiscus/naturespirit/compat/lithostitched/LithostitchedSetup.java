package net.hibiscus.naturespirit.compat.lithostitched;

public class LithostitchedSetup {
    public static void registerLithostitchedBiomes() {
        LithostitchedEventHandlers.handleAddRegionsEvent();
        LithostitchedEventHandlers.handleAddBiomeInjectorsEvent();
        LithostitchedEventHandlers.handleAddWorldgenModifiersEvent();
    }
}
