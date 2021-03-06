package id.co.ppu.realmapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Eric on 23-Aug-16.
 */
public class FileUtil {

    /**
     Realm realm = null;

     try {
         realm = Realm.getInstance(this);
         File f = new File(realm.getPath());
         if (f.exists()) {
             try {
             FileUtil.copy(f, new File(Environment.getExternalStorageDirectory()+"/default.realm"));
             } catch (IOException e) {
             e.printStackTrace();
             }
         }
     } finally {
         if (realm != null)
            realm.close();
         }
     * @param src
     * @param dst
     * @throws IOException
     */
    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
}
