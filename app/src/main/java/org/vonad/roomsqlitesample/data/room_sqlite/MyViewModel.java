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
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import org.vonad.roomsqlitesample.model.TableThreeModel;
import org.vonad.roomsqlitesample.model.TableTwoModel;
import org.vonad.roomsqlitesample.model.TableFourModel;
import org.vonad.roomsqlitesample.model.TableFirstModel;

import java.util.List;

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

public class MyViewModel
        extends AndroidViewModel {

    private MyRepository mRepository;
    // Using LiveData and caching what getAllTableOne returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    // uses:
    /*
    *    mWordViewModel.getAllWords().observe(this, new Observer<List<TableFirstModel>>() {
            @Override
            public void onChanged(@Nullable final List<TableFirstModel> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });*/
    private LiveData<List<TableFirstModel>> mAllTableFirstLiveData;
    private LiveData<List<TableTwoModel>> mAllTableTwoLiveData;
    private LiveData<List<TableThreeModel>> mAllTableThreeLiveData;
    private LiveData<List<TableFourModel>> mAllTableFourLiveData;
    public MyViewModel(Application application) {
        super(application);
        mRepository = new MyRepository(application);
        mAllTableFirstLiveData = mRepository.getmAllTableFirstLiveData();
        mAllTableTwoLiveData = mRepository.getmAllTableTwoLiveData();
        mAllTableThreeLiveData = mRepository.getmAllTableThreeLiveData();
        mAllTableFourLiveData = mRepository.getmAllTableFourLiveData();
    }

    public MyRepository getmRepository() {
        return mRepository;
    }

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
    public void insertData(TableTwoModel model){
        mRepository.insert(model);
    }
    public void insertData(TableFirstModel model){
        mRepository.insert(model);
    }public void insertData(TableThreeModel model){
        mRepository.insert(model);
    }public void insertData(TableFourModel model){
        mRepository.insert(model);
    }
}