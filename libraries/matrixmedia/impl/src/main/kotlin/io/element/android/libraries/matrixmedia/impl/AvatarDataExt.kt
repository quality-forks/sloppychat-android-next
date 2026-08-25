/*
 * Copyright (c) 2025 Element Creations Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial.
 * Please see LICENSE files in the repository root for full details.
 */

package io.element.android.libraries.matrixmedia.impl

import io.element.android.libraries.core.mimetype.MimeTypes
import io.element.android.libraries.designsystem.components.avatar.AvatarData
import io.element.android.libraries.matrix.api.media.MediaSource
import io.element.android.libraries.matrixmedia.api.AVATAR_THUMBNAIL_SIZE_IN_PIXEL
import io.element.android.libraries.matrixmedia.api.MediaRequestData

/**
 * @param animated when true, request the original upload instead of a server thumbnail. The
 * /thumbnail endpoint only ever returns a still frame, so an animated avatar cannot animate
 * unless the original bytes reach the decoder.
 */
internal fun AvatarData.toMediaRequestData(animated: Boolean = false): MediaRequestData {
    return MediaRequestData(
        source = url?.let { MediaSource(it) },
        kind = if (animated) {
            MediaRequestData.Kind.File(fileName = "avatar", mimeType = MimeTypes.Images)
        } else {
            MediaRequestData.Kind.Thumbnail(AVATAR_THUMBNAIL_SIZE_IN_PIXEL)
        }
    )
}
