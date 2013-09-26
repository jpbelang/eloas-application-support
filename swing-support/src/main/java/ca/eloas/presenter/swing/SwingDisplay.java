/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.presenter.swing;

import javax.swing.JComponent;

import ca.eloas.presenter.Display;

/**
 * 
 * @author David Peterson, refactored by Steffen Kaempke
 *
 */
public interface SwingDisplay extends Display {
	JComponent asComponent();
}
