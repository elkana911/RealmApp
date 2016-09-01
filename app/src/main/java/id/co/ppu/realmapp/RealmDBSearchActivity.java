package id.co.ppu.realmapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.moonmonkeylabs.realmsearchview.RealmSearchAdapter;
import co.moonmonkeylabs.realmsearchview.RealmSearchView;
import co.moonmonkeylabs.realmsearchview.RealmSearchViewHolder;
import id.co.ppu.realmapp.pojo.MstSecUser;
import io.realm.Realm;

public class RealmDBSearchActivity extends AppCompatActivity {

    @BindView(R.id.search_view)
    RealmSearchView search_view;

    private Realm realm;

    private UserViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_dbsearch);

        ButterKnife.bind(this);

        this.realm = Realm.getDefaultInstance();

        loadDataUser();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.realm.close();
        this.realm = null;
    }


    private void loadDataUser() {

        adapter = new UserViewAdapter(this, this.realm, "fullName");

        search_view.setAdapter(adapter);
    }

    public class UserViewAdapter
            extends RealmSearchAdapter<MstSecUser, UserViewAdapter.DataViewHolder> {

        public UserViewAdapter(@NonNull Context context, @NonNull Realm realm, @NonNull String filterKey) {
            super(context, realm, filterKey);
        }

        @Override
        public DataViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
            View v = inflater.inflate(R.layout.row_user, viewGroup, false);
            return new DataViewHolder((FrameLayout) v);
        }

        @Override
        public void onBindRealmViewHolder(DataViewHolder dataViewHolder, int position) {
            final MstSecUser user = realmResults.get(position);

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

        public class DataViewHolder extends RealmSearchViewHolder {

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
