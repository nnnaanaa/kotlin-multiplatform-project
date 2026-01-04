package org.example.project

import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    // useResource を使うと、JARファイルの中から確実に画像を取り出せます
    val iconPainter = try {
        useResource("icon.png") { stream ->
            BitmapPainter(loadImageBitmap(stream))
        }
    } catch (e: Exception) {
        println("アイコンの読み込みに失敗しました: ${e.message}")
        null
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "kotlin-multiplatform-project",
        icon = iconPainter // ここに読み込んだ Painter をセット
    ) {
        App()
    }
}