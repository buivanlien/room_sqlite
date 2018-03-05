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

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import org.vonad.roomsqlitesample.model.TableFirstModel;
import org.vonad.roomsqlitesample.model.TableFourModel;
import org.vonad.roomsqlitesample.model.TableThreeModel;
import org.vonad.roomsqlitesample.model.TableTwoModel;

import java.util.List;

/**
 * The Room Magic is in this file, where you map a Java method call to an SQL query.
 * <p>
 * When you are using complex data types, such as Date, you have to also supply type converters.
 * To keep this example basic, no types that require type converters are used.
 * See the documentation at
 * https://developer.android.com/topic/libraries/architecture/room.html#type-converters
 */

@Dao
public interface IMyDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from table_one ORDER BY name ASC")
    LiveData<List<TableFirstModel>> getAllTableOne();

    // We do not need a conflict strategy, because the tableFirstModel is our primary key, and you cannot
    // add two items with the same primary key to the database. If the table has more than one
    // column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTableFirst(TableFirstModel tableFirstModel);

    @Query("DELETE FROM table_one")
    void deleteAllTableOne();

    // table two
    @Query("SELECT * from table_two ORDER BY name ASC")
    LiveData<List<TableTwoModel>> getAllTableTwo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTableTwo(TableTwoModel word);

    @Query("DELETE FROM table_two")
    void deleteAllTableTwo();

    // table three.
    @Query("SELECT * from table_three ORDER BY name ASC")
    LiveData<List<TableThreeModel>> getAllTableThree();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTableThree(TableThreeModel word);

    @Query("DELETE FROM table_three")
    void deleteAllTableThree();

    // table four
    @Query("SELECT * from table_four ORDER BY name ASC")
    LiveData<List<TableFourModel>> getAlllTableFour();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTableFour(TableFourModel word);

    @Query("DELETE FROM table_four")
    void deleteAllTableFour();
}
