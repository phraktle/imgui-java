package imgui;

import imgui.binding.ImGuiStruct;
import imgui.binding.annotation.BindingField;
import imgui.binding.annotation.BindingSource;

/**
 * Coordinates of a rectangle within a texture.
 */
@BindingSource
public final class ImTextureRect extends ImGuiStruct {
    public ImTextureRect(final long ptr) {
        super(ptr);
    }

    /*JNI
        #include "_common.h"
        #define THIS ((ImTextureRect*)STRUCT_PTR)
     */

    @BindingField
    public int x;

    @BindingField
    public int y;

    @BindingField
    public int w;

    @BindingField
    public int h;

    /*JNI
        #undef THIS
     */
}
