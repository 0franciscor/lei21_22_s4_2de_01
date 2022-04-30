package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

public class Photo implements ValueObject, Serializable {

    private byte[] image;
    private final String url;

    public Photo (String url) {
        this.url = url;
        try {
            File file = new File(url);
            byte[] image = new byte[(int) file.length()];
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(image);
            fileInputStream.close();
            this.image = image;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
