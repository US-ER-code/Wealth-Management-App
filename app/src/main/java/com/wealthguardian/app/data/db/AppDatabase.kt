package com.wealthguardian.app.data.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wealthguardian.app.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Database(
    entities = [Goal::class, VaultDocument::class, FamilyMember::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
    abstract fun vaultDao(): VaultDao
    abstract fun familyDao(): FamilyDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "wealthguardian.db")
                    .addCallback(SeedCallback(context))
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
    }

    class SeedCallback(private val ctx: Context) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                val inst = getInstance(ctx)
                val sdf = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
                val now = sdf.format(Date())

                // Seed goals
                listOf(
                    Goal("g1","Retirement","🏖️",5000000.0,1980000.0,"Retirement",5000.0,5),
                    Goal("g2","Emergency Fund","🚨",500000.0,320000.0,"Emergency",2000.0,1),
                    Goal("g3","Education","🎓",2000000.0,450000.0,"Education",3000.0,10),
                    Goal("g4","Home Renovation","🏠",800000.0,120000.0,"Home",1500.0,15)
                ).forEach { inst.goalDao().insert(it) }

                // Seed vault documents
                listOf(
                    VaultDocument("v1","LIC Term Policy",DocumentCategory.INSURANCE,"LIC",now,true,"2.4 MB",uploadStatus=UploadStatus.CLOUD,uploadedAt=System.currentTimeMillis()),
                    VaultDocument("v2","HDFC Home Loan Agreement",DocumentCategory.LOAN,"HDFC Bank",now,true,"1.8 MB",uploadStatus=UploadStatus.CLOUD,uploadedAt=System.currentTimeMillis()),
                    VaultDocument("v3","PAN Card",DocumentCategory.IDENTITY,"Income Tax Dept",now,true,"0.5 MB",digilockerDocType="PAN",digilockerUri="digilocker://PAN/ABCDE1234F",uploadStatus=UploadStatus.DIGILOCKER,uploadedAt=System.currentTimeMillis()),
                    VaultDocument("v4","Aadhaar Card",DocumentCategory.IDENTITY,"UIDAI",now,true,"0.3 MB",digilockerDocType="ADHAR",digilockerUri="digilocker://ADHAR/1234-5678-9012",uploadStatus=UploadStatus.DIGILOCKER,uploadedAt=System.currentTimeMillis()),
                    VaultDocument("v5","ITR FY 2022-23",DocumentCategory.TAX,"Income Tax Dept",now,true,"1.2 MB",uploadStatus=UploadStatus.CLOUD,uploadedAt=System.currentTimeMillis()),
                    VaultDocument("v6","CAMS Statement",DocumentCategory.INVESTMENT,"CAMS",now,false,"0.8 MB"),
                    VaultDocument("v7","Star Health Policy",DocumentCategory.INSURANCE,"Star Health",now,true,"1.5 MB",uploadStatus=UploadStatus.CLOUD,uploadedAt=System.currentTimeMillis()),
                    VaultDocument("v8","Property Document",DocumentCategory.MISC,"Municipal Corp",now,false,"5.2 MB")
                ).forEach { inst.vaultDao().insert(it) }

                // Seed family members
                listOf(
                    FamilyMember("f1","Priya Sharma","priya@email.com",FamilyRole.VIEWER,"PS","Added Nov 1",allowedSections="NET_WORTH,ACCOUNTS",inviteAccepted=true),
                    FamilyMember("f2","Rahul Sharma","rahul@email.com",FamilyRole.EMERGENCY,"RS","Added Oct 15",allowedSections="NET_WORTH,ACCOUNTS,VAULT,GOALS",inviteAccepted=true),
                    FamilyMember("f3","Anita Gupta","anita@email.com",FamilyRole.ADMIN,"AG","Added Sep 20",allowedSections="NET_WORTH,ACCOUNTS,VAULT,GOALS,FAMILY,CONSENT",inviteAccepted=true)
                ).forEach { inst.familyDao().insert(it) }
            }
        }
    }
}
