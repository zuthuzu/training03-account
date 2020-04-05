package ua.kpi.tef.zu.model.dao;

import ua.kpi.tef.zu.model.dao.impl.JDBCDaoFactory;

/**
 * Created by Anton Domin on 2020-04-04
 */
public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract RecordDao createRecordDao();

    public static DaoFactory getInstance(){
       if( daoFactory == null ){
           synchronized (DaoFactory.class){
               if(daoFactory==null){
                   daoFactory = new JDBCDaoFactory();
               }
           }
       }   return daoFactory;

    }
}
