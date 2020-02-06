package evilhangman;

import java.io.File;
import java.nio.file.Path;

public class FindFile {
    public Path findFile(String name, File file) {
        File[] list = file.listFiles();
        if(list != null) {
            for (File fil : list)
            {
                if (fil.isDirectory())
                {
                    Path path = findFile(name, fil);
                    if (path != null) {
                        return path;
                    }
                }
                else if (name.equalsIgnoreCase(fil.getName()))
                {
                    return fil.toPath();
                }
            }
        }
        return null;
    }
}