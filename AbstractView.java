package View;

import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;

public abstract class AbstractView extends JComponent{
    public abstract void onModelPropertyChange(PropertyChangeEvent evt);
}
