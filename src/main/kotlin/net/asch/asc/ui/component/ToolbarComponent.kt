package net.asch.asc.ui.component

import com.mojang.blaze3d.systems.RenderSystem
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.component.ButtonComponent.Renderer
import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.HorizontalAlignment
import io.wispforest.owo.ui.core.Insets
import io.wispforest.owo.ui.core.Sizing
import io.wispforest.owo.ui.core.VerticalAlignment
import io.wispforest.owo.ui.util.NinePatchTexture
import io.wispforest.owo.util.EventStream
import io.wispforest.owo.util.Observable
import net.asch.asc.ModClient
import net.asch.asc.ui.component.ToolbarComponent.ToolButtonComponent.OnActiveEvent
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.util.function.Consumer

class ToolbarComponent : FlowLayout(Sizing.fill(100), Sizing.content(), Algorithm.HORIZONTAL) {
    private val layout = Containers.horizontalFlow(Sizing.content(), Sizing.content())
    private val exclusiveGroups: MutableMap<String, MutableList<ToolButtonComponent>> = mutableMapOf()

    companion object {
        private val BASE_ACTIVE_TEXTURE_ID = Identifier.of(ModClient.MOD_ID, "toolbar_base_button/active")
        private val BASE_DISABLED_TEXTURE_ID = Identifier.of(ModClient.MOD_ID, "toolbar_base_button/disabled")
        private val BASE_HOVERED_TEXTURE_ID = Identifier.of(ModClient.MOD_ID, "toolbar_base_button/hovered")

        private val BASE_RENDERER = Renderer { context, button, _ ->
            RenderSystem.enableDepthTest()
            val texture =
                if (button.active) (if (button.isHovered) BASE_HOVERED_TEXTURE_ID else BASE_ACTIVE_TEXTURE_ID) else BASE_DISABLED_TEXTURE_ID
            NinePatchTexture.draw(texture, context, button.x, button.y, button.width, button.height)
        }
    }

    init {
        horizontalAlignment(HorizontalAlignment.LEFT)
        verticalAlignment(VerticalAlignment.CENTER)

        layout.horizontalAlignment(HorizontalAlignment.LEFT)
        layout.verticalAlignment(VerticalAlignment.CENTER)
        layout.margins(Insets.of(2))
        layout.gap(2)
        child(layout)
    }

    fun toolbox(text: Text, id: String, exclusiveGroup: String? = null, action: Consumer<ToolButtonComponent>) {
        val btn = ToolButtonComponent(text, action)
        btn.id(id)
        layout.child(btn)

        if (exclusiveGroup != null) {
            if (exclusiveGroup !in exclusiveGroups) {
                exclusiveGroups[exclusiveGroup] = mutableListOf()
            }

            exclusiveGroups[exclusiveGroup]!!.add(btn)
            btn.onActiveStream.source().subscribe { newActive ->
                if (newActive) {
                    for (excBtn in exclusiveGroups[exclusiveGroup]!!) {
                        if (excBtn != btn) {
                            excBtn.setInactive()
                        }
                    }
                }
            }
        }
    }

    fun button(text: Text, action: Consumer<ButtonComponent>) {
        val btn = Components.button(text, action).renderer(BASE_RENDERER)
        layout.child(btn)
    }

    fun press(group: String, id: String?) {
        if (id == null) {
            return
        }

        if (group !in exclusiveGroups) {
            return
        }

        val btn = exclusiveGroups[group]!!.find { menuComponent -> menuComponent.id() == id }
        btn?.onPress()
    }

    class ToolButtonComponent(text: Text, private val onPressAction: Consumer<ToolButtonComponent>) :
        ButtonComponent(text, { btn -> onPressAction.accept(btn as ToolButtonComponent) }) {
        companion object {
            private val ACTIVE_TEXTURE_ID = Identifier.of(ModClient.MOD_ID, "toolbar_button/active")
            private val DISABLED_TEXTURE_ID = Identifier.of(ModClient.MOD_ID, "toolbar_button/disabled")
            private val HOVERED_TEXTURE_ID = Identifier.of(ModClient.MOD_ID, "toolbar_button/hovered")

            private val RENDERER = Renderer { context, button, _ ->
                RenderSystem.enableDepthTest()
                val texture =
                    if (button.active) (if (button.isHovered) HOVERED_TEXTURE_ID else ACTIVE_TEXTURE_ID) else DISABLED_TEXTURE_ID
                NinePatchTexture.draw(texture, context, button.x, button.y, button.width, button.height)
            }
        }

        private val activeObs: Observable<Boolean> = Observable.of(false)
        val onActiveStream = OnActiveEvent.newStream()

        fun interface OnActiveEvent {
            companion object {
                fun newStream(): EventStream<OnActiveEvent> {
                    return EventStream<OnActiveEvent> { subscribers ->
                        OnActiveEvent { newActive ->
                            for (subscriber in subscribers) {
                                subscriber.onActiveChanged(newActive)
                            }
                        }
                    }
                }
            }

            fun onActiveChanged(newActive: Boolean)
        }

        init {
            renderer(RENDERER)

            activeObs.observe { newActive ->
                active = !newActive
                onActiveStream.sink().onActiveChanged(newActive)
            }
        }

        fun setActive() {
            activeObs.set(true)
        }

        fun setInactive() {
            activeObs.set(false)
        }
    }
}