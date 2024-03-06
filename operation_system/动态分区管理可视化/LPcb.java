import javax.swing.*;
import java.awt.*;

/**
 * @author xuehy
 * @since 2019/4/30
 */
public class LPcb {

    public Memory firstFit(Memory memory, int size, JFrame frame) {
        int sum = 0;
        for (int i = 0; i < memory.getHoles().size(); i++) {    //循环内存中所有分区
            sum++;
            memory.setLastFind(i);  //为循环首次适应算法设置最后寻址的下标
            Hole hole = memory.getHoles().get(i);   //获得对应的分区
            if (hole.isFree() && hole.getSize() >= size) {  //若此分区空闲且大小大于申请的大小，则申请内存
                System.out.println("查找" + sum + "次");
                //为进程分配大小 = size的内存
                return memory.getMemory(size, i, hole);
            }
        }
        System.err.println("OUT OF MEMORY!");
        JOptionPane.showMessageDialog(frame, "OUT OF MEMORY!");
        return memory;
    }


    public Memory bestFit(Memory memory, int size, JFrame frame) {
        int findIndex = -1;         //最佳分区的下标
        int min = memory.getSize(); //min存储当前找到的最小的合适的分区大小
        for (int i = 0; i < memory.getHoles().size(); i++) {
            //memory.setLastFind(i);
            Hole hole = memory.getHoles().get(i);
            if (hole.isFree() && hole.getSize() >= size) {
                if (min > hole.getSize()) {   //若当前找到的分区大小比min还要合适(剩余空间更小),则修改其值
                    min = hole.getSize();
                    findIndex = i;
                }
            }
        }
        if (findIndex != -1) {  //若存在合适分区
            return memory.getMemory(size, findIndex, memory.getHoles().get(findIndex));
        }
        System.err.println("OUT OF MEMORY!");
        JOptionPane.showMessageDialog(frame, "OUT OF MEMORY!");

        return memory;
    }

    public Memory worstFit(Memory memory, int size, JFrame frame) {
        int findIndex = -1;
        int max = 0;
        for (int i = 0; i < memory.getHoles().size(); i++) {
            //memory.setLastFind(i);
            Hole hole = memory.getHoles().get(i);
            if (hole.isFree() && hole.getSize() >= size) {
                if (max < hole.getSize()) {
                    max = hole.getSize();
                    findIndex = i;
                }
            }
        }
        if (findIndex != -1) {
            return memory.getMemory(size, findIndex, memory.getHoles().get(findIndex));
        }
        System.err.println("OUT OF MEMORY!");
        JOptionPane.showMessageDialog(frame, "OUT OF MEMORY!");

        return memory;
    }

    public void showMemory(Memory memory) {
        System.out.println("------------------------------------");
        System.out.println("分区编号\t分区始址\t分区大小\t空闲状态\t");
        System.out.println("------------------------------------");
        for (int i = 0; i < memory.getHoles().size(); i++) {
            Hole hole = memory.getHoles().get(i);
            System.out.println(i + "\t\t" + hole.getHead() + "\t\t" + hole.getSize() + "  \t" + hole.isFree());
        }
        System.out.println("------------------------------------");
    }

    public void showPcbs(Memory memory) {
        System.out.println("------------------------------------");
        System.out.println("进程编号\t进程状态\t进程起始地址\t进程大小\t");
        System.out.println("------------------------------------");
        if (memory.getPcbs().size() > 0) {
            for (int i = 0; i < memory.getPcbs().size(); i++) {
                Pcb pcb = memory.getPcbs().get(i);
                System.out.println(pcb.getId() + "  \t" + pcb.getState() + "\t\t" + pcb.getHole().getHead() + "\t\t\t" + pcb.getHole().getSize());
            }
        } else {
            System.err.println("\t\t\t暂无进程！");
        }
        System.out.println("------------------------------------");
    }


    public void showMemoryGUI(Memory memory, Container contentPane) {
        String[] columnNames = {"分区编号", "分区始址", "分区大小", "空闲状态"};
        Object[][] data = new Object[memory.getHoles().size()][4];
        for (int i = 0; i < memory.getHoles().size(); i++) {
            Hole hole = memory.getHoles().get(i);
            data[i] = new Object[]{i, hole.getHead(), hole.getSize(), hole.isFree()};
            System.out.println(i + "\t\t" + hole.getHead() + "\t\t" + hole.getSize() + "  \t" + hole.isFree());
        }
        JTable jTable = new JTable(data, columnNames);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setSize(300, 200);
        jScrollPane.setLocation(800, 100);
        contentPane.add(jScrollPane);
    }

    public void showPcbGUI(Memory memory, Container contentPane) {
        String[] columnNames = {"进程编号", "进程状态", "进程起始地址", "进程大小"};
        Object[][] data = new Object[memory.getPcbs().size()][4];
        if (memory.getPcbs().size() > 0) {
            for (int i = 0; i < memory.getPcbs().size(); i++) {
                Pcb pcb = memory.getPcbs().get(i);
                data[i] = new Object[]{pcb.getId(), pcb.getState(), pcb.getHole().getHead(), pcb.getHole().getSize()};
                System.out.println(pcb.getId() + "  \t" + pcb.getState() + "\t\t" + pcb.getHole().getHead() + "\t\t\t" + pcb.getHole().getSize());
            }
        } else {
            System.err.println("\t\t\t暂无进程！");
        }
        JTable processTable = new JTable(data, columnNames);
        JScrollPane processScrollPane = new JScrollPane(processTable);
        processScrollPane.setSize(300, 200);
        processScrollPane.setLocation(400, 100);
        contentPane.add(processScrollPane);
    }

    public Memory applyBegin(Memory memory, int applySize, int selected, JFrame frame) {
        switch (selected) {
            case 1:
                memory = firstFit(memory, applySize, frame);
                break;
            case 2:
                memory = bestFit(memory, applySize, frame);
                break;
            case 3:
                memory = worstFit(memory, applySize, frame);
                break;
            default:
                System.out.println("操作取消");
        }
        return memory;
    }
}
