package com.hienthai.baseprojectmvvm.extensions

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.core.text.getSpans
import java.nio.charset.Charset
import java.security.MessageDigest
import java.text.BreakIterator
import java.util.*

fun String.toHtml() = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

fun Spanned.onLinkClick(isUnderline: Boolean = true, action: (String) -> Unit): Spanned {
    val builder = SpannableStringBuilder(this)
    builder.getSpans<URLSpan>().forEach {
        val start = builder.getSpanStart(it)
        val end = builder.getSpanEnd(it)
        builder.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                action(it.url)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = isUnderline
            }
        }, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        builder.removeSpan(it)
    }

    return builder
}

const val SPACE_CHAR_KEY = " "
const val JAPANESE_SPACE_KEY = "　"
const val EMPTY_STRING = ""
const val ENTER_STRING = "\n"
const val ENTER_XML_STRING = "<br/>"
const val ZERO_INT = 0

fun String.countNumberOfCharacters(): Int {
    if (this.trim().isNotEmpty()) {
        return getGraphemeCount(
            this.replace(SPACE_CHAR_KEY.toRegex(), EMPTY_STRING)
                .replace(ENTER_STRING, EMPTY_STRING)
                .replace(JAPANESE_SPACE_KEY, EMPTY_STRING))
    }
    return ZERO_INT
}

fun getGraphemeCount(s: String?): Int {
    val boundary: BreakIterator = BreakIterator.getCharacterInstance(Locale.ROOT)
    boundary.setText(s)
    boundary.first()
    var result = 0
    while (boundary.next() != BreakIterator.DONE) {
        ++result
    }
    return result
}

fun String.sha1(): String {
    val md: MessageDigest = MessageDigest.getInstance("SHA-1")
    val textBytes: ByteArray = toByteArray(Charset.forName("iso-8859-1"))
    md.update(textBytes, 0, textBytes.size)
    val sha1hash: ByteArray = md.digest()
    return sha1hash.convertToHex()
}

fun ByteArray.convertToHex(): String {
    val buf = StringBuilder()
    for (b in this) {
        var halfbyte: Int = b.toInt() ushr 4 and 0x0F
        var twoHalf = 0
        do {
            buf.append(if (halfbyte in 0..9) ('0'.code + halfbyte).toChar() else ('a'.code + (halfbyte - 10)).toChar())
            halfbyte = b.toInt() and 0x0F
        } while (twoHalf++ < 1)
    }
    return buf.toString()
}