package starnet.cdc.backend;

/**
 * Responsabilty: read/write on Disco
 * @author FelipeAlafy
 * Copyright 2020 (C) FelipeAlafy
 * Permissão dada pelo autor da classe para usar nesse projeto Stanet-CDC
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Disco {
    String dir = System.getProperty("user.dir");
    public boolean createFolder(String folder) {
        Path path = Paths.get(folder);
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean writer(String folder, String fileToRead, ArrayList<String> toWrite, Boolean replace){
        File file = new File(folder + "/" + fileToRead);
        Path path = file.toPath();
        try {
            if(!Files.exists(path) && replace) {
                // Writing
                BufferedWriter  writer = new BufferedWriter(new FileWriter(file));
                for (String s : toWrite) {
                    writer.write(s);
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } else if (Files.exists(path) && replace){
                BufferedWriter  writer = new BufferedWriter(new FileWriter(file));
                for (String s : toWrite) {
                    writer.write(s);
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } else {
                ArrayList<String> loaded;
                loaded = read(folder, fileToRead);
                // Writing
                BufferedWriter  writer = new BufferedWriter(new FileWriter(file));
                for (String s : loaded) {
                    writer.write(s);
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean existsFile(String folder, String file){
        File file2 = new File(folder + "/" + file);
        Path path = file2.toPath();
        if (Files.exists(path)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> read (String folder, String fileToRead) {
        File file = new File(folder+"/"+fileToRead);
        Path path = file.toPath();
        ArrayList<String> data = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                //Reading
                FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader);

                String row = null;
                while((row = reader.readLine()) != null){
                    data.add(row);
                }
            } catch (IOException ex) {
                Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return data;
    }
}
