package id.co.ppu.realmapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.ppu.realmapp.pojo.MstSecUser;
import id.co.ppu.realmapp.pojo.MstUser;
import id.co.ppu.realmapp.pojo.TrxCollectAddr;
import id.co.ppu.realmapp.pojo.TrxLDVDetails;
import id.co.ppu.realmapp.pojo.TrxLDVHeader;
import id.co.ppu.realmapp.pojo.UserData;
import id.co.ppu.realmapp.rest.ApiInterface;
import id.co.ppu.realmapp.rest.ServiceGenerator;
import id.co.ppu.realmapp.rest.request.RequestLKP;
import id.co.ppu.realmapp.rest.request.RequestLogin;
import id.co.ppu.realmapp.rest.response.ResponseGetLKP;
import id.co.ppu.realmapp.rest.response.ResponseGetUsers;
import id.co.ppu.realmapp.rest.response.ResponseLogin;
import id.co.ppu.realmapp.util.StopWatch;
import id.co.ppu.realmapp.util.Utility;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Realm realm;
    private RealmChangeListener realmListener;

    @BindView(R.id.tvGetLogin)
    TextView tvGetLogin;

    @BindView(R.id.tvGetUsers)
    TextView tvGetUsers;

    @BindView(R.id.tvGetLKP)
    TextView tvGetLKP;

    @BindView(R.id.btnGetUsers)
    Button btnGetUsers;

    @BindView(R.id.btnPlayRealm)
    Button btnPlayRealm;

    @BindView(R.id.btnDisplayWithSearch)
    Button btnDisplayWithSearch;

    @BindView(R.id.etIP)EditText etIP;
    @BindView(R.id.etPort)EditText etPort;
    @BindView(R.id.spServer)Spinner spServers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        spServers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 1:
                        etIP.setText("203.128.92.77");
                        etPort.setText("8080");
                        break;
                    default:
                        etIP.setText("192.168.0.6");
                        etPort.setText("8090");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        realm = Realm.getDefaultInstance();

        realmListener = new RealmChangeListener() {
            @Override
            public void onChange(Object element) {
                Log.e("eric.realmChange", "change " + element);
            }
        };
        realm.addChangeListener(realmListener);


        long count = realm.where(MstSecUser.class).count();
        tvGetUsers.setText("Existing Users: " + count + " users");
        btnPlayRealm.setText("Display " + count + " users");
        btnDisplayWithSearch.setText("Display " + count + " users and search");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Remove the listener.
        realm.removeChangeListener(realmListener);
        // Close the Realm instance.
        realm.close();
    }

    private HttpUrl buildUrl() {
        HttpUrl.Builder url = new HttpUrl.Builder()
                .scheme("http")
                .host(etIP.getText().toString())
                .port(Integer.parseInt(etPort.getText().toString()))
                ;

        String root = spServers.getSelectedItem().toString();

        if (!root.toLowerCase().startsWith("local")) {
            url.addPathSegment(root);
        }

        return url.build();
    }

    @OnClick(R.id.btnClear)
    public void onClear() {
        tvGetUsers.setText(null);
        tvGetLogin.setText(null);
        tvGetLKP.setText(null);
    }

    @OnClick(R.id.btnPlayRealm)
    public void onStartActivityPlayRealmDB() {
        startActivity(new Intent(this, RealmDBUsersActivity.class));
    }

    @OnClick(R.id.btnDisplayWithSearch)
    public void onStartActivityDisplayWithSearch() {
        startActivity(new Intent(this, RealmDBSearchActivity.class));
    }

    @OnClick(R.id.btnDisplayThread)
    public void onStartActivityDisplayInThread() {
        startActivity(new Intent(this, RealmDBUsersThreadActivity.class));
    }

    @OnClick(R.id.btnEditFormUser)
    public void onStartActivityEditFormUser() {

        MstSecUser first = realm.where(MstSecUser.class).equalTo("emailAddr", "PMP02@radanafinance.co.id").findFirst();

        if (first == null) {
            Utility.showDialog(this, "Error", "no data");
            return;
        }

        Intent intent = new Intent(this, EditUserActivity.class);
        intent.putExtra("userEmail", first.getEmailAddr());
        startActivity(intent);
    }

    @OnClick(R.id.btnGetLogin)
    public void onGetLogin(){

        ApiInterface fastService =
                ServiceGenerator.createService(ApiInterface.class, buildUrl());

        RequestLogin request = new RequestLogin();
        request.setId("21150164");
        request.setPwd("23011987");

        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Get Login...");
        mProgressDialog.show();

        final StopWatch sw = StopWatch.AutoStart();

        Call<ResponseLogin> call = fastService.login(request);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if (response.isSuccessful()) {
                    ResponseLogin respLogin = response.body();
                    Log.e("eric.onResponse", respLogin.toString());

                    if (respLogin.getError() != null) {
                        Utility.showDialog(MainActivity.this, "Error (" + respLogin.getError().getErrorCode() + ")", respLogin.getError().getErrorDesc());

                    } else {
                        Utility.saveObjPreference(getApplicationContext(), "user", respLogin.getData());

                        tvGetLogin.setText(Utility.getObjPreference(getApplicationContext(), "user", UserData.class).toString() + "\n\n" + sw.stopAndGetAsString());
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Utility.toast(getApplicationContext(), "Login Success");
                    }
                } else {
                    int statusCode = response.code();

                    // handle request errors yourself
                    ResponseBody errorBody = response.errorBody();

                    try {
                        Utility.showDialog(MainActivity.this, "Server Problem (" + statusCode + ")", errorBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

                Log.e("eric.onFailure", t.getMessage(), t);

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Utility.showDialog(MainActivity.this, "Server Problem", t.getMessage());
            }
        });

//
//        Realm realm = Realm.getDefaultInstance();
//        try {
//            // ... Use the Realm instance ...
//        } finally {
//            realm.close();
//        }

    }

    @OnClick(R.id.btnGetUsers)
    public void onGetUsers(){

        ApiInterface fastService =
                ServiceGenerator.createService(ApiInterface.class, buildUrl());

        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Get Users...");
        mProgressDialog.show();

        final StopWatch swNetwork = StopWatch.AutoStart();

        Call<ResponseGetUsers> call = fastService.getUsers();
        call.enqueue(new Callback<ResponseGetUsers>() {
            @Override
            public void onResponse(Call<ResponseGetUsers> call, Response<ResponseGetUsers> response) {

                if (response.isSuccessful()) {
                    final ResponseGetUsers respGetUsers = response.body();
                    Log.e("eric.onResponse", respGetUsers.toString());

                    if (respGetUsers.getError() != null) {
                        Utility.showDialog(MainActivity.this, "Error (" + respGetUsers.getError().getErrorCode() + ")", respGetUsers.getError().getErrorDesc());

                    } else {
                        final String swNetworkStr = swNetwork.stopAndGetAsString();

                        final StopWatch swInsert = new StopWatch();
//                        cancelAsync();
                        // save db here
                        realm.executeTransactionAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm bgRealm) {
                                // wipe existing tables?
                                long count = bgRealm.where(MstSecUser.class).count();
                                if (count > 0) {
                                    bgRealm.delete(MstSecUser.class);
                                }
                                count = bgRealm.where(MstUser.class).count();
                                if (count > 0) {
                                    bgRealm.delete(MstUser.class);
                                }

                                swInsert.start();
//                                count = bgRealm.where(User.class).count();
                                bgRealm.copyToRealmOrUpdate(respGetUsers.getData().getUser());
                                bgRealm.copyToRealmOrUpdate(respGetUsers.getData().getSecUser());
//                                count = bgRealm.where(User.class).count();

                            }
                        }, new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess() {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                MstSecUser aUser = realm.where(MstSecUser.class).findFirst();
                                int count = respGetUsers.getData().getSecUser().size();

                                btnPlayRealm.setText("Display " + count + " users");
                                btnDisplayWithSearch.setText("Display " + count + " users and search");

                                tvGetUsers.setText("[0]=" + aUser + "\n\nThere are " + count
                                        + " users\nNetworkElapsed:" + swNetworkStr
                                        + "\nInsertElapsed:" + swInsert.stopAndGetAsString());
                                Utility.toast(getApplicationContext(), "Get Users Success");

                            }
                        }, new Realm.Transaction.OnError() {
                            @Override
                            public void onError(Throwable error) {
                                // Transaction failed and was automatically canceled.
                            }
                        });

                    }
                } else {
                    int statusCode = response.code();

                    // handle request errors yourself
                    ResponseBody errorBody = response.errorBody();

                    try {
                        Utility.showDialog(MainActivity.this, "Server Problem (" + statusCode + ")", errorBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseGetUsers> call, Throwable t) {

                Log.e("eric.onFailure", t.getMessage(), t);

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Utility.showDialog(MainActivity.this, "Server Problem", t.getMessage());
            }
        });


    }

    @OnClick(R.id.btnGetLKP)
    public void onGetLKP(){

        String collectorCode = "21160300";

        ApiInterface fastService =
                ServiceGenerator.createService(ApiInterface.class, buildUrl());

        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Get LKP for " + collectorCode + "...");
        mProgressDialog.show();

        final StopWatch swNetwork = StopWatch.AutoStart();

        RequestLKP request = new RequestLKP();
        request.setCollectorCode(collectorCode);

        Call<ResponseGetLKP> call = fastService.getLKP(request);
        call.enqueue(new Callback<ResponseGetLKP>() {
            @Override
            public void onResponse(Call<ResponseGetLKP> call, Response<ResponseGetLKP> response) {

                if (response.isSuccessful()) {
                    final ResponseGetLKP respGetLKP = response.body();
                    Log.e("eric.onResponse", respGetLKP.toString());

                    if (respGetLKP.getError() != null) {
                        Utility.showDialog(MainActivity.this, "Error (" + respGetLKP.getError().getErrorCode() + ")", respGetLKP.getError().getErrorDesc());

                    } else {
                        final String swNetworkStr = swNetwork.stopAndGetAsString();

                        final StopWatch swInsert = new StopWatch();
//                        cancelAsync();
                        // save db here
                        realm.executeTransactionAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm bgRealm) {
                                // insert header
                                // wipe existing tables?
                                long count = bgRealm.where(TrxLDVHeader.class).count();
                                if (count > 0) {
                                    bgRealm.delete(TrxLDVHeader.class);
                                }
                                swInsert.start();
//                                count = bgRealm.where(User.class).count();
                                bgRealm.copyToRealmOrUpdate(respGetLKP.getData().getHeader());
//                                count = bgRealm.where(User.class).count();

                                // insert details
                                count = bgRealm.where(TrxLDVDetails.class).count();
                                if (count > 0) {
                                    bgRealm.delete(TrxLDVDetails.class);
                                }
                                bgRealm.copyToRealmOrUpdate(respGetLKP.getData().getDetails());

                                // insert address
                                count = bgRealm.where(TrxCollectAddr.class).count();
                                if (count > 0) {
                                    bgRealm.delete(TrxCollectAddr.class);
                                }
                                bgRealm.copyToRealmOrUpdate(respGetLKP.getData().getAddress());
                            }
                        }, new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess() {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                TrxLDVHeader header = realm.where(TrxLDVHeader.class).findFirst();
                                int count = respGetLKP.getData().getDetails().size();
//
//                                btnGetUsers.setText("Display " + count + " users");
//
                                tvGetLKP.setText("Header=" + header
                                        + "\n----\nDetails[" + count + " rows]"
                                        + (count > 0 ? respGetLKP.getData().getDetails().get(0) : "<empty>" )
                                        + "\n----\nAddress[" + count + " rows]"
                                        + (count > 0 ? respGetLKP.getData().getAddress().get(0) : "<empty>" )
                                        + "\n\nNetworkElapsed:" + swNetworkStr
                                        + "\nInsertElapsed:" + swInsert.stopAndGetAsString());
//                                Utility.toast(getApplicationContext(), "Get Users Success");

                            }
                        }, new Realm.Transaction.OnError() {
                            @Override
                            public void onError(Throwable error) {
                                // Transaction failed and was automatically canceled.
                            }
                        });

                    }
                } else {
                    int statusCode = response.code();

                    // handle request errors yourself
                    ResponseBody errorBody = response.errorBody();

                    try {
                        Utility.showDialog(MainActivity.this, "Server Problem (" + statusCode + ")", errorBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseGetLKP> call, Throwable t) {

                Log.e("eric.onFailure", t.getMessage(), t);

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Utility.showDialog(MainActivity.this, "Server Problem", t.getMessage());
            }
        });


    }

}
