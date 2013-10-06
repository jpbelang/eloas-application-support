/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.presenter.swing;

import javax.swing.JComponent;

import ca.eloas.presenter.Display;

import java.awt.*;

/**
 * 
 * @author David Peterson, refactored by Steffen Kaempke
 *
 */
public interface SwingDisplay extends Display {
	Component asComponent();
}
