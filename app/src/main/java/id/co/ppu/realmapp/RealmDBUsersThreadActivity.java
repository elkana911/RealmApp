package id.co.ppu.realmapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.ppu.realmapp.pojo.MstSecUser;
import id.co.ppu.realmapp.util.Utility;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmDBUsersThreadActivity extends AppCompatActivity {

    @BindView(R.id.tvResult)
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_dbusers_thread);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnGetUser)
    public void getUser() {
        new GetData(this).execute();
    }

    class GetData extends AsyncTask<Void, Void, List<MstSecUser>> {
        private Context ctx;
        public GetData(Context ctx) {
            this.ctx = ctx;

        }
        @Override
        protected List<MstSecUser> doInBackground(Void... params) {

            Realm realm = Realm.getDefaultInstance();
            try {
                RealmResults<MstSecUser> all = realm.where(MstSecUser.class).contains("fullName", "motor", Case.INSENSITIVE).findAll();
                List<MstSecUser> list = new ArrayList<>();

                for (int i = 0; i < all.size(); i++) {
                    MstSecUser u = realm.copyFromRealm(all.get(i));

                    list.add(u);
                }
/*                for (User user: all ) {
                    list.add(user);
                }*/
                return list;
            }finally {
                realm.close();
            }
        }

        @Override
        protected void onPostExecute(List<MstSecUser> list) {
            super.onPostExecute(list);

            StringBuffer sb = new StringBuffer();
            for (MstSecUser user : list) {
                sb.append(user.getEmailAddr()).append("\n");
            }

            tvResult.setText(sb.toString());

            Utility.showDialog(ctx, "Judul", "Done");
        }
    }

}
