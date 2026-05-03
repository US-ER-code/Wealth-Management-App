package com.wealthguardian.app.data.db

import androidx.room.TypeConverter
import com.wealthguardian.app.data.model.DocumentCategory
import com.wealthguardian.app.data.model.FamilyRole
import com.wealthguardian.app.data.model.UploadStatus

class Converters {
    @TypeConverter fun fromDocCat(v: DocumentCategory) = v.name
    @TypeConverter fun toDocCat(v: String) = DocumentCategory.valueOf(v)
    @TypeConverter fun fromUpload(v: UploadStatus) = v.name
    @TypeConverter fun toUpload(v: String) = UploadStatus.valueOf(v)
    @TypeConverter fun fromRole(v: FamilyRole) = v.name
    @TypeConverter fun toRole(v: String) = FamilyRole.valueOf(v)
}
