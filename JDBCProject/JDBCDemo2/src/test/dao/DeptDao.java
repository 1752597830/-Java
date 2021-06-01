package test.dao;

import test.pojo.Dept;

import java.util.List;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/23 - 05 - 23 - 14:03
 * @Description: test.dao
 */
public interface DeptDao {
    int DeptAdd(Dept dept);
    int DeleteBydeptno(int deptno);
    int UpdateBydeptno(int deptno,Dept dept);
    void QueryBydeptno(String S);
    List<Dept> DeptFindAll();
}
