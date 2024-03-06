import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class Draw {

    public static void drawMemory(JPanel memoryGUI, JPanel tag) {
        //画内存条
        // 先移除原先的memoryGUI
        memoryGUI.removeAll();
        tag.removeAll();
        for (int i = 0; i < GUI.memory.get().getHoles().size(); i++) {
            Hole hole = GUI.memory.get().getHoles().get(i);
            if (!hole.isFree()) {
                //画进程
                JPanel holeGUI = new JPanel();
                holeGUI.setBackground(new Color(0x0B8FFF));
                int height = (int) Math.floor(hole.getSize() / (1.0 * GUI.memorySize) * GUI.memoryHeight);
                int y = (int) Math.floor(hole.getHead() / (1.0 * GUI.memorySize) * GUI.memoryHeight);
                holeGUI.setSize(GUI.memoryWidth, height);
                holeGUI.setLocation(0, y);
                holeGUI.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                holeGUI.setVisible(true);
                memoryGUI.add(holeGUI);

                //画进程号
                for (Pcb pcb : GUI.memory.get().getPcbs()) {
                    if (pcb.getHole().equals(hole)) {
                        JLabel label = new JLabel("进程" + pcb.getId());
                        label.setLocation(0, y);
                        label.setSize(GUI.memoryWidth / 2, height);
                        label.setVisible(true);
                        tag.add(label);
                        break;
                    }
                }
            }
        }
        memoryGUI.validate();
        memoryGUI.repaint();
        tag.validate();
        tag.repaint();

    }

    public static JPanel drawApplyMemory(LPcb lPcb, JPanel memoryGUI, JPanel tag,JFrame frame) {
        //开始分配
        //分配内存输入框
        JPanel allocatePanel = new JPanel();
        allocatePanel.setBackground(new Color(0x72B3EA));
        allocatePanel.setLayout(null);
        allocatePanel.setSize(300, 300);
        allocatePanel.setLocation(400, 400);
        allocatePanel.setVisible(true);

        JLabel label2 = new JLabel("申请内存");
        label2.setLocation(115, 5);
        label2.setSize(150, 50);
        label2.setFont(new Font(null, 0, 18));
        label2.setVisible(true);
        allocatePanel.add(label2);
        allocatePanel.validate();

        //申请大小
        JLabel label1 = new JLabel("申请大小:");
        label1.setLocation(30, 40);
        label1.setSize(100, 50);
        label1.setVisible(true);
        allocatePanel.add(label1);
        allocatePanel.validate();
        //输入框
        JTextField textField1 = new JTextField(10);
        textField1.setLocation(30, 80);
        textField1.setSize(100, 30);
        allocatePanel.add(textField1);

        //单选框
        JLabel label3 = new JLabel("选择算法：");
        label3.setLocation(30, 110);
        label3.setSize(100, 50);
        label3.setVisible(true);
        allocatePanel.add(label3);
        allocatePanel.validate();
        //选择算法
        ButtonGroup algGroup = new ButtonGroup();
        JRadioButton firstFit = new JRadioButton("最先适应算法");
        firstFit.setLocation(30, 150);
        firstFit.setSize(120, 30);
        firstFit.setBackground(new Color(0x72B3EA));
        JRadioButton bestFit = new JRadioButton("最佳适应算法");
        bestFit.setLocation(30, 180);
        bestFit.setSize(120, 30);
        bestFit.setBackground(new Color(0x72B3EA));
        JRadioButton worstFit = new JRadioButton("最坏适应算法");
        worstFit.setLocation(30, 210);
        worstFit.setSize(120, 30);
        worstFit.setBackground(new Color(0x72B3EA));
        algGroup.add(firstFit);
        algGroup.add(bestFit);
        algGroup.add(worstFit);

        allocatePanel.add(firstFit);
        allocatePanel.add(bestFit);
        allocatePanel.add(worstFit);

        //申请按钮
        JButton algorithmButton = new JButton("开始申请");
        algorithmButton.setLocation(105, 245);
        algorithmButton.setSize(100, 30);
        allocatePanel.add(algorithmButton);
        algorithmButton.addActionListener(e -> {
            //申请内存大小
            String applySize = textField1.getText();

            //选择的算法
            int selected = firstFit.isSelected() ? 1 :
                    (bestFit.isSelected() ? 2 : (worstFit.isSelected() ? 3 : -1));
            if (!Util.isNum(applySize) || applySize.equals("") || selected == -1) {
                textField1.setText("");
                algGroup.clearSelection();
            } else {
                GUI.memory.set(lPcb.applyBegin(GUI.memory.get(), Integer.parseInt(applySize), selected, frame));
                lPcb.showMemoryGUI(GUI.memory.get(), GUI.contentPane);
                lPcb.showPcbGUI(GUI.memory.get(), GUI.contentPane);
                Draw.drawMemory(memoryGUI, tag);
            }
        });
        return allocatePanel;
    }

    public static JPanel drawRecycleMemory(LPcb lPcb, JPanel memoryGUI, JPanel tag) {
        //开始分配
        //分配内存输入框
        JPanel allocatePanel = new JPanel();
        allocatePanel.setBackground(new Color(0x72B3EA));
        allocatePanel.setLayout(null);
        allocatePanel.setSize(300, 300);
        allocatePanel.setLocation(800, 400);
        allocatePanel.setVisible(true);

        JLabel label2 = new JLabel("回收进程");
        label2.setLocation(115, 5);
        label2.setSize(150, 50);
        label2.setFont(new Font(null, 0, 18));
        label2.setVisible(true);
        allocatePanel.add(label2);
        allocatePanel.validate();

        //申请大小
        JLabel label1 = new JLabel("回收进程号:");
        label1.setLocation(30, 40);
        label1.setSize(100, 50);
        label1.setVisible(true);
        allocatePanel.add(label1);
        allocatePanel.validate();
        //输入框
        JTextField textField1 = new JTextField(10);
        textField1.setLocation(30, 80);
        textField1.setSize(100, 30);
        allocatePanel.add(textField1);

        //申请按钮
        JButton algorithmButton = new JButton("开始回收");
        algorithmButton.setLocation(105, 245);
        algorithmButton.setSize(100, 30);
        allocatePanel.add(algorithmButton);
        algorithmButton.addActionListener(e -> {
            //回收进程号
            String processNo = textField1.getText();

            if (!Util.isNum(processNo) || processNo.equals("")) {
                textField1.setText("");
            } else {
                //选择的算法
                GUI.memory.get().releaseMemory(Integer.parseInt(processNo));
                lPcb.showMemoryGUI(GUI.memory.get(), GUI.contentPane);
                lPcb.showPcbGUI(GUI.memory.get(), GUI.contentPane);
                Draw.drawMemory(memoryGUI, tag);
            }
        });
        return allocatePanel;
    }
}
