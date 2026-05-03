package com.wealthguardian.app.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wealthguardian.app.data.model.DocumentCategory;
import com.wealthguardian.app.data.model.UploadStatus;
import com.wealthguardian.app.data.model.VaultDocument;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VaultDao_Impl implements VaultDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VaultDocument> __insertionAdapterOfVaultDocument;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<VaultDocument> __deletionAdapterOfVaultDocument;

  private final EntityDeletionOrUpdateAdapter<VaultDocument> __updateAdapterOfVaultDocument;

  private final SharedSQLiteStatement __preparedStmtOfUpdateStatus;

  public VaultDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVaultDocument = new EntityInsertionAdapter<VaultDocument>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `vault_documents` (`id`,`name`,`category`,`issuer`,`dateAdded`,`isVerified`,`fileSize`,`localPath`,`mimeType`,`digilockerUri`,`digilockerDocType`,`uploadStatus`,`uploadedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VaultDocument entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        final String _tmp = __converters.fromDocCat(entity.getCategory());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp);
        }
        if (entity.getIssuer() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIssuer());
        }
        if (entity.getDateAdded() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDateAdded());
        }
        final int _tmp_1 = entity.isVerified() ? 1 : 0;
        statement.bindLong(6, _tmp_1);
        if (entity.getFileSize() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getFileSize());
        }
        if (entity.getLocalPath() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getLocalPath());
        }
        if (entity.getMimeType() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getMimeType());
        }
        if (entity.getDigilockerUri() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getDigilockerUri());
        }
        if (entity.getDigilockerDocType() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDigilockerDocType());
        }
        final String _tmp_2 = __converters.fromUpload(entity.getUploadStatus());
        if (_tmp_2 == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, _tmp_2);
        }
        statement.bindLong(13, entity.getUploadedAt());
      }
    };
    this.__deletionAdapterOfVaultDocument = new EntityDeletionOrUpdateAdapter<VaultDocument>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `vault_documents` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VaultDocument entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfVaultDocument = new EntityDeletionOrUpdateAdapter<VaultDocument>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `vault_documents` SET `id` = ?,`name` = ?,`category` = ?,`issuer` = ?,`dateAdded` = ?,`isVerified` = ?,`fileSize` = ?,`localPath` = ?,`mimeType` = ?,`digilockerUri` = ?,`digilockerDocType` = ?,`uploadStatus` = ?,`uploadedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VaultDocument entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        final String _tmp = __converters.fromDocCat(entity.getCategory());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp);
        }
        if (entity.getIssuer() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIssuer());
        }
        if (entity.getDateAdded() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDateAdded());
        }
        final int _tmp_1 = entity.isVerified() ? 1 : 0;
        statement.bindLong(6, _tmp_1);
        if (entity.getFileSize() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getFileSize());
        }
        if (entity.getLocalPath() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getLocalPath());
        }
        if (entity.getMimeType() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getMimeType());
        }
        if (entity.getDigilockerUri() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getDigilockerUri());
        }
        if (entity.getDigilockerDocType() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDigilockerDocType());
        }
        final String _tmp_2 = __converters.fromUpload(entity.getUploadStatus());
        if (_tmp_2 == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, _tmp_2);
        }
        statement.bindLong(13, entity.getUploadedAt());
        if (entity.getId() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getId());
        }
      }
    };
    this.__preparedStmtOfUpdateStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE vault_documents SET uploadStatus = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final VaultDocument doc, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfVaultDocument.insert(doc);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final VaultDocument doc, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfVaultDocument.handle(doc);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final VaultDocument doc, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfVaultDocument.handle(doc);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateStatus(final String id, final UploadStatus status,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateStatus.acquire();
        int _argIndex = 1;
        final String _tmp = __converters.fromUpload(status);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp);
        }
        _argIndex = 2;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, id);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<VaultDocument>> getAllLive() {
    final String _sql = "SELECT * FROM vault_documents ORDER BY uploadedAt DESC, dateAdded DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"vault_documents"}, false, new Callable<List<VaultDocument>>() {
      @Override
      @Nullable
      public List<VaultDocument> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIssuer = CursorUtil.getColumnIndexOrThrow(_cursor, "issuer");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfIsVerified = CursorUtil.getColumnIndexOrThrow(_cursor, "isVerified");
          final int _cursorIndexOfFileSize = CursorUtil.getColumnIndexOrThrow(_cursor, "fileSize");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final int _cursorIndexOfMimeType = CursorUtil.getColumnIndexOrThrow(_cursor, "mimeType");
          final int _cursorIndexOfDigilockerUri = CursorUtil.getColumnIndexOrThrow(_cursor, "digilockerUri");
          final int _cursorIndexOfDigilockerDocType = CursorUtil.getColumnIndexOrThrow(_cursor, "digilockerDocType");
          final int _cursorIndexOfUploadStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "uploadStatus");
          final int _cursorIndexOfUploadedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "uploadedAt");
          final List<VaultDocument> _result = new ArrayList<VaultDocument>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VaultDocument _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final DocumentCategory _tmpCategory;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toDocCat(_tmp);
            final String _tmpIssuer;
            if (_cursor.isNull(_cursorIndexOfIssuer)) {
              _tmpIssuer = null;
            } else {
              _tmpIssuer = _cursor.getString(_cursorIndexOfIssuer);
            }
            final String _tmpDateAdded;
            if (_cursor.isNull(_cursorIndexOfDateAdded)) {
              _tmpDateAdded = null;
            } else {
              _tmpDateAdded = _cursor.getString(_cursorIndexOfDateAdded);
            }
            final boolean _tmpIsVerified;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsVerified);
            _tmpIsVerified = _tmp_1 != 0;
            final String _tmpFileSize;
            if (_cursor.isNull(_cursorIndexOfFileSize)) {
              _tmpFileSize = null;
            } else {
              _tmpFileSize = _cursor.getString(_cursorIndexOfFileSize);
            }
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            final String _tmpMimeType;
            if (_cursor.isNull(_cursorIndexOfMimeType)) {
              _tmpMimeType = null;
            } else {
              _tmpMimeType = _cursor.getString(_cursorIndexOfMimeType);
            }
            final String _tmpDigilockerUri;
            if (_cursor.isNull(_cursorIndexOfDigilockerUri)) {
              _tmpDigilockerUri = null;
            } else {
              _tmpDigilockerUri = _cursor.getString(_cursorIndexOfDigilockerUri);
            }
            final String _tmpDigilockerDocType;
            if (_cursor.isNull(_cursorIndexOfDigilockerDocType)) {
              _tmpDigilockerDocType = null;
            } else {
              _tmpDigilockerDocType = _cursor.getString(_cursorIndexOfDigilockerDocType);
            }
            final UploadStatus _tmpUploadStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfUploadStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfUploadStatus);
            }
            _tmpUploadStatus = __converters.toUpload(_tmp_2);
            final long _tmpUploadedAt;
            _tmpUploadedAt = _cursor.getLong(_cursorIndexOfUploadedAt);
            _item = new VaultDocument(_tmpId,_tmpName,_tmpCategory,_tmpIssuer,_tmpDateAdded,_tmpIsVerified,_tmpFileSize,_tmpLocalPath,_tmpMimeType,_tmpDigilockerUri,_tmpDigilockerDocType,_tmpUploadStatus,_tmpUploadedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<VaultDocument>> getByCategory(final DocumentCategory cat) {
    final String _sql = "SELECT * FROM vault_documents WHERE category = ? ORDER BY dateAdded DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromDocCat(cat);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"vault_documents"}, false, new Callable<List<VaultDocument>>() {
      @Override
      @Nullable
      public List<VaultDocument> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIssuer = CursorUtil.getColumnIndexOrThrow(_cursor, "issuer");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfIsVerified = CursorUtil.getColumnIndexOrThrow(_cursor, "isVerified");
          final int _cursorIndexOfFileSize = CursorUtil.getColumnIndexOrThrow(_cursor, "fileSize");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final int _cursorIndexOfMimeType = CursorUtil.getColumnIndexOrThrow(_cursor, "mimeType");
          final int _cursorIndexOfDigilockerUri = CursorUtil.getColumnIndexOrThrow(_cursor, "digilockerUri");
          final int _cursorIndexOfDigilockerDocType = CursorUtil.getColumnIndexOrThrow(_cursor, "digilockerDocType");
          final int _cursorIndexOfUploadStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "uploadStatus");
          final int _cursorIndexOfUploadedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "uploadedAt");
          final List<VaultDocument> _result = new ArrayList<VaultDocument>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VaultDocument _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final DocumentCategory _tmpCategory;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toDocCat(_tmp_1);
            final String _tmpIssuer;
            if (_cursor.isNull(_cursorIndexOfIssuer)) {
              _tmpIssuer = null;
            } else {
              _tmpIssuer = _cursor.getString(_cursorIndexOfIssuer);
            }
            final String _tmpDateAdded;
            if (_cursor.isNull(_cursorIndexOfDateAdded)) {
              _tmpDateAdded = null;
            } else {
              _tmpDateAdded = _cursor.getString(_cursorIndexOfDateAdded);
            }
            final boolean _tmpIsVerified;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsVerified);
            _tmpIsVerified = _tmp_2 != 0;
            final String _tmpFileSize;
            if (_cursor.isNull(_cursorIndexOfFileSize)) {
              _tmpFileSize = null;
            } else {
              _tmpFileSize = _cursor.getString(_cursorIndexOfFileSize);
            }
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            final String _tmpMimeType;
            if (_cursor.isNull(_cursorIndexOfMimeType)) {
              _tmpMimeType = null;
            } else {
              _tmpMimeType = _cursor.getString(_cursorIndexOfMimeType);
            }
            final String _tmpDigilockerUri;
            if (_cursor.isNull(_cursorIndexOfDigilockerUri)) {
              _tmpDigilockerUri = null;
            } else {
              _tmpDigilockerUri = _cursor.getString(_cursorIndexOfDigilockerUri);
            }
            final String _tmpDigilockerDocType;
            if (_cursor.isNull(_cursorIndexOfDigilockerDocType)) {
              _tmpDigilockerDocType = null;
            } else {
              _tmpDigilockerDocType = _cursor.getString(_cursorIndexOfDigilockerDocType);
            }
            final UploadStatus _tmpUploadStatus;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfUploadStatus)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfUploadStatus);
            }
            _tmpUploadStatus = __converters.toUpload(_tmp_3);
            final long _tmpUploadedAt;
            _tmpUploadedAt = _cursor.getLong(_cursorIndexOfUploadedAt);
            _item = new VaultDocument(_tmpId,_tmpName,_tmpCategory,_tmpIssuer,_tmpDateAdded,_tmpIsVerified,_tmpFileSize,_tmpLocalPath,_tmpMimeType,_tmpDigilockerUri,_tmpDigilockerDocType,_tmpUploadStatus,_tmpUploadedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getById(final String id, final Continuation<? super VaultDocument> $completion) {
    final String _sql = "SELECT * FROM vault_documents WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VaultDocument>() {
      @Override
      @Nullable
      public VaultDocument call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIssuer = CursorUtil.getColumnIndexOrThrow(_cursor, "issuer");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfIsVerified = CursorUtil.getColumnIndexOrThrow(_cursor, "isVerified");
          final int _cursorIndexOfFileSize = CursorUtil.getColumnIndexOrThrow(_cursor, "fileSize");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final int _cursorIndexOfMimeType = CursorUtil.getColumnIndexOrThrow(_cursor, "mimeType");
          final int _cursorIndexOfDigilockerUri = CursorUtil.getColumnIndexOrThrow(_cursor, "digilockerUri");
          final int _cursorIndexOfDigilockerDocType = CursorUtil.getColumnIndexOrThrow(_cursor, "digilockerDocType");
          final int _cursorIndexOfUploadStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "uploadStatus");
          final int _cursorIndexOfUploadedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "uploadedAt");
          final VaultDocument _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final DocumentCategory _tmpCategory;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toDocCat(_tmp);
            final String _tmpIssuer;
            if (_cursor.isNull(_cursorIndexOfIssuer)) {
              _tmpIssuer = null;
            } else {
              _tmpIssuer = _cursor.getString(_cursorIndexOfIssuer);
            }
            final String _tmpDateAdded;
            if (_cursor.isNull(_cursorIndexOfDateAdded)) {
              _tmpDateAdded = null;
            } else {
              _tmpDateAdded = _cursor.getString(_cursorIndexOfDateAdded);
            }
            final boolean _tmpIsVerified;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsVerified);
            _tmpIsVerified = _tmp_1 != 0;
            final String _tmpFileSize;
            if (_cursor.isNull(_cursorIndexOfFileSize)) {
              _tmpFileSize = null;
            } else {
              _tmpFileSize = _cursor.getString(_cursorIndexOfFileSize);
            }
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            final String _tmpMimeType;
            if (_cursor.isNull(_cursorIndexOfMimeType)) {
              _tmpMimeType = null;
            } else {
              _tmpMimeType = _cursor.getString(_cursorIndexOfMimeType);
            }
            final String _tmpDigilockerUri;
            if (_cursor.isNull(_cursorIndexOfDigilockerUri)) {
              _tmpDigilockerUri = null;
            } else {
              _tmpDigilockerUri = _cursor.getString(_cursorIndexOfDigilockerUri);
            }
            final String _tmpDigilockerDocType;
            if (_cursor.isNull(_cursorIndexOfDigilockerDocType)) {
              _tmpDigilockerDocType = null;
            } else {
              _tmpDigilockerDocType = _cursor.getString(_cursorIndexOfDigilockerDocType);
            }
            final UploadStatus _tmpUploadStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfUploadStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfUploadStatus);
            }
            _tmpUploadStatus = __converters.toUpload(_tmp_2);
            final long _tmpUploadedAt;
            _tmpUploadedAt = _cursor.getLong(_cursorIndexOfUploadedAt);
            _result = new VaultDocument(_tmpId,_tmpName,_tmpCategory,_tmpIssuer,_tmpDateAdded,_tmpIsVerified,_tmpFileSize,_tmpLocalPath,_tmpMimeType,_tmpDigilockerUri,_tmpDigilockerDocType,_tmpUploadStatus,_tmpUploadedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object verifiedCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM vault_documents WHERE isVerified = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
