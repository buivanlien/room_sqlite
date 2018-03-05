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

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.vonad.roomsqlitesample.model.TableFirstModel;
import org.vonad.roomsqlitesample.model.TableFourModel;
import org.vonad.roomsqlitesample.model.TableThreeModel;
import org.vonad.roomsqlitesample.model.TableTwoModel;

import java.util.List;

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */

public class MyRepository {

    private IMyDao mMyDao;
    private LiveData<List<TableFirstModel>> mAllTableFirstLiveData;
    private LiveData<List<TableTwoModel>> mAllTableTwoLiveData;
    private LiveData<List<TableThreeModel>> mAllTableThreeLiveData;
    private LiveData<List<TableFourModel>> mAllTableFourLiveData;

    // Note that in order to unit test the MyRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    MyRepository(Application application) {
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        mMyDao = db.myDao();
//        mITestMyDao= db.testMyDao();
        mAllTableFirstLiveData = mMyDao.getAllTableOne();
        mAllTableTwoLiveData = mMyDao.getAllTableTwo();
        mAllTableThreeLiveData = mMyDao.getAllTableThree();
        mAllTableFourLiveData = mMyDao.getAlllTableFour();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.


    public LiveData<List<TableFirstModel>> getmAllTableFirstLiveData() {
        return mAllTableFirstLiveData;
    }

    public LiveData<List<TableTwoModel>> getmAllTableTwoLiveData() {
        return mAllTableTwoLiveData;
    }

    public LiveData<List<TableThreeModel>> getmAllTableThreeLiveData() {
        return mAllTableThreeLiveData;
    }

    public LiveData<List<TableFourModel>> getmAllTableFourLiveData() {
        return mAllTableFourLiveData;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    void insert(final TableThreeModel word) {
        new insertDataTableThreeAsyncTask(mMyDao).execute(word);
    }

    void insert(final TableFourModel word) {
        new insertDataTableFourAsyncTask(mMyDao).execute(word);
    }

    void insert(final TableFirstModel word) {
        new insertDataTableOneAsyncTask(mMyDao).execute(word);
    }

    void insert(final TableTwoModel word) {
        new insertDataTableTwoAsyncTask(mMyDao).execute(word);
    }

    private static class insertDataTableOneAsyncTask
            extends AsyncTask<TableFirstModel, Void, Void> {

        private IMyDao mAsyncTaskDao;

        insertDataTableOneAsyncTask(IMyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TableFirstModel... params) {
            mAsyncTaskDao.insertTableFirst(params[0]);
            return null;
        }
    }

    private static class insertDataTableTwoAsyncTask
            extends AsyncTask<TableTwoModel, Void, Void> {

        private IMyDao mAsyncTaskDao;

        insertDataTableTwoAsyncTask(IMyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TableTwoModel... params) {
            mAsyncTaskDao.insertTableTwo(params[0]);
            return null;
        }
    }

    private static class insertDataTableThreeAsyncTask
            extends AsyncTask<TableThreeModel, Void, Void> {

        private IMyDao mAsyncTaskDao;

        insertDataTableThreeAsyncTask(IMyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TableThreeModel... params) {
            mAsyncTaskDao.insertTableThree(params[0]);
            return null;
        }
    }

    private static class insertDataTableFourAsyncTask
            extends AsyncTask<TableFourModel, Void, Void> {

        private IMyDao mAsyncTaskDao;

        insertDataTableFourAsyncTask(IMyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TableFourModel... params) {
            mAsyncTaskDao.insertTableFour(params[0]);
            return null;
        }
    }
}
