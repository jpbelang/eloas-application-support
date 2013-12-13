package ca.eloas.presenter.swing;

import java.awt.*;

/**
 * @author JP
 */
public class DisplayImpl<C extends Component> implements Display {

    private  C component;

    public DisplayImpl() {

        //this.component = component;
    }

    @Override
    public void setInSlot(String name, Display childPresenter) {

    }

    @Override
    public void removeFromSlot(String name) {

    }
}
