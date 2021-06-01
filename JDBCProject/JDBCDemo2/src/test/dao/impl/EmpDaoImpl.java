package test.dao.impl;

import test.dao.BaseDao;
import test.dao.EmpDao;
import test.pojo.Emp;

import java.util.List;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/23 - 05 - 23 - 14:03
 * @Description: test.dao.impl
 */
public class EmpDaoImpl extends BaseDao implements EmpDao {
    @Override
    public int EmpAdd(Emp emp){
        String sql="insert into emp values(DEFAULT,?,?,?,?,?,?,?)";
        return BaseDao.baseUpdate(sql,emp.getEname(),emp.getJob(),emp.getMgr(),emp.getHiredate(),emp.getSal(),emp.getComm(),emp.getDeptno());
    }

    @Override
    public int EmpDelByempno(int empno) {
        String sql="delete from emp where empno=?";
        return BaseDao.baseUpdate(sql,empno);
    }

    @Override
    public int EmpUpdateByempno(int empno,Emp emp) {
        String sql="update emp set ename=?,job=?,mgr=?,hiredate=? where empno=?";
        return BaseDao.baseUpdate(sql,emp.getEname(),emp.getJob(),emp.getMgr(),emp.getHiredate(),empno);
    }

    @Override
    public List<Emp> EmpQueryBydeptno(int deptno) {
        String sql="select *from emp where deptno=?";
        return BaseDao.baseQuery(Emp.class,sql,deptno);
    }

    @Override
    public List<Emp> EmpFindAll() {
        String sql="select *from emp";
        return BaseDao.baseQuery(Emp.class,sql);
    }
}
