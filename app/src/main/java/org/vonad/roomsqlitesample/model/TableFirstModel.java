package org.vonad.roomsqlitesample.model;

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

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * A basic class representing an entity that is a row in a one-column database table.
 *
 * @ Entity - You must annotate the class as an entity and supply a table name if not class name.
 * @ PrimaryKey - You must identify the primary key.
 * @ ColumnInfo - You must supply the column name if it is different from the variable name.
 * <p>
 * See the documentation for the full rich set of annotations.
 * https://developer.android.com/topic/libraries/architecture/room.html
 */

@Entity(tableName = "table_one")
public class TableFirstModel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String nameFirst="";
    @ColumnInfo(name = "last_update")
    private String lastUpdate = "";
    @ColumnInfo(name = "description")
    private String description="";

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(@NonNull final String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(final String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public TableFirstModel(@NonNull final String nameFirst,
                           final String lastUpdate,
                           final String description) {
        this.nameFirst = nameFirst;
        this.lastUpdate = lastUpdate;
        this.description = description;
    }
}