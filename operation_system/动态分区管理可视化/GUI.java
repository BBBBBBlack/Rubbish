import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class GUI extends JFrame {
    public static final int memoryWidth = 200;
    public static final int memoryHeight = 560;

    public static int memorySize;

    static AtomicReference<Memory> memory;

    static Container contentPane;

    public static void main(String[] args) {

        GUI fr = new GUI("Hello"); //构造方法
        fr.setSize(1500, 1500);  //设置Frame的大小
        contentPane = fr.getContentPane();
        contentPane.setBackground(new Color(135, 206, 250));
        fr.setVisible(true); //设置Frame为可见，默认不可见W

        contentPane.setLayout(null);


        //初始化内存大小
        String input = null;
        while (input == null) {
            input = JOptionPane.showInputDialog(contentPane, "初始化内存大小：");
            try {
                memorySize = Integer.parseInt(input);
                memory = new AtomicReference<>(new Memory(memorySize));
            } catch (NumberFormatException e) {
                input = null;
                JOptionPane.showMessageDialog(contentPane, "Invalid input! Please enter a valid number.");
            }
        }
        //画内存条
        System.out.println("初始化成功!");
        JLabel label = new JLabel("内存大小:" + memorySize);
        label.setLocation(100, 50);
        label.setSize(100, 30);
        label.setVisible(true);
        contentPane.add(label);

        JPanel memoryGUI = new JPanel();
        memoryGUI.setBackground(new Color(11, 98, 196));
        memoryGUI.setSize(GUI.memoryWidth, GUI.memoryHeight);
        memoryGUI.setVisible(true);
        memoryGUI.setLocation(100, 100);
        memoryGUI.setLayout(null);
        contentPane.add(memoryGUI);

        JPanel tag = new JPanel();
        tag.setBackground(new Color(135, 206, 250));
        tag.setSize(85, GUI.memoryHeight);
        tag.setVisible(true);
        tag.setLocation(15, 100);
        tag.setLayout(null);
        contentPane.add(tag);

        fr.repaint();
        fr.setVisible(true); //设置Frame为可见，默认不可见W

        //显示进程表
        LPcb lPcb = new LPcb();
        lPcb.showPcbGUI(memory.get(), contentPane);

        //显示内存块
        lPcb.showMemoryGUI(memory.get(), contentPane);

        //开始分配
        JPanel allocatePanel = Draw.drawApplyMemory(lPcb, memoryGUI, tag, fr);
        //开始回收
        JPanel recyclePanel = Draw.drawRecycleMemory(lPcb, memoryGUI, tag);
        contentPane.add(allocatePanel);
        contentPane.add(recyclePanel);
        contentPane.validate();
        contentPane.repaint();
        fr.validate();
        fr.repaint();
    }

    public GUI(String str) {
        super(str);
    }
}

