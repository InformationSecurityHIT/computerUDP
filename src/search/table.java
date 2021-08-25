package search;

import DataBase.DBBean;
import MyUI.MyJTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Vector;

public class table extends JTable {
    private Vector<Object> columnNames;
    private DBBean db;

    public table(Vector<Object> name, DBBean dbBean) {
        this.columnNames = name;
        this.db = dbBean;
    }

    /**
     * 返回数据库tableName表的全部数据的表格
     *
     * @param tableName
     * @return
     */
    public MyJTable returnAllData_table(String tableName) {
        MyJTable table = new MyJTable();
        TableModel tableModel = new DefaultTableModel(returnVector.FromDBReadAll(db, tableName, columnNames), columnNames);
        table.setModel(tableModel);

        return table;
    }

    /**
     * 返回想要查询满足某些条件的表格
     *
     * @param tableName 表名
     * @param value     想要查询的值
     * @param index     想要查询数据库的表的那一列的名字
     * @return
     */
    public MyJTable returnData_table(String tableName, String value, String index) {
        MyJTable table = new MyJTable();
        TableModel tableModel = new DefaultTableModel(returnVector.FromDBRead(db, tableName, columnNames, value, index), columnNames);
        table.setModel(tableModel);
        return table;
    }
}