package org.vonad.roomsqlitesample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.vonad.roomsqlitesample.data.room_sqlite.MyViewModel;
import org.vonad.roomsqlitesample.model.TableFirstModel;
import org.vonad.roomsqlitesample.model.TableFourModel;
import org.vonad.roomsqlitesample.model.TableThreeModel;
import org.vonad.roomsqlitesample.model.TableTwoModel;

import java.util.List;

public class TestActivity
        extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        MyViewModel myViewModel = ViewModelProviders.of(this)
                                                    .get(MyViewModel.class);
        myViewModel.insertData(new TableFirstModel("nad ",
                                                   "7-3-2018",
                                                   "ok"));
        myViewModel.insertData(new TableTwoModel("two table"));
        myViewModel.insertData(new TableThreeModel("three table"));
        myViewModel.insertData(new TableFourModel("four table"));
        myViewModel.getmAllTableFourLiveData()
                   .observe(this,
                            new Observer<List<TableFourModel>>() {
                                @Override
                                public void onChanged(@Nullable
                                                      final List<TableFourModel> tableFourModels) {
                                    System.out.println(">>> test:"+tableFourModels.size());
                                }
                            });
        myViewModel.getmAllTableFirstLiveData()
                   .observe(this,
                            new Observer<List<TableFirstModel>>() {
                                @Override
                                public void onChanged(@Nullable
                                                      final List<TableFirstModel> tableFirstModels) {
                                    if (tableFirstModels != null) {
                                        for (int i = 0; i < tableFirstModels.size(); i++) {
                                            System.out.println(">>> test:"+tableFirstModels.get(i).getNameFirst());
                                        }
                                    }
                                }
                            });
        myViewModel.getmAllTableTwoLiveData()
                   .observe(this,
                            new Observer<List<TableTwoModel>>() {
                                @Override
                                public void onChanged(@Nullable
                                                      final List<TableTwoModel> tableTwoModels) {
                                    System.out.println(">>> test:"+tableTwoModels.size());
                                }
                            });
        myViewModel.getmAllTableThreeLiveData()
                   .observe(this,
                            new Observer<List<TableThreeModel>>() {
                                @Override
                                public void onChanged(@Nullable
                                                      final List<TableThreeModel> geWords) {
                                    System.out.println(">>> test:"+geWords.size());

                                }
                            });
    }
}
