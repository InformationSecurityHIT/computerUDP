package search;

import DataBase.DBBean;

import com.mysql.cj.util.StringUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class returnVector {
    /**
     * 读取数据库所有数据
     *
     * @param db        要连接的数据库
     * @param tablename 数据库的表名
     * @param vector    表的所有列的字段名
     * @return 返回一个Vector存储所有的数据库的数据
     */
    public static Vector<Vector<Object>> FromDBReadAll(DBBean db, String tablename, Vector<Object> vector) {
        Vector<Vector<Object>> res = new Vector<Vector<Object>>();
        ResultSet temp = db.executeFindAll(tablename);
        while (true) {
            try {
                if (!temp.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Vector<Object> v = new Vector<Object>();
            // 读表头信息
            for (int i = 0; i < vector.size(); i++) {
                try {
                    v.add(temp.getString((String) vector.get(i)));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            res.add(v);
        }
        int len= res.size();
        Vector<Vector<Object>> res_new = new Vector<Vector<Object>>();
        for(int i=0;i<len;i++)
            res_new.add(res.get(len-1-i));
        return res_new;
    }

    /**
     * 读取数据库查询的数据
     *
     * @param db        要连接的数据库
     * @param tablename 数据库的表名
     * @param vector    表的所有列的字段名
     * @param value     想要查询的值
     * @param index     想要查询数据库的表的哪一列的名字
     * @return 返回查询到的所有行
     */
    public static Vector<Vector<Object>> FromDBRead_old(DBBean db, String tablename, Vector<Object> vector, String value, String index) {
        Vector<Vector<Object>> res = new Vector<Vector<Object>>();
        ResultSet temp = db.executeFind(value, tablename, index);
        while (true) {
            try {
                if (!temp.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Vector<Object> v = new Vector<Object>();
            // 读表头信息
            for (int i = 0; i < vector.size(); i++) {
                try {
                    v.add(temp.getString((String) vector.get(i)));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            res.add(v);
        }
        return res;
    }

    public static Vector<Vector<Object>> FromDBRead(DBBean db, String tablename, Vector<Object> vector, String value, String index) {
        Vector<Vector<Object>> res = new Vector<Vector<Object>>();
        //查询全部
        ResultSet temp = db.executeFindLike(value, tablename, index);
        while (true) {

            try {
                if (!temp.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Vector<Object> v = new Vector<Object>();
            // 读表头信息
            for (int i = 0; i < vector.size(); i++) {
                try {
                    v.add(temp.getString((String) vector.get(i)));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            res.add(v);
        }
        int len= res.size();
        Vector<Vector<Object>> res_new = new Vector<Vector<Object>>();
        for(int i=0;i<len;i++)
            res_new.add(res.get(len-1-i));
        return res_new;
    }


    /**
     * @param db        要连接的数据库
     * @param tablename 数据库的表名字
     * @return 返回表的所有列的字段名
     * @throws SQLException
     */
    public static Vector<Object> getHeadName(DBBean db, String tablename) {
        ResultSet temp1 = db.executeTablehead(tablename);
        Vector<Object> v1 = new Vector<Object>();
        while (true) {
            try {
                if (!temp1.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            // 读表头信息
            try {
                v1.add(temp1.getString(1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return v1;
    }
}
