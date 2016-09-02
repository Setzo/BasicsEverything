package gui.file.filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import controller.uti.Utilitiess;

public class BinaryFileFilter extends FileFilter {

    public boolean accept(File file) {

        if (file.isDirectory()) {
            return true;
        }

        if (Utilitiess.getExtension(file.getName()).equals("bin")) {
            return true;
        }

        return false;
    }

    public String getDescription() {

        return "Binary file (.bin)";
    }
}