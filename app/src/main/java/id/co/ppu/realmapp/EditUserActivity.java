package id.co.ppu.realmapp;

import android.text.TextUtils;

import com.github.dkharrat.nexusdialog.FormWithAppCompatActivity;
import com.github.dkharrat.nexusdialog.controllers.EditTextController;
import com.github.dkharrat.nexusdialog.controllers.FormSectionController;

import id.co.ppu.realmapp.pojo.MstSecUser;
import io.realm.Realm;

public class EditUserActivity extends FormWithAppCompatActivity {


    private Realm realm;

    @Override
    protected void initForm() {
        String email = getIntent().getStringExtra("userEmail");

        realm = Realm.getDefaultInstance();

        if (TextUtils.isEmpty(email)) {
            return;
        }
        MstSecUser user = realm.where(MstSecUser.class).equalTo("emailAddr", email).findFirst();

        setTitle("Edit " + user.getUserName());

        // build dynamic form here based on screen mockup
        final FormSectionController section = new FormSectionController(this, "User Info");
        section.addElement(new EditTextController(this, "fullName", "Full name", "", true));

        getFormController().addSection(section);

//        section.addElement(customElem);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
        realm = null;
    }
}
