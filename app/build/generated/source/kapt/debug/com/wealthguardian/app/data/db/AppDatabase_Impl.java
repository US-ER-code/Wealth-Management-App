package com.wealthguardian.app.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile GoalDao _goalDao;

  private volatile VaultDao _vaultDao;

  private volatile FamilyDao _familyDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `goals` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `emoji` TEXT NOT NULL, `targetAmount` REAL NOT NULL, `currentAmount` REAL NOT NULL, `category` TEXT NOT NULL, `sipAmount` REAL NOT NULL, `sipDay` INTEGER NOT NULL, `notes` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `vault_documents` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `category` TEXT NOT NULL, `issuer` TEXT NOT NULL, `dateAdded` TEXT NOT NULL, `isVerified` INTEGER NOT NULL, `fileSize` TEXT NOT NULL, `localPath` TEXT NOT NULL, `mimeType` TEXT NOT NULL, `digilockerUri` TEXT NOT NULL, `digilockerDocType` TEXT NOT NULL, `uploadStatus` TEXT NOT NULL, `uploadedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `family_members` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `email` TEXT NOT NULL, `role` TEXT NOT NULL, `avatarInitials` TEXT NOT NULL, `addedDate` TEXT NOT NULL, `phone` TEXT NOT NULL, `allowedSections` TEXT NOT NULL, `inviteAccepted` INTEGER NOT NULL, `inviteSentAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '2bf123362e4ca4b02fd67b1d8cbe0aec')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `goals`");
        db.execSQL("DROP TABLE IF EXISTS `vault_documents`");
        db.execSQL("DROP TABLE IF EXISTS `family_members`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsGoals = new HashMap<String, TableInfo.Column>(10);
        _columnsGoals.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("emoji", new TableInfo.Column("emoji", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("targetAmount", new TableInfo.Column("targetAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("currentAmount", new TableInfo.Column("currentAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("sipAmount", new TableInfo.Column("sipAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("sipDay", new TableInfo.Column("sipDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoals.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGoals = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGoals = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGoals = new TableInfo("goals", _columnsGoals, _foreignKeysGoals, _indicesGoals);
        final TableInfo _existingGoals = TableInfo.read(db, "goals");
        if (!_infoGoals.equals(_existingGoals)) {
          return new RoomOpenHelper.ValidationResult(false, "goals(com.wealthguardian.app.data.model.Goal).\n"
                  + " Expected:\n" + _infoGoals + "\n"
                  + " Found:\n" + _existingGoals);
        }
        final HashMap<String, TableInfo.Column> _columnsVaultDocuments = new HashMap<String, TableInfo.Column>(13);
        _columnsVaultDocuments.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultDocuments.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultDocuments.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultDocuments.put("issuer", new TableInfo.Column("issuer", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultDocuments.put("dateAdded", new TableInfo.Column("dateAdded", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultDocuments.put("isVerified", new TableInfo.Column("isVerified", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultDocuments.put("fileSize", new TableInfo.Column("fileSize", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultDocuments.put("localPath", new TableInfo.Column("localPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultDocuments.put("mimeType", new TableInfo.Column("mimeType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultDocuments.put("digilockerUri", new TableInfo.Column("digilockerUri", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultDocuments.put("digilockerDocType", new TableInfo.Column("digilockerDocType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultDocuments.put("uploadStatus", new TableInfo.Column("uploadStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultDocuments.put("uploadedAt", new TableInfo.Column("uploadedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVaultDocuments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVaultDocuments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVaultDocuments = new TableInfo("vault_documents", _columnsVaultDocuments, _foreignKeysVaultDocuments, _indicesVaultDocuments);
        final TableInfo _existingVaultDocuments = TableInfo.read(db, "vault_documents");
        if (!_infoVaultDocuments.equals(_existingVaultDocuments)) {
          return new RoomOpenHelper.ValidationResult(false, "vault_documents(com.wealthguardian.app.data.model.VaultDocument).\n"
                  + " Expected:\n" + _infoVaultDocuments + "\n"
                  + " Found:\n" + _existingVaultDocuments);
        }
        final HashMap<String, TableInfo.Column> _columnsFamilyMembers = new HashMap<String, TableInfo.Column>(10);
        _columnsFamilyMembers.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFamilyMembers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFamilyMembers.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFamilyMembers.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFamilyMembers.put("avatarInitials", new TableInfo.Column("avatarInitials", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFamilyMembers.put("addedDate", new TableInfo.Column("addedDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFamilyMembers.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFamilyMembers.put("allowedSections", new TableInfo.Column("allowedSections", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFamilyMembers.put("inviteAccepted", new TableInfo.Column("inviteAccepted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFamilyMembers.put("inviteSentAt", new TableInfo.Column("inviteSentAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFamilyMembers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFamilyMembers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFamilyMembers = new TableInfo("family_members", _columnsFamilyMembers, _foreignKeysFamilyMembers, _indicesFamilyMembers);
        final TableInfo _existingFamilyMembers = TableInfo.read(db, "family_members");
        if (!_infoFamilyMembers.equals(_existingFamilyMembers)) {
          return new RoomOpenHelper.ValidationResult(false, "family_members(com.wealthguardian.app.data.model.FamilyMember).\n"
                  + " Expected:\n" + _infoFamilyMembers + "\n"
                  + " Found:\n" + _existingFamilyMembers);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "2bf123362e4ca4b02fd67b1d8cbe0aec", "f8709a345ba152def9699069e023ac59");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "goals","vault_documents","family_members");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `goals`");
      _db.execSQL("DELETE FROM `vault_documents`");
      _db.execSQL("DELETE FROM `family_members`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(GoalDao.class, GoalDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VaultDao.class, VaultDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FamilyDao.class, FamilyDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public GoalDao goalDao() {
    if (_goalDao != null) {
      return _goalDao;
    } else {
      synchronized(this) {
        if(_goalDao == null) {
          _goalDao = new GoalDao_Impl(this);
        }
        return _goalDao;
      }
    }
  }

  @Override
  public VaultDao vaultDao() {
    if (_vaultDao != null) {
      return _vaultDao;
    } else {
      synchronized(this) {
        if(_vaultDao == null) {
          _vaultDao = new VaultDao_Impl(this);
        }
        return _vaultDao;
      }
    }
  }

  @Override
  public FamilyDao familyDao() {
    if (_familyDao != null) {
      return _familyDao;
    } else {
      synchronized(this) {
        if(_familyDao == null) {
          _familyDao = new FamilyDao_Impl(this);
        }
        return _familyDao;
      }
    }
  }
}
