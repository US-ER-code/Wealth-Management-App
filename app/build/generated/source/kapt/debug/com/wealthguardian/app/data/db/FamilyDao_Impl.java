package com.wealthguardian.app.data.db;

import android.database.Cursor;
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
import com.wealthguardian.app.data.model.FamilyMember;
import com.wealthguardian.app.data.model.FamilyRole;
import java.lang.Class;
import java.lang.Exception;
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
public final class FamilyDao_Impl implements FamilyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FamilyMember> __insertionAdapterOfFamilyMember;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<FamilyMember> __deletionAdapterOfFamilyMember;

  private final EntityDeletionOrUpdateAdapter<FamilyMember> __updateAdapterOfFamilyMember;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfUpdateRole;

  public FamilyDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFamilyMember = new EntityInsertionAdapter<FamilyMember>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `family_members` (`id`,`name`,`email`,`role`,`avatarInitials`,`addedDate`,`phone`,`allowedSections`,`inviteAccepted`,`inviteSentAt`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FamilyMember entity) {
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
        if (entity.getEmail() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEmail());
        }
        final String _tmp = __converters.fromRole(entity.getRole());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        if (entity.getAvatarInitials() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAvatarInitials());
        }
        if (entity.getAddedDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getAddedDate());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getPhone());
        }
        if (entity.getAllowedSections() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getAllowedSections());
        }
        final int _tmp_1 = entity.getInviteAccepted() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        statement.bindLong(10, entity.getInviteSentAt());
      }
    };
    this.__deletionAdapterOfFamilyMember = new EntityDeletionOrUpdateAdapter<FamilyMember>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `family_members` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FamilyMember entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfFamilyMember = new EntityDeletionOrUpdateAdapter<FamilyMember>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `family_members` SET `id` = ?,`name` = ?,`email` = ?,`role` = ?,`avatarInitials` = ?,`addedDate` = ?,`phone` = ?,`allowedSections` = ?,`inviteAccepted` = ?,`inviteSentAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FamilyMember entity) {
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
        if (entity.getEmail() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEmail());
        }
        final String _tmp = __converters.fromRole(entity.getRole());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        if (entity.getAvatarInitials() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAvatarInitials());
        }
        if (entity.getAddedDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getAddedDate());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getPhone());
        }
        if (entity.getAllowedSections() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getAllowedSections());
        }
        final int _tmp_1 = entity.getInviteAccepted() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        statement.bindLong(10, entity.getInviteSentAt());
        if (entity.getId() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM family_members WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateRole = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE family_members SET role = ?, allowedSections = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final FamilyMember member, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFamilyMember.insert(member);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final FamilyMember member, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFamilyMember.handle(member);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final FamilyMember member, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFamilyMember.handle(member);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
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
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateRole(final String id, final FamilyRole role, final String sections,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateRole.acquire();
        int _argIndex = 1;
        final String _tmp = __converters.fromRole(role);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp);
        }
        _argIndex = 2;
        if (sections == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, sections);
        }
        _argIndex = 3;
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
          __preparedStmtOfUpdateRole.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<FamilyMember>> getAllLive() {
    final String _sql = "SELECT * FROM family_members ORDER BY inviteSentAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"family_members"}, false, new Callable<List<FamilyMember>>() {
      @Override
      @Nullable
      public List<FamilyMember> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfAvatarInitials = CursorUtil.getColumnIndexOrThrow(_cursor, "avatarInitials");
          final int _cursorIndexOfAddedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "addedDate");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfAllowedSections = CursorUtil.getColumnIndexOrThrow(_cursor, "allowedSections");
          final int _cursorIndexOfInviteAccepted = CursorUtil.getColumnIndexOrThrow(_cursor, "inviteAccepted");
          final int _cursorIndexOfInviteSentAt = CursorUtil.getColumnIndexOrThrow(_cursor, "inviteSentAt");
          final List<FamilyMember> _result = new ArrayList<FamilyMember>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FamilyMember _item;
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
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final FamilyRole _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            _tmpRole = __converters.toRole(_tmp);
            final String _tmpAvatarInitials;
            if (_cursor.isNull(_cursorIndexOfAvatarInitials)) {
              _tmpAvatarInitials = null;
            } else {
              _tmpAvatarInitials = _cursor.getString(_cursorIndexOfAvatarInitials);
            }
            final String _tmpAddedDate;
            if (_cursor.isNull(_cursorIndexOfAddedDate)) {
              _tmpAddedDate = null;
            } else {
              _tmpAddedDate = _cursor.getString(_cursorIndexOfAddedDate);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpAllowedSections;
            if (_cursor.isNull(_cursorIndexOfAllowedSections)) {
              _tmpAllowedSections = null;
            } else {
              _tmpAllowedSections = _cursor.getString(_cursorIndexOfAllowedSections);
            }
            final boolean _tmpInviteAccepted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfInviteAccepted);
            _tmpInviteAccepted = _tmp_1 != 0;
            final long _tmpInviteSentAt;
            _tmpInviteSentAt = _cursor.getLong(_cursorIndexOfInviteSentAt);
            _item = new FamilyMember(_tmpId,_tmpName,_tmpEmail,_tmpRole,_tmpAvatarInitials,_tmpAddedDate,_tmpPhone,_tmpAllowedSections,_tmpInviteAccepted,_tmpInviteSentAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
