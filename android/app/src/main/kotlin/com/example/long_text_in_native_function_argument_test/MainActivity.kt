package com.example.long_text_in_native_function_argument_test

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import org.json.JSONArray
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.exists
import kotlin.io.path.pathString


class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "my_kotlin_functions").setMethodCallHandler { call, result ->
            if (call.method.contentEquals("get_a_string")) {
                var text: String = ""
                // 1048576 characters == 1MB, even 2MB will break it
                repeat(1048576*20) {
                    text += 'a'
                }
                result.success(text)
            }
        }
    }
}

