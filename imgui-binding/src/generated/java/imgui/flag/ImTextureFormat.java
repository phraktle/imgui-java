package imgui.flag;


/**
 * Texture format communicated between core library and Renderer Backend.
 */
public final class ImTextureFormat {
    private ImTextureFormat() {
    }

    /**
     * 4 components per pixel, each is unsigned 8-bit. Total size = TexWidth * TexHeight * 4
     */
    public static final int RGBA32 = 0;

    /**
     * 1 component per pixel, each is unsigned 8-bit. Total size = TexWidth * TexHeight
     */
    public static final int Alpha8 = 1;
}
