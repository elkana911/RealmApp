package id.co.ppu.realmapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import id.co.ppu.realmapp.pojo.User;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;
import io.realm.Sort;

public class RealmDBUsersActivity extends AppCompatActivity {

    @BindView(R.id.realm_recycler_view)
    RealmRecyclerView realmRecyclerView;

    private Realm realm;

    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_db);

        ButterKnife.bind(this);

        realmRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        realm = Realm.getDefaultInstance();

        loadDataUser();
    }

    public void loadDataUser() {
        RealmResults<User> all = realm.where(User.class).findAllSorted("fullName", Sort.ASCENDING);
//        RealmResults<User> all = realm.where(User.class).findAllSortedAsync("fullName", Sort.ASCENDING);  //sepertinya buggy, krn ada loading progress nongol ga ilang2

        all.addChangeListener(new RealmChangeListener<RealmResults<User>>() {
            @Override
            public void onChange(RealmResults<User> element) {
                Log.e("eric.onChange", "something=" + element.size());
            }
        });

        boolean automaticUpdate = true; // dont set to false
        boolean animateIdType = true;
        String animateExtraColumnName = "emailAddr";
        mAdapter = new UserAdapter(
                getBaseContext(),
                all,
                automaticUpdate,
                animateIdType,
                animateExtraColumnName
                );

        realmRecyclerView.setAdapter(mAdapter);

        realmRecyclerView.setOnLoadMoreListener(new RealmRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore(Object o) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();

        realm = null;
    }

    public static class UserAdapter extends RealmBasedRecyclerViewAdapter<User, UserAdapter.DataViewHolder> {

        public UserAdapter(Context context,
                           RealmResults<User> realmResults,
                           boolean automaticUpdate,
                           boolean animateIdType,
                           String animateExtraColumnName) {
//            super(context, realmResults, automaticUpdate, animateResults, addSectionHeaders, headerColumnName, animateExtraColumnName);
            super(context, realmResults, automaticUpdate, animateIdType, animateExtraColumnName);
        }

        @Override
        public DataViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
            View v = inflater.inflate(R.layout.row_user, viewGroup, false);
            return new DataViewHolder((FrameLayout) v);
        }

        @Override
        public void onBindRealmViewHolder(DataViewHolder dataViewHolder, int position) {
            final User user = realmResults.get(position);

            TextView v = dataViewHolder.tvUser;
            String f = user.getFullName();
            v.setText(f);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "You click " + user.getFullName(), Toast.LENGTH_SHORT).show();
                }
            });


        }

        @Override
        public void onItemSwipedDismiss(int position) {
            final User user = realmResults.get(position);
            Toast.makeText(getContext(), "You swipe " + user.getFullName(), Toast.LENGTH_SHORT).show();
            super.onItemSwipedDismiss(position);    //harus dipanggil belakangan spy geser layoutnya, hati2 bakal hapus data jika app:rrvSwipeToDelete="true"
        }

        public class DataViewHolder extends RealmViewHolder{

            public FrameLayout container;

            @BindView(R.id.tvUser)
            TextView tvUser;

            public DataViewHolder(FrameLayout container) {
                super(container);

                this.container = container;
//                this.tvUser = (TextView) container.findViewById(R.id.tvUser);
//                ButterKnife.bind(container); not working
                ButterKnife.bind(this, container); //not working
            }

            //            @OnClick(R.)
            public void onClickItem() {
//                _listener.onClickedItem(position);
            }
        }
    }
}
