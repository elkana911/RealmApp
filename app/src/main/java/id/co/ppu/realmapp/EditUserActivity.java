package id.co.ppu.realmapp;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.facebook.stetho.common.StringUtil;
import com.github.dkharrat.nexusdialog.FormWithAppCompatActivity;
import com.github.dkharrat.nexusdialog.controllers.CheckBoxController;
import com.github.dkharrat.nexusdialog.controllers.DatePickerController;
import com.github.dkharrat.nexusdialog.controllers.EditTextController;
import com.github.dkharrat.nexusdialog.controllers.FormSectionController;
import com.github.dkharrat.nexusdialog.controllers.SearchableSelectionController;
import com.github.dkharrat.nexusdialog.controllers.SelectionController;
import com.github.dkharrat.nexusdialog.controllers.TimePickerController;

import java.util.Arrays;

import difflib.StringUtills;
import id.co.ppu.realmapp.pojo.User;
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
        User user = realm.where(User.class).equalTo("emailAddr", email).findFirst();

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
