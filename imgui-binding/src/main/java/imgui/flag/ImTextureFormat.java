package imgui.flag;

import imgui.binding.annotation.BindingAstEnum;
import imgui.binding.annotation.BindingSource;

/**
 * Texture format communicated between core library and Renderer Backend.
 */
@BindingSource
public final class ImTextureFormat {
    private ImTextureFormat() {
    }

    @BindingAstEnum(file = "ast-imgui.json", qualType = "ImTextureFormat", sanitizeName = "ImTextureFormat_")
    public Void __;
}
