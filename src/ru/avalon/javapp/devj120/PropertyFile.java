package ru.avalon.javapp.devj120;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PropertyFile {
    private final Map<String ,String> properties = new HashMap<>();
    private File usedFile;

    public PropertyFile() {
    }

    public PropertyFile(String fileName) throws IOException {
        if (fileName == null)
            throw new IllegalArgumentException("fileName can't be null");
        read(new File(fileName));
    }

    public PropertyFile(File file) throws IOException {
        if (file== null)
            throw new IllegalArgumentException("file can't be null");
        read(file);
    }

    private void read(File file) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;
            int lineNo = 0;
            while ((s = br.readLine()) != null) {
                lineNo++;
                //s.isBlank() || JDK 9
                if(s.charAt(0) == '#')
                    continue;
                int p = s.indexOf('=');
                if (p == -1)
                    throw new IOException("Error in line " + lineNo);
                String key = s.substring(0,p),
                        val = s.substring(p+1);
                properties.put(key,val);
            }
        }
        usedFile = file;
    }

    public String get(String propertyName) {
        return properties.get(propertyName);
    }

    public void set(String propertyName, String value) {
        properties.put(propertyName,value);
    }

    public void remove(String propertyName) {
        properties.remove(propertyName);
    }

    public boolean contains(String propertyName) {
        return properties.containsKey(propertyName);
    }

    public void save() throws FileNotFoundException {
        if (usedFile == null)
            throw new IllegalStateException("Properties haven't neither read, not written from/to file");
        save(usedFile);
    }

    public void save(String fileName) throws FileNotFoundException {
        if (fileName == null)
            throw new IllegalArgumentException("fileName can't be null");
        save(new File(fileName));
    }

    public void save(File file) throws FileNotFoundException {
        if (file == null)
            throw new IllegalArgumentException("file can't be null");
        try(PrintWriter pw = new PrintWriter(file)) {
            // первый способ
            //for (Map.Entry<String ,String> kv : properties.entrySet()) {
            //    pw.println(kv.getKey() + '=' + kv.getValue());
            //}
            // второй способ
            properties.forEach((k,v) -> pw.println(k + "=" + v));
        }
        usedFile = file;
    }
}
