package com.hienthai.vohoctrunghoa.extensions

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.hienthai.vohoctrunghoa.presentation.base.navigator.NavigationActivity
import com.hienthai.vohoctrunghoa.presentation.base.navigator.Navigator
import com.markodevcic.peko.*
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

val Fragment.navigator: Navigator?
    get() = (activity as? NavigationActivity<*>)?.navigator

fun <T : Any> safeArgs(key: String? = null): ReadWriteProperty<Fragment, T> =
    FragmentArgumentDelegate(key)

fun <T : Any> args(key: String? = null): ReadWriteProperty<Fragment, T?> =
    FragmentNullableArgumentDelegate(key)

class FragmentArgumentDelegate<T : Any>(private val key: String?) :
    ReadWriteProperty<Fragment, T> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val name = key ?: property.name
        return thisRef.arguments
            ?.get(name) as? T
            ?: throw IllegalStateException("Property $name could not be read")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val args = thisRef.arguments
            ?: Bundle().also(thisRef::setArguments)
        val name = key ?: property.name
        args.put(name, value)
    }
}

class FragmentNullableArgumentDelegate<T : Any?>(private val key: String?) :
    ReadWriteProperty<Fragment, T?> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>
    ): T? {
        val name = key ?: property.name
        return thisRef.arguments?.get(name) as? T
    }

    override fun setValue(
        thisRef: Fragment,
        property: KProperty<*>, value: T?
    ) {
        val args = thisRef.arguments
            ?: Bundle().also(thisRef::setArguments)
        val name = key ?: property.name
        value?.let { args.put(name, it) } ?: args.remove(name)
    }
}

fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Short -> putShort(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        is Bundle -> putBundle(key, value)
        is Parcelable -> putParcelable(key, value)
        is Serializable -> putSerializable(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}

suspend fun Fragment.requestImageStoragePermissionsAsync(): Boolean {
    val requester = PermissionRequester.instance()
    return if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requester.request(Manifest.permission.READ_MEDIA_IMAGES).allGranted()
    } else {
        requester.request(Manifest.permission.READ_EXTERNAL_STORAGE).allGranted()
    }
}

suspend fun Fragment.requestPermissionsAsync(vararg permissions: String): Boolean {
    val requester = PermissionRequester.instance()
    return requester.request(*permissions).allGranted()
}

suspend fun Activity.requestPermissionsAsync(vararg permissions: String): Boolean {
    val requester = PermissionRequester.instance()
    return requester.request(*permissions).allGranted()
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}
