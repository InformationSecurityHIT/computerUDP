package MyUI;

import MyUI.MyBorder2;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

//重写Jtable类中的getTableheader方法来设置表头的样式

public class MyJTable extends JTable {
    boolean[] columnEditables = { false, false, false, false };
    public MyJTable(boolean[] columnEditables) {
        this.columnEditables = columnEditables;
    }
    public MyJTable() {
        super();
        makeFace(this);
    }
    @Override
    public JTableHeader getTableHeader() {
        // TODO Auto-generated method stub
        JTableHeader tableHeader = super.getTableHeader();
        // 表格列不可移被移动
        tableHeader.setReorderingAllowed(false);
        // 设置表头的背景颜色
        tableHeader.setBackground(new Color(230, 166, 152));
        // 设置表头的高度
        tableHeader.setPreferredSize(new Dimension(this.getWidth(), 29));
//        tableHeader.setBorder(BorderFactory.createLoweredBevelBorder());
//        tableHeader.setBorder(new MyBorder3());
        // 设置表头的字体
        tableHeader.setFont(new Font("等线", Font.BOLD, 18));
        tableHeader.setForeground(Color.white);
        tableHeader.setOpaque(false);
        DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader
                .getDefaultRenderer();
        // 列名居中
        hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return tableHeader;
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        // TODO Auto-generated method stub
        return columnEditables[column];
    }

    public static void makeFace(JTable table) {
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table,
                                                               Object value, boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    if (row % 2 == 0)
                        setBackground(Color.white); // 设置奇数行底色
                    else if (row % 2 == 1)
                        setBackground(new Color(246, 217, 197)); // 设置偶数行底色
                    return super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
                }
            };

            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}