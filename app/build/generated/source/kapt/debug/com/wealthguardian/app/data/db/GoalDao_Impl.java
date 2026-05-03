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
import com.wealthguardian.app.data.model.Goal;
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
public final class GoalDao_Impl implements GoalDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Goal> __insertionAdapterOfGoal;

  private final EntityDeletionOrUpdateAdapter<Goal> __deletionAdapterOfGoal;

  private final EntityDeletionOrUpdateAdapter<Goal> __updateAdapterOfGoal;

  private final SharedSQLiteStatement __preparedStmtOfAddLumpsum;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSip;

  public GoalDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGoal = new EntityInsertionAdapter<Goal>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `goals` (`id`,`name`,`emoji`,`targetAmount`,`currentAmount`,`category`,`sipAmount`,`sipDay`,`notes`,`createdAt`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Goal entity) {
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
        if (entity.getEmoji() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEmoji());
        }
        statement.bindDouble(4, entity.getTargetAmount());
        statement.bindDouble(5, entity.getCurrentAmount());
        if (entity.getCategory() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCategory());
        }
        statement.bindDouble(7, entity.getSipAmount());
        statement.bindLong(8, entity.getSipDay());
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        statement.bindLong(10, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfGoal = new EntityDeletionOrUpdateAdapter<Goal>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `goals` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Goal entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfGoal = new EntityDeletionOrUpdateAdapter<Goal>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `goals` SET `id` = ?,`name` = ?,`emoji` = ?,`targetAmount` = ?,`currentAmount` = ?,`category` = ?,`sipAmount` = ?,`sipDay` = ?,`notes` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Goal entity) {
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
        if (entity.getEmoji() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEmoji());
        }
        statement.bindDouble(4, entity.getTargetAmount());
        statement.bindDouble(5, entity.getCurrentAmount());
        if (entity.getCategory() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCategory());
        }
        statement.bindDouble(7, entity.getSipAmount());
        statement.bindLong(8, entity.getSipDay());
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        statement.bindLong(10, entity.getCreatedAt());
        if (entity.getId() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getId());
        }
      }
    };
    this.__preparedStmtOfAddLumpsum = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE goals SET currentAmount = currentAmount + ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSip = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE goals SET sipAmount = ?, sipDay = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Goal goal, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfGoal.insert(goal);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Goal goal, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfGoal.handle(goal);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Goal goal, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfGoal.handle(goal);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object addLumpsum(final String id, final double amount,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfAddLumpsum.acquire();
        int _argIndex = 1;
        _stmt.bindDouble(_argIndex, amount);
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
          __preparedStmtOfAddLumpsum.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSip(final String id, final double amount, final int day,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSip.acquire();
        int _argIndex = 1;
        _stmt.bindDouble(_argIndex, amount);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, day);
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
          __preparedStmtOfUpdateSip.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Goal>> getAllLive() {
    final String _sql = "SELECT * FROM goals ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"goals"}, false, new Callable<List<Goal>>() {
      @Override
      @Nullable
      public List<Goal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSipAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "sipAmount");
          final int _cursorIndexOfSipDay = CursorUtil.getColumnIndexOrThrow(_cursor, "sipDay");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Goal> _result = new ArrayList<Goal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Goal _item;
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
            final String _tmpEmoji;
            if (_cursor.isNull(_cursorIndexOfEmoji)) {
              _tmpEmoji = null;
            } else {
              _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            }
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final double _tmpSipAmount;
            _tmpSipAmount = _cursor.getDouble(_cursorIndexOfSipAmount);
            final int _tmpSipDay;
            _tmpSipDay = _cursor.getInt(_cursorIndexOfSipDay);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Goal(_tmpId,_tmpName,_tmpEmoji,_tmpTargetAmount,_tmpCurrentAmount,_tmpCategory,_tmpSipAmount,_tmpSipDay,_tmpNotes,_tmpCreatedAt);
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
  public Object getAll(final Continuation<? super List<Goal>> $completion) {
    final String _sql = "SELECT * FROM goals ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Goal>>() {
      @Override
      @NonNull
      public List<Goal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSipAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "sipAmount");
          final int _cursorIndexOfSipDay = CursorUtil.getColumnIndexOrThrow(_cursor, "sipDay");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Goal> _result = new ArrayList<Goal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Goal _item;
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
            final String _tmpEmoji;
            if (_cursor.isNull(_cursorIndexOfEmoji)) {
              _tmpEmoji = null;
            } else {
              _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            }
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final double _tmpSipAmount;
            _tmpSipAmount = _cursor.getDouble(_cursorIndexOfSipAmount);
            final int _tmpSipDay;
            _tmpSipDay = _cursor.getInt(_cursorIndexOfSipDay);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Goal(_tmpId,_tmpName,_tmpEmoji,_tmpTargetAmount,_tmpCurrentAmount,_tmpCategory,_tmpSipAmount,_tmpSipDay,_tmpNotes,_tmpCreatedAt);
            _result.add(_item);
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
  public Object getById(final String id, final Continuation<? super Goal> $completion) {
    final String _sql = "SELECT * FROM goals WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Goal>() {
      @Override
      @Nullable
      public Goal call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSipAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "sipAmount");
          final int _cursorIndexOfSipDay = CursorUtil.getColumnIndexOrThrow(_cursor, "sipDay");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final Goal _result;
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
            final String _tmpEmoji;
            if (_cursor.isNull(_cursorIndexOfEmoji)) {
              _tmpEmoji = null;
            } else {
              _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            }
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final double _tmpSipAmount;
            _tmpSipAmount = _cursor.getDouble(_cursorIndexOfSipAmount);
            final int _tmpSipDay;
            _tmpSipDay = _cursor.getInt(_cursorIndexOfSipDay);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new Goal(_tmpId,_tmpName,_tmpEmoji,_tmpTargetAmount,_tmpCurrentAmount,_tmpCategory,_tmpSipAmount,_tmpSipDay,_tmpNotes,_tmpCreatedAt);
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
