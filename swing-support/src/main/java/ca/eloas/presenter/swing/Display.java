/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.presenter.swing;

import java.awt.*;

/**
 * 
 * @author David Peterson, refactored by Steffen Kaempke
 *
 */
public interface Display {
    void setInSlot(String name, Display childPresenter);
    void removeFromSlot(String name);
}
