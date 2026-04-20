package imgui;

import imgui.binding.ImGuiStruct;

/**
 * Coordinates of a rectangle within a texture.
 */
public final class ImTextureRect extends ImGuiStruct {
    public ImTextureRect(final long ptr) {
        super(ptr);
    }

    /*JNI
        #include "_common.h"
        #define THIS ((ImTextureRect*)STRUCT_PTR)
     */

    public int getx() {
        return nGetx();
    }

    public void setx(final int value) {
        nSetx(value);
    }

    private native int nGetx(); /*
        return THIS->x;
    */

    private native void nSetx(int value); /*
        THIS->x = value;
    */

    public int gety() {
        return nGety();
    }

    public void sety(final int value) {
        nSety(value);
    }

    private native int nGety(); /*
        return THIS->y;
    */

    private native void nSety(int value); /*
        THIS->y = value;
    */

    public int getw() {
        return nGetw();
    }

    public void setw(final int value) {
        nSetw(value);
    }

    private native int nGetw(); /*
        return THIS->w;
    */

    private native void nSetw(int value); /*
        THIS->w = value;
    */

    public int geth() {
        return nGeth();
    }

    public void seth(final int value) {
        nSeth(value);
    }

    private native int nGeth(); /*
        return THIS->h;
    */

    private native void nSeth(int value); /*
        THIS->h = value;
    */

    /*JNI
        #undef THIS
     */
}
