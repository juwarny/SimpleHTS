package test;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * 자동 완성 기능의 콤보박스!
 * @author 남유림
 */
public class AutoSuggest extends JComboBox {

    private final JTextField tf;
    private final Vector<String> v = new Vector<String>();
    private boolean layingOut = false;
    private int widestLengh = 0;
    private boolean wide = false;
    private boolean hide_flag = false;
    private Object[] tags;
    
    
    public void settags(Object[] tags){
    	this.tags = tags;
    }
    
    public AutoSuggest(Object[] tags) {
        setEditable(true);
        settags(tags);
        tf = (JTextField) getEditor().getEditorComponent();
        tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                tf.selectAll();
            }
        });
        tf.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        String text = tf.getText();
                        if (text.length() == 0) {
                            hidePopup();
                            setModel(new DefaultComboBoxModel(v), "");
                        } else {
                            DefaultComboBoxModel m = getSuggestedModel(v, text);
                            if (m.getSize() == 0 || hide_flag) {
                                hidePopup();
                                hide_flag = false;
                            } else {
                                setModel(m, text);
                                showPopup();
                            }
                        }
                    }
                });
            }

            @Override
            public void keyPressed(KeyEvent e) {
                String text = tf.getText();
                int code = e.getKeyCode();

                if (code == KeyEvent.VK_RIGHT) {
                    hide_flag = true;
                } else if (code == KeyEvent.VK_ESCAPE) {
                    hide_flag = true;
                } else if (code == KeyEvent.VK_ENTER) {
                    hide_flag = true;
                    for (int i = 0; i < v.size(); i++) {
                        String str = v.elementAt(i);
                        if (str.startsWith(text)) {
                            tf.setText(str);
                            return;
                        }
                    }
                }
            }
        });
        for (int i = 0; i < tags.length; i++) {
            v.addElement((String)tags[i]);
        }
        setModel(new DefaultComboBoxModel(v), "");
    }

    public boolean isWide() {
        return wide;
    }

    public int getWidestItemWidth() {
        int numOfItems = this.getItemCount();
        Font font = this.getFont();
        FontMetrics metrics = this.getFontMetrics(font);
        int widest = 0;
        for (int i = 0; i < numOfItems; i++) {
            Object item = this.getItemAt(i);
            int lineWidth = metrics.stringWidth(item.toString());
            widest = Math.max(widest, lineWidth);
        }
        return widest + 20;
    }

    @Override
    public void doLayout() {
        try {
            layingOut = true;
            super.doLayout();
        } finally {
            layingOut = false;
        }
    }

    @Override
    public Dimension getSize() {
        Dimension dim = super.getSize();
        if (!layingOut && isWide()) {
            dim.width = Math.max(widestLengh, dim.width);
        }
        return dim;
    }

    private void setModel(DefaultComboBoxModel mdl, String str) {
        setModel(mdl);
        setSelectedIndex(-1);
        tf.setText(str);
    }

    private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (String s : list) {
            if (s.startsWith(text)) {
                m.addElement(s);
            }
        }
        return m;
    }

    /**
     *  넓은 드랍다운 리스트를 사용할 때. true를 주면 콤보박스보다 드랍다운리스트가 커짐.
     * @param wide true/false
     */
    public void setWide(boolean wide) {
        this.wide = wide;
        widestLengh = getWidestItemWidth();
    }

    /**
     * 현재 입력되어있는 텍스트 반환
     * @return 현재 입력되어있는 텍스트 반환
     */
    public String getText() {
        return tf.getText();
    }
}