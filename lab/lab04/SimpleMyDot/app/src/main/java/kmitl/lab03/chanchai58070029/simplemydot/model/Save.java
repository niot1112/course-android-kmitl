package kmitl.lab03.chanchai58070029.simplemydot.model;


import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Save {

    public static File getDisc(){
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        return new File(file, "Image demo");
    }
    public static void startSave(ImageView imageView){
        FileOutputStream fileoutputstream = null;
        File file = getDisc();
        String name = "img.jpg";
        String file_name = file.getAbsolutePath()+"/"+name;
        File new_file = new File(file_name);
        try {
            fileoutputstream = new FileOutputStream(new_file);
            Bitmap bitmap = Screenshot.getScreenShot(imageView);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileoutputstream);
            fileoutputstream.flush();
            fileoutputstream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
        }
    }

}
