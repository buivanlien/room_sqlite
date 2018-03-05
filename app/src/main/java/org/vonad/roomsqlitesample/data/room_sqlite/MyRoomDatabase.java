package org.vonad.roomsqlitesample.data.room_sqlite;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import org.vonad.roomsqlitesample.model.TableFirstModel;
import org.vonad.roomsqlitesample.model.TableFourModel;
import org.vonad.roomsqlitesample.model.TableThreeModel;
import org.vonad.roomsqlitesample.model.TableTwoModel;

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */

@Database(entities = {TableFirstModel.class,
                      TableTwoModel.class,
                      TableThreeModel.class,
                      TableFourModel.class}, version = 6)
// list entities = { Repo.class, User.class } add more class you must create dao, and version upgrade
abstract class MyRoomDatabase
        extends RoomDatabase {

    abstract IMyDao myDao();

    //    abstract  ITestMyDao testMyDao();
    private static MyRoomDatabase INSTANCE;

    static MyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                                    MyRoomDatabase.class,
                                                    "database")
                                   // Wipes and rebuilds instead of migrating if no Migration object.
                                   // Migration is not part of this codelab.
                                   .addMigrations(new Migration(1,
                                                                2) {
                                       @Override
                                       public void migrate(@NonNull
                                                           final SupportSQLiteDatabase database) {
                                           database.execSQL("ALTER TABLE " + TableFirstModel.class.getAnnotation(Entity.class)
                                                                                                  .tableName()
                                                                    + " ADD COLUMN last_update TEXT");
                                       }
                                   })
                                   .addMigrations(new Migration(2,
                                                                3) {
                                       @Override
                                       public void migrate(@NonNull
                                                           final SupportSQLiteDatabase database) {
                                           database.execSQL("ALTER TABLE " + TableFirstModel.class.getAnnotation(Entity.class)
                                                                                                  .tableName()
                                                                    + " ADD COLUMN description TEXT");
                                       }
                                   })
                                   .addMigrations(new Migration(3,
                                                                4) {
                                       @Override
                                       public void migrate(@NonNull
                                                           final SupportSQLiteDatabase database) {
                                           database.execSQL(
                                                   "CREATE TABLE " +
                                                           TableTwoModel.class.getAnnotation(Entity.class)
                                                                              .tableName() + " (name TEXT NOT NULL,"
                                                           + " PRIMARY KEY(name))");
                                       }
                                   })
                                   .addMigrations(new Migration(4,
                                                                5) {
                                       @Override
                                       public void migrate(@NonNull
                                                           final SupportSQLiteDatabase database) {
                                           database.execSQL(
                                                   "CREATE TABLE " +
                                                           TableThreeModel.class.getAnnotation(Entity.class)
                                                                                .tableName() + " (name TEXT NOT NULL,"
                                                           + " PRIMARY KEY(name))");
                                       }
                                   })
                                   .addMigrations(new Migration(5,
                                                                6) {
                                       @Override
                                       public void migrate(@NonNull
                                                           final SupportSQLiteDatabase database) {
                                           database.execSQL(
                                                   "CREATE TABLE " +
                                                           TableFourModel.class.getAnnotation(Entity.class)
                                                                               .tableName() + " (name TEXT NOT NULL,"
                                                           + " PRIMARY KEY(name))");
                                       }
                                   })
                                   .fallbackToDestructiveMigration()
                                   .build();
                }
            }
        }
        return INSTANCE;
    }


}
