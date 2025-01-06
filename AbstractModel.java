package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.FileWriter;

import Data.DataFactory;

public abstract class AbstractModel {
    protected PropertyChangeSupport propertyChangeSupport;

    public AbstractModel() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    protected static <T> void loadDB(Class<T> dataType, Collection<T> collection, List<String> fieldsNames, String path)
            throws Exception {
        CsvReader reader = new CsvReader(path);
        reader.readHeaders();

        while (reader.readRecord()) {
            List<String> args = new ArrayList<>(fieldsNames.size());
            for (String string : fieldsNames) {
                args.add(reader.get(string));
            }
            collection.add(DataFactory.make(dataType, args));
        }
    }

    protected static <T> void updateDB(Class<T> dataType, Collection<T> collection, List<String> fieldsNames,
            String path) throws Exception {
        CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
        for (String string : fieldsNames) {
            csvOutput.write(string);
        }
        csvOutput.endRecord();

        for (T e : collection) {
            for (String string : fieldsNames) {
                csvOutput.write("" + dataType.getDeclaredField(string).get(e));
            }
            csvOutput.endRecord();
        }
        csvOutput.close();
    }

    public abstract boolean add(Object value);

    public abstract boolean update(Object oldValue, Object newValue);

    public abstract boolean load();

    public abstract boolean delete(Object value);
}