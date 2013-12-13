package ca.eloas.presenter.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @author JP
 */
public class JFrameDisplay extends JFrame implements Display {


    @Override
    public Component asComponent() {

        return this;
    }
}
