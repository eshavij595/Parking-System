package Controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;

import Data.User;
import Model.AbstractModel;
import View.AbstractView;

public abstract class AbstractController implements PropertyChangeListener {

    public Collection<AbstractView> views = new ArrayList<AbstractView>();
    static protected User userContext = null;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        for (AbstractView abstractView : views) {
            abstractView.onModelPropertyChange(evt);
        }
    }

    protected void addView(AbstractView view) {
        views.add(view);
    }

    protected void removeView(AbstractView view) {
        views.remove(view);
    }

    protected void listen(AbstractModel model) {
        model.addPropertyChangeListener(this);
    }
}
