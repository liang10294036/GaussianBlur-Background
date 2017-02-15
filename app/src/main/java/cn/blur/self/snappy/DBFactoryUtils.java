package cn.blur.self.snappy;

import com.snappydb.DB;
import com.snappydb.SnappyDB;
import com.snappydb.SnappydbException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.blur.self.BaseApplication;


/**
 * Created by MyPC on 2016/11/29.
 * <p>
 * snappyDB 数据缓存
 */

public class DBFactoryUtils {

    static DB snappyDB;

    static DBFactoryUtils instance;

    public DBFactoryUtils() {
        initSnappy();
    }

    public void initSnappy() {
        try {
            snappyDB = new SnappyDB.Builder(BaseApplication.getInstance())
                    .directory(FileUtils.getDownloadFilePath()) //optional
                    .name(DBConfigUtils.DB_NAME)//optional
                    .build();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DBFactoryUtils getInstance() {
        if (instance == null) {
            instance = new DBFactoryUtils();
        }
        return instance;
    }

    public synchronized DB getSnappyDB() {
        return snappyDB;
    }

    public void putString(String key, String val) {
        if (snappyDB != null) {
            try {
                snappyDB.put(key, val);
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }

    public void putInt(String key, int val) {
        if (snappyDB != null) {
            try {
                snappyDB.putInt(key, val);
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }

    public int getInt(String key) {
        if (snappyDB != null) {
            try {
                snappyDB.getInt(key);
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void put(String key, Object obj) {
        if (snappyDB != null) {
            try {
                snappyDB.put(key, obj);
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }

    public <T extends Serializable> T get(String key, Class<T> className) {
        if (snappyDB != null) {
            try {
                return snappyDB.get(key, className);
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public <T> T[] getObjectArray(String key, Class<T> className) {
        if (snappyDB != null) {
            try {
                return snappyDB.getObjectArray(key, className);
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getString(String key) {
        if (snappyDB != null) {
            try {
                return snappyDB.get(key);
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    public void destroy() {
        if (snappyDB != null) {
            try {
                snappyDB.destroy();
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }

    public void del(String key) {
        if (snappyDB != null) {
            try {
                snappyDB.del(key);
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        if (snappyDB != null) {
            try {
                snappyDB.close();
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }


    public <T extends Serializable> T[] getArray(String key, Class<T> className) {
        try {
            return snappyDB.getArray(key, className);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return null;
    }


    public <T extends Serializable> ArrayList<T> getArrayList(String key, Class<T> className) {
        if (snappyDB != null) {
            try {
                //添加后缀，防止错乱
                T[] array = snappyDB.getArray(key + "arrayList", className);
                if (array != null) {
                    return new ArrayList<T>(Arrays.asList(array));

                } else {
                    return null;
                }

            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void putArrayList(String key, ArrayList arrayList) {
        if (snappyDB != null) {
            try {

                Object[] objects = arrayList.toArray();
                snappyDB.put(key + "arrayList", objects);
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }

    }

    public void putList(String key, List arrayList) {
        if (snappyDB != null) {
            try {
                Object[] objects = arrayList.toArray();
                snappyDB.put(key + "arrayList", objects);
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }
}