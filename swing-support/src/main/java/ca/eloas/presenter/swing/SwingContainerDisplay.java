/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.presenter.swing;

import javax.swing.JComponent;
import java.awt.*;

/**
 * 
 * @author David Peterson, refactored by Steffen Kaempke
 *
 */
public interface SwingContainerDisplay extends SwingDisplay {
	/**
    *
    * @param component
    */
   void showComponent(Component component);
   
   void addComponent(Component component);
   
   void removeComponent(Component component);
}
