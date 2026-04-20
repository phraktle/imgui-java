package imgui;

import imgui.binding.ImGuiStruct;
import imgui.binding.annotation.BindingField;
import imgui.binding.annotation.BindingSource;

import java.nio.ByteBuffer;

/**
 * Specs and pixel storage for a texture used by Dear ImGui.
 * Only useful for (1) core library and (2) renderer backends.
 */
@BindingSource
public final class ImTextureData extends ImGuiStruct {
    public ImTextureData(final long ptr) {
        super(ptr);
    }

    /*JNI
        #include "_common.h"
        #define THIS ((ImTextureData*)STRUCT_PTR)
     */

    /**
     * Sequential index to facilitate identifying a texture when debugging/printing.
     * Unique per atlas.
     */
    @BindingField(accessors = BindingField.Accessor.GETTER)
    public int UniqueID;

    /**
     * {@link imgui.flag.ImTextureStatus} value.
     * Always use {@link #setStatus(int)} to modify.
     */
    @BindingField(accessors = BindingField.Accessor.GETTER)
    public int Status;

    /**
     * {@link imgui.flag.ImTextureFormat} value.
     */
    @BindingField(accessors = BindingField.Accessor.GETTER)
    public int Format;

    /**
     * Texture width.
     */
    @BindingField(accessors = BindingField.Accessor.GETTER)
    public int Width;

    /**
     * Texture height.
     */
    @BindingField(accessors = BindingField.Accessor.GETTER)
    public int Height;

    /**
     * 4 (RGBA32) or 1 (Alpha8).
     */
    @BindingField(accessors = BindingField.Accessor.GETTER)
    public int BytesPerPixel;

    /**
     * In order to facilitate handling Status==WantDestroy in some backend: count of successive frames
     * where the texture was not used. Always {@code >0} when Status==WantDestroy.
     */
    @BindingField(accessors = BindingField.Accessor.GETTER)
    public int UnusedFrames;

    /**
     * Tells whether the texture data is known to use colors (rather than just white + alpha).
     */
    @BindingField(accessors = BindingField.Accessor.GETTER)
    public boolean UseColors;

    /**
     * Backend-specific texture identifier (the same value stored in {@code ImDrawCmd}).
     * Always use {@link #setTexID(long)} to modify.
     */
    public native long getTexID(); /*
        return (uintptr_t)THIS->TexID;
    */

    /**
     * Store the texture identifier produced by the renderer backend. Called after honoring
     * a {@code WantCreate} request.
     */
    public native void setTexID(long texID); /*
        THIS->SetTexID((ImTextureID)texID);
    */

    /**
     * Mark the texture with a new {@link imgui.flag.ImTextureStatus} value after honoring a
     * {@code WantCreate} / {@code WantUpdates} / {@code WantDestroy} request.
     */
    public native void setStatus(int status); /*
        THIS->SetStatus((ImTextureStatus)status);
    */

    /**
     * Bounding box encompassing all past and queued updates.
     */
    public ImTextureRect getUsedRect() {
        return new ImTextureRect(nGetUsedRect());
    }

    private native long nGetUsedRect(); /*
        return (uintptr_t)&THIS->UsedRect;
    */

    /**
     * Bounding box encompassing all queued updates (relevant while {@code Status} is {@code WantUpdates}).
     */
    public ImTextureRect getUpdateRect() {
        return new ImTextureRect(nGetUpdateRect());
    }

    private native long nGetUpdateRect(); /*
        return (uintptr_t)&THIS->UpdateRect;
    */

    /**
     * Number of individual update rectangles queued (relevant while {@code Status} is {@code WantUpdates}).
     */
    public native int getUpdatesSize(); /*
        return THIS->Updates.Size;
    */

    /**
     * Individual update rectangle at the given index. See {@link #getUpdatesSize()}.
     * The returned wrapper aliases the native rect and remains valid only until the backend
     * calls {@link #setStatus(int)} to mark the texture OK again.
     */
    public ImTextureRect getUpdate(final int index) {
        return new ImTextureRect(nGetUpdate(index));
    }

    private native long nGetUpdate(int index); /*
        return (uintptr_t)&THIS->Updates[index];
    */

    /**
     * Direct ByteBuffer view over the texture's pixel storage.
     * The buffer aliases the native pixel array — no copy is performed, and its contents
     * remain valid only while the texture is in a non-destroyed state.
     * Length is {@code Width * Height * BytesPerPixel} bytes.
     */
    public native ByteBuffer getPixels(); /*
        if (THIS->Pixels == NULL) return NULL;
        return env->NewDirectByteBuffer((void*)THIS->Pixels, THIS->GetSizeInBytes());
    */

    /*JNI
        #undef THIS
     */
}
