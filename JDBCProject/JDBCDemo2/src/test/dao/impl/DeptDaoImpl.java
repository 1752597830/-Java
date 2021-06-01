package test.dao.impl;

import test.dao.BaseDao;
import test.dao.DeptDao;
import test.pojo.Dept;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/23 - 05 - 23 - 14:03
 * @Description: test.dao.impl
 */
public class DeptDaoImpl extends BaseDao implements DeptDao {
    @Override
    public int DeptAdd(Dept dept) {
        String sql="insert into dept values(DEFAULT,?,?)";
        return BaseDao.baseUpdate(sql,dept.getDname(),dept.getLoc());
    }

    @Override
    public int DeleteBydeptno(int deptno) {
        String sql="delete from dept where deptno=?";
        return BaseDao.baseUpdate(sql,deptno);
    }

    @Override
    public int UpdateBydeptno(int deptno, Dept dept) {
            String sql="update dept set dname=?,loc=? where deptno=?";
            return BaseDao.baseUpdate(sql,dept.getDname(),dept.getLoc(),dept.getLoc());
    }

    @Override
    public void QueryBydeptno(String S) {
        List<Dept> list=null;
        list=new ArrayList<>();
        String sql="select *from dept where dname like ?";
        list=BaseDao.baseQuery(Dept.class,sql,S);
        for (Dept dept:list) {
            System.out.println(dept);
        }
    }

    @Override
    public List<Dept> DeptFindAll() {
        String sql="select *from dept";
        return BaseDao.baseQuery(Dept.class,sql);
    }
}
